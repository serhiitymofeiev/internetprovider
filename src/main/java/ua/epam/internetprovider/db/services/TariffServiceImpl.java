package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.Tariff;
import ua.epam.internetprovider.db.repository.ITariff;
import ua.epam.internetprovider.db.repository.TariffImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * The service class for implementing changes in the database related to service tariffs
 */
public class TariffServiceImpl implements ITariffService {
    private final ITariff repo = new TariffImpl();

    /**
     * The procedure for obtaining a list of service tariffs
     * @return returns a list of all service tariffs
     */
    @Override
    public List<Tariff> findAll() {
        return this.repo.getAll();
    }

    /**
     * The procedure for receiving service tariffs by service tariff ID
     * @param id Service tariff ID
     * @return returns a list of tariffs
     */
    @Override
    public Tariff find(long id) {
        return this.repo.getById(id);
    }

    /**
     * The procedure for obtaining a service tariff by name
     * @param name the name of the service tariff
     * @return returns the tariff
     */
    @Override
    public Tariff find(String name) {
        return this.repo.getByName(name);
    }

    /**
     * The procedure for receiving service tariffs by package ID
     * @param id ID of the service package
     * @return returns a list of tariffs
     */
    @Override
    public List<Tariff> findAllById(long id) {
        return this.repo.getAllByServiceId(id);
    }

    /**
     * Процедура створення пакету послуг в базі даних
     * @param tariff service tariff object of type Tariff
     */
    @Override
    public void save(Tariff tariff) throws SQLException {
        this.repo.create(tariff);
    }

    /**
     * The procedure for updating the tariff in the database
     * @param tariff object of the package of services of type Tariff
     */
    @Override
    public void update(Tariff tariff) {
        this.repo.update(tariff);
    }

    /**
     * The procedure for removing the tariff from the database
     * @param id Tariff ID
     */
    @Override
    public void remove(long id) throws SQLException {
        this.repo.delete(id);
    }
}
