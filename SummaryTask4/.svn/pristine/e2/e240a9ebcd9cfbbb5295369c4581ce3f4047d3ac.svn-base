package ua.nure.sereda.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Book;
import ua.nure.sereda.SummaryTask4.service.BookService;
import ua.nure.sereda.SummaryTask4.utils.CatalogSorter;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.AnswerStatus;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class ShowAllBooks extends Command {

    private static final long serialVersionUID = 8149444954384972142L;
    private static final Logger LOG = Logger.getLogger(ShowAllBooks.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command start");
        BookService bookService = (BookService) request.getServletContext().getAttribute(Services.BOOK);
        String sort = request.getParameter("sort");
        if (sort == null) {
            sort = "name";
        }
        LOG.debug("obtained sort - " + sort);

        try {
            LOG.trace("Getting all books...");
            List<Book> books = bookService.getAll();
            LOG.trace("Sorting books");
            switch (sort) {
                case "author":
                    LOG.debug("Sort by author");
                    new CatalogSorter().setSortBooksByAuthor(books);
                    break;
                case "publisher":
                    LOG.debug("Sort by publisher");
                    new CatalogSorter().setSortBooksByPublisher(books);
                    break;
                case "publicationDate":
                    LOG.debug("Sort by publication date");
                    new CatalogSorter().setSortBooksByPublicationDate(books);
                    break;
                default:
                    LOG.debug("Sort by name");
                    new CatalogSorter().setSortBooksByName(books);
            }
            request.getSession().setAttribute("allBooks", books);
        } catch (ServiceException exception) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException();
        }
        LOG.debug("Command was finished");
        return Path.PAGE_ALL_BOOKS;
    }
}
