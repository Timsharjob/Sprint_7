import api.courierpackage.Courier;
import api.courierpackage.CourierClient;
import api.courierpackage.CourierGenerator;
import api.courierpackage.CourierLogin;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;

public class CourierTest {
    private CourierClient courierClient;
    private Courier courier;
    private CourierLogin courierLogin;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
        courierLogin = CourierGenerator.getDefaultLogin();
    }

    @After
    public void cleanUp() {
        courierLogin = new CourierLogin();
        courierClient.delete(id, courierLogin);
    }

    @DisplayName("Создание курьера")
    @Test
    public void courierCanBeCreated() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(courierLogin);
        boolean expectedIsOk = true;
        id = responseLogin.extract().path("id");
        int actualStatusCode = responseCreate.extract().statusCode();
        boolean actualIsOk = responseCreate.extract().path("ok");
        Assert.assertEquals(SC_CREATED, actualStatusCode);
        Assert.assertEquals(expectedIsOk, actualIsOk);
    }

    @DisplayName("Создание курьера")
    @Test
    public void twoSameCourierCanBeCreated() {
        ValidatableResponse firstResponseCreate = courierClient.create(courier);
        ValidatableResponse secondResponseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(courierLogin);
        id = responseLogin.extract().path("id");
        String expectedMessage = "Этот логин уже используется. Попробуйте другой.";
        String actualMessage = secondResponseCreate.extract().path("message");
        int actualStatusCode = secondResponseCreate.extract().statusCode();
        Assert.assertEquals(expectedMessage, actualMessage);
        Assert.assertEquals(SC_CONFLICT, actualStatusCode);
    }

}
