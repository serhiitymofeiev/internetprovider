package ua.epam.internetprovider.db.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Service package class with fields<b>number</b>, <b>description</b> та <b>tariffs</b>.
 * In fact, now creating new packages and changing the name of packages is not used, since the pages are built on the names of specific packages
 */
public class PackageServices extends Entity {
    /** The name of the service package*/
    private String name;

    /** Description of the service package*/
    private String description;

    /** List of service package tariffs*/
    private Set<Tariff> tariffs;

    /**
     * Constructor - creation of a new object
     * @see PackageServices#PackageServices()
     */
    public PackageServices() {
        this.tariffs = new HashSet<>();
    }

    /**
     * The procedure for obtaining the name of the service package
     * @return returns the service package name
     */
    public String getName() {
        return name;
    }

    /**
     * The procedure for setting the name of the service package
     * @param name - the name of the service package
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The procedure for obtaining a description of the service package
     * @return returns the service package name
     */
    public String getDescription() {
        return description;
    }

    /**
     * The procedure for setting the description of the service package
     * @param description - description of the service package
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The procedure for obtaining a list of service package tariffs
     * @return returns a list of service package rates
     */
    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    /**
     * The procedure for establishing a list of tariffs for a package of services
     * @param tariffs - list of tariffs
     */
    public void setTariffs(Set<Tariff> tariffs) {
        this.tariffs = tariffs;
    }
}
