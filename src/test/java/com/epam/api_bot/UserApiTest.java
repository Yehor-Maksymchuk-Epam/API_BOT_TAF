package com.epam.api_bot;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"newuser\",\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Doe\",\n" +
                "  \"email\": \"johndoe@example.com\",\n" +
                "  \"password\": \"password123\",\n" +
                "  \"phone\": \"+1234567890\",\n" +
                "  \"userStatus\": 1\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertEquals(200, response.statusCode());
    }

    @Test
    public void testGetUserByUsername() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/user/newuser")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertEquals(200, response.statusCode());
        assertEquals("newuser", response.jsonPath().getString("username"));
        assertEquals("johndoe@example.com", response.jsonPath().getString("email"));
    }
}