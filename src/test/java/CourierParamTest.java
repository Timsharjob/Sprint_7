import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)

public class CourierParamTest {

    private  int statusCode;
    private  String message;
    private CourierClient courierClient;
    private Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
    }


    public CourierParamTest(Courier courier, int statusCode, String message) {
        this.courier = courier;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Parameters(name = "Тестовые данные: {0}, {1} ,{2} ,{3}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {CourierGenerator.getWithoutLogin(),SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},

        };
    }
    @Test()
    public void negativeCourierTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        int actualStatusCode = responseCreate.extract().statusCode();
        String actualMessage = responseCreate.extract().path("message");
        Assert.assertEquals(statusCode,actualStatusCode);
        Assert.assertEquals(message,actualMessage);
        //response.then().assertThat().statusCode(statusCode).and().body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }
}
