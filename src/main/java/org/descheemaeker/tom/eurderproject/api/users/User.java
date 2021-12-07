package org.descheemaeker.tom.eurderproject.api.users;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;
    private final UserType userType;

    public User(UserBuilder userBuilder) {
        this.userId = UUID.randomUUID().toString();
        this.firstName = userBuilder.getFirstName();
        this.lastName = userBuilder.getLastName();
        this.emailAddress = userBuilder.getEmailAddress();
        this.address = userBuilder.getAddress();
        this.phoneNumber = userBuilder.getPhoneNumber();
        this.userType = userBuilder.getUserType();
    }

    public String getUserId() {
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

    public UserType getUserType() {
        return userType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
