package ua.nure.sereda.Photostudio.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.User;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.UserService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.utils.encoding.Codec;
import ua.nure.sereda.Photostudio.utils.validation.Validator;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.AnswerStatus;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * Created by Vladyslav.
 */
public class DeleteReserveCommand extends Command {
    private static final long serialVersionUID = -6279009975533051119L;
    private static final Logger LOG = Logger.getLogger(DeleteReserveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        LOG.debug("Command start");
        ReservationService reservationService = (ReservationService) request.getServletContext().getAttribute(Services.RESERVATION);
        WorkDayService workDayService = (WorkDayService) request.getServletContext().getAttribute(Services.WORKDAY);

        WorkDay workDay;
        Reservation reservation;
        int reservationId;

        LOG.trace("Getting params from request");
        try {
            reservationId = Integer.parseInt(request.getParameter("reservationId"));
            LOG.trace(String.format("Obtained param reservation id = %s ", reservationId));

            LOG.trace("Getting workday and reservation ...");
            reservation = reservationService.getById(reservationId);
            workDay = workDayService.getById(reservation.getDayId());

            if (workDay.getDate().isBefore(LocalDate.now().plusDays(1))) {
                LOG.error("Date is too early");
                response.setStatus(AnswerStatus.INVALID_INPUT);
            } else {
                LOG.trace("Deleting reservation");
                reservationService.delete(reservationId);
                response.setStatus(AnswerStatus.OK);
            }
        } catch (NumberFormatException e) {
            throw new WebException(e.getMessage());
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new WebException();
        }
        LOG.debug("Reservation was created");
        return null;
    }
}
