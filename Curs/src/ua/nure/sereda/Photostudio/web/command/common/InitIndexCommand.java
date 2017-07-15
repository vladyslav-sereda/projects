package ua.nure.sereda.Photostudio.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.FreeTimeForReservation;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.web.Path;
import ua.nure.sereda.Photostudio.web.Services;
import ua.nure.sereda.Photostudio.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Vladyslav.
 */
public class InitIndexCommand extends Command {

    private static final long serialVersionUID = 393841832132443389L;
    private static final Logger LOG = Logger.getLogger(InitIndexCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws WebException {
        WorkDayService workDayService = (WorkDayService) request.getServletContext().getAttribute(Services.WORKDAY);
        ReservationService reservationService = (ReservationService) request.getServletContext().getAttribute(Services.RESERVATION);

        LOG.debug("Start");
        List<WorkDay> workDays;
        List<Reservation> reservations;
        List<FreeTimeForReservation> freeTimeForReservations;
        Map<Integer, List<FreeTimeForReservation>> mapFreeTime = new TreeMap<>();

        try {
            LOG.trace("Getting workday-, reservation-lists");
            workDays = workDayService.getAllFromToday();
            System.out.println(workDays);
            for (WorkDay workDay : workDays) {
                reservations = reservationService.getReservationsByWorkDay(workDay.getId());
                freeTimeForReservations = new ArrayList<>();
                if (reservations != null) {
                    if (!reservations.isEmpty()) {
                        Collections.sort(reservations);
                        for (int i = 0; i < reservations.size(); i++) {
                            if (i + 1 < reservations.size()) {
                                if (i==0 && !reservations.get(i).getStartTime().equals(workDay.getStartDay())) {
                                    freeTimeForReservations.add(new FreeTimeForReservation(
                                            workDay.getStartDay(), reservations.get(i).getStartTime()));
                                }
                                if (!reservations.get(i).getEndTime().equals(reservations.get(i + 1).getStartTime())) {
                                    freeTimeForReservations.add(new FreeTimeForReservation(
                                            reservations.get(i).getEndTime(), reservations.get(i + 1).getStartTime()));
                                }
                            } else if (i + 1 == reservations.size()) {
                                if (!reservations.get(i).getEndTime().equals(workDay.getEndDay())) {
                                    freeTimeForReservations.add(new FreeTimeForReservation(
                                            reservations.get(i).getEndTime(), workDay.getEndDay()));
                                }
                            }
                        }
                    } else {
                        freeTimeForReservations.add(new FreeTimeForReservation(
                                workDay.getStartDay(), workDay.getEndDay()));
                    }
                }
                System.out.println(freeTimeForReservations + " <== free");
                Collections.sort(freeTimeForReservations);
                mapFreeTime.put(workDay.getId(), freeTimeForReservations);
            }
        } catch (ServiceException e) {
            System.out.println(e);
            throw new WebException();
        }


        request.setAttribute("workdays", workDays);
        request.setAttribute("mapFreeTime", mapFreeTime);
        System.out.println(workDays);
        System.out.println(mapFreeTime);
        LOG.debug("Index page was initialized");
        return Path.PAGE_INDEX;
    }

}
