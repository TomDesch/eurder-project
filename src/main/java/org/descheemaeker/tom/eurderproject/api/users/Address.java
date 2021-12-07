package org.descheemaeker.tom.eurderproject.api.users;

import java.util.Objects;

public record Address(String streetName, String houseNumber, String postalCode, String country) {

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
}
