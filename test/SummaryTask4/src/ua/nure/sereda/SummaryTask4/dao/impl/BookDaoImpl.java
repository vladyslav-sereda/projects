package ua.nure.sereda.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.dao.BookDao;
import ua.nure.sereda.SummaryTask4.db.ConnectionHolder;
import ua.nure.sereda.SummaryTask4.db.Fields;
import ua.nure.sereda.SummaryTask4.exceptions.DaoException;
import ua.nure.sereda.SummaryTask4.exceptions.ErrorMessages;
import ua.nure.sereda.SummaryTask4.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class BookDaoImpl implements BookDao {
    private static final Logger LOG = Logger.getLogger(BookDaoImpl.class);

    private static final String CREATE_BOOK = "INSERT INTO books VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOK = "UPDATE books SET  " +
            "name=?, author=?, publisher=?, publication_date=?, available=? WHERE id=?";
    private static final String DELETE_BOOK = "DELETE FROM books WHERE id=?";
    private static final String GET_ALL_BOOKS = "SELECT * FROM books";
    private static final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE id=?";
    private static final String GET_BOOK_BY_NAME = "SELECT * FROM books WHERE name=?" ;
    private static final String GET_BOOK_BY_AUTHOR= "SELECT * FROM books WHERE author=?" ;
    private static final String GET_AVAILABLE_BOOKS = "SELECT * FROM books WHERE available>0" ;


    @Override
    public Book create(Book book) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            preparedStatement.setString(k++, book.getName());
            preparedStatement.setString(k++, book.getAuthor());
            preparedStatement.setString(k++, book.getPublisher());
            preparedStatement.setDate(k++, Date.valueOf(book.getPublicationDate()));
            preparedStatement.setInt(k, book.getAvailable());

            LOG.trace("Executing query >>" + preparedStatement);

            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    book.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_CREATE_BOOK, exception);
        }
        return book;
    }

    @Override
    public void update(Book book) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_BOOK);
            int k = 1;
            preparedStatement.setString(k++, book.getName());
            preparedStatement.setString(k++, book.getAuthor());
            preparedStatement.setString(k++, book.getPublisher());
            preparedStatement.setDate(k++, Date.valueOf(book.getPublicationDate()));
            preparedStatement.setInt(k++, book.getAvailable());
            preparedStatement.setInt(k, book.getId());

            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_UPDATE_BOOK, exception);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(DELETE_BOOK);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_DELETE_BOOK, exception);
        }
    }

    @Override
    public List<Book> getAll() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement statement;
        ResultSet resultSet;
        List<Book> books;
        try {
            books = new ArrayList<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ALL_BOOKS);
            LOG.trace("Executing query >>" + statement);

            while (resultSet.next()) {
                books.add(extractBook(resultSet));
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_ALL_BOOKS, exception);
        }
        return books;
    }


    @Override
    public Book getById(int id) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Book book= null;
        try {
            preparedStatement = connection.prepareStatement(GET_BOOK_BY_ID);
            preparedStatement.setInt(1, id);
            LOG.trace("Executing query >>" + preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = extractBook(resultSet);
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_BOOK_BY_ID+ id, exception);
        }
        return book;
    }



    @Override
    public Book getBookByName(String name) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Book book = null;
        try {
            preparedStatement = connection.prepareStatement(GET_BOOK_BY_NAME);
            preparedStatement.setString(1, name);
            LOG.trace("Executing query >>" + preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = extractBook(resultSet);
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_BOOK_BY_NAME + name, exception);
        }
        return book;
    }

    @Override
    public Book getBookByAuthor(String author) throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Book book = null;
        try {
            preparedStatement = connection.prepareStatement(GET_BOOK_BY_AUTHOR);
            preparedStatement.setString(1, author);
            LOG.trace("Executing query >>" + preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = extractBook(resultSet);
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_BOOK_BY_AUTHOR + author, exception);
        }
        return book;
    }

    public List<Book> getAvailable() throws DaoException {
        LOG.debug("Start");

        Connection connection = ConnectionHolder.getConnection();
        Statement statement;
        ResultSet resultSet;
        List<Book> books;
        try {
            books = new ArrayList<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_AVAILABLE_BOOKS);
            LOG.trace("Executing query >>" + statement);

            while (resultSet.next()) {
                books.add(extractBook(resultSet));
            }
        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANT_GET_AVAILABLE_BOOKS, exception);
        }
        return books;
    }

    private Book extractBook(ResultSet resultSet) throws DaoException {
        Book book = new Book();
        try {
            book.setId(resultSet.getInt(Fields.Books.ID));
            book.setName(resultSet.getString(Fields.Books.NAME));
            book.setAuthor(resultSet.getString(Fields.Books.AUTHOR));
            book.setPublisher(resultSet.getString(Fields.Books.PUBLISHER));
            book.setPublicationDate(resultSet.getDate(Fields.Books.PUBLIC_DATE).toLocalDate());
            book.setAvailable(resultSet.getInt(Fields.Books.AVAILABLE));

        } catch (SQLException exception) {
            throw new DaoException(ErrorMessages.CANNOT_OBTAIN_BOOK, exception);
        }
        LOG.trace("Obtained book >>" + book);
        return book;
    }
}
