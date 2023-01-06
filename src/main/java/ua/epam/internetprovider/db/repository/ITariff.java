package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.entity.Tariff;

import java.util.List;

public interface ITariff extends IEntity<Tariff> {

    List<Tariff> getAllByServiceId(long id);

    Tariff getByName(String name);
}
