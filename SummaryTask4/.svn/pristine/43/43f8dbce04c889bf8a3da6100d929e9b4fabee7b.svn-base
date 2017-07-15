package ua.nure.sereda.SummaryTask4.web.websocket;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.dao.BookDao;
import ua.nure.sereda.SummaryTask4.dao.impl.BookDaoImpl;
import ua.nure.sereda.SummaryTask4.db.TransactionManager;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Book;
import ua.nure.sereda.SummaryTask4.service.BookService;
import ua.nure.sereda.SummaryTask4.service.impl.BookServiceImpl;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Search-action handler. Works as a filter for interactive room-search
 */
public class SearchEvent extends SocketEvent {
    private static final Logger LOG = Logger.getLogger(SearchEvent.class);


    /**
     * @param jsObj   - obtained data from client
     * @param session - client's session
     * @throws AppException Method invokes search function with all needed params, and
     *                      sends processed data to client in right format.
     */
    public void execute(JsonObject jsObj, Session session) throws AppException {
        LOG.debug("Start, obtained JSON >> " + jsObj);
        List<Book> books = filterSearch(jsObj);
        if (!books.isEmpty()) {
            LOG.debug("Sending answer to client");
            session.getAsyncRemote().sendText(createAnswer("books", books));
        }
    }

    private List<Book> filterSearch(JsonObject jsObj) throws AppException {
        LOG.debug("Start, searching for requested books");

        List<Book> books;
        List<Book> result = new ArrayList<>();
        try {
            TransactionManager manager = TransactionManager.getInstance();
            BookDao bookDao = new BookDaoImpl();
            BookService bookService = new BookServiceImpl(manager, bookDao);
            books = bookService.getAvailable();

            if (jsObj.has("name")) {
                if (!jsObj.get("name").getAsString().trim().isEmpty()) {
                    LOG.trace("Search for books by name: " + jsObj.get("name").getAsString());
                    String name = jsObj.get("name").getAsString();
                    for (Book book : books) {
                        if (book.getName().toLowerCase().contains(name.toLowerCase())) {
                            result.add(book);
                        }
                    }
                }
            } else if (jsObj.has("author")) {
                if (!jsObj.get("author").getAsString().trim().isEmpty()) {
                    LOG.trace("Search for books by author: " + jsObj.get("author").getAsString());
                    String author = jsObj.get("author").getAsString();
                    for (Book book : books) {
                        if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                            result.add(book);
                        }
                    }
                }
            }
            LOG.debug("Found - " + result);
        } catch (ServiceException exception) {
            throw new AppException("search exception", exception);
        }
        return result;
    }
}
