package clients;

import config.BaseTest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Базовый клиент, предоставляющий методы‑обертки и Allure‑фильтр.
 * Наследует BaseTest, получая общую спецификацию запроса.
 */
public abstract class ApiClientBase extends BaseTest {

    /** Спецификация, унаследованная из BaseTest, с подключенным Allure‑фильтром. */
    protected final RequestSpecification spec;

    protected ApiClientBase() {
        // Создаём RequestSpecification на основе базовой, добавляем Allure‑фильтр
        this.spec = RestAssured.given()
                .spec(BaseTest.SPEC)
                .filter(new AllureRestAssured());
    }

    // Простейшие HTTP‑методы -------------------------------------------------
    protected Response get(String path) {
        return spec.when().get(path);
    }

    protected Response post(String path, Object body) {
        return spec.body(body).when().post(path);
    }

    protected Response put(String path, Object body) {
        return spec.body(body).when().put(path);
    }

    protected Response delete(String path) {
        return spec.when().delete(path);
    }
}
