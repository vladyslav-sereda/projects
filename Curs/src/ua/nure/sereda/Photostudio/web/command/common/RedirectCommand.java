package ua.nure.sereda.Photostudio.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.*;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.UserService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.web.Path;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Vladyslav.
 */
public class RedirectCommand extends Command {

    private static final long serialVersionUID = -9025116172879780074L;
    private static final Logger LOG = Logger.getLogger(RedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws WebException {
        LOG.debug("Start");
        LOG.trace("Getting user from session scope...");
        User user = (User) request.getSession().getAttribute("user");
        Role role;
        if (user != null) {
            role = user.getRole();
            LOG.debug("User's role is : " + role);
            if (role.equals(Role.ADMIN)) {
                initAdminPage(request);
                return Path.PAGE_ADMIN;
            }
        }
        try {
            request.getRequestDispatcher(new InitIndexCommand().execute(request, response)).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new WebException();
        }
        return null;
    }

    private void initAdminPage(HttpServletRequest request) throws WebException {
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);
        WorkDayService workDayService = (WorkDayService) request.getServletContext().getAttribute(Services.WORKDAY);
        ReservationService reservationService = (ReservationService) request.getServletContext().getAttribute(Services.RESERVATION);

        LOG.debug("Start");
        List<WorkDay> workDays;
        List<Reservation> reservations;
        List<User> users = new ArrayList<>();
        List<Reservation> unpaidReservs;
        Map<Integer, List<Reservation>> mapReservations = new TreeMap<>();

        try {
            LOG.trace("Getting workday-, reservation- and user-lists");
            workDays = workDayService.getAll();
            for (WorkDay workDay : workDays) {
                reservations = reservationService.getReservationsByWorkDay(workDay.getId());
                if (reservations != null) {
                    Collections.sort(reservations);
                    mapReservations.put(workDay.getId(), reservations);
                }
            }
            users = userService.getAll();

            unpaidReservs = reservationService.getReservationsByStatus(ReservationStatus.UNPAID);
            Collections.sort(unpaidReservs);
        } catch (ServiceException e) {
            System.out.println(e);
            throw new WebException();
        }

        request.getSession().setAttribute("workdays", workDays);
        request.getSession().setAttribute("reservations", mapReservations);
        request.getSession().setAttribute("users", users);
        request.getSession().setAttribute("unpaidReservs", unpaidReservs);
        System.out.println(workDays);
        System.out.println(mapReservations);
        System.out.println(users);
        System.out.println(unpaidReservs);
        LOG.debug("Manager page was initialized");
    }
}
