package ua.nure.sereda.SummaryTask4.exception;

import org.apache.log4j.Logger;

/**
 * Created by Vladyslav.
 */
public class AppException extends Exception {

    private static final long serialVersionUID = -6600265103017255506L;
    private static final Logger LOG = Logger.getLogger(AppException.class);


    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        LOG.error(message, cause);
    }
}
