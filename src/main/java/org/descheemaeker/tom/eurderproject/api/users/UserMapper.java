package org.descheemaeker.tom.eurderproject.api.users;

import org.descheemaeker.tom.eurderproject.api.users.dto.CreateUserDto;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;
import org.descheemaeker.tom.eurderproject.domain.User;

public enum UserMapper {
    USER_MAPPER;

    UserMapper() {
    }

    public UserDto userToDto(User user) {
        return new UserDto(
                user.getUserId()
                , user.getUserType()
                , user.getFirstName()
                , user.getLastName()
                , user.getEmailAddress()
                , user.getAddress()
                , user.getPhoneNumber());
    }


    public User dtoToUser(CreateUserDto createUserDto) {
        return User.UserBuilder.aUser()
                .withUserType(createUserDto.userType())
                .withFirstName(createUserDto.firstName())
                .withLastName(createUserDto.lastName())
                .withAddress(createUserDto.address())
                .withEmailAddress(createUserDto.emailAddress())
                .withPhoneNumber(createUserDto.phoneNumber())
                .build();
    }
}
