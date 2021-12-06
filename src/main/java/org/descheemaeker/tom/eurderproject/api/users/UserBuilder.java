package org.descheemaeker.tom.eurderproject.api.users;

import java.util.UUID;

public class UserBuilder {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Address address;
    private String phoneNumber;


    public UserBuilder() {
    }

    public UserBuilder userBuilder() {
        return new UserBuilder();
    }

    public User build() {
        return new User(this);
    }

    public UserBuilder withUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}