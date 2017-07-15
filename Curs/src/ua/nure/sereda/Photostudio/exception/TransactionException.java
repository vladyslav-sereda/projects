package ua.nure.sereda.Photostudio.exception;

/**
 * Created by sered on 11.05.2017.
 */
public class TransactionException extends Exception {

    private static final long serialVersionUID = -6335754041852949190L;

    public TransactionException(Exception cause) {
        super(cause);
    }

    public TransactionException(Exception cause, String message) {
        super(message, cause);
    }

}

