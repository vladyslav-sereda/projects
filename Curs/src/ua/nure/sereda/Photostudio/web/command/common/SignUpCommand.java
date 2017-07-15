package ua.nure.sereda.Photostudio.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.service.UserService;
import ua.nure.sereda.Photostudio.utils.encoding.Codec;
import ua.nure.sereda.Photostudio.utils.validation.Validator;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.AnswerStatus;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class SignUpCommand extends Command {

    private static final long serialVersionUID = -6585516950730621241L;
    private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws WebException {
        LOG.debug("Start");
        LOG.trace("Getting user from session scope");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);

        try {
            if (validateData(request, userService) == AnswerStatus.OK) {
                LOG.trace("Creating new user...");
                userService.addUser(request.getParameter("email"),
                        request.getParameter("name"),
                        request.getParameter("phone"),
                        Codec.md5(request.getParameter("password")));
                response.setStatus(AnswerStatus.OK);
            } else {
                response.setStatus(validateData(request, userService));
            }
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException(e.getMessage());
        }
        LOG.debug("User created");
        return null;
    }

    private int validateData(HttpServletRequest request, UserService userService) throws ServiceException {
        LOG.debug("Checking obtained registration params for validity");
        if (!Validator.Email(request.getParameter("email"))
                || !Validator.Name(request.getParameter("name"))
                || !Validator.Phone(request.getParameter("phone"))
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
