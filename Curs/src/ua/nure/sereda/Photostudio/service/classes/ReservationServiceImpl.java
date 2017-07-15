package ua.nure.sereda.Photostudio.service.classes;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.dao.ReservationDao;
import ua.nure.sereda.Photostudio.db.TransactionManager;
import ua.nure.sereda.Photostudio.db.VoidTransaction;
import ua.nure.sereda.Photostudio.exception.DaoException;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.TransactionException;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.ReservationStatus;
import ua.nure.sereda.Photostudio.service.ReservationService;

import java.sql.Connection;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class ReservationServiceImpl implements ReservationService {

    private ReservationDao reservationDao;
    private TransactionManager transactionManager;
    private static final Logger LOG = Logger.getLogger(ReservationServiceImpl.class);

    public ReservationServiceImpl(ReservationDao reservationDao, TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.reservationDao = reservationDao;
    }

    @Override
    public Reservation makeReservation(int dayId, int userId, LocalTime startTime, LocalTime endTime, float price) throws ServiceException {
        LOG.debug(String.format("Start with params: day id = %d, user id = %d, start time = %s, end time = %s, price = %f",
                dayId, userId, startTime, endTime, price));
        Reservation reservation = new Reservation(dayId, userId, startTime, endTime, price);
        try {
            return transactionManager.doTask(() -> reservationDao.create(reservation),
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Reservation getById(int reservationId) throws ServiceException {
        LOG.debug("Start");

        try {
            return transactionManager.doTask(() -> reservationDao.getById(reservationId),
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Reservation> getAll() throws ServiceException {
        LOG.debug("Start");

        try {
            return transactionManager.doTask(() -> reservationDao.getAll(), Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int reservationId) throws ServiceException {
        LOG.debug("Start, reservation id = " + reservationId);

        try {
            transactionManager.doVoidTask(() -> reservationDao.remove(reservationId),
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Reservation reservation) throws ServiceException {
        LOG.debug("Start, reservation >>" + reservation);

        try {
            transactionManager.doVoidTask(() -> reservationDao.update(reservation), Connection.TRANSACTION_READ_COMMITTED);

        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Reservation> getReservationsByStatus(ReservationStatus status) throws ServiceException {
        LOG.debug("Start, status = " + status);

        try {
            return transactionManager.doTask(() -> reservationDao.getReservationsByStatus(status),
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Reservation> getReservationsByWorkDay(int dayId) throws ServiceException {
        LOG.debug("Start, day id = " + dayId);

        try {
            return transactionManager.doTask(() -> reservationDao.getReservationsByWorkDay(dayId),
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Reservation> getReservationsByUser(int userId) throws ServiceException {
        LOG.debug("Start, user id = " + userId);

        try {
            return transactionManager.doTask(() -> reservationDao.getReservationsByUser(userId),
                    Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }
}
