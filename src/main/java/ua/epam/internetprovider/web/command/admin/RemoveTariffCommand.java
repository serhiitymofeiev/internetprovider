package ua.epam.internetprovider.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.db.services.ITariffService;
import ua.epam.internetprovider.db.services.TariffServiceImpl;
import ua.epam.internetprovider.web.command.ICommand;

import java.sql.SQLException;

/**
 * Tariff removal command class
 */
public class RemoveTariffCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        ITariffService service = new TariffServiceImpl();
        int tariffId = Integer.parseInt(request.getParameter("tariff_id"));
        try {
            service.remove(tariffId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String resp = Path.COMMAND_MAIN;
        HttpSession session = request.getSession(true);
        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        return resp;
    }
}
