package org.descheemaeker.tom.eurderproject.services;

import org.descheemaeker.tom.eurderproject.api.users.User;
import org.descheemaeker.tom.eurderproject.api.users.dto.CreateUserDto;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;
import org.descheemaeker.tom.eurderproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.descheemaeker.tom.eurderproject.api.users.UserMapper.USER_MAPPER;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserDto addUser(User user) {
        userRepository.addUser(user);
        return USER_MAPPER.userToDto(user);
    }

    public UserDto addUser(CreateUserDto createUserDto) {
        return addUser(USER_MAPPER.dtoToUser(createUserDto));
    }
}
