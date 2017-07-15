package ua.nure.sereda.Photostudio.web.websocet;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.dao.ReservationDao;
import ua.nure.sereda.Photostudio.dao.WorkDayDao;
import ua.nure.sereda.Photostudio.dao.classes.ReservationDaoImpl;
import ua.nure.sereda.Photostudio.dao.classes.WorkDayDaoImpl;
import ua.nure.sereda.Photostudio.db.TransactionManager;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.PricePerHour;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.service.classes.ReservationServiceImpl;
import ua.nure.sereda.Photostudio.service.classes.WorkDayServiceImpl;
import ua.nure.sereda.Photostudio.utils.validation.Validator;

import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * Created by Vladyslav.
 */
public class ReservationEvent extends SocketEvent {
    private TransactionManager manager = new TransactionManager();
    private ReservationDao reservationDao = new ReservationDaoImpl();
    private ReservationService reservationService = new ReservationServiceImpl(reservationDao, manager);
    private WorkDayDao workDayDao = new WorkDayDaoImpl();
    private WorkDayService workDayService = new WorkDayServiceImpl(manager, workDayDao);
    private static final Logger LOG = Logger.getLogger(ReservationEvent.class);

    /**
     * Method invokes reservation function with all needed params,
     * and sends processed data to manager in right format.
     */
    public void execute(JsonObject jsObj, Set<Session> peers) throws WebException {
        LOG.debug("Start, obtained JSON >> " + jsObj);

        Reservation reservation = extractReservation(jsObj);
        if (reservation != null) {
            for (Session peer : peers)
                try {
                    LOG.debug("Sending answer to client");
                    peer.getBasicRemote().sendText(createAnswer("reservation", reservation));
                } catch (IOException e) {
                    throw new WebException(e.getMessage());
                }
        }
    }

    private Reservation extractReservation(JsonObject jsObj) throws WebException {
        LOG.debug("Getting reservation java-object from JSON");


        Reservation reserve = null;
        WorkDay workDay;
        List<Reservation> reservations;
        LocalTime startTime = LocalTime.parse(jsObj.get("startTime").getAsString());
        LocalTime endTime = LocalTime.parse(jsObj.get("endTime").getAsString());
        int dayId = jsObj.get("dayId").getAsInt();
        int userId = jsObj.get("userId").getAsInt();
        try {
            if (dayId != 0) {
                workDay = workDayService.getById(dayId);
                reservations = reservationService.getReservationsByWorkDay(workDay.getId());
                if (Validator.time(startTime, endTime)
                        || Validator.dateAfterToday(workDay.getDate())
                        || !startTime.isBefore(LocalTime.now().plusHours(1))
                        || !workDay.getStartDay().isAfter(startTime)
                        || !workDay.getEndDay().isBefore(endTime)) {
                    for (Reservation reservation : reservations) {
                        if ((startTime.isAfter(reservation.getStartTime()) & startTime.isBefore(reservation.getEndTime()))
                                || (endTime.isBefore(reservation.getEndTime()) & endTime.isAfter(reservation.getStartTime()))
                                || (startTime.isBefore(reservation.getStartTime()) & endTime.isAfter(reservation.getEndTime()))) {
                            LOG.trace("Reservation contains time of another reserve " + reservation);
                            return null;
                        }
                    }
                    LOG.trace("Price calculation");
                    float price = PricePerHour.PRICE * ((float) startTime.until(endTime, HOURS));
                    LOG.trace("Adding reservation");
                    reserve = reservationService.makeReservation(workDay.getId(), userId, startTime, endTime, price);
                } else {
                    return null;
                }
            }
        } catch (ServiceException e) {
            throw new WebException(e.getMessage());
        }

        LOG.debug("Obtained object >> " + reserve);

        return reserve;
    }
}
