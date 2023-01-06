package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.builders.QueryBuilder;
import ua.epam.internetprovider.db.builders.TariffQueryBuilder;
import ua.epam.internetprovider.db.entity.Tariff;

import java.sql.SQLException;
import java.util.List;

/**
 * The class for implementing changes in the database related to service tariffs
 */
public class TariffImpl implements ITariff {
    private static final String GET_ALL = "SELECT * FROM provider.tariffs";
    private static final String GET_ALL_BY_SERVICES_ID = "SELECT * FROM provider.tariffs WHERE services_id = ?";
    private static final String GET_BY_ID = "SELECT id, name, description, price, services_id FROM provider.tariffs WHERE id = ?";
    private static final String GET_BY_NAME = "SELECT id, name, description, price, services_id FROM provider.tariffs WHERE name = ?";
    private static final String CREATE = "INSERT INTO provider.tariffs (name, price, description, services_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE provider.tariffs SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM provider.tariffs WHERE id = ?";

    private final DBManager instance = DBManager.getInstance();
    private final QueryBuilder<Tariff> queryBuilder = new TariffQueryBuilder();

    /**
     * The procedure for obtaining a list of service tariffs
     * @return returns a list of all service tariffs
     */
    @Override
    public List<Tariff> getAll() {
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    /**
     * The procedure for receiving service tariffs by package ID
     * @param id ID of the service package
     * @return returns a list of tariffs
     */
    @Override
    public List<Tariff> getAllByServiceId(long id) {
        return this.queryBuilder.executeAndReturnList(instance, GET_ALL_BY_SERVICES_ID, id);
    }

    /**
     * The procedure for obtaining a service tariff by name
     * @param name the name of the service tariff
     * @return returns the tariff
     */
    @Override
    public Tariff getByName(String name) {
        return this.queryBuilder.executeAndReturn(instance, GET_BY_NAME, name);
    }

    /**
     * Процедура отримання тарифу послуг по ID тарифу
     * @param id Service tariff ID
     * @return returns the tariff
     */
    @Override
    public Tariff getById(long id) {
        return queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    /**
     * The procedure for creating a package of services in the database
     * @param tariff service tariff object of type Tariff
     */
    @Override
    public void create(Tariff tariff) {
        try {
            queryBuilder.execute(instance, CREATE, tariff.getName(), tariff.getPrice(), tariff.getDescription(), tariff.getServiceId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The procedure for updating the tariff in the database
     * @param tariff object of the package of services of type Tariff
     */
    @Override
    public void update(Tariff tariff) {
        try {
            queryBuilder.execute(instance, UPDATE, tariff.getName(), tariff.getDescription(), tariff.getPrice(), tariff.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The procedure for removing the tariff from the database
     * @param id Tariff ID
     */
    @Override
    public void delete(long id) {
        try {
            queryBuilder.execute(instance, DELETE, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
