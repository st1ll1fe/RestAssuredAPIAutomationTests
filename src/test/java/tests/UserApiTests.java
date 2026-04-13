package tests;

import config.BaseTest;
import config.Config;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static models.UserBuilder.anUser;

/**
 * Тесты для User API (пользователи)
 * Проверяют функциональность работы с пользователями в системе
 */
public class UserApiTests extends BaseTest {

    // Сервис для работы с User API
    private final services.UserService userService = new services.UserService();

    /**
     * Тест: Создание пользователя
     * Проверяет, что можно создать нового пользователя с уникальными данными
     */
    @Test
    void shouldCreateUser() {
        Response response = userService.createUser(
                "testuser_" + Config.generateId(), 
                "test@test.com", 
                "password123"
        );

        response.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"));
    }

    /**
     * Тест: Получение пользователя по имени
     * Проверяет, что можно получить информацию о созданном пользователе
     */
@Test
    void shouldGetUserByUsername() throws InterruptedException {
        String username = userService.createAndGetUsername();
        Response response = userService.getUserByUsername(username);
        // Если API возвращает 404 после создания, повторяем запрос несколько раз, пока не получим 200 (максимум 20 попыток)
        int attempts = 0;
        while (response.getStatusCode() == 404 && attempts < 20) {
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            response = userService.getUserByUsername(username);
            attempts++;
        }
        // After retry we expect 200; if still 404 we treat as failure
        response.then()
                .statusCode(200)
                .body("username", equalTo(username));
    }

    /**
     * Тест: Получение 404 при запросе несуществующего пользователя
     */
    @Test
    void shouldReturn404WhenUserNotFound() {
        Response response = userService.getUserByUsername("nonexistentuser123");
        response.then().statusCode(404);
    }

    /**
     * Тест: Обновление пользователя
     * Проверяет, что можно обновить данные существующего пользователя
     */
    @Test
    void shouldUpdateUser() {
        String username = userService.createAndGetUsername();
        
        Response response = userService.updateUser(username, "updated@test.com", "newpassword");

        response.then()
                .statusCode(200);
    }

    /**
     * Тест: Удаление пользователя
     * Проверяет, что можно удалить пользователя и получить 404 при повторном запросе
     */


    /**
     * Тест: Вход пользователя в систему
     * Проверяет, что можно войти с правильными учетными данными
     */
    @Test
    void shouldLoginUser() {
        String username = userService.createAndGetUsername();
        
        Response response = userService.login(username, "password123");

        response.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", containsString("logged in"));
    }

    /**
     * Тест: Выход пользователя из системы
     * Проверяет, что можно выйти из системы
     */
    @Test
    void shouldLogoutUser() {
        Response response = userService.logout();

        response.then()
                .statusCode(200)
                .body("code", equalTo(200));
    }

    /**
     * Тест: Создание списка пользователей
     * Проверяет, что можно создать нескольких пользователей одним запросом
     */
    @Test
    void shouldCreateUserWithList() {
        models.User[] users = new models.User[] {
                anUser()
                        .withUsername("user1_" + Config.generateId())
                        .withEmail("user1@test.com")
                        .withPassword("pass1")
                        .build(),
                anUser()
                        .withUsername("user2_" + Config.generateId())
                        .withEmail("user2@test.com")
                        .withPassword("pass2")
                        .build()
        };

        Response response = userService.createUserList(users);

        response.then()
                .statusCode(200)
                .body("code", equalTo(200));
    }
}