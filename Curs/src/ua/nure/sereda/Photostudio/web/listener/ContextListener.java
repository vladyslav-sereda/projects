package ua.nure.sereda.Photostudio.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.sereda.Photostudio.dao.ReservationDao;
import ua.nure.sereda.Photostudio.dao.UserDao;
import ua.nure.sereda.Photostudio.dao.WorkDayDao;
import ua.nure.sereda.Photostudio.dao.classes.ReservationDaoImpl;
import ua.nure.sereda.Photostudio.dao.classes.UserDaoImpl;
import ua.nure.sereda.Photostudio.dao.classes.WorkDayDaoImpl;
import ua.nure.sereda.Photostudio.db.TransactionManager;
import ua.nure.sereda.Photostudio.service.ReservationService;
import ua.nure.sereda.Photostudio.service.UserService;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.service.classes.ReservationServiceImpl;
import ua.nure.sereda.Photostudio.service.classes.UserServiceImpl;
import ua.nure.sereda.Photostudio.service.classes.WorkDayServiceImpl;
import ua.nure.sereda.Photostudio.web.Services;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Vladyslav.
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log("Servlet context initialization starts");
        initCommandContainer();
        ServletContext servletContext = servletContextEvent.getServletContext();
        initService(servletContext);
        initLog4J(servletContext);
        try {
            initLocale(servletContext);
        } catch (IOException e) {
            LOG.error("Can`t initialize locale", e);
        }

        log("Servlet context initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("Servlet context destruction starts");
        scheduler.shutdownNow();
        log("Servlet context destruction finished");

    }

    /**
     * Initializes services for dependency injection.
     */
    private void initService(ServletContext servletContext) {
        log("Start initService");
        TransactionManager manager = new TransactionManager();
        ReservationDao reservationDao = new ReservationDaoImpl();
        UserDao userDao = new UserDaoImpl();
        WorkDayDao workDayDao = new WorkDayDaoImpl();

        ReservationService reservationService = new ReservationServiceImpl(reservationDao, manager);
        UserService userService = new UserServiceImpl(manager, userDao);
        WorkDayService workDayService = new WorkDayServiceImpl(manager, workDayDao);

        servletContext.setAttribute(Services.USER, userService);
        servletContext.setAttribute(Services.RESERVATION, reservationService);
        servletContext.setAttribute(Services.WORKDAY, workDayService);
    }

    /**
     * Initializes log4j framework.
     */
    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("/WEB-INF/log4j.properties"));
        } catch (Exception ex) {
            LOG.error("Cannot configure Log4j", ex);
        }
        log("Log4J initialization finished");
        LOG.debug("Log4j has been initialized");
    }

    /**
     * Initializes Locale.
     */
    private void initLocale(ServletContext servletContext) throws IOException {
        log("locales initialization started");
        String localesFileName = servletContext.getInitParameter("locales");

        String localesFileRealPath = servletContext.getRealPath(localesFileName);
        Properties locales = new Properties();

        try (FileInputStream fis = new FileInputStream(localesFileRealPath)) {
            locales.load(fis);
        } catch (IOException e) {
            LOG.error("Can`t load locales", e);
        }
        servletContext.setAttribute("locales", locales);
        locales.list(System.out);
        log("locales initialization finished");
        LOG.debug("locales has been initialized");
    }

    /**
     * Initializes CommandContainer.
     */
    private void initCommandContainer() {
        log("CommandContainer initialization started");

        try {
            Class.forName("ua.nure.sereda.Photostudio.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
        log("CommandContainer initialization finished");
        LOG.debug("CommandContainer has been initialized");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}
