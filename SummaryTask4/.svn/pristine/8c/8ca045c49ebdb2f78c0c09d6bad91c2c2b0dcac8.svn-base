package ua.nure.sereda.SummaryTask4.exception;

import org.apache.log4j.Logger;

/**
 * Created by Vladyslav.
 */
public class TransactionException extends Exception {
    private static final long serialVersionUID = -4066274051379529432L;
    private static final Logger LOG = Logger.getLogger(TransactionException.class);


    public TransactionException(String message, Throwable cause) {
        super(message, cause);
        LOG.error(message, cause);
    }
}
