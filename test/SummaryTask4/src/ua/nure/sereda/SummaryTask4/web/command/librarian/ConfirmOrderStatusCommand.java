package ua.nure.sereda.SummaryTask4.web.command.librarian;

import org.apache.log4j.Logger;
import ua.nure.sereda.SummaryTask4.exceptions.AppException;
import ua.nure.sereda.SummaryTask4.exceptions.ServiceException;
import ua.nure.sereda.SummaryTask4.service.OrderService;
import ua.nure.sereda.SummaryTask4.web.Services;
import ua.nure.sereda.SummaryTask4.web.command.AnswerStatus;
import ua.nure.sereda.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vladyslav.
 */
public class ConfirmOrderStatusCommand extends Command {
    private static final long serialVersionUID = -5611865574386449569L;
    private static final Logger LOG = Logger.getLogger(ConfirmOrderStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Start");
        OrderService orderService = (OrderService) request.getServletContext().getAttribute(Services.ORDER);


        try {
            LOG.trace("Getting param from request");
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            LOG.trace(String.format("Obtained param order id = %s", orderId));
            orderService.confirmNewOrder(orderId);
        } catch (ServiceException e) {
            response.setStatus(AnswerStatus.SERVER_ERROR);
            throw new AppException();
        }
        LOG.debug("Order status was confirmed");
        return null;
    }
}
