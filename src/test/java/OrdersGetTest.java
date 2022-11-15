import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class OrdersGetTest {
    private OrdersClient ordersClient;
    private Orders order;

    @Before
    public void setUp() {
        ordersClient = new OrdersClient();
        order = new Orders();
    }

    @Test
    public void getOrdersTest() {
        ValidatableResponse getResponse = ordersClient.getOrders(order);
        int actualStatusCode = getResponse.extract().statusCode();
        Assert.assertEquals(SC_OK, actualStatusCode);
        getResponse.assertThat().body("orders", notNullValue());
    }
}
