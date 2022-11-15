import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrdersClient extends Client {
    private static final String PATH = "api/v1/orders";
    private static final String PATH_CANCEL = "api/v1/orders/cancel";

    public ValidatableResponse create(Orders order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(PATH)
                .then();
    }

    public ValidatableResponse delete(OrdersCancel ordersCancel) {
        return given()
                .spec(getSpec())
                .body(ordersCancel)
                .when()
                .put(PATH_CANCEL)
                .then();
    }

    public ValidatableResponse getOrders(Orders order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .get(PATH)
                .then();
    }
}
