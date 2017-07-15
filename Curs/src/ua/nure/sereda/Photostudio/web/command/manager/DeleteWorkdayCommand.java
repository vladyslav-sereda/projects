package ua.nure.sereda.Photostudio.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.utils.validation.Validator;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.AnswerStatus;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class DeleteWorkdayCommand extends Command {
    private static final long serialVersionUID = -5185133714621766763L;
    private static final Logger LOG = Logger.getLogger(DeleteWorkdayCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        LOG.debug("Start");
        WorkDayService workDayService = (WorkDayService) request.getServletContext().getAttribute(Services.WORKDAY);
        ReservationService reservationService = (ReservationService) request.getServletContext().getAttribute(Services.RESERVATION);

        WorkDay workDay;
        LocalDate date;

        LOG.trace("Getting params from request");
        try {
            date = LocalDate.parse(request.getParameter("date"));
            LOG.trace(String.format("Obtained params : date = %s", date));

            if (!Validator.dateAfterToday(date)) {
                LOG.error("Date is not valid");
                response.setStatus(AnswerStatus.INVALID_INPUT);
            } else if (workDayService.getByDate(date) == null) {
                LOG.trace("No such workday" + date);
                response.setStatus(AnswerStatus.NOT_AVAILABLE);
            } else if (!reservationService.getReservationsByWorkDay(workDayService.getByDate(date).getId()).isEmpty()) {
                LOG.trace("Workday contains reservations");
                response.setStatus(AnswerStatus.NOT_AVAILABLE);
            } else {
                workDay = workDayService.getByDate(date);
                LOG.trace("deleting workday");
                workDayService.delete(workDay.getId());
                response.setStatus(AnswerStatus.OK);
            }
        } catch (NumberFormatException e) {
            throw new WebException(e.getMessage());
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException();
        }
        LOG.debug("Workday was deleted");

        return null;
    }
}
