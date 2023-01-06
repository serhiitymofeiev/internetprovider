package ua.epam.internetprovider.db.builders;

import ua.epam.internetprovider.db.entity.ContactDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for working with contact information objects
 */
public class ContactDetailsQueryBuilder extends QueryBuilder<ContactDetails> {

    /**
     * The procedure for obtaining a list of user contact data
     * @param rs - record set with contact data
     * @return returns a list of credentials of type ContactDetails
     * @throws SQLException the SQL exception
     */
    @Override
    public List<ContactDetails> getListOfResult(ResultSet rs) throws SQLException {
        List<ContactDetails> details1 = new ArrayList<>();
        while (rs.next()) {
            ContactDetails details = new ContactDetails();
            details.setId(rs.getLong("id"));
            details.setCity(rs.getString("city"));
            details.setStreet(rs.getString("street"));
            details.setHome(rs.getString("home"));
            details.setApartment(rs.getString("apartment"));
            details.setEmail(rs.getString("email"));
            details.setPhone(rs.getString("phone"));
            details1.add(details);
        }
        return details1;
    }

    /**
     * Procedure for obtaining one user account
     * @param rs - record set with contact data
     * @return returns a single account of type ContactDetails
     * @throws SQLException the SQL exception
     */
    @Override
    public ContactDetails getResult(ResultSet rs) throws SQLException {
        ContactDetails detail = new ContactDetails();
        while (rs.next()) {
            detail.setId(rs.getLong("id"));
            detail.setCity(rs.getString("city"));
            detail.setStreet(rs.getString("street"));
            detail.setHome(rs.getString("home"));
            detail.setApartment(rs.getString("apartment"));
            detail.setEmail(rs.getString("email"));
            detail.setPhone(rs.getString("phone"));
        }
        return detail;
    }
}
