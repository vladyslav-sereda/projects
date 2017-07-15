package ua.nure.sereda.Photostudio.service;

import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.ReservationStatus;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface ReservationService {

    Reservation makeReservation (int dayId, int userId, LocalTime startTime, LocalTime endTime, float price) throws ServiceException;

    Reservation getById(int reservationId) throws ServiceException;

    List<Reservation> getAll() throws ServiceException;

    void delete(int reservationId) throws ServiceException;

    void update(Reservation reservation) throws ServiceException;

    List<Reservation> getReservationsByStatus(ReservationStatus status) throws ServiceException;

    List<Reservation> getReservationsByWorkDay(int dayId) throws ServiceException;

    List<Reservation> getReservationsByUser(int userId) throws ServiceException;

}
