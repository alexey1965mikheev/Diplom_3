package praktikum.users;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import praktikum.EnvConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static praktikum.EnvConfig.*;

public class UserClient {

    @Step("Создание пользователя")
    public static Response createUser(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(EnvConfig.BASE_URL)
                .body(user)
                .when()
                .post(CREATE_USER);
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .baseUri(BASE_URL)
                .when()
                .delete(CREDENTIALS)
                .then();
    }
}