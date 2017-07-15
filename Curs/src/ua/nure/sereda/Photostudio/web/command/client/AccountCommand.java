package ua.nure.sereda.Photostudio.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.User;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.web.Path;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class AccountCommand extends Command {
    private static final long serialVersionUID = -1103806651726040061L;
    private static final Logger LOG = Logger.getLogger(AccountCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws WebException {
        LOG.debug("Start");

        WorkDayService workDayService = (WorkDayService) request.getServletContext()
                .getAttribute(Services.WORKDAY);
        ReservationService reservationService = (ReservationService) request.getServletContext()
                .getAttribute(Services.RESERVATION);

        LOG.trace("Getting user from session...");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            LOG.error("No user element in session scope");
            request.setAttribute("errorMessage", "You are not authorized");
            return Path.PAGE_ERROR_PAGE;
        } else {
            LOG.trace("Obtained user >> " + user);

            try {
                LOG.trace("Getting user's reservations...");
                List<Reservation> reservations = reservationService.getReservationsByUser(user.getId());
                List<WorkDay> workDays = new ArrayList<>();
                List<WorkDay> workDaysFromToday = workDayService.getAllFromToday();
                LOG.trace("Getting reservation's workday...");
                for (Reservation reservation : reservations) {
                    workDays.add(workDayService.getById(reservation.getDayId()));
                }
                for (WorkDay workDay : workDays) {
                    if (!workDaysFromToday.contains(workDay)) {
                        workDays.remove(workDay);
                    }
                }
                for (Reservation reservation : reservations) {
                    for (WorkDay workDay : workDays) {
                        if (reservation.getDayId() != workDay.getId()){
                            reservations.remove(reservation);
                        }
                    }
                }
                request.getSession().setAttribute("reserves", reservations);
                request.getSession().setAttribute("workdays", workDays);
                LOG.trace("User's account initialized");
            } catch (ServiceException e) {
                throw new WebException(e.getMessage());
            }
            return Path.PAGE_ACCOUNT;
        }
    }
}
