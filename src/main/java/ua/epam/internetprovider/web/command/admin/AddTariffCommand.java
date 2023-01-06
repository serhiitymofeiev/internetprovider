package ua.epam.internetprovider.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.services.ITariffService;
import ua.epam.internetprovider.db.services.TariffServiceImpl;
import ua.epam.internetprovider.web.command.ICommand;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Tariff addition command class
 */
public class AddTariffCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name").trim();
        String price = request.getParameter("price").trim();
        String description = request.getParameter("description").trim();
        String serviceId = request.getParameter("serviceId");

        String resp = Path.COMMAND_MAIN;

        ITariffService service = new TariffServiceImpl();
        Tariff tariff = new Tariff();
        tariff.setName(name);
        tariff.setPrice(Double.parseDouble(price));
        tariff.setDescription(description);
        tariff.setServiceId(Long.parseLong(serviceId));

        HttpSession session = request.getSession(true);
        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        try {
            service.save(tariff);
        } catch (SQLException e) {
            resp = Path.PAGE_ERROR_PAGE;
        }
        try {
            response.sendRedirect(resp);
            resp = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            resp = Path.PAGE_ERROR_PAGE;
        }



        return resp;
    }
}
