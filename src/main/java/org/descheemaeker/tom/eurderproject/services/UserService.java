package org.descheemaeker.tom.eurderproject.services;

import org.descheemaeker.tom.eurderproject.api.users.UserMapper;
import org.descheemaeker.tom.eurderproject.domain.User;
import org.descheemaeker.tom.eurderproject.api.users.dto.CreateUserDto;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;
import org.descheemaeker.tom.eurderproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.descheemaeker.tom.eurderproject.domain.UserType.ADMIN;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        initiateBasicAccounts();
    }

    private void initiateBasicAccounts() {
        User admin = User.UserBuilder.aUser()
                .withUserType(ADMIN)
                .withFirstName("non null")
                .withLastName("non null either")
                .withEmailAddress("admin")
                .withPassword("admin")
                .build();

        addUser(admin);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }


    public UserDto addUser(User user) {
        userRepository.addUser(user);
        return userMapper.userToDto(user);
    }

    public UserDto addUser(CreateUserDto createUserDto) {
        return addUser(userMapper.dtoToUser(createUserDto));
    }

    public User getByEmail(String emailAddress) {
        return getAllUsers().stream()
                .filter(user -> user.getEmailAddress().equalsIgnoreCase(emailAddress))
                .findFirst().orElse(null);
    }
}
