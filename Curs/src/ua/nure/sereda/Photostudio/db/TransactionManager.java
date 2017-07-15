package ua.nure.sereda.Photostudio.db;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.exception.DaoException;
import ua.nure.sereda.Photostudio.exception.TransactionException;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vladyslav.
 */
public class TransactionManager {
 //   private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/Photostudio?user=root&password=root&useSSL=false";
    private static final Logger LOG = Logger.getLogger(TransactionManager.class);
    private static PooledConnection pooledConnection;

    public TransactionManager() {
        try {
            LOG.debug("Transaction task start");
            getDriver();
            LOG.trace("MySQL JDBC Driver Registered");
            MysqlConnectionPoolDataSource connPoolDS = new MysqlConnectionPoolDataSource();
            connPoolDS.setDatabaseName("photostudio");
            connPoolDS.setUser("root");
            connPoolDS.setPassword("root");
            connPoolDS.setURL("jdbc:mysql://localhost:3306/photostudio");
            connPoolDS.setPort(3306);
            System.out.println("Trying to get DB-Connection...");
            pooledConnection = connPoolDS.getPooledConnection();
            System.out.println(pooledConnection.getConnection());
            ;
        } catch (SQLException | TransactionException exception) {
            System.out.println("Errors occurred during getting connection ==>" + exception);
        }
    }

    public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
        Connection connection = null;
        try {
            connection = pooledConnection.getConnection();
            LOG.trace("Connection obtained");
            connection.setAutoCommit(false);
            LOG.trace("Connection auto commit set to 'false'");
            ConnectionHolder.setConnection(connection);
            LOG.trace("Connection setted to connection holder");
            connection.setTransactionIsolation(transactionIsolation);
            LOG.trace("Transaction Isolation set >> " + transactionIsolation);
            LOG.debug("Transaction execute started");
            T value = transaction.execute();
            LOG.debug("Transaction execute finished");
            connection.commit();
            LOG.debug("Transaction committed");
            return value;
        } catch (SQLException | DaoException exception) {
            LOG.debug("Trying to roll back...");
            tryToRollback(connection);
            throw new TransactionException(exception, "Errors occurred during handling transaction");
        } finally {
            LOG.debug("Trying to close connection...");
            tryToCloseConnection(connection);
        }
    }

    public <T> void doVoidTask(VoidTransaction<T> transaction, int transactionIsolation) throws TransactionException {
        Connection connection = null;
        try {
            connection = pooledConnection.getConnection();
            LOG.trace("Connection obtained");
            connection.setAutoCommit(false);
            LOG.trace("Connection auto commit set to 'false'");
            ConnectionHolder.setConnection(connection);
            LOG.trace("Connection setted to connection holder");
            connection.setTransactionIsolation(transactionIsolation);
            LOG.trace("Transaction Isolation set >> " + transactionIsolation);
            LOG.debug("Transaction execute started");
            transaction.executeVoid();
            LOG.debug("Transaction execute finished");
            connection.commit();
            LOG.debug("Transaction committed");
        } catch (SQLException | DaoException exception) {
            LOG.debug("Trying to roll back...");
            tryToRollback(connection);
            throw new TransactionException(exception, "Errors occurred during handling transaction");
        } finally {
            LOG.debug("Trying to close connection...");
            tryToCloseConnection(connection);
        }
    }

    private void getDriver() throws TransactionException {
        try {
            LOG.debug("Trying get JDBC Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("Didn't find JDBC Driver");
            throw new TransactionException(e, "Didn't find JDBC Driver");
        }
    }

    private void tryToRollback(Connection connection) throws TransactionException {
        if (connection != null) {
            try {
                connection.rollback();
                LOG.error("Transaction rolled back");
            } catch (SQLException e) {
                LOG.error("Error during roll back");
                throw new TransactionException(e);
            }
        }
    }

    private void tryToCloseConnection(Connection connection) throws TransactionException {
        ConnectionHolder.setConnection(null);
        try {
            LOG.debug(connection);
            connection.close();
            LOG.trace("Connection closed");
        } catch (SQLException e) {
            LOG.error("Error while closing the connection", e);
            throw new TransactionException(e);
        }
    }
}
