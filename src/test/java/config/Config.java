package config;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import java.util.UUID;

/**
 * Класс конфигурации для тестов
 * Содержит базовые настройки URL, таймауты и утилиты для генерации тестовых данных
 */
public class Config {

    // Базовый URL для API
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    // Таймаут ожидания ответа в секундах
    public static final int TIMEOUT = 30;

    /**
     * Генерирует уникальный числовой ID из UUID
     * Преобразует первые 8 символов hex UUID в положительное число
     */
    private static String uniqueId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        long numericValue = Math.abs(Long.parseLong(uuid.substring(0, 8), 16));
        return String.valueOf(numericValue);
    }

    /**
     * Генерирует уникальный ID для использования в тестах
     */
    public static String generateId() {
        return uniqueId();
    }

    /**
     * Генерирует уникальное имя пользователя с префиксом "user_"
     */
    public static String generateUsername() {
        return "user_" + uniqueId();
    }

    /**
     * Генерирует уникальное имя питомца с префиксом "Pet_"
     */
    public static String generatePetName() {
        return "Pet_" + uniqueId();
    }

    /**
     * Создает базовую спецификацию для HTTP запросов
     * Включает настройки логирования и заголовки по умолчанию
     */
    public static RequestSpecification getDefaultSpec() {
        return RestAssured.given()
                .config(getRestAssuredConfig())
                .contentType("application/json")
                .accept("application/json");
    }

    /**
     * Возвращает конфигурацию REST Assured с настройками логирования
     * Логирует запросы и ответы только при ошибках валидации
     * Скрывает заголовок Authorization из логов для безопасности
     */
    public static RestAssuredConfig getRestAssuredConfig() {
        return RestAssured.config()
                .logConfig(new LogConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails()
                        .blacklistHeader("Authorization"));
    }

    /**
     * Настраивает базовый URI для REST Assured
     */
    public static void configureBaseURI() {
        RestAssured.baseURI = BASE_URL;
    }
}