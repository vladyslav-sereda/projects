package ua.nure.sereda.Photostudio.exception;

import org.apache.log4j.Logger;

/**
 * Created by Vladyslav.
 */
public class WebException extends Exception {

    private static final long serialVersionUID = 7183618813862771037L;
    private static final Logger LOG = Logger.getLogger(WebException.class);

    public WebException(String message) {
        super(message);
        LOG.error(message);
    }

    public WebException() {
        super();
    }
}
