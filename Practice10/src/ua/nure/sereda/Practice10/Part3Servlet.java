package ua.nure.sereda.Practice10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladyslav.
 */
@WebServlet("/Part3Servlet")
public class Part3Servlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("Part3.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        List<String> names = null;

        if (req.getParameter("name") != null) {
            names = (List<String>) session.getAttribute("names");
            if (names == null) {
                names = new ArrayList<>();
            }
            names.add(req.getParameter("name"));

            session.setAttribute("names", names);
        }
        if (req.getParameter("remove") != null) {
            if (names != null) {
                names.clear();
                session.setAttribute("names", null);
            }
        }
        resp.sendRedirect("/Part3Servlet");
    }
}
