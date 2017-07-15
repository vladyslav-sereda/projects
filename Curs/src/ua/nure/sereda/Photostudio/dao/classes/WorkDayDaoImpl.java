package ua.nure.sereda.Photostudio.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.dao.WorkDayDao;
import ua.nure.sereda.Photostudio.db.ConnectionHolder;
import ua.nure.sereda.Photostudio.db.Fields;
import ua.nure.sereda.Photostudio.exception.DaoException;
import ua.nure.sereda.Photostudio.exception.ErrorMessages;
import ua.nure.sereda.Photostudio.models.WorkDay;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class WorkDayDaoImpl implements WorkDayDao {
    private static final Logger LOG = Logger.getLogger(WorkDayDaoImpl.class);

    private static final String DAY_CREATE = "INSERT INTO workdays VALUES(DEFAULT,?,?,?)";
    private static final String DAY_UPDATE = "UPDATE workdays SET date=?, start_day=?, end_day=? WHERE id_day=?";
    private static final String DAY_DELETE = "DELETE FROM workdays WHERE id_day=?";
    private static final String GET_ALL_DAYS = "SELECT * FROM workdays";
    private static final String GET_DAY_BY_ID = "SELECT * FROM workdays WHERE id_day=?";
    private static final String GET_DAY_BY_DATE = "SELECT * FROM workdays WHERE date=?";
    private static final String GET_DAYS_FROM_TODAY = "SELECT * FROM workdays WHERE date>CURDATE()";

    @Override
    public WorkDay create(WorkDay workDay) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(DAY_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            preparedStatement.setDate(k++, Date.valueOf(workDay.getDate()));
            preparedStatement.setTime(k++, Time.valueOf(workDay.getStartDay()));
            preparedStatement.setTime(k, Time.valueOf(workDay.getEndDay()));

            LOG.trace("Executing query >>" + preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    workDay.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_CREATE_WORKDAY, e);
        }
        return workDay;
    }

    @Override
    public void update(WorkDay workDay) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DAY_UPDATE);
            int k = 1;
            preparedStatement.setDate(k++, Date.valueOf(workDay.getDate()));
            preparedStatement.setTime(k++, Time.valueOf(workDay.getStartDay()));
            preparedStatement.setTime(k++, Time.valueOf(workDay.getEndDay()));
            preparedStatement.setInt(k, workDay.getId());
            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_UPDATE_WORKDAY, e);
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DAY_DELETE);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_DELETE_WORKDAY, e);
        }

    }

    @Override
    public List<WorkDay> getAll() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        List<WorkDay> workDays = null;
        try {
            workDays = new ArrayList<WorkDay>();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(GET_ALL_DAYS);
            LOG.trace("Executing query >>" + stmt);

            while (rs.next()) {
                workDays.add(extractDay(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_ALL_WORKDAYS, e);
        }
        return workDays;
    }

    @Override
    public WorkDay getById(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        WorkDay workDay = null;
        try {
            preparedStatement = connection.prepareStatement(GET_DAY_BY_ID);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                workDay = extractDay(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_WORKDAY_BY_ID + id, e);
        }
        return workDay;
    }

    @Override
    public WorkDay getWorkDayByDate(LocalDate date) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        WorkDay workDay = null;
        try {
            preparedStatement = connection.prepareStatement(GET_DAY_BY_DATE);
            preparedStatement.setDate(1,Date.valueOf(date));
            LOG.trace("Executing query >>" + preparedStatement);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                workDay = extractDay(rs);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DaoException(ErrorMessages.CANT_GET_WORKDAY_BY_DATE + date, e);
        }
        return workDay;
    }

    @Override
    public List<WorkDay> getWorkDaysFromToday() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement stmt ;
        ResultSet rs;
        List<WorkDay> workDays ;
        try {
            workDays = new ArrayList<>();
            stmt = connection.createStatement();
            LOG.trace("Executing query >>" + stmt);
            rs = stmt.executeQuery(GET_DAYS_FROM_TODAY);
            System.out.println(rs);
            while (rs.next()) {
                System.out.println("while");
                workDays.add(extractDay(rs));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DaoException(ErrorMessages.CANT_GET_WORKDAYS_FROM_TODAY, e);
        }
        return workDays;
    }

    private WorkDay extractDay(ResultSet resultSet) throws SQLException {
        WorkDay workDay = new WorkDay();
        workDay.setId(resultSet.getInt(Fields.WorkDays.ID));
        workDay.setDate(resultSet.getDate(Fields.WorkDays.DATE).toLocalDate());
        workDay.setStartDay(resultSet.getTime(Fields.WorkDays.START_DAY).toLocalTime());
        workDay.setEndDay(resultSet.getTime(Fields.WorkDays.END_DAY).toLocalTime());
        LOG.trace("Obtained workday >>" + workDay);
        return workDay;
    }
}
