package clients;

import clients.ApiClientBase;
import io.restassured.response.Response;
import models.User;

/**
 * HTTP-клиент для работы с User API
 * Выполняет низкоуровневые HTTP-запросы к эндпоинтам пользователей
 */
public class UserClient extends ApiClientBase implements UserApi {

    // Базовый путь для User API
    private static final String USER_ENDPOINT = "/user";

    /**
     * Получает пользователя по имени (username)
     * @param username - имя пользователя
     * @return Response - ответ от сервера
     */
    public Response getUserByUsername(String username) {
        return get(USER_ENDPOINT + "/" + username);
    }

    /**
     * Создаёт нового пользователя
     * @param user - объект пользователя для создания
     * @return Response - ответ от сервера
     */
    public Response createUser(User user) {
        return post(USER_ENDPOINT, user);
    }

    /**
     * Создаёт список пользователей из массива
     * @param users - массив пользователей для создания
     * @return Response - ответ от сервера
     */
    public Response createUserList(User[] users) {
        return post(USER_ENDPOINT + "/createWithList", users);
    }

    /**
     * Обновляет существующего пользователя по имени
     * @param username - имя пользователя для обновления
     * @param user - объект пользователя с обновлёнными данными
     * @return Response - ответ от сервера
     */
    public Response updateUser(String username, User user) {
        return put(USER_ENDPOINT + "/" + username, user);
    }

    /**
     * Удаляет пользователя по имени
     * @param username - имя пользователя для удаления
     * @return Response - ответ от сервера
     */
    public Response deleteUser(String username) {
        return delete(USER_ENDPOINT + "/" + username);
    }

    /**
     * Выполняет вход пользователя в систему
     * @param username - имя пользователя
     * @param password - пароль пользователя
     * @return Response - ответ от сервера (содержит sessionId в message)
     */
    public Response login(String username, String password) {
        return spec.queryParam("username", username)
                .queryParam("password", password)
                .get(USER_ENDPOINT + "/login");
    }

    /**
     * Выполняет выход пользователя из системы
     * @return Response - ответ от сервера
     */
    public Response logout() {
        return get(USER_ENDPOINT + "/logout");
    }
}