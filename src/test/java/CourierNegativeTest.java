import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class CourierNegativeTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    private final String login;
    private final String password;
    private final String firstName;
    private final int statusCode;
    public CourierNegativeTest(String login, String password, String firstName, int statusCode) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.statusCode = statusCode;
    }
    @Parameters(name = "Тестовые данные: {0}, {1} ,{2} ,{3}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {null,null,null,400},
                {"Tim","1234",null,400},
                {"Tim",null,"Timur",400},
                {null,"1234","Timur",400}
        };
    }
    @Test()
    public void negativeCourierTest() {
        CourierJson courierJson = new CourierJson();
        courierJson.setLogin(login);
        courierJson.setPassword(password);
        courierJson.setFirstName(firstName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(courierJson)
                        .when()
                        .post("api/v1/courier");
        response.then().assertThat().statusCode(statusCode).and().body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }
}
