package ua.epam.internetprovider.web.filter;

import org.apache.log4j.Logger;
import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.db.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Access security filter class
 */
public class SecurityFilter implements Filter {
    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    // commands access
    private static final Map<Role, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();
    private static List<String> outOfControl = new ArrayList<>();

    /**
     * Access security filter initialization procedure
     * @param fConfig filter configuration
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        log.debug("Filter initialization starts");

        // roles
        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter("client")));
        log.trace("Access map --> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("common"));
        log.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        log.trace("Out of control commands --> " + outOfControl);

        log.debug("Filter initialization finished");
    }

    /**
     * The procedure for performing an access security filter
     * @param request request passed to the filter
     * @param response the request response used to save the parameters
     * @param chain servlet filter
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter starts");
        if (accessAllowed(request)) {
            log.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessages = "You do not have permission to access the requested resource";
            request.setAttribute("errorMessage", errorMessages);

            log.trace("Set the request attribute: errorMessage --> " + errorMessages);

            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("action");
        if (commandName == null || commandName.isEmpty())
            return false;

        if (outOfControl.contains(commandName))
            return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null)
            return false;

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null)
            return false;

        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
    }

    /**
     * Procedure for destroying the access security filter
     */
    @Override
    public void destroy() {
        log.debug("Filter destruction starts");
        // do nothing
        log.debug("Filter destruction finished");
    }

    private List<String> asList(String param) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(param);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
