package org.descheemaeker.tom.eurderproject.api.users.controllers;

import io.restassured.RestAssured;
import org.descheemaeker.tom.eurderproject.api.users.Address;
import org.descheemaeker.tom.eurderproject.api.users.dto.CreateUserDto;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;
import org.descheemaeker.tom.eurderproject.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
    private final UserService userService;
    private Address placeWhereEverybodyLives;

    @Autowired
    public UserControllerTest(UserService userService) {
        this.userService = userService;
    }

    @BeforeAll
    void setUp() {
        placeWhereEverybodyLives = new Address("Drury Lane", "1", "1000", "Far far away");
    }

    @Test
    void givenRepoWithUsers_whenRegisteringNewCustomer_thenDoIt() {
        CreateUserDto customerCreateDto = new CreateUserDto(CUSTOMER, "t1", "t1", "t1", placeWhereEverybodyLives, "t1");
        UserDto customer = USER_MAPPER.userToDto(USER_MAPPER.dtoToUser(customerCreateDto));

        int newSize = userService.getAllUsers().size();

        UserDto[] userDtos =
                RestAssured.given()
                        .body(customerCreateDto)
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
        assertTrue(Arrays.asList(userDtos).contains(customer));
    }
}
