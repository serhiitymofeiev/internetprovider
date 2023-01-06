package ua.epam.internetprovider.web.command.admin;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.db.entity.User;
import ua.epam.internetprovider.db.services.*;
import ua.epam.internetprovider.web.command.ICommand;

import java.sql.SQLException;
import java.util.HashSet;

/**
 * Profile display command class
 */
public class ProfileCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();

        String forward = Path.PAGE_PROFILE;

        if (session.getAttribute("newUser") != null) {
            User newUser = (User) session.getAttribute("newUser");
            request.setAttribute("fullUser", newUser);
        }

        if (request.getParameter("user_id") != null) {
            long id = Long.parseLong(request.getParameter("user_id"));
            show(request, id);
        }

        if (servletContext.getAttribute("user_id") != null) {
            Long id = (Long) servletContext.getAttribute("user_id");
            show(request, id);
        }

        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        return forward;
    }

    private void show(HttpServletRequest request, long id) throws SQLException {
        IUserService userService = new UserServiceImpl();
        IContactDetailsService detailsService = new ContactDetailsServiceImpl();
        IAccountService accountService = new AccountServiceImpl();

        User user = userService.find(id);
        user.setRoleId(user.getRoleId());
        user.setDetails(detailsService.find(user.getDetails().getId()));
        user.setAccount(accountService.find(user.getAccount().getId()));
        user.setTariffs(new HashSet<>(userService.findUserTariffs(user)));
        request.setAttribute("fullUser", user);
    }
}
