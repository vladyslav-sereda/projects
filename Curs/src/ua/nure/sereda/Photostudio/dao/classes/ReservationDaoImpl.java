package ua.nure.sereda.Photostudio.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.dao.ReservationDao;
import ua.nure.sereda.Photostudio.db.ConnectionHolder;
import ua.nure.sereda.Photostudio.db.Fields;
import ua.nure.sereda.Photostudio.exception.DaoException;
import ua.nure.sereda.Photostudio.exception.ErrorMessages;
import ua.nure.sereda.Photostudio.models.Reservation;
import ua.nure.sereda.Photostudio.models.ReservationStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class ReservationDaoImpl implements ReservationDao {

    private static final Logger LOG = Logger.getLogger(ReservationDaoImpl.class);

    private static final String RESERVATION_CREATE =
            "INSERT INTO reservations VALUES(DEFAULT,?,?, ?,?,?, DEFAULT)";
    private static final String RESERVATION_UPDATE =
            "UPDATE reservations SET id_day=?, id_user=?, start_time=?, end_time=?, price=?, status=? WHERE id_reservation=?";
    private static final String RESERVATION_DELETE = "DELETE FROM reservations WHERE id_reservation=?";
    private static final String GET_ALL_RESERVATIONS = "SELECT * FROM reservations";
    private static final String GET_RESERVATION_BY_ID = "SELECT * FROM reservations WHERE id_reservation =?";
    private static final String GET_RESERVATIONS_BY_STATUS = "SELECT * FROM reservations WHERE status=?";
    private static final String GET_RESERVATIONS_BY_WORKDAY = "SELECT * FROM reservations WHERE id_day=?";
    private static final String GET_RESERVATIONS_BY_USER= "SELECT * FROM reservations WHERE id_user=?";


    @Override
    public Reservation create(Reservation reservation) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        System.out.println(reservation.toString());
        try {
            preparedStatement = connection.prepareStatement(RESERVATION_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;

            preparedStatement.setInt(k++, reservation.getDayId());
            preparedStatement.setInt(k++, reservation.getUserId());
            preparedStatement.setTime(k++, Time.valueOf(reservation.getStartTime()));
            preparedStatement.setTime(k++, Time.valueOf(reservation.getEndTime()));
            preparedStatement.setFloat(k, reservation.getPrice());

            LOG.trace("Executing query >>" + preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    reservation.setId(rs.getInt(1));
                    reservation.setStatus(ReservationStatus.UNPAID);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DaoException(ErrorMessages.CANT_CREATE_RESERVATION, e);
        }
        return reservation;
    }

    @Override
    public void update(Reservation reservation) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(RESERVATION_UPDATE);
            int k = 1;
            preparedStatement.setInt(k++, reservation.getDayId());
            preparedStatement.setInt(k++, reservation.getUserId());
            preparedStatement.setTime(k++, Time.valueOf(reservation.getStartTime()));
            preparedStatement.setTime(k++, Time.valueOf(reservation.getEndTime()));
            preparedStatement.setFloat(k++, reservation.getPrice());
            preparedStatement.setString(k++, reservation.getStatus().toString());
            preparedStatement.setInt(k, reservation.getId());

            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_UPDATE_RESERVATION, e);
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(RESERVATION_DELETE);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_DELETE_RESERVATION, e);
        }
    }

    @Override
    public List<Reservation> getAll() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement stmt;
        ResultSet rs;
        List<Reservation> reservations;
        try {
            reservations = new ArrayList<>();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(GET_ALL_RESERVATIONS);
            LOG.trace("Executing query >>" + stmt);

            while (rs.next()) {
                reservations.add(extractReservation(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_ALL_RESERVATIONS, e);
        }
        return reservations;
    }

    @Override
    public Reservation getById(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        Reservation reservation = null;
        try {
            preparedStatement = connection.prepareStatement(GET_RESERVATION_BY_ID);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                reservation = extractReservation(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_RESERVATION_BY_ID + id, e);
        }
        return reservation;
    }

    @Override
    public List<Reservation> getReservationsByStatus(ReservationStatus status) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        List<Reservation> reservations;
        try {
            reservations = new ArrayList<>();
            preparedStatement = connection.prepareStatement(GET_RESERVATIONS_BY_STATUS);
            preparedStatement.setString(1, status.toString());
            LOG.trace("Executing query >>" + preparedStatement);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reservations.add(extractReservation(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_RESERVATION_BY_STATUS + status, e);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByWorkDay(int dayId) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        List<Reservation> reservations;
        try {
            reservations = new ArrayList<>();
            preparedStatement = connection.prepareStatement(GET_RESERVATIONS_BY_WORKDAY);
            preparedStatement.setInt(1, dayId);
            LOG.trace("Executing query >>" + preparedStatement);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("while");
                reservations.add(extractReservation(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_RESERVATION_BY_WORKDAY + dayId, e);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByUser(int userId) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        List<Reservation> reservations;
        try {
            reservations = new ArrayList<>();
            preparedStatement = connection.prepareStatement(GET_RESERVATIONS_BY_USER);
            preparedStatement.setInt(1, userId);
            LOG.trace("Executing query >>" + preparedStatement);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reservations.add(extractReservation(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_RESERVATION_BY_USER + userId, e);
        }
        return reservations;
    }

    private Reservation extractReservation(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getInt(Fields.Reservations.ID));
        reservation.setDayId(resultSet.getInt(Fields.Reservations.DAY_ID));
        reservation.setUserId(resultSet.getInt(Fields.Reservations.USER_ID));
        reservation.setStartTime(resultSet.getTime(Fields.Reservations.START_TIME).toLocalTime());
        reservation.setEndTime(resultSet.getTime(Fields.Reservations.END_TIME).toLocalTime());
        reservation.setPrice(resultSet.getFloat(Fields.Reservations.PRICE));
        reservation.setStatus(ReservationStatus.valueOf(resultSet.getString(Fields.Reservations.STATUS)));

        LOG.trace("Obtained user >>" + reservation);
        return reservation;
    }
}
