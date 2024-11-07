package praktikum.users;

import io.restassured.http.ContentType;
import praktikum.EnvConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static praktikum.EnvConfig.CREATE_USER;
import static praktikum.EnvConfig.CREDENTIALS;

public class UserClient {

    public static Response createUser(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(EnvConfig.BASE_URL)
                .body(user)
                .when()
                .post(CREATE_USER);
    }

    public Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(CREDENTIALS);
    }
}
