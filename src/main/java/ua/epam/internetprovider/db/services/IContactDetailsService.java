package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.ContactDetails;

import java.util.List;

public interface IContactDetailsService {

    List<ContactDetails> findAll();

    ContactDetails find(long id);

    void save(ContactDetails account);

    void update(ContactDetails account);

    void remove(int id);
}
