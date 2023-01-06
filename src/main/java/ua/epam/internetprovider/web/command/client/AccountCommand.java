package ua.epam.internetprovider.web.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.epam.internetprovider.Path;
import ua.epam.internetprovider.db.entity.Account;
import ua.epam.internetprovider.db.entity.ContactDetails;
import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.entity.User;
import ua.epam.internetprovider.db.services.*;
import ua.epam.internetprovider.utils.ReportBuilder;
import ua.epam.internetprovider.web.command.ICommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Client account edit command class
 */
public class AccountCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        String forward = Path.PAGE_ACCOUNT;

        IUserService userService = new UserServiceImpl();
        IContactDetailsService detailsService = new ContactDetailsServiceImpl();
        IAccountService accountService = new AccountServiceImpl();
        ITariffService tariffService = new TariffServiceImpl();

        User fullUser = (User) session.getAttribute("user");
        fullUser.setRoleId(fullUser.getRoleId());
        fullUser.setDetails(detailsService.find(fullUser.getDetails().getId()));
        fullUser.setAccount(accountService.find(fullUser.getAccount().getId()));
        fullUser.setTariffs(new HashSet<>(userService.findUserTariffs(fullUser)));

        session.setAttribute("fullUser", fullUser);
        request.setAttribute("fullUser", fullUser);

        if (request.getParameter("tariff_id") != null) {
            printTariff(request, response, fullUser);
        }

        if (request.getParameter("account_balance") != null) {
            forward = topUpAccount(request, response, userService, accountService, fullUser);
        }

        if (request.getParameter("btnEmail") != null) {
            forward = changeEmail(request, response, detailsService, fullUser);
        }

        if (request.getParameter("btnPassword") != null) {
            forward = changePassword(request, response, userService, fullUser);
        }

        List<Tariff> internetTariffs = tariffService.findAllById(1);
        internetTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));

        List<Tariff> iptvTariffs = tariffService.findAllById(2);
        iptvTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));

        List<Tariff> telephoneTariffs = tariffService.findAllById(3);
        telephoneTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));

        request.setAttribute("internetTariffs", internetTariffs);
        request.setAttribute("iptvTariffs", iptvTariffs);
        request.setAttribute("telephoneTariffs", telephoneTariffs);

        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        return forward;
    }

    private void printTariff(HttpServletRequest request, HttpServletResponse response, User user) {
        long tariffId = Long.parseLong(request.getParameter("tariff_id"));
        Set<Tariff> tariffs = user.getTariffs();
        for (Tariff tariff : tariffs) {
            if (tariff.getId() == tariffId) {
                ReportBuilder.tariffPDF(response, tariff);
            }
        }
    }

    private String topUpAccount(HttpServletRequest request, HttpServletResponse response, IUserService userService, IAccountService accountService, User user) {
        String resp = Path.COMMAND_ACCOUNT;
        double balance = Double.parseDouble(request.getParameter("account_balance"));
        double oldBalance = user.getAccount().getBalance();
        double newBalance = oldBalance + balance;
        Account account = user.getAccount();
        account.setBalance(newBalance);
        accountService.update(account);

        if (user.isBlocked() && user.getAccount().getBalance() > 0) {
            user.setBlocked(false);
            userService.update(user);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        try {
            response.sendRedirect(resp);
            resp = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            resp = Path.PAGE_ERROR_PAGE;
        }
        return resp;
    }

    private String changeEmail(HttpServletRequest request, HttpServletResponse response, IContactDetailsService detailsService, User user) {
        String errorMessage;
        String resp = Path.COMMAND_ACCOUNT;

        String url = resp;
        HttpSession session = request.getSession(true);
        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        String email = request.getParameter("inputEmail");
        if (email == null || email.isEmpty()) {
            errorMessage = "Email can't be empty";
            request.setAttribute("errorMessage", errorMessage);
        } else {
            ContactDetails detail = user.getDetails();
            detail.setEmail(email);
            detailsService.update(detail);
            resp = Path.PAGE_ACCOUNT;

            try {
                response.sendRedirect(resp);
                resp = Path.COMMAND_REDIRECT;
            } catch (IOException e) {
                resp = Path.PAGE_ERROR_PAGE;
            }
        }
        return resp;
    }

    private String changePassword(HttpServletRequest request, HttpServletResponse response, IUserService userService, User user) {
        String errorMessage;
        String resp = Path.COMMAND_ACCOUNT;
        String password = request.getParameter("inputPassword");

        HttpSession session = request.getSession(true);
        resp = Path.COMMAND_REDIRECT;
        session.setAttribute(Path.ATTRIBUTE_URL, response);
        session.setAttribute(Path.ATTRIBUTE_REQUEST, request);

        if (password == null || password.isEmpty()) {
            errorMessage = "Password can't be empty";
            request.setAttribute("errorMessage", errorMessage);
            resp = Path.PAGE_ACCOUNT;
        } else {
            user.setPassword(password);
            userService.update(user);

             try {
                response.sendRedirect(resp);
                resp = Path.COMMAND_REDIRECT;
            } catch (IOException e) {
                resp = Path.PAGE_ERROR_PAGE;
            }
        }
        return resp;
    }
}