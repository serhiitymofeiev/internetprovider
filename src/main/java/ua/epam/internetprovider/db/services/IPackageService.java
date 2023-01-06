package ua.epam.internetprovider.db.services;

import ua.epam.internetprovider.db.entity.PackageServices;

import java.util.List;

public interface IPackageService {

    List<PackageServices> findAll();

    PackageServices find(long id);

    void save(PackageServices service);

    void update(PackageServices service);

    void remove(long id);
}
