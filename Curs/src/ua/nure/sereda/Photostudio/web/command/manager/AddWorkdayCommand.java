package ua.nure.sereda.Photostudio.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.utils.validation.Validator;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.AnswerStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Vladyslav.
 */
public class AddWorkdayCommand extends ua.nure.sereda.Photostudio.web.command.Command {
    private static final long serialVersionUID = 7306056062625422472L;
    private static final Logger LOG = Logger.getLogger(AddWorkdayCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        LOG.debug("Start");
        WorkDayService workDayService = (WorkDayService) request.getServletContext().getAttribute(Services.WORKDAY);
        LocalDate date;
        LocalTime startDay;
        LocalTime endDay;

        LOG.trace("Getting params from request");
        try {
            date = LocalDate.parse(request.getParameter("date"));
            startDay = LocalTime.parse(request.getParameter("startDay"));
            endDay = LocalTime.parse(request.getParameter("endDay"));
            LOG.trace(String.format("Obtained params : date = %s, start day = %s, end day = %s", date, startDay, endDay));

            if (date == null || startDay == null || endDay == null) {
                LOG.error("Dates are null");
                response.setStatus(AnswerStatus.INVALID_INPUT);
            } else if (!Validator.dateAfterToday(date) || !Validator.time(startDay,endDay)) {
                LOG.error("Date or time is not valid");
                response.setStatus(AnswerStatus.INVALID_INPUT);
            }else if (!workDayService.availableWorkDay(date)) {
                LOG.trace("Workday is already created at " + date);
                response.setStatus(AnswerStatus.NOT_AVAILABLE);
            } else {
                LOG.trace("Adding workday");
                workDayService.addDay(date, startDay, endDay);
                response.setStatus(AnswerStatus.OK);
            }
        } catch (NumberFormatException e) {
            throw new WebException(e.getMessage());
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException();
        }
        LOG.debug("Workday was created");


        return null;
    }
}
