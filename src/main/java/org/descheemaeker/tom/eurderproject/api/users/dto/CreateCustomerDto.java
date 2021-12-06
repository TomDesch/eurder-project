package org.descheemaeker.tom.eurderproject.api.users.dto;

import org.descheemaeker.tom.eurderproject.api.users.Address;

public record CreateCustomerDto(String firstName, String lastName, String emailAddress, Address address,
                                String phoneNumber) {}
