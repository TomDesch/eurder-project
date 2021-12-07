package org.descheemaeker.tom.eurderproject.api.users.dto;

import org.descheemaeker.tom.eurderproject.api.users.Address;
import org.descheemaeker.tom.eurderproject.api.users.UserType;

import java.util.UUID;

public record CreateUserDto(UUID userId, UserType userType, String firstName, String lastName, String emailAddress, Address address,
                            String phoneNumber) {}
