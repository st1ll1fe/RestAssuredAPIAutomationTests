package clients;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import models.Pet;

public class PetClient extends BaseClient {
    private static final String PET_ENDPOINT = "/pet";

    public PetClient(RequestSpecification spec) {
        super(spec);
    }

    public Response getPetById(Long petId) {
        return get(PET_ENDPOINT + "/" + petId);
    }

    public Response findByStatus(String status) {
        return spec().queryParam("status", status).get(PET_ENDPOINT + "/findByStatus");
    }

    public Response createPet(Pet pet) {
        return post(PET_ENDPOINT, pet);
    }

    public Response deletePet(Long petId) {
        return delete(PET_ENDPOINT + "/" + petId);
    }
}
