package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.builders.QueryBuilder;
import ua.epam.internetprovider.db.builders.TariffQueryBuilder;
import ua.epam.internetprovider.db.builders.UserQueryBuilder;
import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * The class for implementing user-related database changes
 */
public class UserImpl implements IUser {
    private static final String GET_ALL = "SELECT * FROM provider.users";
    private static final String GET_BY_ID = "SELECT id, login, password, first_name, last_name, surname, blocked, roles_id, contact_details_id, accounts_id FROM provider.users WHERE id = ?";
    private static final String GET_BY_LOGIN = "SELECT id, login, password, first_name, last_name, surname, blocked, roles_id, contact_details_id, accounts_id FROM provider.users WHERE login = ?";
    private static final String CREATE = "INSERT INTO provider.users (login, password, first_name, last_name, surname, blocked, roles_id, contact_details_id, accounts_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE provider.users SET login = ?, password = ?, first_name = ?, last_name = ?, surname = ?, blocked = ?, roles_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM provider.users WHERE id = ?";

    private static final String ADD_LINK_USERS_HAS_TRAFFICS = "INSERT INTO provider.users_has_tariffs (users_id, tariffs_id) VALUES (?, ?)";
    private static final String GET_LINK_USERS_HAS_TRAFFICS = "SELECT t.id, t.name, t.description, t.price, t.services_id FROM tariffs AS t JOIN users_has_tariffs AS uht ON t.id = uht.tariffs_id AND uht.users_id = (SELECT id FROM users WHERE id = ?)";
    private static final String DELETE_LINK_USERS_HAS_TRAFFICS = "DELETE FROM provider.users_has_tariffs WHERE users_id = ?";

    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'provider' AND TABLE_NAME = 'users'";

    private final DBManager instance = DBManager.getInstance();
    private final QueryBuilder<User> queryBuilder = new UserQueryBuilder();

    /**
     * The procedure for obtaining a list of all users
     * @return returns a list of all users
     */
    @Override
    public List<User> getAll() {
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    /**
     * The procedure for obtaining a user by ID
     * @param id User ID
     * @return returns a user object
     */
    @Override
    public User getById(long id) {
        return queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    /**
     * The procedure for creating a user in the database
     * @param user an account object of type User
     */
    @Override
    public void create(User user) throws SQLException {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        queryBuilder.execute(instance, CREATE, user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getSurname(), user.isBlocked(), user.getRoleId(), id, id);
     }

    /**
     * Procedure for updating user data in the database
     * @param user a user object of type User record
     */
    @Override
    public void update(User user) {
        try {
            queryBuilder.execute(instance, UPDATE, user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getSurname(), user.isBlocked(), user.getRoleId(), user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Procedure for deleting a user from the database
     * @param id User ID
     */
    @Override
    public void delete(long id) {
        try {
            queryBuilder.execute(instance, DELETE, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The procedure for obtaining a user by login
     * @param login user login
     * @return returns a user object
     */
    @Override
    public User getByLogin(String login) {
        return queryBuilder.executeAndReturn(instance, GET_BY_LOGIN, login);
    }

    /**
     * The procedure for obtaining user tariffs
     * @param user a user object of type User
     * @return returns a list of tariffs
     */
    @Override
    public List<Tariff> getTariffs(User user) {
        QueryBuilder<Tariff> queryBuilder = new TariffQueryBuilder();
        return queryBuilder.executeAndReturnList(instance, GET_LINK_USERS_HAS_TRAFFICS, user.getId());
    }

    /**
     * The procedure for assigning tariffs to the user
     * @param user a user object of type User
     * @param tariffsId a list of tariff IDs
     */
    @Override
    public void addLinksUsersHasTariffs(User user, String[] tariffsId) throws SQLException {
        User tmp = getByLogin(user.getLogin());
        QueryBuilder<Tariff> queryBuilder = new TariffQueryBuilder();
        for (String id : tariffsId) {
            queryBuilder.execute(instance, ADD_LINK_USERS_HAS_TRAFFICS, tmp.getId(), id);
        }
    }

    /**
     * Procedure for removing user tariffs
     * @param user a user object of type User
     */
    @Override
    public void deleteLinksUsersHasTariffs(User user) throws SQLException {
        queryBuilder.execute(instance, DELETE_LINK_USERS_HAS_TRAFFICS, user.getId());
    }
}
