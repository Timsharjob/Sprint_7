import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ApiLoginCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";

    }
    @Test
    public void loginCourierTest() {
        CourierJson courierLoginJson = new CourierJson();
        courierLoginJson.setLogin("Tim1");
        courierLoginJson.setPassword("1234");
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierLoginJson)
                        .when()
                        .post("api/v1/courier/login");
        loginResponse.then().assertThat().statusCode(200).and().body("id",notNullValue());
        String test = loginResponse.jsonPath().getString("id");
        System.out.println(test);
    }
    @Test
    public void loginNullDataCourierTest() {
        CourierJson courierLoginJson = new CourierJson();
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierLoginJson)
                        .when()
                        .post("api/v1/courier/login");
        loginResponse.then().assertThat().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
    }
    @Test
    public void loginWithoutLoginDataCourierTest() {
        CourierJson courierLoginJson = new CourierJson();
        courierLoginJson.setPassword("1234");
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierLoginJson)
                        .when()
                        .post("api/v1/courier/login");
        loginResponse.then().assertThat().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void loginWithoutPasswordDataCourierTest() {
        CourierJson courierLoginJson = new CourierJson();
        courierLoginJson.setLogin("Tim1");
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierLoginJson)
                        .when()
                        .post("api/v1/courier/login");
        loginResponse.then().assertThat().statusCode(400).and().body("message",equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void loginWrongDataCourierTest() {
        CourierJson courierLoginJson = new CourierJson();
        courierLoginJson.setLogin("Tim1");
        courierLoginJson.setPassword("1235");
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierLoginJson)
                        .when()
                        .post("api/v1/courier/login");
        loginResponse.then().assertThat().statusCode(404).and().body("message",equalTo("Учетная запись не найдена"));
    }
    @Test
    public void loginNonExistentDataCourierTest() {
        CourierJson courierLoginJson = new CourierJson();
        courierLoginJson.setLogin("Tim2");
        courierLoginJson.setPassword("1235");
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierLoginJson)
                        .when()
                        .post("api/v1/courier/login");
        loginResponse.then().assertThat().statusCode(404).and().body("message",equalTo("Учетная запись не найдена"));
    }

}
