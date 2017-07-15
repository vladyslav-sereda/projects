package ua.nure.sereda.Photostudio.web.command.manager;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.ReservationStatus;
import ua.nure.sereda.Photostudio.models.User;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.UserService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.AnswerStatus;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class showAllUnpaidReservesCommand extends Command {

    private static final long serialVersionUID = -4909086846532261337L;
    private static final Logger LOG = Logger.getLogger(showAllUnpaidReservesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        LOG.debug("Start");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);
        WorkDayService workDayService = (WorkDayService) request.getServletContext().getAttribute(Services.WORKDAY);
        ReservationService reservationService = (ReservationService) request.getServletContext().getAttribute(Services.RESERVATION);

        List<Reservation> reservations;
        List<WorkDay> workDays = new ArrayList<>();
        List<User> users = new ArrayList<>();

        try {
            LOG.trace("Getting workday-, reservation-lists");
            reservations = reservationService.getReservationsByStatus(ReservationStatus.UNPAID);
            for (Reservation reservation: reservations) {
                workDays.add(workDayService.getById(reservation.getDayId()));
                users.add(userService.getById(reservation.getUserId()));
            }
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException();
        }

        request.getSession().setAttribute("workdays", workDays);
        request.getSession().setAttribute("reservations", reservations);
        request.getSession().setAttribute("users", users);
        LOG.debug("Reservation status was changed");

        return null;
    }
}
