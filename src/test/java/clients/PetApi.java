package clients;

import io.restassured.response.Response;
import models.Pet;

/**
 * Интерфейс, описывающий операции над сущностью Pet.
 * Позволяет подменять реализацию (реальный клиент, мок, заглушка).
 */
public interface PetApi {
    Response getPetById(Long petId);
    Response findByStatus(String status);
    Response createPet(Pet pet);
    Response updatePet(Pet pet);
    Response updatePetById(Long petId, String name, String status);
    Response deletePet(Long petId);
    Response uploadImage(Long petId, String additionalMetadata);
}
