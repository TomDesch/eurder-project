package org.descheemaeker.tom.eurderproject.exception;

import io.restassured.RestAssured;
import org.descheemaeker.tom.eurderproject.Utility;
import org.descheemaeker.tom.eurderproject.api.items.ItemController;
import org.descheemaeker.tom.eurderproject.api.items.ItemMapper;
import org.descheemaeker.tom.eurderproject.api.items.dto.CreateItemDto;
import org.descheemaeker.tom.eurderproject.api.users.Address;
import org.descheemaeker.tom.eurderproject.api.users.UserController;
import org.descheemaeker.tom.eurderproject.api.users.UserMapper;
import org.descheemaeker.tom.eurderproject.api.users.UserType;
import org.descheemaeker.tom.eurderproject.domain.Item;
import org.descheemaeker.tom.eurderproject.domain.User;
import org.descheemaeker.tom.eurderproject.services.ItemService;
import org.descheemaeker.tom.eurderproject.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AllExceptionHandlerTest {

    @Value("${server.port}")
    private int port;
    private final ItemService itemService;
    private final UserService userService;
    private final UserController userController;
    private final ItemController itemController;

    @Autowired
    public AllExceptionHandlerTest(ItemService itemService, UserService userService, UserController userController, ItemController itemController) {
        this.itemService = itemService;
        this.userService = userService;
        this.userController = userController;
        this.itemController = itemController;
    }

    @Test
    void givenRepoWithItems_whenRegisteringNewItemAsCustomer_thenReturn401_andThrowRuntimeException() {
        CreateItemDto itemCreateDto = new CreateItemDto("tt3", "tt3", 1D, 1);

        // Make sure the user exists
        String everything = "NotAdmin2";
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


}