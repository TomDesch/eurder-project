package org.descheemaeker.tom.eurderproject.api.users.dto;

import org.descheemaeker.tom.eurderproject.api.users.Address;
import org.descheemaeker.tom.eurderproject.api.users.UserType;

import java.util.Objects;

public record UserDto(String userId, UserType userType, String firstName, String lastName, String emailAddress,
                      Address address,
                      String phoneNumber,
                      String password) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;
        return Objects.equals(userId, userDto.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
