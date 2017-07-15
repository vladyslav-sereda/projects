package ua.nure.sereda.Photostudio.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.PricePerHour;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.User;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.UserService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.utils.validation.Validator;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.AnswerStatus;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;


/**
 * Created by Vladyslav.
 */
public class ReserveCommand extends Command {
    private static final long serialVersionUID = 3932401582690196456L;
    private static final Logger LOG = Logger.getLogger(ReserveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        LOG.debug("Command start");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);
        ReservationService reservationService = (ReservationService) request.getServletContext().getAttribute(Services.RESERVATION);
        WorkDayService workDayService = (WorkDayService) request.getServletContext().getAttribute(Services.WORKDAY);
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("Current user >>" + user);
        String password = request.getParameter("password");
        LOG.trace("Password obtained");
        if (user == null) {
            LOG.error("user element doesn't exist in session scope");
            response.setStatus(AnswerStatus.UNAUTHORIZED);
            return null;
        }

        WorkDay workDay;
        List<Reservation> reservations;
        LocalTime startTime;
        LocalTime endTime;
        int dayId;

        LOG.trace("Getting params from request");
        try {
            dayId = Integer.parseInt(request.getParameter("dayId"));
            startTime = LocalTime.parse(request.getParameter("startTime"));
            endTime = LocalTime.parse(request.getParameter("endTime"));
            LOG.trace(String.format("Obtained params :day id = %s start time = %s, end time = %s", dayId, startTime, endTime));

            LOG.trace("Getting workday and reservations-list...");
            workDay = workDayService.getById(dayId);
            reservations = reservationService.getReservationsByWorkDay(workDay.getId());

            if (startTime == null || endTime == null) {
                LOG.error("Time is null");
                response.setStatus(AnswerStatus.INVALID_INPUT);
            } else if (!Validator.time(startTime, endTime)
                    || !Validator.dateAfterToday(workDay.getDate())
                    || !workDay.getStartDay().isBefore(startTime)
                    || !workDay.getEndDay().isAfter(endTime)) {
                LOG.error("Time or date is not valid" + workDay.getDate());
                response.setStatus(AnswerStatus.INVALID_INPUT);
            } else {
                for (Reservation reserve : reservations) {
                    if ((startTime.isAfter(reserve.getStartTime()) & startTime.isBefore(reserve.getEndTime())) ||
                            (endTime.isBefore(reserve.getEndTime()) & endTime.isAfter(reserve.getStartTime())) ||
                            (startTime.isBefore(reserve.getStartTime()) & endTime.isAfter(reserve.getEndTime()))) {
                        LOG.trace("Reservation contains time of another reserve " + reserve);
                        response.setStatus(AnswerStatus.NOT_AVAILABLE);
                        return null;
                    }
                }
                LOG.trace("Price calculation");
                float price = PricePerHour.PRICE * ((float) startTime.until(endTime, HOURS));
                LOG.trace("Adding reservation");
                reservationService.makeReservation(workDay.getId(), user.getId(),startTime,endTime, price);
                response.setStatus(AnswerStatus.OK);
            }
        } catch (NumberFormatException e) {
            throw new WebException(e.getMessage());
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException();
        }
        LOG.debug("Reservation was created");


        return null;
    }
}
