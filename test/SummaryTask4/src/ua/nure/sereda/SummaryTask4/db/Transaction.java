package ua.nure.sereda.SummaryTask4.db;


import ua.nure.sereda.SummaryTask4.exceptions.DaoException;

/**
 * Created by Vladyslav.
 */
@FunctionalInterface
public interface Transaction<T> {

    T execute() throws DaoException;

}
