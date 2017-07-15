package ua.nure.sereda.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exceptions.AppException;
import ua.nure.sereda.SummaryTask4.exceptions.ServiceException;
import ua.nure.sereda.SummaryTask4.service.UserService;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.AnswerStatus;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class DeleteLibrarianCommand extends Command {
    private static final long serialVersionUID = 306756629710930597L;
    private static final Logger LOG = Logger.getLogger(DeleteLibrarianCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command start");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);

        try {
            LOG.trace("Getting param from request");
            int userId = Integer.parseInt(request.getParameter("userId"));
            LOG.trace(String.format("Obtained param user id = %s ", userId));
            userService.delete(userId);
        } catch (ServiceException exception) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException();
        }
        LOG.debug("Librarian was deleted");
        return null;
    }
}
