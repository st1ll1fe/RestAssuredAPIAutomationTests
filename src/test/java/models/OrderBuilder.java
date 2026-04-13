package models;

/**
 * Builder для создания объектов Order
 * Позволяет удобно конструировать объекты заказов с помощью цепочки методов
 */
public class OrderBuilder {

    private Long id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private Boolean complete;

    /**
     * Создает новый экземпляр OrderBuilder
     * @return новый OrderBuilder
     */
    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    /**
     * Устанавливает ID заказа
     * @param id - ID заказа
     * @return текущий OrderBuilder для цепочки вызовов
     */
    public OrderBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Устанавливает ID питомца в заказе
     * @param petId - ID питомца
     * @return текущий OrderBuilder для цепочки вызовов
     */
    public OrderBuilder withPetId(Long petId) {
        this.petId = petId;
        return this;
    }

    /**
     * Устанавливает количество питомцев
     * @param quantity - количество
     * @return текущий OrderBuilder для цепочки вызовов
     */
    public OrderBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Устанавливает дату доставки
     * @param shipDate - дата доставки в формате ISO 8601
     * @return текущий OrderBuilder для цепочки вызовов
     */
    public OrderBuilder withShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    /**
     * Устанавливает статус заказа
     * @param status - статус (placed, approved, delivered)
     * @return текущий OrderBuilder для цепочки вызовов
     */
    public OrderBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Устанавливает флаг завершения заказа
     * @param complete - true если заказ завершён
     * @return текущий OrderBuilder для цепочки вызовов
     */
    public OrderBuilder withComplete(Boolean complete) {
        this.complete = complete;
        return this;
    }

    /**
     * Собирает и возвращает объект Order
     * @return готовый объект Order
     */
    public Order build() {
        Order order = new Order(id, petId, quantity, status);
        order.setShipDate(shipDate);
        order.setComplete(complete);
        return order;
    }
}