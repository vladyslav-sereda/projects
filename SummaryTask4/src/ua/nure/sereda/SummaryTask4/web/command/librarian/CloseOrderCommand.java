package ua.nure.sereda.SummaryTask4.web.command.librarian;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exception.AppException;
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

/**
 * Created by Vladyslav.
 */
public class CloseOrderCommand extends Command {
    private static final long serialVersionUID = -6279009975533051119L;
    private static final Logger LOG = Logger.getLogger(CloseOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command start");
        OrderService orderService = (OrderService) request.getServletContext().getAttribute(Services.ORDER);
        BookService bookService = (BookService) request.getServletContext().getAttribute(Services.BOOK);
        try {
            LOG.trace("Getting param from request");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            LOG.trace(String.format("Obtained param order id = %s ", orderId));
            if (orderId != 0) {
                Order order = orderService.getOrderById(orderId);
                orderService.delete(orderId);
                Book book = bookService.getById(order.getBookId());
                book.setAvailable(book.getAvailable() + 1);
                bookService.update(book);
            }
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException();
        }
        LOG.debug("Order was closed");
        return null;
    }
}
