package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.CreateUserBodyLombokModel;
import models.lombok.CreateUserResponseLombokModel;
import static org.assertj.core.api.Assertions.assertThat;

import models.lombok.GetSingleUserBodyLombokModel;
import models.lombok.GetSingleUserResponseLombokModel;
import org.junit.jupiter.api.Test;
import specs.CreateUserSpecs;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static specs.CreateUserSpecs.createUserRequestSpec;
import static specs.CreateUserSpecs.createUserResponseSpec;


public class RegresInTests  {

    @Test
    void createUser() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setName("morpheus");
        body.setJob("leader");

        CreateUserResponseLombokModel response= given()
                .spec(createUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(createUserResponseSpec)
                .extract().as(CreateUserResponseLombokModel.class);

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");

    }
    @Test
    void getSingleUser() {
        GetSingleUserBodyLombokModel body = new GetSingleUserBodyLombokModel();

        GetSingleUserResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(GetSingleUserResponseLombokModel.class);
        assertThat(response.getData().get(0)).isEqualTo("janet.weaver@reqres.in");



           /*     .body(  "data.id", is(2),
                        "data.email", is("janet.weaver@reqres.in"),
                        "data.first_name", is("Janet"),
                        "data.last_name", is("Weaver")); */

    }




}
