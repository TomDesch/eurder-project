package org.descheemaeker.tom.eurderproject.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;


@Embeddable
public final class Address {
    @Column(name = "STREET_NAME", nullable = false)
    private String streetName;

    @Column(name = "HOUSE_NUMBER", nullable = false)
    private String houseNumber;

    @Column(name = "POSTAL_CODE", nullable = false)
    private String postalCode;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    public Address(String streetName, String houseNumber, String postalCode, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return streetName + houseNumber + '\'' +
                postalCode + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(streetName, address.streetName) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(postalCode, address.postalCode) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, houseNumber, postalCode, country);
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
