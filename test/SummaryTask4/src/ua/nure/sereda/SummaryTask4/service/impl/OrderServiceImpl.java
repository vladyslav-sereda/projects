package ua.nure.sereda.SummaryTask4.service.impl;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.dao.OrderDao;
import ua.nure.sereda.SummaryTask4.db.TransactionManager;
import ua.nure.sereda.SummaryTask4.exceptions.ServiceException;
import ua.nure.sereda.SummaryTask4.exceptions.TransactionException;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.models.OrderStatus;
import ua.nure.sereda.SummaryTask4.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;


/**
 * Created by Vladyslav.
 */
public class OrderServiceImpl implements OrderService {
    private static final BigDecimal PENALTY4DAY = BigDecimal.valueOf(5.5);
    private OrderDao orderDao;
    private TransactionManager transactionManager;
    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(TransactionManager transactionManager, OrderDao orderDao) {
        this.transactionManager = transactionManager;
        this.orderDao = orderDao;
    }

    @Override
    public Order makeReadingRoomOrder(int userId, int bookId) throws ServiceException {
        LOG.debug(String.format("Start with params: user id = %s, book id = %s", userId, bookId));

        Order order = new Order(userId, bookId);
        try {
            return transactionManager.task(() -> orderDao.create(order));
        } catch (TransactionException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public Order makeSubscriptionOrder(int userId, int bookId, LocalDate deadline) throws ServiceException {
        LOG.debug(String.format("Start with params: user id = %s, book id = %s, deadline = %s",
                userId, bookId, deadline));

        Order order = new Order(userId, bookId, deadline);
        try {
            return transactionManager.task(() -> orderDao.create(order));
        } catch (TransactionException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        LOG.debug("Start delete order, id = " + id);

        try {
            transactionManager.task(() -> {
                orderDao.delete(id);
                return null;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void confirmNewOrder(int id) throws ServiceException {
        LOG.debug("Start confirm order, id = " + id);

        try {
            transactionManager.task(() -> {
                Order order = orderDao.getById(id);
                order.setStatus(OrderStatus.CONFIRMED);
                orderDao.update(order);
                return null;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOverdueOrders() throws ServiceException {
        LOG.debug("Start get overdue orders");
        List<Order> orders;
        List<Order> overdueOrders = new ArrayList<>();
        try {
            orders = transactionManager.task(() -> orderDao.getOrdersByStatus(OrderStatus.CONFIRMED));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }

        if (orders != null) {
            for (Order order : orders) {
                if (order.getDeadline().isBefore(LocalDate.now())) {
                    overdueOrders.add(order);
                }
            }
            setPenalty(overdueOrders);
            Collections.sort(overdueOrders);
        }
        return overdueOrders;
    }

    @Override
    public List<Order> getNewOrders() throws ServiceException {
        LOG.debug("Start get new orders");

        try {
            return transactionManager.task(() -> {
                List<Order> orders;
                List<Order> newOrders = new ArrayList<>();
                orders = orderDao.getOrdersByStatus(OrderStatus.ORDERED);
                if (Objects.isNull(orders)) {
                    return newOrders;
                }

                for (Order order : orders) {
                    if (order.getDeadline().isAfter(LocalDate.now())) {
                        newOrders.add(order);
                    } else {
                        orderDao.delete(order.getId());
                    }
                }

                Collections.sort(newOrders);
                return newOrders;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getConfirmedOrders() throws ServiceException {
        LOG.debug("Start get confirmed orders");
        List<Order> orders;

        try {
            orders = transactionManager.task(() -> orderDao.getOrdersByStatus(OrderStatus.CONFIRMED));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }

        setPenalty(orders);
        Collections.sort(orders);
        return orders;
    }

    @Override
    public List<Order> getOrdersByUser(int id) throws ServiceException {
        LOG.debug("Start get by user, id = " + id);
        List<Order> orders;
        try {
            orders = transactionManager.task(() -> orderDao.getOrdersByUser(id));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }

        setPenalty(orders);
        Collections.sort(orders);
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) throws ServiceException {
        LOG.debug("Start get order, id = " + orderId);

        try {
            return transactionManager.task(() -> orderDao.getById(orderId));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    private void setPenalty(List<Order> orders) {
        for (Order order : orders) {
            if (order.getDeadline().isBefore(LocalDate.now())) {
                BigDecimal penalty = PENALTY4DAY.multiply(BigDecimal.valueOf(order.getDeadline().until(LocalDate.now(), DAYS)));
                order.setPenalty(penalty);
            }
        }
    }
}
