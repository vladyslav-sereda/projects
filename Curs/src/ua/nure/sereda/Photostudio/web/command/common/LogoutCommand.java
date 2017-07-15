package ua.nure.sereda.Photostudio.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.web.Path;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class LogoutCommand extends Command {

    private static final long serialVersionUID = 3791327416434569374L;
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        LOG.debug("'user' element was removed from session scope");
        return Path.PAGE_INDEX;
    }
}
