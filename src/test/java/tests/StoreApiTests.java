package tests;

import clients.StoreClient;
import config.BaseTest;
import io.restassured.response.Response;
import models.Order;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StoreApiTests extends BaseTest {
    private final StoreClient storeClient = new StoreClient(SPEC);

    private Order createOrder(Long id, Long petId, Integer quantity, String status) {
        Order order = new Order();
        order.setId(id);
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setStatus(status);
        return order;
    }

    @Test
    void shouldPlaceOrder() {
        Long orderId = System.currentTimeMillis();
        Response response = storeClient.placeOrder(createOrder(orderId, 1L, 1, "placed"));
        response.then().statusCode(200)
                .body("id", notNullValue())
                .body("petId", equalTo(1))
                .body("quantity", equalTo(1))
                .body("status", equalTo("placed"));
    }

    @Test
    void shouldGetOrderById() {
        Long orderId = System.currentTimeMillis();
        storeClient.placeOrder(createOrder(orderId, 1L, 1, "approved"));
        Response response = storeClient.getOrderById(orderId);
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getLong("id"), equalTo(orderId));
    }

    @Test
    void shouldReturn404WhenOrderNotFound() {
        Response response = storeClient.getOrderById(999999L);
        response.then().statusCode(404);
    }

    @Test
    void shouldDeleteOrder() {
        Long orderId = System.currentTimeMillis();
        storeClient.placeOrder(createOrder(orderId, 1L, 1, "placed"));
        Response deleteResponse = storeClient.deleteOrder(orderId);
        assertThat(deleteResponse.getStatusCode(), equalTo(200));
    }

    @Test
    void shouldGetInventory() {
        Response response = storeClient.getInventory();
        response.then().statusCode(200);
    }
}
