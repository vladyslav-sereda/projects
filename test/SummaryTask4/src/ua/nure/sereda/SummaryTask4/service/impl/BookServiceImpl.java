package ua.nure.sereda.SummaryTask4.service.impl;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.dao.BookDao;
import ua.nure.sereda.SummaryTask4.db.TransactionManager;
import ua.nure.sereda.SummaryTask4.exceptions.ServiceException;
import ua.nure.sereda.SummaryTask4.exceptions.TransactionException;
import ua.nure.sereda.SummaryTask4.models.Book;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.service.BookService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    private TransactionManager transactionManager;
    private static final Logger LOG = Logger.getLogger(BookServiceImpl.class);

    public BookServiceImpl(TransactionManager transactionManager, BookDao bookDao) {
        this.transactionManager = transactionManager;
        this.bookDao = bookDao;
    }

    @Override
    public void createBook(String name, String author, String publisher, LocalDate publicationDate, int available)
            throws ServiceException {
        LOG.debug(String.format("Start with params: name = %s, author = %s, publisher = %s, publication date = %s, " +
                "available = %s", name, author, publisher, publicationDate, available));

        Book book = new Book(name, author, publisher, publicationDate, available);
        try {
            transactionManager.task(() -> {
                bookDao.create(book);
                return null;
            });
        } catch (TransactionException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public Book getById(int id) throws ServiceException {
        LOG.debug("Start get by id = " + id);

        try {
            return transactionManager.task(() -> bookDao.getById(id));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Book getByName(String name) throws ServiceException {
        LOG.debug("Start get by name = " + name);

        try {
            return transactionManager.task(() -> bookDao.getBookByName(name));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Book getByAuthor(String author) throws ServiceException {
        LOG.debug("Start get by author = " + author);

        try {
            return transactionManager.task(() -> bookDao.getBookByAuthor(author));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> getBooksByOrders(List<Order> orders) throws ServiceException {
        LOG.debug("Start get by orders = " + orders);

        try {
            return transactionManager.task(() -> {
                List<Book> books = new ArrayList<>();
                Book book;
                for (Order order : orders) {
                    book = bookDao.getById(order.getBookId());
                    if (books.isEmpty() && book != null) {
                        books.add(book);
                    } else if (!books.contains(book)) {
                        books.add(book);
                    }
                }
                return books;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> getAvailable() throws ServiceException {
        LOG.debug("Start get available");

        try {
            return transactionManager.task(() -> bookDao.getAvailable());
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> getAll() throws ServiceException {
        LOG.debug("Start get all");

        try {
            return transactionManager.task(() -> bookDao.getAll());
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        LOG.debug("Start delete, id = " + id);

        try {
            transactionManager.task(() -> {
                bookDao.delete(id);
                return null;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Book book) throws ServiceException {
        LOG.debug("Start update, book = " + book);

        try {
            transactionManager.task(() -> {
                bookDao.update(book);
                return null;
            });
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }
}
