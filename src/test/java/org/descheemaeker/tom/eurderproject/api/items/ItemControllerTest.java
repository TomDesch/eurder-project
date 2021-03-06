package org.descheemaeker.tom.eurderproject.api.items;

import io.restassured.RestAssured;
import org.descheemaeker.tom.eurderproject.Utility;
import org.descheemaeker.tom.eurderproject.api.items.dto.CreateItemDto;
import org.descheemaeker.tom.eurderproject.api.items.dto.ItemDto;
import org.descheemaeker.tom.eurderproject.domain.Address;
import org.descheemaeker.tom.eurderproject.domain.User;
import org.descheemaeker.tom.eurderproject.domain.UserType;
import org.descheemaeker.tom.eurderproject.services.UserService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ItemControllerTest {

    @LocalServerPort
    private int port;

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
    }

    @Test
    void givenRepoWithItems_whenRegisteringNewItemAsAdmin_thenDoIt() {
        CreateItemDto itemCreateDto = new CreateItemDto("t123", "t123", 1D, 1);


        ItemDto itemDto =
                RestAssured.given()
                        .body(itemCreateDto)
                        .accept(JSON)
                        .contentType(JSON)
//                        .header("Authorization", Utility.generateBase64Authorization("admin", "admin"))
                        .when()
                        .port(port)
                        .post("/items")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ItemDto.class);


        assertFalse(itemDto.itemId().isBlank());
        assertEquals(itemDto.name(), itemCreateDto.name());
        assertEquals(itemDto.description(), itemCreateDto.description());
        assertEquals(itemDto.price(), itemCreateDto.price());
        assertEquals(itemDto.amount(), itemCreateDto.amount());
    }

    @Test
    void givenRepoWithItems_whenRegisteringNewItemNoAuthentication_thenReturn400() {
        CreateItemDto itemCreateDto = new CreateItemDto("t2", "t2", 1D, 1);

        RestAssured.given()
                .body(itemCreateDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void givenRepoWithItems_whenRegisteringNewItemAsCustomer_thenReturn401_andThrowRuntimeException() {
        CreateItemDto itemCreateDto = new CreateItemDto("t2", "t2", 1D, 1);

        // Make sure the user exists 
        String everything = "NotAdmin";
        userService.addUser(User.UserBuilder.aUser()
                .withUserType(UserType.CUSTOMER)
                .withFirstName(everything)
                .withLastName(everything)
                .withPassword(everything)
                .withAddress(new Address(everything, everything, everything, everything))
                .withEmailAddress(everything)
                .withPhoneNumber(everything)
                .build());

        RestAssured.given()
                .body(itemCreateDto)
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization(everything, everything))
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());

//        Assertions.assertEquals(response, everything + " does not have access to this feature.");
    }

    @Test
    void givenRepoWithItems_whenAuthorisingNoEmailExists_thenThrowRuntimeException() {
        CreateItemDto itemCreateDto = new CreateItemDto("t2", "t2", 1D, 1);

        RestAssured.given()
                .body(itemCreateDto)
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization("thisEmailDoesNotExist", "thisPasswordNeither"))
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());

//        Assertions.assertEquals(response, "This email does not exist in our database.");
    }


    @Test
    void givenRepoWithItems_whenAuthorisingWrongPassword_thenThrowRuntimeException() {
        CreateItemDto itemCreateDto = new CreateItemDto("tj2", "tj2", 1D, 1);

        RestAssured.given()
                .header("Authorization", Utility.generateBase64Authorization("admin", "incorrectPassword"))
                .body(itemCreateDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
//                .body("message", Matchers.is("There is no account found with this username and password combination."));
    }
}