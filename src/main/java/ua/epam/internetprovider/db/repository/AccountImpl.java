package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.builders.AccountQueryBuilder;
import ua.epam.internetprovider.db.builders.QueryBuilder;
import ua.epam.internetprovider.db.entity.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * The class that implements account-specific changes to the database
 */
public class AccountImpl implements IAccount {
    private static final String CREATE = "INSERT INTO provider.accounts (id, number, balance) VALUES (?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM provider.accounts";
    private static final String GET_BY_ID = "SELECT id, number, balance FROM provider.accounts WHERE id = ?";
    private static final String UPDATE = "UPDATE provider.accounts SET balance = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM provider.accounts WHERE id = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'provider' AND TABLE_NAME = 'users'";
    private static final String GET_MAX_ID = "SELECT MAX(id) FROM accounts";

    private DBManager instance = DBManager.getInstance();
    private QueryBuilder queryBuilder = new AccountQueryBuilder();

    /**
     * The procedure for obtaining a list of all accounts
     * @return returns a list of all accounts
     */
    @Override
    public List<Account> getAll() {
        return this.queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    /**
     * The procedure for obtaining an account by ID
     * @param id Account ID
     * @return returns the account
     */
    @Override
    public Account getById(long id) {
        return (Account) this.queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    /**
     * Procedure for creating an account in the database
     * @param account an account object of type Account
     */
    @Override
    public void create(Account account) {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        try {
            this.queryBuilder.execute(instance, CREATE, id, account.getNumber(), account.getBalance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Procedure for updating account data in the database
     * @param account an account object of type Account
     */
    @Override
    public void update(Account account) {
        try {
            this.queryBuilder.execute(instance, UPDATE, account.getBalance(), account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Procedure for deleting an account in the database
     * @param id Account ID
     */
    @Override
    public void delete(long id) {
        try {
            this.queryBuilder.execute(instance, DELETE, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The procedure for obtaining a new contract number
     * @return returns the contract number, which grows incrementally
     */
    @Override
    public long newNumberContract() {
        long accountNumber = 0;
        long id = queryBuilder.getNextAutoIncrement(instance, GET_MAX_ID);
        Account account = getById(id);

        if (account != null) {
            accountNumber = 1 + account.getNumber();
        }

        return accountNumber;
    }
}
