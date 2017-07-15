package ua.nure.sereda.Photostudio.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.utils.validation.Validator;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.AnswerStatus;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class EditWorkdayCommand extends Command {
    private static final long serialVersionUID = -2091299074035170872L;
    private static final Logger LOG = Logger.getLogger(EditWorkdayCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        LOG.debug("Start");
        WorkDayService workDayService = (WorkDayService) request.getServletContext().getAttribute(Services.WORKDAY);
        ReservationService reservationService = (ReservationService) request.getServletContext().getAttribute(Services.RESERVATION);

        WorkDay workDay;
        List<Reservation> reservations;
        LocalDate date;
        LocalTime newStarDay;
        LocalTime newEndDay;

        LOG.trace("Getting params from request");
        try {
            date = LocalDate.parse(request.getParameter("date"));
            newStarDay = LocalTime.parse(request.getParameter("newStarDay"));
            newEndDay = LocalTime.parse(request.getParameter("newEndDay"));
            LOG.trace(String.format("Obtained params : date = %s, start day = %s, end day = %s", date, newStarDay, newEndDay));

            if (date == null || newStarDay == null || newEndDay == null) {
                LOG.error("Dates are null");
                response.setStatus(AnswerStatus.INVALID_INPUT);
            } else if (!Validator.dateAfterToday(date) || !Validator.time(newStarDay, newEndDay)) {
                LOG.error("Date or time is not valid");
                response.setStatus(AnswerStatus.INVALID_INPUT);
            } else if (workDayService.getByDate(date) == null) {
                LOG.trace("No such workday" + date);
                response.setStatus(AnswerStatus.NOT_AVAILABLE);
            } else {
                workDay = workDayService.getByDate(date);
                reservations = reservationService.getReservationsByWorkDay(workDay.getId());
                for (Reservation reservation : reservations) {
                    if (reservation.getStartTime().isBefore(newStarDay) || reservation.getEndTime().isAfter(newEndDay)) {
                        LOG.trace("Start or end time cross reservation time from " + newStarDay + " to " + newEndDay );
                        response.setStatus(AnswerStatus.NOT_AVAILABLE);
                        return null;
                    }
                }
                workDay.setStartDay(newStarDay);
                workDay.setEndDay(newEndDay);
                LOG.trace("Changing workday");
                workDayService.update(workDay);
                response.setStatus(AnswerStatus.OK);
            }
        } catch (NumberFormatException e) {
            throw new WebException(e.getMessage());
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException();
        }
        LOG.debug("Workday was created");


        return null;
    }
}
