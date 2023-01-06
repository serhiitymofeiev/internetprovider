package ua.epam.internetprovider.db.repository;

import ua.epam.internetprovider.db.builders.QueryBuilder;
import ua.epam.internetprovider.db.builders.ServiceQueryBuilder;
import ua.epam.internetprovider.db.entity.PackageServices;

import java.sql.SQLException;
import java.util.List;

/**
 * The class for implementing changes to the database related to the service package
 */
public class ServiceImpl implements IService {
    private static final String GET_ALL = "SELECT * FROM provider.services";
    private static final String GET_BY_ID = "SELECT id, name, description FROM provider.services WHERE id = ?";
    private static final String CREATE = "INSERT INTO provider.services (name, description) VALUES (?, ?)";
    //private static final String UPDATE = "UPDATE provider.services SET name = ?, description = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM provider.services WHERE id = ?";

    private final DBManager instance = DBManager.getInstance();

    /**
     * The procedure for obtaining a list of service packages
     * @return returns a list of all service packages
     */
    @Override
    public List<PackageServices> getAll() {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    /**
     * The procedure for obtaining a package of ID services
     * @param id Service package ID
     * @return returns a package of services
     */
    @Override
    public PackageServices getById(long id) {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        return queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    /**
     * The procedure for creating a package of services in the database
     * @param packageServices a service package object of type PackageServices
     */
    @Override
    public void create(PackageServices packageServices) throws SQLException {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        queryBuilder.execute(instance, CREATE, packageServices.getName(), packageServices.getDescription());
    }

    /**
     * The procedure for updating the service package in the database
     * @param packageServices a service package object of type PackageServices
     */
    @Override
    public void update(PackageServices packageServices) {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        try {
            queryBuilder.execute(instance, CREATE, packageServices.getName(), packageServices.getDescription(),packageServices.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The procedure for removing a service package from the database
     * @param id Service ID
     */
    @Override
    public void delete(long id) throws SQLException {
        QueryBuilder<PackageServices> queryBuilder = new ServiceQueryBuilder();
        queryBuilder.execute(instance, DELETE, id);
    }
}
