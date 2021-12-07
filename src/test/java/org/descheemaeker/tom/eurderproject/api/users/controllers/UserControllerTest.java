package org.descheemaeker.tom.eurderproject.api.users.controllers;

import io.restassured.RestAssured;
import org.descheemaeker.tom.eurderproject.api.users.*;
import org.descheemaeker.tom.eurderproject.api.users.dto.CreateUserDto;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;
import org.descheemaeker.tom.eurderproject.repositories.UserRepository;
import org.descheemaeker.tom.eurderproject.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static io.restassured.http.ContentType.JSON;
import static org.descheemaeker.tom.eurderproject.api.users.UserMapper.USER_MAPPER;
import static org.descheemaeker.tom.eurderproject.api.users.UserType.CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {


    @Value("${server.port}")
    private int port;
    private static final UserBuilder userBuilder = new UserBuilder();
    private final UserService userService;
    private User customer;
    private Address placeWhereEverybodyLives;

    @Autowired
    public UserControllerTest(UserService userService) {
        this.userService = userService;
    }

    @BeforeAll
    void setUp() {
        placeWhereEverybodyLives = new Address("Drury Lane", "1", 9000, "Far far away", "Shrek");

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

        CreateUserDto newCustomerCreateDto = USER_MAPPER.userToCreateDto(newCustomer);

        userService.addUser(newCustomerCreateDto);
        int newSize = userService.getAllUsers().size();

        UserDto[] userDtos =
                RestAssured.given()
                        .body(newCustomerCreateDto)
                        .accept(JSON)
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
        assertTrue(Arrays.asList(userDtos).contains(USER_MAPPER.userToDto(newCustomer)));
    }
}
