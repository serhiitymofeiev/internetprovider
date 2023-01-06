package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUser extends IEntity<User> {

    User getByLogin(String login);

    List<Tariff> getTariffs(User user);

    void addLinksUsersHasTariffs(User user, String[] tariffsId) throws SQLException;

    void deleteLinksUsersHasTariffs(User user) throws SQLException;
}
