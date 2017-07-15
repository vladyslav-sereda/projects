package ua.nure.sereda.Photostudio.dao;

import ua.nure.sereda.Photostudio.exception.DaoException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.ReservationStatus;

import java.util.List;

/**
 * Created by sered on 11.05.2017.
 */
public interface ReservationDao extends Dao<Reservation> {

    List<Reservation> getReservationsByStatus(ReservationStatus status) throws DaoException;

    List<Reservation> getReservationsByWorkDay(int dayId) throws DaoException;

    List<Reservation> getReservationsByUser(int userId) throws DaoException;
}
