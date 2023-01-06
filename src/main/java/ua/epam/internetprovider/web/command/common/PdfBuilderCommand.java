package ua.epam.internetprovider.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.db.entity.User;
import ua.epam.internetprovider.db.services.*;
import ua.epam.internetprovider.utils.ReportBuilder;
import ua.epam.internetprovider.web.command.ICommand;

import java.sql.SQLException;
import java.util.HashSet;

/**
 * The class of the PDF print form construction command
 */
public class PdfBuilderCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession(true);
        String forward = Path.COMMAND_MAIN;

        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        if (session.getAttribute("newUser") != null) {
            User user = (User) session.getAttribute("newUser");

            ReportBuilder.contractPDF(response, user);
        } else {
            long id = Long.parseLong(request.getParameter("user_id"));
            IUserService userService = new UserServiceImpl();
            IContactDetailsService detailsService = new ContactDetailsServiceImpl();
            IAccountService accountService = new AccountServiceImpl();

            User fullUser = userService.find(id);
            fullUser.setRoleId(fullUser.getRoleId());
            fullUser.setDetails(detailsService.find(fullUser.getDetails().getId()));
            fullUser.setAccount(accountService.find(fullUser.getAccount().getId()));
            fullUser.setTariffs(new HashSet<>(userService.findUserTariffs(fullUser)));
            request.setAttribute("fullUser", fullUser);

            ReportBuilder.contractPDF(response, fullUser);
        }
        return forward;
    }
}
