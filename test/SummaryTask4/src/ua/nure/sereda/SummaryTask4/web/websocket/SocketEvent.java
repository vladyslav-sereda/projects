package ua.nure.sereda.SummaryTask4.web.websocket;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.models.User;

/**
 * Parent class for all web-socket event handlers
 */
abstract class SocketEvent {
    private static final Logger LOG = Logger.getLogger(SocketEvent.class);

    String createAnswer(String type, Object data) {
        LOG.debug("Converting data to JSON format");
        Gson gson = new Gson();
        return gson.toJson(new ResultObject(type, data));
    }

    String createAnswer(String type, Object order, User user) {
        LOG.debug("Converting data to JSON format");
        Gson gson = new Gson();
        return gson.toJson(new ResultObject(type, order, user));
    }
    /**
     * Inner class for creating answer-object for converting to JSON
     * format
     */

    public class ResultObject {
        String type;
        Object data;
        User user;

        ResultObject(String type, Object data) {
            this.type = type;
            this.data = data;
        }

        ResultObject(String type, Object data, User user) {
            this.type = type;
            this.data = data;
            this.user = user;
        }
    }
}
