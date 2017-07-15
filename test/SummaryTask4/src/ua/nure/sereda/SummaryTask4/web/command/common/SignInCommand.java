package ua.nure.sereda.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exceptions.*;
import ua.nure.sereda.SummaryTask4.models.User;
import ua.nure.sereda.SummaryTask4.service.UserService;
import ua.nure.sereda.SummaryTask4.utils.PassEncryption;
import ua.nure.sereda.SummaryTask4.utils.Validator;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.AnswerStatus;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class SignInCommand extends Command {

    private static final long serialVersionUID = 4273665242042225476L;
    private static final Logger LOG = Logger.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Start");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);

        LOG.trace("Getting user from session...");
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (sessionUser != null) {
            LOG.error("User element in session scope");
            request.setAttribute("errorMessage", "You are authorized as" + sessionUser.getName());
            return Path.PAGE_ERROR_PAGE;
        }
        try {
            if (!Validator.Email(request.getParameter("email"))) {
                throw new WrongEmailException();
            }
            if (!Validator.Password(request.getParameter("password"))) {
                throw new WrongPasswordException();
            }

            LOG.trace("Try to log in...");
            User user = userService.login(request.getParameter("email"),
                    PassEncryption.encrypt(request.getParameter("password")));
            if (user != null) {
                request.getSession().setAttribute("user", user);
                LOG.debug("User logged in successfully");
                response.setStatus(AnswerStatus.OK);
            }

        }catch (BannedUserException exception){
            LOG.trace("User banned");
            response.setStatus(AnswerStatus.FORBIDDEN);
        } catch (WrongPasswordException exception) {
            LOG.trace("Wrong password");
            response.setStatus(AnswerStatus.NOT_AVAILABLE);

        } catch (WrongEmailException exception) {
            LOG.trace("No user with such email");
            response.setStatus(AnswerStatus.UNKNOWN_EMAIL);

        } catch (ServiceException exception) {
            LOG.error("Error occurred ->" + exception.getMessage());
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException(exception.getMessage());
        }
        return null;
    }
}
