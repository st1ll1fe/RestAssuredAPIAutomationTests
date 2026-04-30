package tests;

import clients.PetClient;
import config.BaseTest;
import io.restassured.response.Response;
import models.Pet;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetApiTests extends BaseTest {
    private final PetClient petClient = new PetClient(SPEC);

    @Test
    void shouldCreatePet() {
        Pet pet = createPet(Long.parseLong(generateId()), generatePetName(), "available");
        Response response = petClient.createPet(pet);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    void shouldGetPetById() {
        Long petId = Long.parseLong(generateId());
        petClient.createPet(createPet(petId, generatePetName(), "available"));
        Response response = petClient.getPetById(petId);
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getLong("id"), equalTo(petId));
    }

    @Test
    void shouldReturn404WhenPetNotFound() {
        Response response = petClient.getPetById(999999L);
        response.then().statusCode(404);
    }

    @Test
    void shouldDeletePet() {
        Long petId = Long.parseLong(generateId());
        petClient.createPet(createPet(petId, generatePetName(), "available"));
        Response deleteResponse = petClient.deletePet(petId);
        assertThat(deleteResponse.getStatusCode(), equalTo(200));
    }

    @Test
    void shouldFindPetsByStatus() {
        Response response = petClient.findByStatus("available");
        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("status", everyItem(equalTo("available")));
    }
}
