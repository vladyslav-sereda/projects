package ua.nure.sereda.SummaryTask4.web.command.reader;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
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
public class EditUserCommand extends Command {

    private static final long serialVersionUID = -7657778553411249483L;
    private static final Logger LOG = Logger.getLogger(EditUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        LOG.debug("Command start");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("Current user ->" + user);
        String password = request.getParameter("password");
        LOG.trace("Password obtained");
        if (user == null) {
            LOG.error("user element doesn't exist in session scope");
            response.setStatus(AnswerStatus.UNAUTHORIZED);
            return null;
        }
        if (!Validator.Password(password)) {
            LOG.error("Password is not valid :" + password);
            response.setStatus(AnswerStatus.INVALID_INPUT);
            return null;
        } else if (!user.getPassword().equals(PassEncryption.encrypt(password))) {
            LOG.error("Password doesn't match");
            response.setStatus(AnswerStatus.NOT_AVAILABLE);
            return null;
        }

        String field = request.getParameter("field");
        if (field.trim().isEmpty()) {
            LOG.error("Field to change is not specified");
            return null;
        }
        switch (field) {
            case "delete": {
                try {
                    LOG.debug("Deleting user ...");
                    userService.delete(user.getId());
                    LOG.debug("Delete user complete");
                    request.getSession().removeAttribute("user");
                    LOG.debug("'user' element was removed from session scope");
                    return Path.PAGE_INDEX;
                } catch (ServiceException exception) {
                    LOG.error("Service error occurred.");
                    response.setStatus(AnswerStatus.SERVER_ERROR);
                    throw new AppException(exception.getMessage());
                }
            }
            case "name": {
                LOG.debug("Changing user name...");
                String name = request.getParameter("name");
                LOG.trace("Obtained new name >> " + name);
                if (Validator.Name(name)) {
                    LOG.trace("Name is valid");
                    user.setName(name);
                } else {
                    LOG.error("Name is not valid");
                    response.setStatus(AnswerStatus.INVALID_INPUT);
                    return null;
                }
                LOG.debug("Change name complete.");
                break;
            }

            case "email": {
                LOG.debug("Changing user email...");

                String email = request.getParameter("email");
                LOG.trace("Obtained new email >> " + email);

                if (Validator.Email(email)) {
                    LOG.trace("Email is valid");

                    user.setEmail(email);
                } else {
                    LOG.error("Email is not valid");

                    response.setStatus(AnswerStatus.INVALID_INPUT);
                    return null;
                }
                LOG.debug("Change email complete.");

                break;
            }
            case "password": {
                LOG.debug("Changing user password...");

                String newPass1 = request.getParameter("newPassword1");
                String newPass2 = request.getParameter("newPassword2");

                if (Validator.Password(newPass1) && newPass1.equals(newPass2)) {
                    LOG.trace("Passwords match and valid");
                    user.setPassword(PassEncryption.encrypt(newPass1));
                } else {
                    LOG.error("Passwords don't match or invalid");

                    response.setStatus(AnswerStatus.INVALID_INPUT);
                    return null;
                }
                LOG.debug("Change password complete.");
                break;
            }
        }

        try {
            LOG.debug("Trying to update user ...");
            userService.update(user);
        } catch (ServiceException exception) {
            LOG.error("Service error occurred.");
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException(exception.getMessage());
        }
        LOG.debug("Update user complete");

        response.setStatus(AnswerStatus.OK);

        return null;
    }
}
