package org.descheemaeker.tom.eurderproject.api.users.dto;

import org.descheemaeker.tom.eurderproject.domain.Address;
import org.descheemaeker.tom.eurderproject.domain.UserType;


public record CreateUserDto(UserType userType, String firstName, String lastName, String emailAddress, Address address,
                            String phoneNumber, String password) {}
