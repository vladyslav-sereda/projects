package ua.nure.sereda.SummaryTask4.dao;

import ua.nure.sereda.SummaryTask4.exceptions.DaoException;

import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface Dao<T> {

    T create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    T getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    void delete(int id) throws DaoException;

}
