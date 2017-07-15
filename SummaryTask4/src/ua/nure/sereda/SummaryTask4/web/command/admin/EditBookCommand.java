package ua.nure.sereda.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.BookOrderedException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Book;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.service.BookService;
import ua.nure.sereda.SummaryTask4.service.OrderService;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.AnswerStatus;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class EditBookCommand extends Command {
    private static final long serialVersionUID = -7245740271058461122L;
    private static final Logger LOG = Logger.getLogger(EditBookCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command start");
        BookService bookService = (BookService) request.getServletContext().getAttribute(Services.BOOK);
        OrderService orderService = (OrderService) request.getServletContext().getAttribute(Services.ORDER);
        List<Order> orders;
        Book book;
        try {
            LOG.debug("Trying to get book...");
            book = bookService.getById(Integer.parseInt(request.getParameter("bookId")));
            orders = orderService.getConfirmedOrders();
        } catch (ServiceException exception) {
            LOG.error("Service error occurred.");
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException(exception.getMessage());
        }
        String field = request.getParameter("field");
        if (field.trim().isEmpty()) {
            LOG.error("Field to change is not specified");
            return null;
        }
        switch (field) {
            case "delete": {
                try {
                    for (Order order : orders) {
                        if (order.getBookId() == book.getId()) {
                            throw new BookOrderedException();
                        }
                    }
                    LOG.debug("Deleting book ...");
                    bookService.delete(book.getId());
                    LOG.debug("Delete book complete");
                    return null;
                } catch (ServiceException exception) {
                    LOG.error("Service error occurred.", exception);
                    response.setStatus(AnswerStatus.SERVER_ERROR);
                    throw new AppException(exception.getMessage());

                } catch (BookOrderedException e) {
                    response.setStatus(AnswerStatus.NOT_AVAILABLE);
                    return null;
                }
            }
            case "name": {
                LOG.debug("Changing book name...");
                String name = request.getParameter("name");
                LOG.trace("Obtained new name >> " + name);
                book.setName(name);
                LOG.debug("Change name complete.");
                break;
            }

            case "author": {
                LOG.debug("Changing user author...");
                String author = request.getParameter("author");
                LOG.trace("Obtained new author >> " + author);
                book.setAuthor(author);
                LOG.debug("Change author complete.");
                break;
            }
            case "publisher": {
                LOG.debug("Changing publisher...");
                String publisher = request.getParameter("publisher");
                book.setPublisher(publisher);
                LOG.debug("Change publisher complete.");
                break;
            }
            case "publicationDate": {
                LOG.debug("Changing publication date...");
                LocalDate publicationDate = LocalDate.parse(request.getParameter("publicationDate"));
                LOG.trace("Obtained new publication date >> " + publicationDate);
                book.setPublicationDate(publicationDate);
                LOG.debug("Change publicationDate complete.");
                break;
            }
            case "available": {
                LOG.debug("Changing available...");

                int available = Integer.parseInt(request.getParameter("available"));
                LOG.trace("Obtained new available >> " + available);
                book.setAvailable(available);
                LOG.debug("Change available complete.");
                break;
            }
        }

        try {
            LOG.debug("Trying to update book ...");
            bookService.update(book);
        } catch (ServiceException exception) {
            LOG.error("Service error occurred.");
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException(exception.getMessage());
        }
        LOG.debug("Update book complete");

        response.setStatus(AnswerStatus.OK);

        return null;
    }
}

