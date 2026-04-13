package services;

import clients.StoreApi;
import clients.StoreClient;
import io.restassured.response.Response;
import models.Order;

import static config.Config.generateId;
import static models.OrderBuilder.anOrder;

/**
 * Сервисный слой для работы с Store API
 * Содержит бизнес-логику и комбинирует несколько API вызовов при необходимости
 */
public class StoreService {

    // HTTP-клиент для выполнения запросов к Store API
    private final StoreApi storeClient = new StoreClient();

    /**
     * Получает заказ по ID
     * @param orderId - ID заказа
     * @return Response - ответ от сервера
     */
    public Response getOrderById(Long orderId) {
        return storeClient.getOrderById(orderId);
    }

    /**
     * Создаёт новый заказ с указанными параметрами
     * @param orderId - ID заказа
     * @param petId - ID питомца
     * @param quantity - количество
     * @param status - статус заказа
     * @return Response - ответ от сервера
     */
    public Response placeOrder(Long orderId, Long petId, Integer quantity, String status) {
        Order order = anOrder()
                .withId(orderId)
                .withPetId(petId)
                .withQuantity(quantity)
                .withStatus(status)
                .build();
        return storeClient.placeOrder(order);
    }

    /**
     * Создаёт новый заказ из готового объекта Order
     * @param order - объект заказа
     * @return Response - ответ от сервера
     */
    public Response placeOrder(Order order) {
        return storeClient.placeOrder(order);
    }

    /**
     * Удаляет заказ по ID
     * @param orderId - ID заказа
     * @return Response - ответ от сервера
     */
    public Response deleteOrder(Long orderId) {
        return storeClient.deleteOrder(orderId);
    }

    /**
     * Получает инвентарь магазина (количество питомцев по статусам)
     * @return Response - ответ от сервера
     */
    public Response getInventory() {
        return storeClient.getInventory();
    }

    /**
     * Создаёт заказ со случайным ID для указанного питомца
     * Используется в тестах для генерации уникальных заказов
     * @param petId - ID питомца
     * @return ID созданного заказа
     */
    public Long createAndGetOrderId(Long petId) {
        Long orderId = Long.parseLong(generateId());
        placeOrder(orderId, petId, 1, "placed");
        return orderId;
    }
}