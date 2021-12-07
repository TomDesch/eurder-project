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
        //todo check everything not null
        this.userId = UUID.randomUUID().toString();
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.emailAddress = userBuilder.emailAddress;
        this.address = userBuilder.address;
        this.phoneNumber = userBuilder.phoneNumber;
        this.userType = userBuilder.userType;
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


    public static final class UserBuilder {
        private String userId;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private Address address;
        private String phoneNumber;
        private UserType userType;

        private UserBuilder() {
        }

        public static UserBuilder aUser() {
            return new UserBuilder();
        }

        public UserBuilder withUserId(String userId) {
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

        public UserBuilder withUserType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
