package ua.nure.sereda.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.service.UserService;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.AnswerStatus;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class BanUserCommand extends Command {
    private static final long serialVersionUID = -4669227709740572586L;
    private static final Logger LOG = Logger.getLogger(BanUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command start");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);

        try {
            LOG.trace("Getting param from request");
            int userId = Integer.parseInt(request.getParameter("userId"));
            LOG.trace(String.format("Obtained param user id = %s ", userId));
            userService.banUser(userId);
        } catch (ServiceException exception) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException();
        }
        LOG.debug("User was baned");
        return null;
    }
}
