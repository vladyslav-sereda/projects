package ua.nure.sereda.SummaryTask4.service;

import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface OrderService {

    Order makeReadingRoomOrder(int userId, int bookId) throws ServiceException;

    Order makeSubscriptionOrder(int userId, int bookId, LocalDate deadline) throws ServiceException;

    void delete(int id) throws ServiceException;

    void confirmNewOrder(int orderId) throws ServiceException;

    List<Order> getOverdueOrders() throws ServiceException;

    List<Order> getNewOrders() throws ServiceException;

    List<Order> getConfirmedOrders() throws ServiceException;

    List<Order> getOrdersByUser(int userId) throws ServiceException;

    Order getOrderById(int orderId) throws ServiceException;
}
