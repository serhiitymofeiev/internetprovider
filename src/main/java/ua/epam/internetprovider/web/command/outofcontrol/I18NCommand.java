package ua.epam.internetprovider.web.command.outofcontrol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.web.command.ICommand;

/**
 * The locale command class
 */
public class I18NCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
        String defaultLocale = "defaultLocale";

        if (request.getParameter("ua") != null) {
            Config.set(session, fmtLocale, Path.LOCALE_NAME_UA);
            session.setAttribute(defaultLocale, "ua");
        } else {
            Config.set(session, fmtLocale, "en");
            session.setAttribute(defaultLocale, Path.LOCALE_NAME_EN);
        }

        boolean isNotExistUrl = session.getAttribute(Path.ATTRIBUTE_URL) == null;
        if (!isNotExistUrl)
        {
            return "redirect";
        };

        return Path.PAGE_INDEX;
    }
}