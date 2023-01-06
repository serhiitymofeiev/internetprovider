package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.ContactDetails;
import ua.epam.internetprovider.db.repository.ContactDetailsImpl;
import ua.epam.internetprovider.db.repository.IContactDetails;

import java.sql.SQLException;
import java.util.List;

/**
 * Клас сервісу імплементації змін в базі даних, що стосується контактних даних користувачів
 */
public class ContactDetailsServiceImpl implements IContactDetailsService {
    private final IContactDetails repo = new ContactDetailsImpl();

    /**
     * Процедура отримання переліку контактних даних уміх користувачів
     * @return повертає список усіх облікових записів
     */
    @Override
    public List<ContactDetails> findAll() {
        return this.repo.getAll();
    }

    /**
     * Процедура отримання контактних даних по ID
     * @param id ID користувача
     * @return повертає контактні дані
     */
    @Override
    public ContactDetails find(long id) {
        return this.repo.getById(id);
    }

    /**
     * Процедура створення контактних даних в базі даних
     * @param account об'єкт контактних даних типу ContactDetails
     */
    @Override
    public void save(ContactDetails account) {
        try {
            this.repo.create(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Процедура оновлення контактних даних в базі даних
     * @param account об'єкт контактних даних типу ContactDetails
     */
    @Override
    public void update(ContactDetails account) {
        this.repo.update(account);
    }

    /**
     * Процедура видалення контактних даних з бази даних
     * @param id ID користувача
     */
    @Override
    public void remove(int id) {
        try {
            this.repo.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
