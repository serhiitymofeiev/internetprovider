package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.Tariff;

import java.sql.SQLException;
import java.util.List;

public interface ITariffService {

    List<Tariff> findAll();

    List<Tariff> findAllById(long id);

    Tariff find(long id);

    Tariff find(String name);

    void save(Tariff tariff) throws SQLException;

    void update(Tariff tariff);

    void remove(long id) throws SQLException;
}
