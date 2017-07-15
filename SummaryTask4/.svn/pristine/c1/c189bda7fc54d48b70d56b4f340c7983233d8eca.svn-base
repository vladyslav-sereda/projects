package ua.nure.sereda.SummaryTask4.web.websocket;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.dao.BookDao;
import ua.nure.sereda.SummaryTask4.dao.OrderDao;
import ua.nure.sereda.SummaryTask4.dao.UserDao;
import ua.nure.sereda.SummaryTask4.dao.impl.BookDaoImpl;
import ua.nure.sereda.SummaryTask4.dao.impl.OrderDaoImpl;
import ua.nure.sereda.SummaryTask4.dao.impl.UserDaoImpl;
import ua.nure.sereda.SummaryTask4.db.TransactionManager;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Book;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.models.User;
import ua.nure.sereda.SummaryTask4.service.BookService;
import ua.nure.sereda.SummaryTask4.service.OrderService;
import ua.nure.sereda.SummaryTask4.service.UserService;
import ua.nure.sereda.SummaryTask4.service.impl.BookServiceImpl;
import ua.nure.sereda.SummaryTask4.service.impl.OrderServiceImpl;
import ua.nure.sereda.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.sereda.SummaryTask4.utils.Validator;

import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class OrderEvent extends SocketEvent {
    private static final Logger LOG = Logger.getLogger(OrderEvent.class);
    private TransactionManager manager = TransactionManager.getInstance();
    private OrderDao orderDao = new OrderDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private OrderService orderService = new OrderServiceImpl(manager, orderDao);
    private BookService bookService = new BookServiceImpl(manager, bookDao);
    private UserService userService = new UserServiceImpl(manager, userDao);

    /**
     * @param jsObj - obtained data from client
     * @throws AppException Method invokes orders function with all needed params,
     *                      and sends processed data to librarian in right format.
     */
    public void execute(JsonObject jsObj, Set<Session> peers, Session session) throws AppException {

        LOG.debug("Start, obtained JSON >> " + jsObj);
        User user = extractUser(jsObj);
        Order order = extractOrder(jsObj);
        if (order != null) {
            for (Session peer : peers)
                try {
                    LOG.debug("Sending answer to client");
                    peer.getBasicRemote().sendText(createAnswer("order", order, user));
                } catch (IOException exception) {
                    throw new AppException(exception.getMessage());
                }
        } else {
            LOG.debug("Sending answer to client - book not available");
            session.getAsyncRemote().sendText(createAnswer("notAvailable", null));
        }
    }

    private Order extractOrder(JsonObject jsObj) throws AppException {
        LOG.debug("Getting order java-object from JSON");

        Order order = null;
        LocalDate deadline = null;
        boolean readingRoom;
        int userId = jsObj.get("userId").getAsInt();
        int bookId = jsObj.get("bookId").getAsInt();

        readingRoom = jsObj.get("readingRoom").getAsBoolean();
        if (!readingRoom) {
            deadline = LocalDate.parse(jsObj.get("deadline").getAsString());
        }

        try {
            Book book = bookService.getById(bookId);
            if (book.getAvailable() < 1) {
                LOG.debug("book not available");
                return null;
            } else if (readingRoom) {
                book.setAvailable(book.getAvailable() - 1);
                bookService.update(book);
                order = orderService.makeReadingRoomOrder(userId, bookId);
            } else if (Validator.Deadline(deadline)) {
                book.setAvailable(book.getAvailable() - 1);
                bookService.update(book);
                order = orderService.makeSubscriptionOrder(userId, bookId, deadline);

            }
        } catch (ServiceException e) {
            throw new AppException("Can't make order", e);
        }
        LOG.debug("obtained order = " + order);
        return order;
    }

    private User extractUser(JsonObject jsObj) throws AppException {
        LOG.debug("Getting order java-object from JSON");
        User user;
        int userId = jsObj.get("userId").getAsInt();

        try {
            user = userService.getById(userId);
        } catch (ServiceException e) {
            throw new AppException("Can't get user", e);
        }
        LOG.debug("obtained user = " + user);
        return user;
    }
}
