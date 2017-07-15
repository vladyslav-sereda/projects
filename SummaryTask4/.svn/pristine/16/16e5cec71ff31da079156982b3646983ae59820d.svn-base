package ua.nure.sereda.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Book;
import ua.nure.sereda.SummaryTask4.service.BookService;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class ShowBookEditForm extends Command {

    private static final long serialVersionUID = -7517344816766467765L;
    private static final Logger LOG = Logger.getLogger(ShowBookEditForm.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        LOG.debug("Start");
        BookService bookService = (BookService) request.getServletContext().getAttribute(Services.BOOK);

        LOG.trace("Getting book id from session...");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        try {
            LOG.trace("Getting book...");
            Book book = bookService.getById(bookId);
            request.getSession().setAttribute("book", book);
            LOG.trace("Book's edit page initialized");
        } catch (ServiceException e) {
            throw new AppException(e.getMessage());
        }
        return Path.PAGE_BOOK_EDIT;
    }
}
