package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.Account;
import ua.epam.internetprovider.db.repository.AccountImpl;
import ua.epam.internetprovider.db.repository.IAccount;

import java.sql.SQLException;
import java.util.List;

/**
 * The service class for implementing account-related database changes
 */
public class AccountServiceImpl implements IAccountService {
    private final IAccount repo = new AccountImpl();

    /**
     * Процедура отримання переліку усіх обікових записів
     * @return повертає список усіх облікових записів
     */
    @Override
    public List<Account> findAll() {
        return this.repo.getAll();
    }

    /**
     * Процедура отримання облікового запису по ID
     * @param id ID облікового запису
     * @return повертає обліковий запис
     */
    @Override
    public Account find(long id) {
        return this.repo.getById(id);
    }

    /**
     * Процедура сервісу створення облікового запису
     * @param account об'єкт облікового запису
     *
     */
    @Override
    public void save(Account account) {
        try {
            this.repo.create(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Процедура сервісу оновлення даних облікового запису в базі даних
     * @param account об'єкт облікового запису типу Account
     */
    @Override
    public void update(Account account) {
        this.repo.update(account);
    }

    /**
     * Процедура сервісу видалення облікового запису в базі даних
     * @param id ID облікового запису
     */
    @Override
    public void remove(long id) {
        try {
            this.repo.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Процедура  сервісу отримання нового номеру договору
     * @return повертає номер договору, що зростає інкрементно
     */
    @Override
    public long getNumberContract() {
        return repo.newNumberContract();
    }
}
