package ua.nure.sereda.Photostudio.exception;

import org.apache.log4j.Logger;

/**
 * Created by sered on 11.05.2017.
 */
public class DaoException extends Exception {

    private static final long serialVersionUID = 8221651426804108978L;
    private static final Logger LOG = Logger.getLogger(DaoException.class);

    public DaoException() {
    }

    public DaoException(String message, Exception e){
        super(message);
        LOG.error(message);
    }
}
