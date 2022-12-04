package tests;

import models.lombok.CreateUserBody;
import models.lombok.CreateUserResponse;
import static org.assertj.core.api.Assertions.assertThat;

import models.lombok.GetUserResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;


public class RegresInTests  {

    @Test
    void createUser() {
        CreateUserBody body = new CreateUserBody();
        body.setName("morpheus");
        body.setJob("leader");

        CreateUserResponse response= given()
                .spec(UserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(UserResponseSpec)
                .extract().as(CreateUserResponse.class);

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");

    }
    @Test
    void getSingleUser() {

        GetUserResponse response = given()
                .spec(UserRequestSpec)
                .when()
                .get("/2")
                .then()
                .statusCode(200)
                .extract().as(GetUserResponse.class);
        assertThat(response.getUser().getEmail()).isEqualTo("janet.weaver@reqres.in");
        assertThat(response.getUser().getFirstName()).isEqualTo("Janet");
        assertThat(response.getUser().getLastName()).isEqualTo("Weaver");
        assertThat(response.getSupport().getUrl()).isEqualTo("https://reqres.in/#support-heading");




    }




}
