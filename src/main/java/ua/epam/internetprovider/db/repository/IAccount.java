package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.entity.Account;


public interface IAccount extends IEntity<Account> {
    long newNumberContract();
}
