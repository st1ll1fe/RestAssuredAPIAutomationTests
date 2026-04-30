package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import models.Pet;
import models.User;

public class BaseTest {
    protected static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "https://petstore.swagger.io/v2");
    protected static final RequestSpecification SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType("application/json")
            .setAccept("application/json")
            .build();

    protected String generateId() {
        return String.valueOf(System.currentTimeMillis());
    }

    protected String generateUsername() {
        return "user_" + generateId();
    }

    protected String generatePetName() {
        return "Pet_" + generateId();
    }

    protected Pet createPet(Long id, String name, String status) {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setStatus(status);
        return pet;
    }

    protected User createUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
