package clients;

import io.restassured.response.Response;
import models.User;

/**
 * Интерфейс для операций над пользователями.
 */
public interface UserApi {
    Response getUserByUsername(String username);
    Response createUser(User user);
    Response createUserList(User[] users);
    Response updateUser(String username, User user);
    Response deleteUser(String username);
    Response login(String username, String password);
    Response logout();
}
