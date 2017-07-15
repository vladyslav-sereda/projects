package ua.nure.sereda.SummaryTask4.db;

import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * Created by Vladyslav.
 */
public class ConnectionHolder {
    private static final Logger log = Logger.getLogger(ConnectionHolder.class);
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    static void setConnection(Connection conn) {
        log.trace("Setting connection");
        connectionHolder.set(conn);
    }

    public static Connection getConnection() {
        log.trace("Getting connection...");
        return connectionHolder.get();
    }
}
