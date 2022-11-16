import api.orderspackage.Orders;
import api.orderspackage.OrdersClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
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

    @DisplayName("Получение списка заказов")
    @Test
    public void getOrdersTest() {
        ValidatableResponse getResponse = ordersClient.getOrders(order);
        getResponse.assertThat().statusCode(SC_OK);
        getResponse.assertThat().body("orders", notNullValue());
    }
}
