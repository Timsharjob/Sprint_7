import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
@RunWith(Parameterized.class)
public class PositiveCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Create courier test")
    public void createCourierTest() {
        CourierJson courierJson = new CourierJson("Tim1", "1234", "Timur");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierJson)
                        .when()
                        .post("api/v1/courier");
        response.then().assertThat().statusCode(201).and().body("ok",equalTo(true));
    }
    @Test
    public void createTwoSameCourierTest() {
        CourierJson courierJson = new CourierJson("Tim2", "1234", "Timur");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierJson)
                        .when()
                        .post("api/v1/courier");

        response.then().assertThat().statusCode(409).and().body("message",equalTo("Этот логин уже используется. Попробуйте другой."));
    }


}
