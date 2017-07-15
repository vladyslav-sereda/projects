package ua.nure.sereda.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.dao.OrderDao;
import ua.nure.sereda.SummaryTask4.db.ConnectionHolder;
import ua.nure.sereda.SummaryTask4.db.Fields;
import ua.nure.sereda.SummaryTask4.exception.DaoException;
import ua.nure.sereda.SummaryTask4.exception.ErrorMessages;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.models.OrderStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);

    private static final String ORDER_CREATE = "INSERT INTO orders VALUES(DEFAULT,?,?,DEFAULT,?,?)";
    private static final String ORDER_UPDATE =
            "UPDATE orders SET user_id=?, book_id=?, status=?, reading_room=?, deadline=? WHERE id=?";
    private static final String ORDER_DELETE = "DELETE FROM orders WHERE id=?";
    private static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    private static final String GET_ORDERS_BY_ID = "SELECT * FROM orders WHERE id =?";
    private static final String GET_ORDERS_BY_USER = "SELECT * FROM orders WHERE user_id=?";
    private static final String GET_ORDERS_BY_STATUS = "SELECT * FROM orders WHERE status=?";


    @Override
    public Order create(Order order) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        System.out.println(order.toString());
        try {
            preparedStatement = connection.prepareStatement(ORDER_CREATE, Statement.RETURN_GENERATED_KEYS);
            int k = 1;

            preparedStatement.setInt(k++, order.getUserId());
            preparedStatement.setInt(k++, order.getBookId());
            preparedStatement.setBoolean(k++, order.isReadingRoom());
            preparedStatement.setDate(k, Date.valueOf(order.getDeadline()));
            LOG.trace("Executing query >>" + preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    order.setId(resultSet.getInt(1));
                    order.setStatus(OrderStatus.ORDERED);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_CREATE_ORDER, exception);
        }
        return order;
    }

    @Override
    public void update(Order order) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(ORDER_UPDATE);
            int k = 1;
            preparedStatement.setInt(k++, order.getUserId());
            preparedStatement.setInt(k++, order.getBookId());
            preparedStatement.setString(k++, order.getStatus().toString());
            preparedStatement.setBoolean(k++, order.isReadingRoom());
            preparedStatement.setDate(k++, Date.valueOf(order.getDeadline()));
            preparedStatement.setInt(k, order.getId());
            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_UPDATE_ORDER, e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ORDER_DELETE);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_DELETE_ORDER, exception);
        }
    }

    @Override
    public List<Order> getAll() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement statement;
        ResultSet resultSet;
        List<Order> orders;
        try {
            orders = new ArrayList<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_ORDERS);
            LOG.trace("Executing query >>" + statement);

            while (resultSet.next()) {
                orders.add(extractOrder(resultSet));
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_ALL_ORDERS, exception);
        }
        return orders;
    }

    @Override
    public Order getById(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        Order order = null;
        try {
            preparedStatement = connection.prepareStatement(GET_ORDERS_BY_ID);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                order = extractOrder(rs);
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_ORDER_BY_ID + id, exception);
        }
        return order;
    }


    @Override
    public List<Order> getOrdersByUser(int userId) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        List<Order> orders;
        try {
            orders = new ArrayList<>();
            preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER);
            preparedStatement.setInt(1, userId);
            LOG.trace("Executing query >>" + preparedStatement);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_ORDER_BY_USER + userId, exception);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet rs;
        List<Order> orders;
        try {
            orders = new ArrayList<>();
            preparedStatement = connection.prepareStatement(GET_ORDERS_BY_STATUS);
            preparedStatement.setString(1, status.toString());
            LOG.trace("Executing query >>" + preparedStatement);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorMessages.CANT_GET_ORDER_BY_STATUS + status, e);
        }
        return orders;
    }

    private Order extractOrder(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        try {
            order.setId(resultSet.getInt(Fields.Orders.ID));
            order.setUserId(resultSet.getInt(Fields.Orders.USER_ID));
            order.setBookId(resultSet.getInt(Fields.Orders.BOOK_ID));
            order.setStatus(OrderStatus.valueOf(resultSet.getString(Fields.Orders.STATUS)));
            order.setReadingRoom(resultSet.getBoolean(Fields.Orders.READING_ROOM));
            order.setDeadline(resultSet.getDate(Fields.Orders.DEADLINE).toLocalDate());
            order.setPenalty(BigDecimal.ZERO);
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANNOT_OBTAIN_ORDER, exception);
        }
        LOG.trace("Obtained order >>" + order);
        return order;
    }
}
