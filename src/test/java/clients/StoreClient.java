package clients;

import clients.ApiClientBase;
import clients.StoreApi;
import io.restassured.response.Response;
import models.Order;

/**
 * HTTP-клиент для работы с Store API
 * Выполняет низкоуровневые HTTP-запросы к эндпоинтам магазина (заказов)
 */
public class StoreClient extends ApiClientBase implements StoreApi {

    // Базовый путь для Store API
    private static final String STORE_ENDPOINT = "/store";

    /**
     * Получает заказ по ID
     * @param orderId - ID заказа
     * @return Response - ответ от сервера
     */
    public Response getOrderById(Long orderId) {
        return get(STORE_ENDPOINT + "/order/" + orderId);
    }

    /**
     * Создаёт новый заказ
     * @param order - объект заказа для создания
     * @return Response - ответ от сервера
     */
    public Response placeOrder(Order order) {
        return post(STORE_ENDPOINT + "/order", order);
    }

    /**
     * Удаляет заказ по ID
     * @param orderId - ID заказа
     * @return Response - ответ от сервера
     */
    public Response deleteOrder(Long orderId) {
        return delete(STORE_ENDPOINT + "/order/" + orderId);
    }

    /**
     * Получает инвентарь магазина (количество питомцев по статусам)
     * @return Response - ответ от сервера
     */
    public Response getInventory() {
        return get(STORE_ENDPOINT + "/inventory");
    }
}