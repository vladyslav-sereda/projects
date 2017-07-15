package ua.nure.sereda.Photostudio.web.websocet;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

/**
 * Created by Vladyslav.
 */
abstract class SocketEvent {
    private static final Logger LOG = Logger.getLogger(SocketEvent.class);

    String createAnswer(String type, Object data) {
        LOG.debug("Converting data to JSON format");
        Gson gson = new Gson();
        return gson.toJson(new ResultObject(type, data));
    }
    /**
     *         Inner class for creating answer-object for converting to JSON
     *         format
     */
    public class ResultObject {
        String type;
        Object data;

        ResultObject(String type, Object data) {
            this.type = type;
            this.data = data;
        }
    }
}
