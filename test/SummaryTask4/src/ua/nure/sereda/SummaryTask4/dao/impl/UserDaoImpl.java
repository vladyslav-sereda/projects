package ua.nure.sereda.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.dao.UserDao;
import ua.nure.sereda.SummaryTask4.db.ConnectionHolder;
import ua.nure.sereda.SummaryTask4.db.Fields;
import ua.nure.sereda.SummaryTask4.exceptions.DaoException;
import ua.nure.sereda.SummaryTask4.exceptions.ErrorMessages;
import ua.nure.sereda.SummaryTask4.models.Role;
import ua.nure.sereda.SummaryTask4.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Vladyslav.
 */
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private static final String CREATE_USER = "INSERT INTO users VALUES(DEFAULT,?,?,?,(SELECT id FROM roles WHERE roles.name=?),DEFAULT)";
    private static final String UPDATE_USER = "UPDATE users SET  name=?, email=?, password=?, " +
            "role_id=(SELECT id FROM roles WHERE roles.name=?), banned=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?;";
    private static final String GET_ALL_LIBRARIAN = "SELECT * FROM users WHERE role_id=(SELECT id FROM roles WHERE roles.name='LIBRARIAN')";
    private static final String GET_ROLE_BY_ID = "SELECT name FROM roles WHERE id=?";


    @Override
    public User create(User user) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            preparedStatement.setString(k++, user.getName());
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setString(k, user.getRole().toString());

            LOG.trace("Executing query >>" + preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                    user.setBanned(resultSet.getBoolean(1));
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_CREATE_USER, exception);
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER);
            int k = 1;
            preparedStatement.setString(k++, user.getName());
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setString(k++, user.getRole().toString());
            preparedStatement.setBoolean(k++, user.isBanned());
            preparedStatement.setInt(k, user.getId());

            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_UPDATE_USER, exception);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_DELETE_USER, exception);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement statement;
        ResultSet resultSet;
        List<User> users;
        try {
            users = new ArrayList<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_USERS);
            LOG.trace("Executing query >>" + statement);

            while (resultSet.next()) {
                users.add(extractUser(resultSet));
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_ALL_USERS, exception);
        }
        return users;
    }


    @Override
    public User getById(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_USER_BY_ID + id, exception);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            LOG.trace("Executing query >>" + preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_USER_BY_EMAIL + email, exception);
        }
        return user;
    }

    @Override
    public List<User> getLibrarians() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement statement;
        ResultSet resultSet;
        List<User> users;
        try {
            users = new ArrayList<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_LIBRARIAN);
            LOG.trace("Executing query >>" + statement);

            while (resultSet.next()) {
                users.add(extractUser(resultSet));
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_LIBRARIANS, exception);
        }
        return users;
    }

    private Role getRoleName(int roleId) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(GET_ROLE_BY_ID);
            preparedStatement.setInt(1, roleId);
            LOG.trace("Executing query >>" + preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Role.valueOf(resultSet.getString(Fields.Roles.NAME));
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_ROLE_NAME + roleId, exception);
        }
        return null;
    }

    private User extractUser(ResultSet resultSet) throws DaoException{
        User user = new User();
        try {
            user.setId(resultSet.getInt(Fields.Users.ID));
            user.setName(resultSet.getString(Fields.Users.NAME));
            user.setEmail(resultSet.getString(Fields.Users.EMAIL));
            user.setPassword(resultSet.getString(Fields.Users.PASSWORD));
            user.setBanned(resultSet.getBoolean(Fields.Users.BANNED));
            user.setRole(getRoleName(resultSet.getInt(Fields.Users.ROLE)));
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANNOT_OBTAIN_USER, exception);
        }
        LOG.trace("Obtained user >>" + user);
        return user;
    }
}
