package ua.nure.sereda.Photostudio.dao;

import ua.nure.sereda.Photostudio.exception.DaoException;

import java.util.List;

/**
 * Created by sered on 11.05.2017.
 */
public interface Dao<T> {

    T create (T entity) throws DaoException;

    void update(T entity) throws DaoException;

    T getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    void remove (int id) throws DaoException;
}
