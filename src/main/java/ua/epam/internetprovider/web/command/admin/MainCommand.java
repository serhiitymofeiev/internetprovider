package ua.epam.internetprovider.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.db.entity.PackageServices;
import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.entity.User;
import ua.epam.internetprovider.db.services.*;
import ua.epam.internetprovider.web.command.ICommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * User data editing command class
 */
public class MainCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        IPackageService IPackageService = new PackageServiceImpl();
        ITariffService ITariffService = new TariffServiceImpl();
        IUserService IUserService = new UserServiceImpl();
        IContactDetailsService detailsService = new ContactDetailsServiceImpl();
        IAccountService IAccountService = new AccountServiceImpl();

        List<PackageServices> services = IPackageService.findAll();
        List<Tariff> internetTariffs = ITariffService.findAllById(1);
        List<Tariff> iptvTariffs = ITariffService.findAllById(2);
        List<Tariff> telephoneTariffs = ITariffService.findAllById(3);

        List<User> users = IUserService.findAll();
        List<User> fullUser = new ArrayList<>();
        for (User user : users) {
            user.setRoleId(user.getRoleId());
            user.setDetails(detailsService.find(user.getDetails().getId()));
            user.setAccount(IAccountService.find(user.getAccount().getId()));
            user.setTariffs(new HashSet<>(IUserService.findUserTariffs(user)));
            fullUser.add(user);
        }

        request.setAttribute("services", services);
        request.setAttribute("internetTariffs", internetTariffs);
        request.setAttribute("iptvTariffs", iptvTariffs);
        request.setAttribute("telephoneTariffs", telephoneTariffs);
        request.setAttribute("fullUser", fullUser);

        String resp = Path.PAGE_MAIN;

        HttpSession session = request.getSession(true);
        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        return resp;
    }
}

