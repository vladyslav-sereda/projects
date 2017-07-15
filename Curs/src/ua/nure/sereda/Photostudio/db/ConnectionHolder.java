package ua.nure.sereda.Photostudio.db;

import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * Created by Vladyslav.
 */
public class ConnectionHolder {
    private static final Logger log = Logger.getLogger(ConnectionHolder.class);
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static void setConnection(Connection connection) {
        connectionHolder.set(connection);
        log.trace("Setting connection sated");
    }

    public static Connection getConnection() {
        log.trace("Getting connection...");
        return connectionHolder.get();
    }
}
