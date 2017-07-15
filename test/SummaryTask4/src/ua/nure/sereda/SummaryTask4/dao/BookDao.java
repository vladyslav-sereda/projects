package ua.nure.sereda.SummaryTask4.dao;

import ua.nure.sereda.SummaryTask4.exceptions.DaoException;
import ua.nure.sereda.SummaryTask4.models.Book;

import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface BookDao extends Dao<Book> {

    Book getBookByName(String name) throws DaoException;

    Book getBookByAuthor(String author) throws DaoException;

    List<Book> getAvailable() throws DaoException;
}
