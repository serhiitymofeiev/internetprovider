package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.PackageServices;
import ua.epam.internetprovider.db.repository.IService;
import ua.epam.internetprovider.db.repository.ServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Service class for implementing changes in the database related to the ambassador packageг
 */
public class PackageServiceImpl implements IPackageService {
    private final IService repo = new ServiceImpl();

    /**
     * The procedure for obtaining a list of service packages
     * @return returns a list of all service packages
     */
    @Override
    public List<PackageServices> findAll() {
        return this.repo.getAll();
    }

    /**
     * The procedure for obtaining a package of ID services
     * @param id Service package ID
     * @return returns a package of services
     */
    @Override
    public PackageServices find(long id) {
        return this.repo.getById(id);
    }

    /**
     * The procedure for creating a package of services in the database
     * @param service a service package object of type PackageServices
     */
    @Override
    public void save(PackageServices service) {
        try {
            this.repo.create(service);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The procedure for updating the service package in the database
     * @param service a service package object of type PackageServices
     */
    @Override
    public void update(PackageServices service) {
        this.repo.update(service);
    }

    /**
     * The procedure for removing a service package from the database
     * @param id Service ID
     */
    @Override
    public void remove(long id) {
        try {
            this.repo.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
