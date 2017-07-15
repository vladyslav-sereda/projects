package ua.nure.sereda.SummaryTask4.service;

import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Book;
import ua.nure.sereda.SummaryTask4.models.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public interface BookService {

    void createBook(String name, String author, String publisher, LocalDate publicationDate, int available) throws ServiceException;

    Book getById(int id) throws ServiceException;

    Book getByName(String name) throws ServiceException;

    Book getByAuthor(String author) throws ServiceException;

    List<Book> getBooksByOrders(List<Order> orders) throws ServiceException;

    List<Book> getAvailable() throws ServiceException;

    List<Book> getAll() throws ServiceException;

    void delete(int id) throws ServiceException;

    void update(Book book) throws ServiceException;
}
