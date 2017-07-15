package ua.nure.sereda.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exceptions.AppException;
import ua.nure.sereda.SummaryTask4.exceptions.ServiceException;
import ua.nure.sereda.SummaryTask4.service.BookService;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.AnswerStatus;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * Created by Vladyslav.
 */
public class AddBookCommand extends Command {
    private static final long serialVersionUID = 7306056062625422472L;
    private static final Logger LOG = Logger.getLogger(AddBookCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Start");
        BookService bookService = (BookService) request.getServletContext().getAttribute(Services.BOOK);

        try {
            LOG.trace("Getting params from request");
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            LocalDate publicationDate = LocalDate.parse(request.getParameter("publicationDate"));
            int available = Integer.parseInt(request.getParameter("available"));
            LOG.trace(String.format("Obtained params : name = %s, author = %s, publisher = %s, " +
                    "publication date = %s, available = %s", name, author, publisher, publicationDate, available));
            if (name == null || author == null || publisher == null || publicationDate == null || available == 0) {
                response.setStatus(AnswerStatus.NOT_AVAILABLE);
            } else if (bookService.getByName(name) != null & bookService.getByAuthor(author) != null) {
                response.setStatus(AnswerStatus.NOT_AVAILABLE);
            } else {
                LOG.trace("Adding book");
                bookService.createBook(name, author, publisher, publicationDate, available);
                response.setStatus(AnswerStatus.OK);
            }
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException();
        }
        LOG.debug("Book was created");
        return null;
    }
}
