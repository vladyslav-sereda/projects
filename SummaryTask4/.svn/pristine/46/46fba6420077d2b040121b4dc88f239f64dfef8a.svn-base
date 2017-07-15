package ua.nure.sereda.SummaryTask4.web.command.reader;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
import ua.nure.sereda.SummaryTask4.exception.ServiceException;
import ua.nure.sereda.SummaryTask4.models.Book;
import ua.nure.sereda.SummaryTask4.models.Order;
import ua.nure.sereda.SummaryTask4.models.User;
import ua.nure.sereda.SummaryTask4.service.BookService;
import ua.nure.sereda.SummaryTask4.service.OrderService;
import ua.nure.sereda.SummaryTask4.web.Path;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Vladyslav.
 */
public class AccountCommand extends Command {
    private static final long serialVersionUID = -1103806651726040061L;
    private static final Logger LOG = Logger.getLogger(AccountCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        LOG.debug("Start");

        OrderService orderService = (OrderService) request.getServletContext().getAttribute(Services.ORDER);
        BookService bookService = (BookService) request.getServletContext().getAttribute(Services.BOOK);

        LOG.trace("Getting user from session...");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            LOG.error("No user element in session scope");
            request.setAttribute("errorMessage", "You are not authorized");
            return Path.PAGE_ERROR_PAGE;
        } else {
            LOG.trace("Obtained user -> " + user);

            try {
                LOG.trace("Getting user's orders...");
                List<Order> orders = orderService.getOrdersByUser(user.getId());
                LOG.trace("Getting books by orders...");
                List<Book> books = bookService.getBooksByOrders(orders);

                System.out.println(orders);
                System.out.println(books);
                request.getSession().setAttribute("orders", orders);
                request.getSession().setAttribute("books", books);
                LOG.trace("User's account initialized");
            } catch (ServiceException e) {
                throw new AppException(e.getMessage());
            }

            return Path.PAGE_ACCOUNT;
        }
    }
}
