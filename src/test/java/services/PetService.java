package services;

import clients.PetApi;
import clients.PetClient;
import io.restassured.response.Response;
import models.Pet;

import static config.Config.generateId;
import static config.Config.generatePetName;
import static models.PetBuilder.aPet;

/**
 * Сервисный слой для работы с Pet API
 * Содержит бизнес-логику и комбинирует несколько API вызовов при необходимости
 */
public class PetService {

    // HTTP-клиент для выполнения запросов к Pet API
    private final PetApi petClient = new PetClient();

    /**
     * Создаёт питомца с указанными параметрами
     * @param id - ID питомца
     * @param name - имя питомца
     * @param status - статус питомца
     * @return Response - ответ от сервера
     */
    public Response createPet(Long id, String name, String status) {
        Pet pet = aPet()
                .withId(id)
                .withName(name)
                .withStatus(status)
                .build();
        return petClient.createPet(pet);
    }

    /**
     * Создаёт питомца из готового объекта Pet
     * @param pet - объект питомца
     * @return Response - ответ от сервера
     */
    public Response createPet(Pet pet) {
        return petClient.createPet(pet);
    }

    /**
     * Получает питомца по ID
     * @param id - ID питомца
     * @return Response - ответ от сервера
     */
    public Response getPetById(Long id) {
        return petClient.getPetById(id);
    }

    /**
     * Ищет питомцев по статусу
     * @param status - статус питомца
     * @return Response - ответ от сервера (список питомцев)
     */
    public Response findByStatus(String status) {
        return petClient.findByStatus(status);
    }

    /**
     * Обновляет питомца (полное обновление)
     * @param id - ID питомца
     * @param name - новое имя
     * @param status - новый статус
     * @return Response - ответ от сервера
     */
    public Response updatePet(Long id, String name, String status) {
        Pet pet = aPet()
                .withId(id)
                .withName(name)
                .withStatus(status)
                .build();
        return petClient.updatePet(pet);
    }

    /**
     * Обновляет питомца по ID (частичное обновление через форму)
     * @param id - ID питомца
     * @param name - новое имя
     * @param status - новый статус
     * @return Response - ответ от сервера
     */
    public Response updatePetById(Long id, String name, String status) {
        return petClient.updatePetById(id, name, status);
    }

    /**
     * Удаляет питомца по ID
     * @param id - ID питомца
     * @return Response - ответ от сервера
     */
    public Response deletePet(Long id) {
        return petClient.deletePet(id);
    }

    /**
     * Создаёт питомца со случайными данными (уникальный ID и имя)
     * Используется для тестов, чтобы избежать конфликтов
     * @return Response - ответ от сервера
     */
    public Response createRandomPet() {
        Long id = Long.parseLong(generateId());
        String name = generatePetName();
        return createPet(id, name, "available");
    }

    /**
     * Создаёт питомца со случайными данными и возвращает его ID
     * Используется в тестах для получения ID созданного питомца
     * @return ID созданного питомца
     */
    public Long createAndGetPetId() {
        Long id = Long.parseLong(generateId());
        String name = generatePetName();
        createPet(id, name, "available");
        return id;
    }
}