package ua.epam.internetprovider.db.builders;

import ua.epam.internetprovider.db.entity.PackageServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for working with objects of service packages
 */
public class ServiceQueryBuilder extends QueryBuilder<PackageServices> {
    /**
     * The procedure for obtaining a list of service packages
     * @param rs - recordset with record set with service packages
     * @return returns a list of data of type PackageServices
     * @throws SQLException the SQL exception
     */
    @Override
    public List<PackageServices> getListOfResult(ResultSet rs) throws SQLException {
        List<PackageServices> services = new ArrayList<>();
        while (rs.next()) {
            PackageServices packageServices = new PackageServices();
            packageServices.setId(rs.getLong("id"));
            packageServices.setName(rs.getString("name"));
            packageServices.setDescription(rs.getString("description"));
            services.add(packageServices);
        }
        return services;
    }

    /**
     * The procedure for obtaining one service package account
     * @param rs - recordset with service packages
     * @return returns a single account of type PackageServices
     * @throws SQLException the SQL exception
     */
    @Override
    public PackageServices getResult(ResultSet rs) throws SQLException {
        PackageServices packageService = new PackageServices();
        while (rs.next()) {
            packageService.setId(rs.getLong("id"));
            packageService.setName(rs.getString("name"));
            packageService.setDescription(rs.getString("description"));

        }
        return packageService;
    }
}
