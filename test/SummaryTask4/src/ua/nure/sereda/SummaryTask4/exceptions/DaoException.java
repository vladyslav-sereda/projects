package ua.nure.sereda.SummaryTask4.exceptions;

import org.apache.log4j.Logger;

/**
 * Created by Vladyslav.
 */
public class DaoException extends Exception {

    private static final long serialVersionUID = 2488992506194915673L;
    private static final Logger LOG = Logger.getLogger(DaoException.class);


    public DaoException(String message, Throwable cause) {
        super(message, cause);
        LOG.error(message, cause);
    }

}
