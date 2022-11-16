import api.orderspackage.Orders;
import api.orderspackage.OrdersCancel;
import api.orderspackage.OrdersClient;
import api.orderspackage.OrdersGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrdersParamTest {
    private int statusCode;
    private Orders order;
    private OrdersCancel ordersCancel;
    private OrdersClient ordersClient;
    private int track;

    public OrdersParamTest(int statusCode, Orders order) {
        this.statusCode = statusCode;
        this.order = order;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {SC_CREATED, OrdersGenerator.getDefaultOrder()},
                {SC_CREATED, OrdersGenerator.getOrderBlackColor()},
                {SC_CREATED, OrdersGenerator.getOrderGreyColor()},
                {SC_CREATED, OrdersGenerator.getOrderGreyAndBlackColor()},
        };
    }

    @Before
    public void setUp() {
        ordersClient = new OrdersClient();
        ordersCancel = new OrdersCancel(track);
    }

    @After
    public void cleanUp() {
        ordersCancel.setTrack(track);
        ordersClient.delete(ordersCancel);
    }

    @DisplayName("Получение списка заказов")
    @Test
    public void ordersCanBeCreated() {
        ValidatableResponse responseCreate = ordersClient.create(order);
        responseCreate.assertThat().body("track", notNullValue());
        responseCreate.assertThat().statusCode(statusCode);
        track = responseCreate.extract().path("track");
    }

}
