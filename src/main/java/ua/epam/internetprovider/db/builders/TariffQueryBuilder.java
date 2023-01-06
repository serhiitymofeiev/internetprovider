package ua.epam.internetprovider.db.builders;

import ua.epam.internetprovider.db.entity.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with tariff objects
 */
public class TariffQueryBuilder extends QueryBuilder<Tariff> {
    /**
     * The procedure for obtaining a list of tariffs
     * @param rs - record set with tariffs
     * @return returns a list of Tariff type data
     * @throws SQLException the SQL exception
     */
    @Override
    public List<Tariff> getListOfResult(ResultSet rs) throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        while (rs.next()) {
            Tariff tariff = new Tariff();
            tariff.setId(rs.getLong("id"));
            tariff.setName(rs.getString("name"));
            tariff.setDescription(rs.getString("description"));
            tariff.setPrice(rs.getDouble("price"));
            tariff.setServiceId(rs.getLong("services_id"));
            tariffs.add(tariff);
        }
        return tariffs;
    }

    /**
     * The procedure for obtaining one tariff
     * @param rs - record set with service packages
     * @return returns one tariff of type Tariff
     * @throws SQLException the SQL exception
     */
    @Override
    public Tariff getResult(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        while (rs.next()) {
            tariff.setId(rs.getLong("id"));
            tariff.setName(rs.getString("name"));
            tariff.setDescription(rs.getString("description"));
            tariff.setPrice(rs.getDouble("price"));
            tariff.setServiceId(rs.getLong("services_id"));
        }
        return tariff;
    }
}
