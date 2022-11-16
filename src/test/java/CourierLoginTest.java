import api.courierpackage.Courier;
import api.courierpackage.CourierClient;
import api.courierpackage.CourierGenerator;
import api.courierpackage.CourierLogin;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginTest {
    private CourierClient courierClient;
    private Courier courier;
    private CourierLogin courierLogin;
    private int id;

    @Before
    public void setUP() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
        courierLogin = CourierGenerator.getDefaultLogin();
    }

    @After
    public void cleanUp() {
        courierLogin = new CourierLogin();
        courierClient.delete(id, courierLogin);
    }

    @DisplayName("Авторизация курьера")
    @Test
    public void courierCanBeLogin() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(courierLogin);
        responseLogin.assertThat().statusCode(SC_OK);
        responseLogin.assertThat().body("id", notNullValue());
        id = responseLogin.extract().path("id");
    }
}
