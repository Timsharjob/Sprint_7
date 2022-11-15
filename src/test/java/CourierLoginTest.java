import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
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
        courierClient.delete(id,courierLogin);
    }

    @Test
    public void courierCanBeLogin() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(courierLogin);
        id = responseLogin.extract().path("id");
        int actualStatusCode = responseLogin.extract().statusCode();
        Assert.assertEquals(SC_OK, actualStatusCode);
        responseLogin.assertThat().body("id", notNullValue());
    }
}
