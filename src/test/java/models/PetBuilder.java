package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder для создания объектов Pet
 * Позволяет удобно конструировать объекты питомцев с помощью цепочки методов
 */
public class PetBuilder {

    private Long id;
    private String name;
    private Pet.Category category;
    private List<String> photoUrls = new ArrayList<>();
    private List<Pet.Tag> tags = new ArrayList<>();
    private String status;

    /**
     * Создает новый экземпляр PetBuilder
     * @return новый PetBuilder
     */
    public static PetBuilder aPet() {
        return new PetBuilder();
    }

    /**
     * Устанавливает ID питомца
     * @param id - ID питомца
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Устанавливает имя питомца
     * @param name - имя питомца
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Устанавливает категорию питомца (объект Category)
     * @param category - объект категории
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withCategory(Pet.Category category) {
        this.category = category;
        return this;
    }

    /**
     * Устанавливает категорию питомца (создает новый объект Category)
     * @param id - ID категории
     * @param name - название категории
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withCategory(Long id, String name) {
        this.category = new Pet.Category(id, name);
        return this;
    }

    /**
     * Устанавливает список URL фотографий
     * @param photoUrls - список URL
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    /**
     * Добавляет один URL фотографии в список
     * @param url - URL фотографии
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withPhotoUrl(String url) {
        this.photoUrls.add(url);
        return this;
    }

    /**
     * Устанавливает список тегов
     * @param tags - список тегов
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withTags(List<Pet.Tag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Добавляет один тег в список
     * @param tag - объект тега
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withTag(Pet.Tag tag) {
        this.tags.add(tag);
        return this;
    }

    /**
     * Создает и добавляет новый тег в список
     * @param id - ID тега
     * @param name - название тега
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withTag(Long id, String name) {
        this.tags.add(new Pet.Tag(id, name));
        return this;
    }

    /**
     * Устанавливает статус питомца
     * @param status - статус (available, pending, sold)
     * @return текущий PetBuilder для цепочки вызовов
     */
    public PetBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Собирает и возвращает объект Pet
     * @return готовый объект Pet
     */
    public Pet build() {
        Pet pet = new Pet(id, name, status);
        pet.setCategory(category);
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        return pet;
    }
}