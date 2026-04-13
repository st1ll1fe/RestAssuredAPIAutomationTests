package clients;

import io.restassured.response.Response;
import models.Order;

/**
 * Interface for Store (order) operations.
 */
public interface StoreApi {
    Response placeOrder(Order order);
    Response getOrderById(Long orderId);
    Response deleteOrder(Long orderId);
    Response getInventory();
}
