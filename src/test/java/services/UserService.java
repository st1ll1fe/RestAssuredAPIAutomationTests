package services;

import clients.UserApi;
import clients.UserClient;
import io.restassured.response.Response;
import models.User;

import static config.Config.generateId;
import static config.Config.generateUsername;
import static models.UserBuilder.anUser;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

/**
 * Сервисный слой для работы с User API
 * Содержит бизнес-логику и комбинирует несколько API вызовов при необходимости
 */
public class UserService {

    // HTTP-клиент для выполнения запросов к User API
    private final UserApi userClient = new UserClient();

    /**
     * Получает пользователя по имени (username)
     * @param username - имя пользователя
     * @return Response - ответ от сервера
     */
    public Response getUserByUsername(String username) {
        return userClient.getUserByUsername(username);
    }

    /**
     * Создаёт пользователя с указанными параметрами
     * @param username - имя пользователя (логин)
     * @param email - email пользователя
     * @param password - пароль пользователя
     * @return Response - ответ от сервера
     */
    public Response createUser(String username, String email, String password) {
        User user = anUser()
                .withUsername(username)
                .withEmail(email)
                .withPassword(password)
                .build();
        return userClient.createUser(user);
    }

    /**
     * Создаёт пользователя из готового объекта User
     * @param user - объект пользователя
     * @return Response - ответ от сервера
     */
    public Response createUser(User user) {
        return userClient.createUser(user);
    }

    /**
     * Создаёт список пользователей из массива
     * @param users - массив пользователей
     * @return Response - ответ от сервера
     */
    public Response createUserList(User[] users) {
        return userClient.createUserList(users);
    }

    /**
     * Обновляет пользователя по имени
     * @param username - имя пользователя для обновления
     * @param email - новый email
     * @param password - новый пароль
     * @return Response - ответ от сервера
     */
    public Response updateUser(String username, String email, String password) {
        User user = anUser()
                .withUsername(username)
                .withEmail(email)
                .withPassword(password)
                .build();
        return userClient.updateUser(username, user);
    }

    /**
     * Удаляет пользователя по имени
     * @param username - имя пользователя для удаления
     * @return Response - ответ от сервера
     */
    public Response deleteUser(String username) {
        return userClient.deleteUser(username);
    }

    /**
     * Выполняет вход пользователя в систему
     * @param username - имя пользователя
     * @param password - пароль
     * @return Response - ответ от сервера
     */
    public Response login(String username, String password) {
        return userClient.login(username, password);
    }

    /**
     * Выполняет выход пользователя из системы
     * @return Response - ответ от сервера
     */
    public Response logout() {
        return userClient.logout();
    }

    /**
     * Создаёт пользователя со случайным уникальным именем
     * Используется в тестах для генерации уникальных пользователей
     * @return имя созданного пользователя (username)
     */
    public String createAndGetUsername() {
        String username = generateUsername();
        Response resp = createUser(username, username + "@test.com", "password123");
        // Ensure пользователь действительно создан, иначе тест будет нестабилен
        resp.then().statusCode(anyOf(equalTo(200), equalTo(201)));
        // Поскольку API иногда возвращает 404 сразу после создания, делаем небольшую пере‑проверку
        int attempts = 0;
        while (attempts < 10) {
            Response getResp = getUserByUsername(username);
            if (getResp.getStatusCode() == 200) {
                break;
            }
            attempts++;
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }
        return username;
    }

    /**
     * Создаёт пользователя со случайным уникальным именем и возвращает объект
     * Используется в тестах для получения полного объекта пользователя
     * @return объект созданного пользователя User
     */
    public User createAndGetUser() {
        String username = generateUsername();
        User user = anUser()
                .withUsername(username)
                .withEmail(username + "@test.com")
                .withPassword("password123")
                .withFirstName("Test")
                .withLastName("User")
                .build();
        createUser(user);
        return user;
    }
}