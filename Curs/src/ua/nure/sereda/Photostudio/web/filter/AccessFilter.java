package ua.nure.sereda.Photostudio.web.filter;

import org.apache.log4j.Logger;
import ua.nure.sereda.Photostudio.models.Role;
import ua.nure.sereda.Photostudio.web.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Vladyslav.
 */
public class AccessFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AccessFilter.class);

    private Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> guest = new ArrayList<>();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("Filter starts");

        if (accessAllowed(request)) {
            LOG.debug(" CommandAccessFilter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessage);
            LOG.trace("Set the request attribute: errorMessage >> " + errorMessage);

            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        LOG.trace("Obtained command >>" + commandName);

        if (commandName == null || commandName.isEmpty()) {
            LOG.trace("Command name is empty");
            return false;
        }

        HttpSession session = httpRequest.getSession(false);

        if (guest.contains(commandName)) {
            LOG.trace("Command is in out-of-control list");
            return true;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        LOG.trace("User role >>" + userRole);

        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        // roles
        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter("client")));
        LOG.trace("Access map >> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("common"));
        LOG.trace("Common commands >> " + commons);

        // guest
        guest = asList(fConfig.getInitParameter("guest"));
        LOG.trace("Out of control commands >> " + guest);

        LOG.debug("Filter initialization finished");
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

    public void destroy() {
        LOG.debug("Filter destruction starts");
        LOG.debug("Filter destruction finished");
    }

}
