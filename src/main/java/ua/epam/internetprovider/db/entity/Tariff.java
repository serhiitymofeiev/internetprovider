package ua.epam.internetprovider.db.entity;

import java.util.Objects;

/**
 * Tariff class with fields <b>name</b>, <b>description</b> , <b>price</b>  та <b>serviceId</b>.
 */
public class Tariff extends Entity {
    /** Tariff name*/
    private String name;

    /** Tariff description*/
    private String description;

    /** Tariff price*/
    private double price;

    /** ID service tariff*/
    private long serviceId;

    /**
     * The procedure for obtaining the name of the tariff
     * @return returns the tariff name
     */
    public String getName() {
        return name;
    }

    /**
     * The procedure for setting the name of the tariff
     * @param name - the name of the tariff
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The procedure for obtaining a description of the tariff
     * @return returns the tariff name
     */
    public String getDescription() {
        return description;
    }

    /**
     * The procedure for setting the description of the service package
     * @param description - tariff description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The procedure for obtaining the tariff price
     * @return returns the tariff price
     */
    public double getPrice() {
        return price;
    }

    /**
     * The procedure for setting the tariff price
     * @param price - tariff price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * The procedure for obtaining a tariff service ID
     * @return returns the tariff service ID
     */
    public long getServiceId() {
        return serviceId;
    }

    /**
     * The procedure for setting the tariff service ID
     * @param serviceId - Tariff service ID
     */
    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * The procedure for comparing tariffs by full match or name
     * @param o - tariff object
     * @return returns boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tariff)) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(name, tariff.name);
    }

    /**
     * The procedure for obtaining a tariff hash
     * @return numeric value of the tariff hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
