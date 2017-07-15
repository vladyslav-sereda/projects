package ua.nure.sereda.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.web.command.Command;
import ua.nure.sereda.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vladyslav.
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1649944976334420312L;
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Start Controller#doGet");
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command -> " + commandName);
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command -> " + command);
        String forward = Path.PAGE_ERROR_PAGE;

        try {
            LOG.trace("Command start");
            forward = command.execute(request, response);
            LOG.trace("Command finish");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        LOG.trace("Forward address -> " + forward);
        LOG.debug("Controller finished, now go to forward address -> " + forward);
        if (forward != null && !forward.isEmpty()) {
            request.getRequestDispatcher(forward).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Start Controller#doPost");
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command -> " + commandName);
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command -> " + command);
        String redirect = null;

        try {
            LOG.trace("Command start");
            redirect = command.execute(request, response);
            LOG.trace("Command finish");
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);

        }
        if (redirect != null) {
            LOG.trace("Redirect address -> " + redirect);
            LOG.debug("Controller finish, redirect to address -> " + redirect);
            response.sendRedirect(redirect);
        }
    }
}
