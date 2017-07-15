package ua.nure.sereda.Photostudio.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.ReservationStatus;
import ua.nure.sereda.Photostudio.web.Path;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vladyslav.
 */
public class NoCommand extends Command {
    private static final Logger LOG = Logger.getLogger(NoCommand.class);
    private static final long serialVersionUID = -7481105594839280465L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        LOG.debug("No command invoked");
        request.setAttribute("errorMessage", "No such command");
        try {
            request.getRequestDispatcher(new InitIndexCommand().execute(request, response)).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new WebException();
        }
        return Path.PAGE_ERROR_PAGE;
    }
}