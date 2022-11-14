import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
public class ApiCreatOrdersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }
    @Test
    public void createOrdersTest() {
        OrdersJson ordersJson = new OrdersJson("Naruto","Uchiha","Konoha, 142 apt.","4",
                "7 800 355 35 35",5,"2020-06-06","Saske, come back to Konoha");
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(ordersJson)
                        .when()
                        .post("/api/v1/orders");
        loginResponse.then().assertThat().statusCode(201).and().body("track",notNullValue());
    }

    @Test
    public void createOrdersWithBlackColorTest() {
        OrdersJson ordersJson = new OrdersJson("Naruto","Uchiha","Konoha, 142 apt.","4",
                "7 800 355 35 35",5,"2020-06-06","Saske, come back to Konoha");
        ordersJson.setColor(new String[]{"BLACK"});
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(ordersJson)
                        .when()
                        .post("/api/v1/orders");
        loginResponse.then().assertThat().statusCode(201).and().body("track",notNullValue());
    }

    @Test
    public void createOrdersWithGreyColorTest() {
        OrdersJson ordersJson = new OrdersJson("Naruto","Uchiha","Konoha, 142 apt.","4",
                "7 800 355 35 35",5,"2020-06-06","Saske, come back to Konoha");
        ordersJson.setColor(new String[]{"GREY"});
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(ordersJson)
                        .when()
                        .post("/api/v1/orders");
        loginResponse.then().assertThat().statusCode(201).and().body("track",notNullValue());
    }
    @Test
    public void getOrdersTest() {
        OrdersJson ordersJson = new OrdersJson();
        Response loginResponse =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(ordersJson)
                        .when()
                        .get("/api/v1/orders");
        loginResponse.then().assertThat().statusCode(200).and().body("orders",notNullValue());
    }
}
