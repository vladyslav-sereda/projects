package ua.nure.sereda.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class InitIndexCommand extends Command {

    private static final long serialVersionUID = 393841832132443389L;
    private static final Logger LOG = Logger.getLogger(InitIndexCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        return Path.PAGE_INDEX;
    }
}
