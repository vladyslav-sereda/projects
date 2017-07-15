package ua.nure.sereda.SummaryTask4.dao;

import ua.nure.sereda.SummaryTask4.exceptions.DaoException;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.models.OrderStatus;

import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface OrderDao extends Dao<Order> {

    List<Order> getOrdersByUser(int userId) throws DaoException;

    List<Order> getOrdersByStatus(OrderStatus status) throws DaoException;

}
