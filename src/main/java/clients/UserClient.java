package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import io.qameta.allure.Step;
import user.information.UserCreds;
import user.information.UserRequest;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;


public class UserClient {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private static final String CREATE_USER_PATH = "api/auth/register";
    private static final String USER_PATH = "api/auth/user";

    public UserClient() {
        RestAssured.baseURI = BASE_URI;
    }

    @Step("Create user")
    public UserCreds userCreate(UserRequest user) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .post(CREATE_USER_PATH);

        // check status code
        response.then().statusCode(SC_OK);

        return response.body().as(UserCreds.class);
    }

    @Step("Delete user")
    public void userDelete(UserCreds userCreds) {
        given()
                .header("Authorization", userCreds.getAccessToken())
                .when()
                .delete(USER_PATH)
                .then()
                .statusCode(SC_ACCEPTED);
    }
}

