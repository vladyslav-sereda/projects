package ua.nure.sereda.Photostudio.dao.classes;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.dao.UserDao;
import ua.nure.sereda.Photostudio.db.ConnectionHolder;
import ua.nure.sereda.Photostudio.db.Fields;
import ua.nure.sereda.Photostudio.exception.DaoException;
import ua.nure.sereda.Photostudio.exception.ErrorMessages;
import ua.nure.sereda.Photostudio.models.Role;
import ua.nure.sereda.Photostudio.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sered on 11.05.2017.
 */
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private static final String USER_CREATE = "INSERT INTO users VALUES(DEFAULT,?,?,?,?,DEFAULT)";
    private static final String USER_UPDATE = "UPDATE users SET email=?, name=?, phone_number=?, " +
            "password=?, role=? WHERE id_user=?";
    private static final String USER_DELETE = "DELETE FROM users WHERE id_user=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id_user=?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?;";


    @Override
    public User create(User user) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = connection.prepareStatement(USER_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k++, user.getName());
            preparedStatement.setString(k++, user.getPhone());
            preparedStatement.setString(k, user.getPassword());

            LOG.trace("Executing query >>" + preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new DaoException(ErrorMessages.CANT_CREATE_USER, e);
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(USER_UPDATE);
            int k = 1;
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k++, user.getName());
            preparedStatement.setString(k++, user.getPhone());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setString(k++, user.getRole().toString());
            preparedStatement.setInt(k, user.getId());

            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_UPDATE_USER, e);
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(USER_DELETE);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_DELETE_USER, e);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        List<User> users = null;
        try {
            users = new ArrayList<User>();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(GET_ALL_USERS);
            LOG.trace("Executing query >>" + stmt);

            while (rs.next()) {
                users.add(extractUser(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_ALL_USERS, e);
        }
        return users;
    }

    @Override
    public User getById(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_USER_BY_ID + id, e);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        User user = null;
        try {
            pstmt = connection.prepareStatement(GET_USER_BY_EMAIL);
            pstmt.setString(1, email);
            LOG.trace("Executing query >>" + pstmt);

            rs = pstmt.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                user = extractUser(rs);
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_USER_BY_EMAIL + email, e);
        }
        return user;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Fields.Users.ID));
        user.setName(resultSet.getString(Fields.Users.NAME));
        user.setEmail(resultSet.getString(Fields.Users.EMAIL));
        user.setPassword(resultSet.getString(Fields.Users.PASSWORD));
        user.setPhone(resultSet.getString(Fields.Users.PHONE));
        user.setRole(Role.valueOf(resultSet.getString(Fields.Users.ROLE)));
        LOG.trace("Obtained user >>" + user);

        return user;
    }
}
