package ua.nure.sereda.Photostudio.web.websocet;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.dao.WorkDayDao;
import ua.nure.sereda.Photostudio.dao.classes.WorkDayDaoImpl;
import ua.nure.sereda.Photostudio.db.TransactionManager;
import ua.nure.sereda.Photostudio.exception.ServiceException;
import ua.nure.sereda.Photostudio.exception.WebException;
import ua.nure.sereda.Photostudio.models.WorkDay;
import ua.nure.sereda.Photostudio.service.WorkDayService;
import ua.nure.sereda.Photostudio.service.classes.WorkDayServiceImpl;
import ua.nure.sereda.Photostudio.utils.validation.Validator;

import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Vladyslav.
 */
public class WorkDayEvent extends SocketEvent{
    private TransactionManager manager = new TransactionManager();
    private WorkDayDao workDayDao = new WorkDayDaoImpl();
    private WorkDayService workDayService = new WorkDayServiceImpl(manager, workDayDao);
    private static final Logger LOG = Logger.getLogger(WorkDayEvent.class);

    /**
     * Method invokes adding new workday function with all needed params, and sends processed data to client in right format.
     */
    public void execute(JsonObject jsObj, Set<Session> peers) throws WebException {
        LOG.debug("Start, obtained JSON >> " + jsObj);
        WorkDay workDay = extractWorkDay(jsObj);
        if (workDay != null) {
            for (Session peer : peers)
                try {
                    LOG.debug("Sending answer to client");
                    peer.getBasicRemote().sendText(createAnswer("workday", workDay));
                } catch (IOException e) {
                    throw new WebException(e.getMessage());
                }
        }
    }

    private WorkDay extractWorkDay(JsonObject jsObj) throws WebException {
        LOG.debug("Getting application java-object from JSON");

        WorkDay workDay = null;
        LocalDate date = LocalDate.parse(jsObj.get("date").getAsString());
        LocalTime startDay = LocalTime.parse(jsObj.get("startTime").getAsString());
        LocalTime endDay = LocalTime.parse(jsObj.get("endTime").getAsString());
        try {
            if (Validator.dateAfterToday(date)
                    || Validator.time(startDay, endDay)
                    || workDayService.availableWorkDay(date)) {
                workDay = workDayService.addDay(date, startDay, endDay);
            }
        } catch (ServiceException e) {
            throw new WebException(e.getMessage());
        }

        LOG.debug("Obtained object >> " + workDay);
        return workDay;
    }
}
