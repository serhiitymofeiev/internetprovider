package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.entity.User;
import ua.epam.internetprovider.db.repository.IUser;
import ua.epam.internetprovider.db.repository.UserImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * The service class of the service implementation of changes in the database that affects users
 */
public class UserServiceImpl implements IUserService {
    private final IUser repo = new UserImpl();

    /**
     * Service procedure for obtaining a list of all users
     * @return returns a list of all users
     */
    @Override
    public List<User> findAll() {
        return this.repo.getAll();
    }

    /**
     * Service procedure for obtaining a user by ID
     * @param id User ID
     * @return returns a user object
     */
    @Override
    public User find(long id) {
        return this.repo.getById(id);
    }

    /**
     * Service procedure for creating a user in the database
     * @param user an account object of type User
     */
    @Override
    public void save(User user) {
        try {
            this.repo.create(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Service procedure for updating user data in the database
     * @param user a user object of type User record
     */
    @Override
    public void update(User user) {
        this.repo.update(user);
    }

    /**
     * Service procedure for deleting a user from the database
     * @param id User ID
     */
    @Override
    public void remove(int id) {
        try {
            this.repo.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Service procedure for receiving a user by login
     * @param login user login
     * @return returns a user object
     */
    @Override
    public User findByLogin(String login) {
        return this.repo.getByLogin(login);
    }

    /**
     * Service procedure for receiving user tariffs
     * @param user a user object of type User
     * @return returns a list of tariffs
     */
    @Override
    public List<Tariff> findUserTariffs(User user) {
        return this.repo.getTariffs(user);
    }

    /**
     * Service procedure for assigning tariffs to the user
     * @param user a user object of type User
     * @param tariffsId a list of tariff IDs
     */
    @Override
    public void saveLinksUsersHasTariffs(User user, String[] tariffsId) {
        try {
            this.repo.addLinksUsersHasTariffs(user, tariffsId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Service procedure for receiving user tariffs
     * @param user a user object of type User
     */
    @Override
    public void removeLinksUsersHasTariffs(User user) {
        try {
            this.repo.deleteLinksUsersHasTariffs(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
