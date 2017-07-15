package ua.nure.sereda.Photostudio.web.websocet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.WebException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vladyslav.
 */
@ServerEndpoint("/webSocketHandler")
public class WebSocketHandler {
    private static final Logger LOG = Logger.getLogger(WebSocketHandler.class);
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Method obtains income type of needed action and invokes
     * handler for it.
     */
    @OnMessage
    public void onMessage(String message, Session session) throws WebException {
        LOG.debug("Message obtained from client : " + message);
        LOG.trace("Parsing JSON message ...");
        JsonElement jsElem = new JsonParser().parse(message);
        JsonObject jsObj = jsElem.getAsJsonObject();
        LOG.trace("Result >> " + jsObj);
        String type = jsObj.get("type").getAsString();
        switch (type) {
            case "workday": {
                LOG.debug("Invoke application handler");

                new WorkDayEvent().execute(jsObj, peers);
                break;
            }
            case "reservation": {
                LOG.debug("Invoke reservation handler");

                new ReservationEvent().execute(jsObj, peers);
                break;
            }
        }
    }

    /**
     * @param peer Method adds client's session to peers list when new connection
     *             opens
     */
    @OnOpen
    public void onOpen(Session peer) {
        LOG.debug("New connection was opened");

        peers.add(peer);
    }

    /**
     * @param peer Method removes client's session from peers list when one of
     *             connections closes
     */
    @OnClose
    public void onClose(Session peer) {
        LOG.debug("One connection was closed");

        peers.remove(peer);
    }

    @OnError
    public void onError(Session session, Throwable thr) {
        LOG.error("Error ocured >> " + thr.getMessage());
    }
}

