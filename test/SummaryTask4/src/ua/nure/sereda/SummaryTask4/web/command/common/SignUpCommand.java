package ua.nure.sereda.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exceptions.AppException;
import ua.nure.sereda.SummaryTask4.exceptions.ServiceException;
import ua.nure.sereda.SummaryTask4.service.UserService;
import ua.nure.sereda.SummaryTask4.utils.PassEncryption;
import ua.nure.sereda.SummaryTask4.utils.Validator;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.AnswerStatus;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class SignUpCommand extends Command {

    private static final long serialVersionUID = 9132281429384263795L;
    private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        LOG.debug("Start");

        LOG.trace("Getting user from session scope");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);

        try {
            if (validateData(request, userService) == AnswerStatus.OK) {
                LOG.trace("Creating new user...");
                userService.signUpReader(
                        request.getParameter("name"),
                        request.getParameter("email"),
                        PassEncryption.encrypt(request.getParameter("password")));
                response.setStatus(AnswerStatus.OK);
                LOG.debug("User created");
            } else {
                response.setStatus(validateData(request, userService));
                LOG.debug("User not created");
            }
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException(e.getMessage());
        }
        return null;
    }

    private int validateData(HttpServletRequest request, UserService userService) throws ServiceException {
        LOG.debug("Checking obtained registration params for validity");
        if (!Validator.Email(request.getParameter("email"))
                || !Validator.Name(request.getParameter("name"))
                || !Validator.Password(request.getParameter("password"))
                || !Validator.Password(request.getParameter("passwordConfirmation"))
                || (!request.getParameter("passwordConfirmation").equals(request.getParameter("password")))) {
            LOG.error("Not all fields are correct");
            return AnswerStatus.INVALID_INPUT;
        } else if (userService.getByEmail(request.getParameter("email")) != null) {
            LOG.debug("User with obtained e-mail already exist");
            return AnswerStatus.EMAIL_IN_USE;
        }
        LOG.debug("All fields are correct");
        return AnswerStatus.OK;
    }
}
