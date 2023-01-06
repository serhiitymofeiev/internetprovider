package ua.epam.internetprovider.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.web.command.ICommand;

/**
 * The class of the command displayed in its absence
 */
public class NoCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        return Path.PAGE_ERROR_PAGE;
    }
}
