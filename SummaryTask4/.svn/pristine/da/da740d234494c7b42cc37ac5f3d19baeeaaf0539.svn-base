package ua.nure.sereda.SummaryTask4.web.command;

import ua.nure.sereda.SummaryTask4.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by Vladyslav.
 */
public abstract class Command implements Serializable {

    private static final long serialVersionUID = 3505824786527976123L;

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
