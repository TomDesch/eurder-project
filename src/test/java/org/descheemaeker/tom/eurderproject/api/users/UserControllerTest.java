package org.descheemaeker.tom.eurderproject.api.users;

import io.restassured.RestAssured;
import org.descheemaeker.tom.eurderproject.api.users.dto.CreateUserDto;
import org.descheemaeker.tom.eurderproject.api.users.dto.UserDto;
import org.descheemaeker.tom.eurderproject.domain.Address;
import org.descheemaeker.tom.eurderproject.domain.User;
import org.descheemaeker.tom.eurderproject.exception.RequiredFieldIsNullException;
import org.descheemaeker.tom.eurderproject.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.http.ContentType.JSON;
import static org.descheemaeker.tom.eurderproject.domain.UserType.ADMIN;
import static org.descheemaeker.tom.eurderproject.domain.UserType.CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    private Address placeWhereEverybodyLives;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        User admin = User.UserBuilder.aUser()
                .withUserType(ADMIN)
                .withFirstName("non null")
                .withLastName("non null either")
                .withEmailAddress("admin")
                .withPassword("admin")
                .build();

        userService.addUser(admin);
        placeWhereEverybodyLives = new Address("Drury Lane", "1", "1000", "Far far away");
    }


    @Test
    void givenRepoWithUsers_whenRegisteringNewCustomer_thenDoIt() {
        CreateUserDto customerCreateDto = new CreateUserDto(CUSTOMER, "t1", "t1", "t1", placeWhereEverybodyLives, "t1", "1234");

        UserDto userDto =
                RestAssured.given()
//                        .header("Authorization", Utility.generateBase64Authorization("admin", "admin"))
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
                        .as(UserDto.class);


//        assertFalse(userDto.userId().isBlank());
        assertEquals(userDto.userType(), customerCreateDto.userType());
        assertEquals(userDto.address(), customerCreateDto.address());
        assertEquals(userDto.emailAddress(), customerCreateDto.emailAddress());
        assertEquals(userDto.firstName(), customerCreateDto.firstName());
        assertEquals(userDto.lastName(), customerCreateDto.lastName());
        assertEquals(userDto.phoneNumber(), customerCreateDto.phoneNumber());
        assertEquals(userDto.password(), customerCreateDto.password());
    }

    @Test
    void givenRepoWithUsers_whenAddingUserWithoutAllFields_thenThrowRuntimeException() {
        Assertions.assertThrows(RequiredFieldIsNullException.class, () -> User.UserBuilder.aUser().build());
    }
}
