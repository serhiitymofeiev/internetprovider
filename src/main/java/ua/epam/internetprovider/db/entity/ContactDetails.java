package ua.epam.internetprovider.db.entity;

/**
 * User contact data class User with fields <b>city</b>, <b>street</b>, <b>home</b>, <b>apartment</b> <b>email</b> <b>phone</b>.
 *
 */
public class ContactDetails extends Entity {
    /** City field*/
    private String city;
    /** Street field*/
    private String street;
    /** House number field*/
    private String home;
    /** Apartment number field*/
    private String apartment;
    /** Email address field*/
    private String email;
    /** ПPhone number field*/
    private String phone;

    /**
     * Constructor - creation of a new object
     * @see ContactDetails#ContactDetails()
     */
    public ContactDetails() {
        super();
    }
    /**
     * Constructor - creation of a new object with defined properties
     * @param id - user ID
     * @see ContactDetails#ContactDetails()
     */
    public ContactDetails(long id) {
        super(id);
    }

    /**
     * The procedure for obtaining the user's city
     * @return returns the user's city
     */
    public String getCity() {
        return city;
    }

    /**
     * The procedure for setting the user address
     * @param city - address city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * The procedure for obtaining the user's street
     * @return returns the street of the user
     */
    public String getStreet() {
        return street;
    }

    /**
     * The procedure for setting the street name of the user's address
     * @param street - street name to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * The procedure for obtaining a user's house number
     * @return returns the user's home number
     */
    public String getHome() {
        return home;
    }

    /**
     * The procedure for setting the user's phone number
     * @param home - apartment number to set
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * The procedure for obtaining the user's apartment number
     * @return returns the user's apartment number
     */
    public String getApartment() {
        return apartment;
    }

    /**
     * The procedure for setting the user's apartment number
     * @param apartment - apartment number to set
     */
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    /**
     * The procedure for receiving a user's e-mail
     * @return returns the user's e-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * The procedure for setting up the user's e-mail
     * @param email - e-mail for set up
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The procedure for obtaining the user's phone number
     * @return returns the user's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * The procedure for setting the user's phone number
     * @param phone - phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
