import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    private static final String PATH_CREATE = "api/v1/courier";
    private static final String PATH_LOGIN = "/api/v1/courier/login";

    @Step("/api/v1/courier - create")
    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(PATH_CREATE)
                .then();
    }
    @Step("/api/v1/courier/login")
    public ValidatableResponse login(CourierLogin courierLogin) {
        return given()
                .spec(getSpec())
                .body(courierLogin)
                .when()
                .post(PATH_LOGIN)
                .then();
    }
    @Step("/api/v1/courier - delete")
    public ValidatableResponse delete(int id, CourierLogin courierLogin) {
        return given()
                .spec(getSpec())
                .body(courierLogin)
                .when()
                .delete(PATH_CREATE + "/" + id)
                .then();
    }
}
