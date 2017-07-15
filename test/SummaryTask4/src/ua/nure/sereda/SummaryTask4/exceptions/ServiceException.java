package ua.nure.sereda.SummaryTask4.exceptions;

import org.apache.log4j.Logger;

/**
 * Created by Vladyslav.
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -4276155708943124440L;
	private static final Logger LOG = Logger.getLogger(ServiceException.class);

	public ServiceException(Throwable cause) {
		super(cause);
		LOG.error(cause.getMessage());
	}
}
