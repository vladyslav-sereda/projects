package ua.nure.sereda.Photostudio.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.exception.WrongEmailException;
import ua.nure.sereda.Photostudio.exception.WrongPassException;
import ua.nure.sereda.Photostudio.models.User;
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
public class SignInCommand extends Command {
    private static final long serialVersionUID = 4273665242042225476L;
    private static final Logger LOG = Logger.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws WebException {
        LOG.debug("Start");
        UserService userService = (UserService) request.getServletContext().getAttribute(Services.USER);
        try {
            if (!Validator.Email(request.getParameter("email"))) {
                throw new WrongEmailException();
            }
            if (!Validator.Password(request.getParameter("password"))) {
                throw new WrongPassException();
            }

            LOG.trace("Try to log in...");
            if(userService.login(request.getParameter("email"), Codec.md5(request.getParameter("password")))){
                User user = userService.getByEmail(request.getParameter("email"));
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("userRole", user.getRole());
                LOG.debug("User logged in successfully");
                response.setStatus(AnswerStatus.OK);
            }
        } catch (WrongPassException e) {
            LOG.trace("Wrong password");
            response.setStatus(AnswerStatus.NOT_AVAILABLE);

        } catch (WrongEmailException e) {
            LOG.trace("No user with such e-mail");
            response.setStatus(AnswerStatus.UNKNOWN_EMAIL);

        } catch (ServiceException e) {
            LOG.error("Error occurred >>" + e.getMessage());
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException(e.getMessage());
        }
        return null;
    }
}
