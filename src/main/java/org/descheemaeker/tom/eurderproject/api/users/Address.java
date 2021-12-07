package org.descheemaeker.tom.eurderproject.api.users;

public record Address(String streetName, String houseNumber, int postalCode, String state,
                      String country) {
}
