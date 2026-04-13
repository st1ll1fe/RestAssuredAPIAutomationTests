package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Базовый класс, задающий базовый URI и общую спецификацию запросов.
 * Делает её доступной через protected поле `spec`.
 */
public class BaseTest {

    /** Базовый URL Petstore (можно переопределить переменной окружения BASE_URL). */
    protected static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "https://petstore.swagger.io/v2");

    /** Общий RequestSpecification, используемый во всех запросах. */
    protected static final RequestSpecification SPEC;

    static {
        RestAssured.baseURI = BASE_URL;
        SPEC = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }
}
