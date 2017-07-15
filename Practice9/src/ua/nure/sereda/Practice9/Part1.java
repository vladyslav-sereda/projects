package ua.nure.sereda.Practice9;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sered on 10.05.2017.
 */

@WebServlet("/Part1")
public class Part1 extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Part1.class);

    static {
        BasicConfigurator.configure();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Part1#doGet starting...");

        int x = Integer.parseInt(req.getParameter("x"));
        LOG.info("x = " + x);

        int y = Integer.parseInt(req.getParameter("y"));
        LOG.info("y = " + y);

        String operation = req.getParameter("operation");
        LOG.info("operation = " + operation);

        int result = 0;
        switch (operation) {
            case "+":
                result = x + y;
                break;
            case "-":
                result = x - y;
                break;
            case "*":
                result = x * y;
                break;
            case "/":
                if (y != 0) {
                    result = x / y;
                } else {
                    LOG.error("division on zero!");
                }
        }

        resp.getWriter().println("<!DOCTYPE html><head><title>Result</title></head>" +
                "<body> result = " + result +
                "<a type=\"button\" href=\"localhost:8080/\"'>GO BACK</a></body></html>");

        LOG.info("Part1#doGet ending");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
