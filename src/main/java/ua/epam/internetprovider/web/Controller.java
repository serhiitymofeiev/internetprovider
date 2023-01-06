package ua.epam.internetprovider.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.web.command.CommandFactory;
import ua.epam.internetprovider.web.command.ICommand;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The controller class is the main servlet
 */
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            proccesRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            proccesRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void proccesRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        CommandFactory commandFactory = CommandFactory.commandFactory();
        ICommand ICommand = commandFactory.getCommand(req);

        // extract command name from the request
        String page = ICommand.execute(req, resp);

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);

        // if the forward address is not null go to the address
        if (page.equals(Path.COMMAND_REDIRECT_LANGUAGE))
        {
            HttpSession session = req.getSession(false);
            HttpServletResponse response;
            if (session.getAttribute(Path.ATTRIBUTE_URL) instanceof String)
            {
                response = resp;
            }
            else
            {
                response = (HttpServletResponse) session.getAttribute(Path.ATTRIBUTE_URL);
            }
            HttpServletRequest request = (HttpServletRequest) session.getAttribute(Path.ATTRIBUTE_REQUEST);
            proccesRequest(request, response);
        }
        else if (!page.equals(Path.COMMAND_REDIRECT))
            dispatcher.forward(req, resp);

    }
}