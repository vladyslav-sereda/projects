package ua.nure.sereda.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exceptions.AppException;
import ua.nure.sereda.SummaryTask4.exceptions.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.models.Role;
import ua.nure.sereda.SummaryTask4.models.User;
import ua.nure.sereda.SummaryTask4.service.OrderService;
import ua.nure.sereda.SummaryTask4.service.UserService;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class RedirectCommand extends Command {

    private static final long serialVersionUID = -9025116172879780074L;
    private static final Logger LOG = Logger.getLogger(RedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
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
            } else if (role.equals(Role.LIBRARIAN)) {
                initLibrarianPage(request);
                return Path.PAGE_LIBRARIAN;
            }
        }
        return  Path.PAGE_INDEX;
    }

    private void initAdminPage(HttpServletRequest request) throws AppException {
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);
        OrderService orderService = (OrderService) request.getServletContext().getAttribute(Services.ORDER);

        LOG.debug("Start");

        try {
            LOG.trace("Getting librarians...");
            List<User> librarians = userService.getLibrarians();
            LOG.trace("Getting banned users...");
            List<User> bannedUsers = userService.getBannedUsers();
            LOG.trace("Getting overdue orders...");
            List<Order> overdueOrders = orderService.getOverdueOrders();
            List<User> violatedUsers = userService.getUsersByOrders(overdueOrders);

            request.getSession().setAttribute("librarians", librarians);
            request.getSession().setAttribute("bannedUsers", bannedUsers);
            request.getSession().setAttribute("overdueOrders", overdueOrders);
            request.getSession().setAttribute("violatedUsers", violatedUsers);

        } catch (ServiceException exception) {
            throw new AppException();
        }
        LOG.debug("Admin page was initialized");
    }

    private void initLibrarianPage(HttpServletRequest request) throws AppException {
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);
        OrderService orderService = (OrderService) request.getServletContext().getAttribute(Services.ORDER);

        LOG.debug("Start");

        try {
            LOG.trace("Getting new orders...");
            List<Order> newOrders = orderService.getNewOrders();
            LOG.trace("Getting readers by new orders...");
            List<User> readers = userService.getUsersByOrders(newOrders);
            LOG.trace("Getting confirmed orders...");
            List<Order> confirmedOrders = orderService.getConfirmedOrders();
            LOG.trace("Getting readers by confirmed orders...");
            List<User> confirmedReaders = userService.getUsersByOrders(confirmedOrders);
            Collections.sort(newOrders);
            Collections.sort(confirmedOrders);
            request.getSession().setAttribute("newOrders", newOrders);
            request.getSession().setAttribute("newOrdersReaders", readers);
            request.getSession().setAttribute("confirmedOrders", confirmedOrders);
            request.getSession().setAttribute("confirmedReaders", confirmedReaders);
            LOG.debug("Librarian page was initialized");

        } catch (ServiceException exception) {
            throw new AppException("Can't initialized librarian page");
        }
    }
}
