package ua.nure.sereda.Practice9;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sered on 10.05.2017.
 */
@WebServlet("/Part2")
public class Part2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        System.out.println(name);
        resp.getWriter().println("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Result</title>" +
                "</head>" +
                "<body> HELLO, " + name + " !!!" +
                "<input type=\"button\" value=\"GO BACK\" onClick='location.href=\"localhost:8080/indexPart2.html\"'>" +
                "</body>" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
