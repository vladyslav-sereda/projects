package ua.nure.sereda.SummaryTask4.web.websocket;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exceptions.AppException;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class manages web-socket commands. Handles open, close, error and
 * message events
 */
@ServerEndpoint("/webSocketHandler")
public class WebSocketHandler {
    private static final Logger LOG = Logger.getLogger(WebSocketHandler.class);
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * @param message
     * @param session
     * @throws AppException Method obtains income type of needed action and invokes
     *                      handler for it.
     */
    @OnMessage
    public void onMessage(String message, Session session) throws AppException {
        LOG.debug("Message obtained from client : " + message);
        LOG.trace("Parsing JSON message ...");
        JsonElement jsElem = new JsonParser().parse(message);
        JsonObject jsObj = jsElem.getAsJsonObject();
        LOG.trace("Result >> " + jsObj);
        String type = jsObj.get("type").getAsString();
        switch (type) {
            case "order": {
                LOG.debug("Invoke order handler");
                new OrderEvent().execute(jsObj, peers, session);
                break;
            }
            case "search": {
                LOG.debug("Invoke search handler");

                new SearchEvent().execute(jsObj, session);
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
        LOG.error("Error occurred >> " + thr.getMessage());
    }

}
