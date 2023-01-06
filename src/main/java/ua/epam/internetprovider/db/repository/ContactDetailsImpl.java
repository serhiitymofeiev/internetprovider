package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.builders.ContactDetailsQueryBuilder;
import ua.epam.internetprovider.db.builders.QueryBuilder;
import ua.epam.internetprovider.db.entity.ContactDetails;

import java.sql.SQLException;
import java.util.List;

/**
 * The class that implements changes in the database related to user contact data
 */
public class ContactDetailsImpl implements IContactDetails {
    private static final String CREATE = "INSERT INTO provider.contact_details (id, city, street, home, apartment, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM provider.contact_details";
    private static final String GET_BY_ID = "SELECT id, city, street, home, apartment, email, phone FROM provider.contact_details WHERE id = ?";
    private static final String UPDATE = "UPDATE provider.contact_details SET email = ?, phone = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM provider.contact_details WHERE id = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'provider' AND TABLE_NAME = 'users'";

    private DBManager instance = DBManager.getInstance();
    private QueryBuilder queryBuilder = new ContactDetailsQueryBuilder();

    /**
     * The procedure for obtaining a list of contact data of users
     * @return returns a list of all accounts
     */
    @Override
    public List<ContactDetails> getAll() {
        return this.queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    /**
     * The procedure for obtaining contact data by ID
     * @param id User ID
     * @return returns contact details
     */
    @Override
    public ContactDetails getById(long id) {
        return (ContactDetails) this.queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    /**
     * The procedure for creating contact data in the database
     * @param details contact data object of type ContactDetails
     */
    @Override
    public void create(ContactDetails details) {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        try {
            queryBuilder.execute(instance, CREATE, id, details.getCity(), details.getStreet(), details.getHome(), details.getApartment(), details.getEmail(), details.getPhone());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The procedure for updating contact data in the database
     * @param details contact data object of type ContactDetails
     */
    @Override
    public void update(ContactDetails details) {
        try {
            this.queryBuilder.execute(instance, UPDATE, details.getEmail(), details.getPhone(), details.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Procedure for deleting contact data from the database
     * @param id User ID
     */
    @Override
    public void delete(long id) {
        try {
            this.queryBuilder.execute(instance, DELETE, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
