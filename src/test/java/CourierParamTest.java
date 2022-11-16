import api.courierpackage.Courier;
import api.courierpackage.CourierClient;
import api.courierpackage.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)

public class CourierParamTest {

    private int statusCode;
    private String message;
    private CourierClient courierClient;
    private Courier courier;

    public CourierParamTest(Courier courier, int statusCode, String message) {
        this.courier = courier;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Parameters(name = "Тестовые данные: {0}, {1} ,{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {CourierGenerator.getCourierWithoutLogin(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getCourierWithoutPassword(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getCourierWithoutFirstName(), SC_BAD_REQUEST, "Недостаточно данных для создания учетной записи"}
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @DisplayName("Создание курьера")
    @Test()
    public void negativeCourierCreateTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        int actualStatusCode = responseCreate.extract().statusCode();
        String actualMessage = responseCreate.extract().path("message");
        Assert.assertEquals(statusCode, actualStatusCode);
        Assert.assertEquals(message, actualMessage);
    }
}
