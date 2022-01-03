package org.descheemaeker.tom.eurderproject.api.users.dto;

import org.descheemaeker.tom.eurderproject.domain.Address;
import org.descheemaeker.tom.eurderproject.domain.UserType;

import java.util.Objects;
import java.util.UUID;

public record UserDto(UUID userId, UserType userType, String firstName, String lastName, String emailAddress,
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
