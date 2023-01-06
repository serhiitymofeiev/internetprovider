package ua.epam.internetprovider.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Enscoding filter class
 */
public class EncodingFilter implements Filter {
    private static final Logger log = Logger.getLogger(EncodingFilter.class);
    private String encoding;

    /**
     * Procedure for destroying the enscoding filter
     */
    public void destroy() {
        log.debug("Filter destruction starts");
        // do nothing
        log.debug("Filter destruction finished");
    }

    /**
     * The procedure for performing the encoding change filter
     * @param req request passed to the filter
     * @param resp the request response used to save the parameters
     * @param chain servlet filter
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        log.debug("Filter starts");

        String requestEncoding = req.getCharacterEncoding();

        if (requestEncoding == null) {
            log.trace("Request encoding = null, set encoding --> " + encoding);
            resp.setContentType("text/html; charset="+encoding);
            req.setCharacterEncoding(encoding);
        }

        log.debug("Filter finished");
        chain.doFilter(req, resp);
    }

    /**
     * Procedure for initializing the enscoding filter
     * @param config filter configuration
     */
    public void init(FilterConfig config) {
        log.debug("Filter initialization starts");
        encoding = config.getInitParameter("encoding");
        log.trace("Encoding from web.xml --> " + encoding);
        log.debug("Filter initialization finished");
    }
}
