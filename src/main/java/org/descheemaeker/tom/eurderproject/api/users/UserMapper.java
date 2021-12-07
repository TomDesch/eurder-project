package org.descheemaeker.tom.eurderproject.api.users;

import org.descheemaeker.tom.eurderproject.api.users.dto.CreateUserDto;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;

public enum UserMapper {
    USER_MAPPER;

    public UserDto userToDto(User user) {
        return new UserDto(user.getUserId()
                , user.getUserType()
                , user.getFirstName()
                , user.getLastName()
                , user.getEmailAddress()
                , user.getAddress()
                , user.getPhoneNumber());
    }


    public User dtoToUser(CreateUserDto createUserDto) {
        return new UserBuilder()
                .withUserId(createUserDto.userId())
                .withUserType(createUserDto.userType())
                .withFirstName(createUserDto.firstName())
                .withLastName(createUserDto.lastName())
                .withAddress(createUserDto.address())
                .withEmailAddress(createUserDto.emailAddress())
                .withPhoneNumber(createUserDto.phoneNumber())
                .build();
    }

    public User dtoToUser(UserDto userDto) {
        return new UserBuilder()
                .withUserId(userDto.userId())
                .withUserType(userDto.userType())
                .withFirstName(userDto.firstName())
                .withLastName(userDto.lastName())
                .withAddress(userDto.address())
                .withEmailAddress(userDto.emailAddress())
                .withPhoneNumber(userDto.phoneNumber())
                .build();
    }
}
