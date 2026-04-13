package clients;

import clients.ApiClientBase;
import io.restassured.response.Response;
import models.Pet;

/**
 * HTTP-клиент для работы с Pet API
 * Выполняет низкоуровневые HTTP-запросы к эндпоинтам питомцев
 */
public class PetClient extends ApiClientBase implements PetApi {

    // Базовый путь для Pet API
    private static final String PET_ENDPOINT = "/pet";

    /**
     * Получает питомца по ID
     * @param petId - ID питомца
     * @return Response - ответ от сервера
     */
    public Response getPetById(Long petId) {
        return get(PET_ENDPOINT + "/" + petId);
    }

    /**
     * Ищет питомцев по статусу
     * @param status - статус питомца (available, pending, sold)
     * @return Response - ответ от сервера
     */
    public Response findByStatus(String status) {
        return spec.queryParam("status", status).get(PET_ENDPOINT + "/findByStatus");
    }

    /**
     * Создаёт нового питомца
     * @param pet - объект питомца для создания
     * @return Response - ответ от сервера
     */
    public Response createPet(Pet pet) {
        return post(PET_ENDPOINT, pet);
    }

    /**
     * Обновляет существующего питомца (полное обновление)
     * @param pet - объект питомца с обновлёнными данными
     * @return Response - ответ от сервера
     */
    public Response updatePet(Pet pet) {
        return put(PET_ENDPOINT, pet);
    }

    /**
     * Обновляет питомца по ID через форму (частичное обновление)
     * @param petId - ID питомца
     * @param name - новое имя питомца
     * @param status - новый статус питомца
     * @return Response - ответ от сервера
     */
    public Response updatePetById(Long petId, String name, String status) {
        return spec.formParam("name", name)
                .formParam("status", status)
                .post(PET_ENDPOINT + "/" + petId);
    }

    /**
     * Удаляет питомца по ID
     * @param petId - ID питомца
     * @return Response - ответ от сервера
     */
    public Response deletePet(Long petId) {
        return delete(PET_ENDPOINT + "/" + petId);
    }

    /**
     * Загружает изображение для питомца
     * @param petId - ID питомца
     * @param additionalMetadata - дополнительные метаданные
     * @return Response - ответ от сервера
     */
    public Response uploadImage(Long petId, String additionalMetadata) {
        return spec.multiPart("file", new byte[0], "application/octet-stream")
                .formParam("additionalMetadata", additionalMetadata)
                .post(PET_ENDPOINT + "/" + petId + "/uploadImage");
    }
}