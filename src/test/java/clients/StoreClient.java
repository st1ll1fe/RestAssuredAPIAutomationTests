package clients;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import models.Order;

public class StoreClient extends BaseClient {
    private static final String STORE_ENDPOINT = "/store";

    public StoreClient(RequestSpecification spec) {
        super(spec);
    }

    public Response getOrderById(Long orderId) {
        return get(STORE_ENDPOINT + "/order/" + orderId);
    }

    public Response placeOrder(Order order) {
        return post(STORE_ENDPOINT + "/order", order);
    }

    public Response deleteOrder(Long orderId) {
        return delete(STORE_ENDPOINT + "/order/" + orderId);
    }

    public Response getInventory() {
        return get(STORE_ENDPOINT + "/inventory");
    }
}
