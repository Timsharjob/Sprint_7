import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

@RunWith(Parameterized.class)
public class CourierLoginParamTest {
    private int statusCode;
    private String message;
    private CourierClient courierClient;
    private CourierLogin courierLogin;

    public CourierLoginParamTest(CourierLogin courierLogin, int statusCode, String message) {
        this.courierLogin = courierLogin;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1} ,{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {CourierGenerator.getLoginWithoutLogin(), SC_BAD_REQUEST, "Недостаточно данных для входа"},
                {CourierGenerator.getLoginWithoutPassword(), SC_BAD_REQUEST, "Недостаточно данных для входа"},
                {CourierGenerator.getLoginWrongLogin(), SC_NOT_FOUND, "Учетная запись не найдена"},
                {CourierGenerator.getLoginWrongPassword(), SC_NOT_FOUND, "Учетная запись не найдена"}
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    public void negativeCourierLoginTest() {
        ValidatableResponse responseLogin = courierClient.login(courierLogin);
        String actualMessage = responseLogin.extract().path("message");
        int actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals(statusCode, actualStatusCode);
        Assert.assertEquals(message, actualMessage);
    }
}
