package ua.nure.sereda.Photostudio.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.User;
import ua.nure.sereda.Photostudio.service.UserService;
import ua.nure.sereda.Photostudio.utils.encoding.Codec;
import ua.nure.sereda.Photostudio.utils.validation.Validator;
import ua.nure.sereda.Photostudio.web.Path;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.AnswerStatus;
import ua.nure.sereda.Photostudio.web.command.Command;

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
            throws WebException {
        LOG.debug("Command start");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("Current user >>" + user);
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
        } else if (!user.getPassword().equals(Codec.md5(password))) {
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
                } catch (ServiceException e) {
                    LOG.error("Service error occurred.");
                    response.setStatus(AnswerStatus.SERVER_ERROR);
                    throw new WebException(e.getMessage());
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
            case "phone": {
                LOG.debug("Changing user phone...");

                String phone = request.getParameter("phone");
                LOG.trace("Obtained new phone >> " + phone);

                if (Validator.Phone(phone)) {
                    LOG.trace("Phone is valid");

                    user.setPhone(phone);
                } else {
                    LOG.error("Phone is not valid");

                    response.setStatus(AnswerStatus.INVALID_INPUT);
                    return null;
                }
                LOG.debug("Change phone complete.");
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
                    user.setPassword(Codec.md5(newPass1));
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
        } catch (ServiceException e) {
            LOG.error("Service error occurred.");
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException(e.getMessage());
        }
        LOG.debug("Update user complete");

        response.setStatus(AnswerStatus.OK);

        return null;
    }
}
