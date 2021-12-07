package org.descheemaeker.tom.eurderproject.api.items;

import io.restassured.RestAssured;
import org.descheemaeker.tom.eurderproject.services.ItemService;
import org.descheemaeker.tom.eurderproject.api.items.dto.CreateItemDto;
import org.descheemaeker.tom.eurderproject.api.items.dto.ItemDto;
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
class ItemControllerTest {

    @Value("${server.port}")
    private int port;
    private final ItemService itemService;

    @Autowired
    public ItemControllerTest(ItemService itemService) {
        this.itemService = itemService;
    }

    @Test
    void givenRepoWithItems_whenRegisteringNewItem_thenDoIt() {
        CreateItemDto itemCreateDto = new CreateItemDto("t1", "t1", 1D, 1);

        ItemDto itemDto =
                RestAssured.given()
                        .body(itemCreateDto)
                        .accept(JSON)
                        .contentType(JSON)
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
}