package ua.epam.internetprovider.db.entity;

/**
 * Account class with <b>number</b> and <b>balance</b> fields.
 *
 */
public class Account extends Entity {
    /** Account sequence number field*/
    private volatile long number;

    /** Account balance field*/
    private double balance;

    /**
     * Constructor - creation of a new object
     * @see Account#Account()
     */
    public Account() {

    }

    /**
     * Constructor - creation of a new object with defined properties
     * @param id - user ID
     * @see Account#Account()
     */
    public Account(long id) {
        super(id);
    }

    /**
     * The procedure for obtaining an account sequence number
     * @return returns the sequence number of the account
     */
    public long getNumber() {
        return number;
    }

    /**
     * The procedure for setting the sequence number of the account
     * @param number - sequence number to install
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Procedure for obtaining account balance
     * @return returns the account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Account balance setting procedure
     * @param balance - balance amount to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
