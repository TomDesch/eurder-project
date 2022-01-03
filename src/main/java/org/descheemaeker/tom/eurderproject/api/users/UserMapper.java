package org.descheemaeker.tom.eurderproject.api.users;

import org.descheemaeker.tom.eurderproject.api.users.dto.CreateUserDto;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;
import org.descheemaeker.tom.eurderproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class  UserMapper {
    @Autowired
    public UserMapper() {
    }

    public UserDto userToDto(User user) {
        return new UserDto(
                user.getUserId()
                , user.getUserType()
                , user.getFirstName()
                , user.getLastName()
                , user.getEmailAddress()
                , user.getAddress()
                , user.getPhoneNumber()
                , user.getPassword());
    }


    public User dtoToUser(CreateUserDto createUserDto) {
        return User.UserBuilder.aUser()
                .withUserType(createUserDto.userType())
                .withFirstName(createUserDto.firstName())
                .withLastName(createUserDto.lastName())
                .withAddress(createUserDto.address())
                .withEmailAddress(createUserDto.emailAddress())
                .withPhoneNumber(createUserDto.phoneNumber())
                .withPassword(createUserDto.password())
                .build();
    }
}
