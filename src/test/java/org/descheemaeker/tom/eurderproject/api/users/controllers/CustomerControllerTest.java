package org.descheemaeker.tom.eurderproject.api.users.controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest {


    @Value("${server.port}")
    private int port;
//    private final CustomerRepository customerRepository;
//    private User user;

    @Autowired
    public CustomerControllerTest(int port) {
        this.port = port;
    }
}
