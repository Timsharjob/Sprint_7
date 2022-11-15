import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CourierTest {
    private CourierClient courierClient;
    private Courier courier;
    private CourierLogin courierLogin;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
        courierLogin = CourierGenerator.getDefaultUser();
    }

    @After
    public void cleanUp() {
        courierClient.delete(id);
    }

    @Test
    public void courierCanBeCreated() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(courierLogin);
        int expectedStatusCode = 201;
        boolean expectedIsOk = true;
        id = responseLogin.extract().path("id");
        int actualStatusCode = responseCreate.extract().statusCode();
        boolean actualIsOk = responseCreate.extract().path("ok");
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
        Assert.assertEquals(expectedIsOk,actualIsOk);
    }
    @Test
    public void twoSameCourierCanBeCreated () {
        ValidatableResponse firstResponseCreate = courierClient.create(courier);
        ValidatableResponse secondResponseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(courierLogin);
        id = responseLogin.extract().path("id");
        int expectedStatusCode = 409;
        String expectedMessage = "Этот логин уже используется. Попробуйте другой.";
        String actualMessage = secondResponseCreate.extract().path("message");
        int actualStatusCode = secondResponseCreate.extract().statusCode();
        Assert.assertEquals(expectedMessage,actualMessage);
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
    }

}
