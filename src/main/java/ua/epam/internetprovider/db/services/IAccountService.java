package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.Account;

import java.util.List;

public interface IAccountService {

    List<Account> findAll();

    Account find(long id);

    void save(Account account);

    void update(Account account);

    void remove(long id);

    long getNumberContract();
}
