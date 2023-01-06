package ua.epam.internetprovider.db.builders;

import ua.epam.internetprovider.db.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * The class for working with account objects
 */
public class AccountQueryBuilder extends QueryBuilder<Account> {

    /**
     * Procedure for obtaining a list of accounts
     * @param rs - recordset with accounts
     * @return returns a list of accounts of type Account
     * @throws SQLException the SQL exception
     */
    @Override
    public List<Account> getListOfResult(ResultSet rs) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            Account account = new Account();
            account.setId(rs.getLong("id"));
            account.setNumber(rs.getInt("number"));
            account.setBalance(rs.getDouble("balance"));
            accounts.add(account);
        }
        return accounts;
    }

    /**
     * Procedure for obtaining one user account
     * @param rs - recordset with accounts
     * @return returns an account of type Account
     * @throws SQLException the SQL exception
     */
    @Override
    public Account getResult(ResultSet rs) throws SQLException {
        Account account = new Account();
        while (rs.next()) {
            account.setId(rs.getLong("id"));
            account.setNumber(rs.getInt("number"));
            account.setBalance(rs.getDouble("balance"));
        }
        return account;
    }
}
