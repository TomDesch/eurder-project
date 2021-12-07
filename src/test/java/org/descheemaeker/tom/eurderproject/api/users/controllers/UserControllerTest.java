package org.descheemaeker.tom.eurderproject.api.users.controllers;

import io.restassured.RestAssured;
import org.descheemaeker.tom.eurderproject.api.users.*;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;
import org.descheemaeker.tom.eurderproject.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static io.restassured.http.ContentType.JSON;
import static org.descheemaeker.tom.eurderproject.api.users.UserType.CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {


    @Value("${server.port}")
    private int port;
    private final UserRepository userRepository;
    private static final UserBuilder userBuilder = new UserBuilder();
    private User customer;
    private Address placeWhereEverybodyLives;

    @Autowired
    public UserControllerTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeAll
    void setUp() {
        placeWhereEverybodyLives = new Address();

        customer = userBuilder
                .withUserType(CUSTOMER)
                .withAddress(placeWhereEverybodyLives)
                .withEmailAddress("poo")
                .withFirstName("doo")
                .withLastName("roo")
                .withPhoneNumber("loo")
                .build();

    }

    @Test
    void givenRepoWithUsers_whenRegisteringNewCustomer_thenDoIt() {
        User newCustomer = userBuilder
                .withUserType(CUSTOMER)
                .withFirstName("test1")
                .withLastName("test1")
                .withEmailAddress("test1")
                .withPhoneNumber("test1")
                .withAddress(placeWhereEverybodyLives)
                .build();
        userRepository.addUser(newCustomer);
        int newSize = userRepository.getAllUsers().size();

        UserDto[] userDtos =
                RestAssured.given()
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/users")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(UserDto[].class);

        assertEquals(newSize, userDtos.length);
        assertTrue(Arrays.asList(userDtos).contains(UserMapper.USER_MAPPER.userToDto(newCustomer)));
    }

}
