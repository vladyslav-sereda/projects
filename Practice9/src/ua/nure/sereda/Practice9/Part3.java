package ua.nure.sereda.Practice9;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.nure.sereda.Practice9.Votes.sports;

/**
 * Created by Vladyslav.
 */
@WebServlet("/Part3")
public class Part3 extends HttpServlet {
    List<String> names = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String sport = req.getParameter("sports");
        Integer vote = sports.get(sport);
        vote++;
        sports.put(sport, vote);
        names.add((req.getSession().getAttribute("name").toString()));


        resp.getWriter().println("<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Result</title>" +
                "</head>" +
                "<body> " +
                "<table border=\"0.2\">");
        for (Map.Entry<String,Integer> entry : sports.entrySet()) {
           resp.getWriter().println("<tr> " +
                   "<td>" + entry.getKey() + "</td>" +
                   "<td>" + entry.getValue() + "</td>" +
                   "</tr>");
        }
        resp.getWriter().println("</table></body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

