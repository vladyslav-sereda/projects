package ua.nure.sereda.Photostudio.exception;

import org.apache.log4j.Logger;

/**
 * Created by sered on 12.05.2017.
 */
public class ServiceException extends Exception {

    private static final Logger LOG = Logger.getLogger(ServiceException.class);

    private static final long serialVersionUID = -5536907529147815944L;

    public ServiceException(Throwable e) {
        super(e);
        LOG.error(e.getMessage());
    }
}
