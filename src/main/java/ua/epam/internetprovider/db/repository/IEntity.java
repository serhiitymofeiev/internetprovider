package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface IEntity<T extends Entity> {
    List<T> getAll();

    T getById(long id);

    void create(T t) throws SQLException;

    void update(T t);

    void delete(long id) throws SQLException;
}
