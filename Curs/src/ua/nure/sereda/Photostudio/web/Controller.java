package ua.nure.sereda.Photostudio.web;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.web.command.Command;
import ua.nure.sereda.Photostudio.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vladyslav.
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 5498905300779595943L;
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Controller#doGet starts");
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command >> " + commandName);
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command >> " + command);
        String forward = Path.PAGE_ERROR_PAGE;

        try {
            LOG.trace("Command start");
            forward = command.execute(request, response);
            LOG.trace("Command finish");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        LOG.trace("Forward address >> " + forward);
        LOG.debug("Controller finished, now go to forward address >> " + forward);
        if (forward != null) {

            request.getRequestDispatcher(forward).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("Controller#doPost start");
        String commandName = request.getParameter("command");
        Command command = CommandContainer.get(commandName);
        LOG.trace("Request param: command >> " + commandName);
        String redirect = null;

        try {
            redirect = command.execute(request, response);
        } catch (WebException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);

        }
        if (redirect != null) {
            LOG.trace("Redirect address >> " + redirect);
            redirect = "/SummaryTask4/" + redirect;
            LOG.debug("Controller finish, redirect to address >> " + redirect);
            response.sendRedirect(redirect);
        }
    }
}
