package ua.epam.internetprovider.db.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User class with fields <b>login</b>, <b>password</b>, <b>firstName</b>, <b>lastName</b>, <b>surname</b>, <b>blocked</b>, <b>roleId</b>, <b>account</b>, <b>details</b> та <b>tariffs</b>.
 */
public class User extends Entity {
    /** Login userа*/
    private String login;
    /** User password*/
    private String password;
    /** First name of user*/
    private String firstName;
    /** Last name of user*/
    private String lastName;
    /** Surname of user*/
    private String surname;
    /** User lockout flag*/
    private boolean blocked;
    /** User role ID*/
    private int roleId;
    /** user account*/
    private Account account;
    /** User contact information*/
    private ContactDetails details;
    /** User tariffs*/
    private Set<Tariff> tariffs;

    /**
     * Constructor - creation of a new object
     * @see User#User()
     */
    public User() {
        this.tariffs = new HashSet<>();
    }

    /**
     * The procedure for obtaining a user login
     * @return returns the name of the user's login
     */
    public String getLogin() {
        return login;
    }

    /**
     * The procedure for establishing a user login
     * @param login - user login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * The procedure for obtaining a user password
     * @return returns the name of the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * User password setting procedure
     * @param password - user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The procedure for obtaining a username
     * @return returns the username
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The procedure for setting a username
     * @param firstName - first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * The procedure for obtaining a user's last name
     * @return returns the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * The procedure for setting the user's last name
     * @param lastName - last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * The procedure for obtaining the user's surname
     * @return returns the surname of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * The procedure for setting the user's surname
     * @param surname - surname of the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Procedure for obtaining the status of a user's lockout
     * @return  returns the lock status
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * User blocking/unblocking procedure
     * @param blocked - blocking flag
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * The procedure for obtaining a user role
     * @return returns the role of the user
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * User role setting procedure
     * @param roleId - user role
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Procedure for obtaining a user account
     * @return returns the user account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Procedure for setting up a user account
     * @param account - user password
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * The procedure for obtaining user contact data
     * @return returns the user's contact information
     */
    public ContactDetails getDetails() {
        return details;
    }

    /**
     * The procedure for establishing user contact data
     * @param details - user password
     */
    public void setDetails(ContactDetails details) {
        this.details = details;
    }

    /**
     * The procedure for obtaining a list of user tariffs
     * @return returns list of user tariffs
     */
    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    /**
     * User tariff setting procedure
     * @param tariffs - list of user tariffs
     */
    public void setTariffs(Set<Tariff> tariffs) {
        this.tariffs = tariffs;
    }
}
