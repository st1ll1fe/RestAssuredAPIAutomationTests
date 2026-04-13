package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) для заказа из Petstore API
 * Используется для сериализации/десериализации JSON при работе с Store API
 */
public class Order {

    // ID заказа
    @JsonProperty("id")
    private Long id;

    // ID питомца, который заказывают
    @JsonProperty("petId")
    private Long petId;

    // Количество питомцев в заказе
    @JsonProperty("quantity")
    private Integer quantity;

    // Дата доставки (формат ISO 8601)
    @JsonProperty("shipDate")
    private String shipDate;

    // Статус заказа: placed, approved, delivered
    @JsonProperty("status")
    private String status;

    // Завершён ли заказ
    @JsonProperty("complete")
    private Boolean complete;

    // Конструктор по умолчанию (нужен для Jackson)
    public Order() {}

    // Конструктор с основными полями
    public Order(Long id, Long petId, Integer quantity, String status) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getShipDate() { return shipDate; }
    public void setShipDate(String shipDate) { this.shipDate = shipDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getComplete() { return complete; }
    public void setComplete(Boolean complete) { this.complete = complete; }
}