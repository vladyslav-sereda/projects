package ua.nure.sereda.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class NoCommand extends Command {

    private static final long serialVersionUID = 6677330232622738193L;
    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("No command invoked");
        request.setAttribute("errorMessage", "No such command");
        return Path.PAGE_ERROR_PAGE;
    }
}