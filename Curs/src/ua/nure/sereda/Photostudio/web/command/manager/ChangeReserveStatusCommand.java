package ua.nure.sereda.Photostudio.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.ReservationStatus;
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
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class ChangeReserveStatusCommand extends Command {
    private static final long serialVersionUID = -5611865574386449569L;
    private static final Logger LOG = Logger.getLogger(ChangeReserveStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        LOG.debug("Start");
        ReservationService reservationService = (ReservationService) request.getServletContext().getAttribute(Services.RESERVATION);

        Reservation reservation;
        int reservationId;

        LOG.trace("Getting params from request");
        try {
            reservationId = Integer.parseInt(request.getParameter("reservationId"));
            LOG.trace(String.format("Obtained param reservation id = %s", reservationId));
            reservation = reservationService.getById(reservationId);
            reservation.setStatus(ReservationStatus.PAID);
            reservationService.update(reservation);
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException();
        }
        LOG.debug("Reservation status was changed");

        return null;
    }
}
