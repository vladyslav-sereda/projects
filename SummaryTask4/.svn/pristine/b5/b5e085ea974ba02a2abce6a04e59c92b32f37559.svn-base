package ua.nure.sereda.SummaryTask4.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.sereda.SummaryTask4.dao.BookDao;
import ua.nure.sereda.SummaryTask4.dao.OrderDao;
import ua.nure.sereda.SummaryTask4.dao.UserDao;
import ua.nure.sereda.SummaryTask4.dao.impl.BookDaoImpl;
import ua.nure.sereda.SummaryTask4.dao.impl.OrderDaoImpl;
import ua.nure.sereda.SummaryTask4.dao.impl.UserDaoImpl;
import ua.nure.sereda.SummaryTask4.db.TransactionManager;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.service.BookService;
import ua.nure.sereda.SummaryTask4.service.OrderService;
import ua.nure.sereda.SummaryTask4.service.UserService;
import ua.nure.sereda.SummaryTask4.service.impl.BookServiceImpl;
import ua.nure.sereda.SummaryTask4.service.impl.OrderServiceImpl;
import ua.nure.sereda.SummaryTask4.service.impl.UserServiceImpl;
import ua.nure.sereda.SummaryTask4.web.Services;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.DAYS;


/**
 * Created by Vladyslav.
 * <p>
 * Application Lifecycle Listener
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);
    private ScheduledExecutorService scheduler;

    public void contextDestroyed(ServletContextEvent event) {
        localLog("Servlet context destruction starts");
        scheduler.shutdownNow();
        localLog("Servlet context destruction finished");
    }

    public void contextInitialized(ServletContextEvent event) {
        localLog("Servlet context initialisation starts");
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Kiev"));
        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        checkOverdueOrders();
        initCommandContainer();
        initService(servletContext);
        initLocale(servletContext);

        localLog("Servlet context initialisation finished");
    }

    /**
     * Initializes services for dependency injection.
     *
     * @param servletContext
     */
    private void initService(ServletContext servletContext) {
        TransactionManager transactionManager = TransactionManager.getInstance();
        OrderDao orderDao = new OrderDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        UserDao userDao = new UserDaoImpl();

        OrderService orderService = new OrderServiceImpl(transactionManager, orderDao);
        BookService bookService = new BookServiceImpl(transactionManager, bookDao);
        UserService userService = new UserServiceImpl(transactionManager, userDao);

        servletContext.setAttribute(Services.ORDER, orderService);
        servletContext.setAttribute(Services.BOOK, bookService);
        servletContext.setAttribute(Services.USER, userService);
    }

    /**
     * Detects overdue not confirmed orders.
     */
    private void checkOverdueOrders() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            TransactionManager manager = TransactionManager.getInstance();
            OrderDao orderDao = new OrderDaoImpl();
            OrderService orderService = new OrderServiceImpl(manager, orderDao);
            List<Order> orders;
            try {
                orders = orderService.getNewOrders();
                for (Order order : orders) {
                    if (order.getDeadline().isBefore(LocalDate.now())) {
                        orderService.delete(order.getId());
                    }
                }
            } catch (ServiceException exception) {
                LOG.error("Can`t get not confirmed orders or delete overdue order", exception);
            }
        }, 0, 1, DAYS);
    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
        localLog("Log4J initialisation started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("/WEB-INF/log4j.properties"));
        } catch (Exception ex) {
            LOG.error("Cannot configure Log4j", ex);
        }
        localLog("Log4J initialisation finished");
        LOG.debug("Log4j has been initialised");
    }

    /**
     * Initializes CommandContainer.
     */
    private void initCommandContainer() {
        localLog("CommandContainer initialisation started");

        try {
            Class.forName("ua.nure.sereda.SummaryTask4.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialise Command Container");
        }
        localLog("CommandContainer initialisation finished");
        LOG.debug("CommandContainer has been initialised");
    }

    /**
     * Initializes Locale.
     *
     * @param servletContext
     */
    private void initLocale(ServletContext servletContext) {
        localLog("locales initialisation started");
        String localesFileName = servletContext.getInitParameter("locales");
        String localesFileRealPath = servletContext.getRealPath(localesFileName);
        Properties locales = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(localesFileRealPath)) {
            locales.load(fileInputStream);
        } catch (IOException e) {
            LOG.error("Can`t load locales", e);
        }
        servletContext.setAttribute("locales", locales);
        locales.list(System.out);
        localLog("locales initialisation finished");
        LOG.debug("locales has been initialised");
    }

    private void localLog(String massage) {
        System.out.println("[ContextListener] " + massage);
    }
}
