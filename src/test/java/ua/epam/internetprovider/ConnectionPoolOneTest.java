package ua.epam.internetprovider;

import org.junit.Test;
import ua.epam.internetprovider.db.entity.Account;
import ua.epam.internetprovider.db.entity.ContactDetails;
import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.entity.User;
import ua.epam.internetprovider.db.services.*;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ConnectionPoolOneTest {
    private final String login;
    public ConnectionPoolOneTest() {
        login = "+380674561245";
    }

    @Test
	public void RegistrationUser() throws SQLException {
        IUserService userService = new UserServiceImpl();
        IContactDetailsService detailsService = new ContactDetailsServiceImpl();
        IAccountService accountService = new AccountServiceImpl();
        ITariffService tariffService = new TariffServiceImpl();

        //тариф
        String name = "Тестовий тариф";
        String price = "1000.00";
        String description = "Тестовий тариф для тестування функціоналу";
        String serviceId = "2";

        ITariffService service = new TariffServiceImpl();
        Tariff tariff = new Tariff();
        tariff.setName(name);
        tariff.setPrice(Double.parseDouble(price));
        tariff.setDescription(description);
        tariff.setServiceId(Long.parseLong(serviceId));

        tariffService.save(tariff);

        long trafficsId = service.find(name).getId();

        //користувач
        String firstName = "Тестовий";
        String lastName = "Тест";
        String surname = "Тестович";

        String city = "Одеса";
        String street = "Дерибасівська";
        String home = "4а";
        String apartment = "66";
        String email = "dfdfdf@mail.net";

        String password = "A546dsdsll";

        ContactDetails details = new ContactDetails();
        details.setCity(city);
        details.setStreet(street);
        details.setHome(home);
        details.setApartment(apartment);
        details.setEmail(email);
        details.setPhone(login);
        detailsService.save(details);

        Account account = new Account();
        account.setNumber(accountService.getNumberContract());
        account.setBalance(0);
        accountService.save(account);

        Set<Tariff> tariffs = new HashSet<>();
        tariffs.add(tariffService.find(trafficsId));

        User newUser = new User();
        newUser.setLogin(details.getPhone());
        newUser.setSurname(surname);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(password);
        newUser.setRoleId(2);
        newUser.setBlocked(true);
        newUser.setDetails(details);
        newUser.setAccount(account);
        newUser.setTariffs(tariffs);
        userService.save(newUser);

        User user = userService.findByLogin(login);
        user.setBlocked(false);
        userService.update(user);
        userService.removeLinksUsersHasTariffs(user);
        detailsService.remove((int) user.getId());
        assertEquals(user.getLogin(), login);
        userService.remove((int) user.getId());

       tariffService.remove(trafficsId);
    }

}
