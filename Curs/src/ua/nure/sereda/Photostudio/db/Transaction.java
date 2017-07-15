package ua.nure.sereda.Photostudio.db;

import ua.nure.sereda.Photostudio.exception.DaoException;

/**
 * Created by sered on 11.05.2017.
 */
@FunctionalInterface
public interface Transaction<T> {

    T execute() throws DaoException;

}
