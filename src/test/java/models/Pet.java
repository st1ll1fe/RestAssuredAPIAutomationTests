package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO (Data Transfer Object) для питомца из Petstore API
 * Используется для сериализации/десериализации JSON при работе с Pet API
 */
public class Pet {

    // Уникальный идентификатор питомца
    @JsonProperty("id")
    private Long id;

    // Имя питомца
    @JsonProperty("name")
    private String name;

    // Категория (вид питомца)
    @JsonProperty("category")
    private Category category;

    // Список URL фотографий питомца
    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    // Список тегов питомца
    @JsonProperty("tags")
    private List<Tag> tags;

    // Статус питомца: available, pending, sold
    @JsonProperty("status")
    private String status;

    // Конструктор по умолчанию (нужен для Jackson)
    public Pet() {}

    // Конструктор с основными полями
    public Pet(Long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public List<String> getPhotoUrls() { return photoUrls; }
    public void setPhotoUrls(List<String> photoUrls) { this.photoUrls = photoUrls; }

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    /**
     * Вложенный класс для категории питомца
     */
    public static class Category {
        // ID категории
        @JsonProperty("id")
        private Long id;

        // Название категории
        @JsonProperty("name")
        private String name;

        public Category() {}

        public Category(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    /**
     * Вложенный класс для тега питомца
     */
    public static class Tag {
        // ID тега
        @JsonProperty("id")
        private Long id;

        // Название тега
        @JsonProperty("name")
        private String name;

        public Tag() {}

        public Tag(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}