package ua.nure.sereda.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.DaoException;
import ua.nure.sereda.SummaryTask4.exception.ErrorMessages;
import ua.nure.sereda.SummaryTask4.exception.TransactionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vladyslav.
 */
public class TransactionManager {
    private static final Logger LOG = Logger.getLogger(TransactionManager.class);
    private static TransactionManager instance;
    private DataSource dataSource;

    private TransactionManager() throws TransactionException {
        try {
            LOG.debug("Start TransactionManager Init");
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/library");
            LOG.trace("Data source ==> " + dataSource);
        } catch (NamingException ex) {
            throw new TransactionException(ErrorMessages.CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public static synchronized TransactionManager getInstance() {
        if (instance == null) {
            try {
                instance = new TransactionManager();
            } catch (TransactionException e) {
                LOG.error("Cant initialized manager");
            }
        }
        return instance;
    }

    private Connection getConnection() throws TransactionException {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            throw new TransactionException(ErrorMessages.CANNOT_OBTAIN_CONNECTION, ex);
        }
        return connection;
    }

    public <T> T task(Transaction<T> transaction) throws TransactionException {
        Connection conn = null;
        try {
            LOG.debug("Transaction task start");
            conn = getConnection();
            LOG.trace("Connection obtained");
            ConnectionHolder.setConnection(conn);
            LOG.trace("Connection set to connection holder");
            T value = transaction.execute();
            LOG.debug("Transaction execute finished");
            conn.commit();
            LOG.debug("Transaction committed");
            return value;
        } catch (SQLException | DaoException exception) {
            LOG.debug("Trying to roll back...");
            tryToRollback(conn);
            throw new TransactionException(ErrorMessages.CANNOT_DO_TASK, exception);
        } finally {
            LOG.debug("Trying to close connection...");
            closeConnection(conn);
        }
    }

    private void tryToRollback(Connection conn) throws TransactionException {
        if (conn != null) {
            try {
                conn.rollback();
                LOG.error("Transaction rolled back");
            } catch (SQLException exception) {
                throw new TransactionException(ErrorMessages.CANNOT_ROLL_BACK, exception);
            }
        }
    }

    private void closeConnection(Connection conn) throws TransactionException {
        ConnectionHolder.setConnection(null);
        try {
            LOG.debug(conn);
            conn.close();
            LOG.trace("Connection closed");
        } catch (SQLException exception) {
            throw new TransactionException(ErrorMessages.CANNOT_CLOSE_CONNECTION, exception);
        }
    }
}
