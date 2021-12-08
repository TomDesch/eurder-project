package org.descheemaeker.tom.eurderproject.domain;

import org.descheemaeker.tom.eurderproject.api.users.Address;
import org.descheemaeker.tom.eurderproject.api.users.UserType;
import org.descheemaeker.tom.eurderproject.exception.RequiredFieldIsNullException;

import java.util.Objects;
import java.util.UUID;

import static org.descheemaeker.tom.eurderproject.api.users.UserType.ADMIN;

public class User {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String phoneNumber;
    private final UserType userType;
    private String password; //not safe
    private static final boolean ADMIN_OVERRIDES = true;

    public User(UserBuilder userBuilder) {
        this.userId = UUID.randomUUID().toString();
        this.userType = checkRequiredFieldForIllegalNull(userBuilder.userType, !ADMIN_OVERRIDES);
        this.firstName = checkRequiredFieldForIllegalNull(userBuilder.firstName, ADMIN_OVERRIDES);
        this.lastName = checkRequiredFieldForIllegalNull(userBuilder.lastName, ADMIN_OVERRIDES);
        this.emailAddress = checkRequiredFieldForIllegalNull(userBuilder.emailAddress, !ADMIN_OVERRIDES);
        this.address = checkRequiredFieldForIllegalNull(userBuilder.address, ADMIN_OVERRIDES);
        this.phoneNumber = checkRequiredFieldForIllegalNull(userBuilder.phoneNumber, ADMIN_OVERRIDES);
        this.password = checkRequiredFieldForIllegalNull(userBuilder.password, !ADMIN_OVERRIDES);

    }

    private <T> T checkRequiredFieldForIllegalNull(T input, boolean adminOverrides) {
        if (input == null && !(adminOverrides && this.userType == ADMIN)) {
            throw new RequiredFieldIsNullException();
        }
        return input;
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

    public String getPassword() {
        return password;
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
                ", password='" + password + '\'' +
                '}';
    }

    public boolean isAbleTo(Features feature) {
        return feature.isPermitted(this.userType);
    }

    public static final class UserBuilder {
        private String userId;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private Address address;
        private String phoneNumber;
        private UserType userType;
        private String password;

        public UserBuilder() {
        }

        public static UserBuilder aUser() {
            return new UserBuilder();
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
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

        public UserBuilder withUserType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
