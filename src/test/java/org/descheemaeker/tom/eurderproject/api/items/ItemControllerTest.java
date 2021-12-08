package org.descheemaeker.tom.eurderproject.api.items;

import io.restassured.RestAssured;
import org.descheemaeker.tom.eurderproject.Utility;
import org.descheemaeker.tom.eurderproject.api.items.dto.CreateItemDto;
import org.descheemaeker.tom.eurderproject.api.items.dto.ItemDto;
import org.descheemaeker.tom.eurderproject.api.users.Address;
import org.descheemaeker.tom.eurderproject.api.users.UserType;
import org.descheemaeker.tom.eurderproject.domain.User;
import org.descheemaeker.tom.eurderproject.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemControllerTest {

    @Value("${server.port}")
    private int port;
    private final UserService userService;

    @Autowired
    public ItemControllerTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void givenRepoWithItems_whenRegisteringNewItemAsAdmin_thenDoIt() {
        CreateItemDto itemCreateDto = new CreateItemDto("t123", "t123", 1D, 1);

        ItemDto itemDto =
                RestAssured.given()
                        .body(itemCreateDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .header("Authorization", Utility.generateBase64Authorization("admin", "admin"))
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
                .statusCode(HttpStatus.BAD_REQUEST.value());
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

        String response = RestAssured.given()
                .body(itemCreateDto)
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization(everything, everything))
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract()
                .path("message");

        Assertions.assertEquals(response, everything + " does not have access to this feature.");
    }

    @Test
    void givenRepoWithItems_whenAuthorisingNoEmailExists_thenThrowRuntimeException() {
        CreateItemDto itemCreateDto = new CreateItemDto("t2", "t2", 1D, 1);

        String response = RestAssured.given()
                .body(itemCreateDto)
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization("thisEmailDoesNotExist", "thisPasswordNeither"))
                .when()
                .port(port)
                .post("/items")
                .then()
                .extract()
                .path("message");

        Assertions.assertEquals(response, "This email does not exist in our database.");
    }


    @Test
    void givenRepoWithItems_whenAuthorisingWrongPassword_thenThrowRuntimeException() {
        CreateItemDto itemCreateDto = new CreateItemDto("tj2", "tj2", 1D, 1);

        // Make sure the user exists
        String everything = "admin";
        userService.addUser(User.UserBuilder.aUser()
                .withUserType(UserType.CUSTOMER)
                .withFirstName(everything)
                .withLastName(everything)
                .withPassword(everything)
                .withAddress(new Address(everything, everything, everything, everything))
                .withEmailAddress(everything)
                .withPhoneNumber(everything)
                .build());

        String response = RestAssured.given()
                .body(itemCreateDto)
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", Utility.generateBase64Authorization(everything, "incorrectPassword"))
                .when()
                .port(port)
                .post("/items")
                .then()
                .extract()
                .path("message");

        Assertions.assertEquals(response, "There is no account found with this username and password combination.");
    }
}