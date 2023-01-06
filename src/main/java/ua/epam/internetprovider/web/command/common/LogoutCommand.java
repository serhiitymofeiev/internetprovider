package ua.epam.internetprovider.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.web.command.ICommand;

/**
 * The class of the command for the user to log out of his account
 */
public class LogoutCommand implements ICommand {
    private static final Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("ICommand starts");

        HttpSession session = request.getSession(false);
        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        if (session != null) {
            session.invalidate();
        }
        log.debug("ICommand finished");
        return Path.PAGE_INDEX;
    }
}
