package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.CreateUserBodyLombokModel;
import models.lombok.CreateUserResponseLombokModel;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import specs.CreateUserSpecs;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static specs.CreateUserSpecs.createUserRequestSpec;
import static specs.CreateUserSpecs.createUserResponseSpec;


public class RegresInTests  {


    @Test
    void getSingleUser() {
        CreateUserResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(CreateUserResponseLombokModel.class);
        assertThat(response.getId()).isEqualTo("2");



           /*     .body(  "data.id", is(2),
                        "data.email", is("janet.weaver@reqres.in"),
                        "data.first_name", is("Janet"),
                        "data.last_name", is("Weaver")); */

    }

    @Test
    void createUser() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setName("morpheus");
        body.setJob("leader");
        CreateUserResponseLombokModel response= given()
                .spec(createUserRequestSpec)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .spec(createUserResponseSpec)
                .statusCode(201)
                .extract().as(CreateUserResponseLombokModel.class);
        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");

    }
    @Test
    void getUsersList() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", hasItems(7, 8, 9, 10));

    }

    @Test
    void deleteUser() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void registerSuccessful() {
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(  "id", is(4),
                        "token", is("QpwL5tke4Pnpja7X4"));


    }



}
