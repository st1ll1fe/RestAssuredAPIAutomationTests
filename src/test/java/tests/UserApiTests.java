package tests;

import clients.UserClient;
import config.BaseTest;
import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserApiTests extends BaseTest {
    private final UserClient userClient = new UserClient(SPEC);

    @Test
    void shouldCreateUser() {
        String username = generateUsername();
        User user = createUser(username, username + "@test.com", "password123");
        Response response = userClient.createUser(user);
        response.then().statusCode(200);
    }

    @Test
    void shouldGetUserByUsername() {
        String username = generateUsername();
        User user = createUser(username, username + "@test.com", "password123");
        userClient.createUser(user);
        Response response = userClient.getUserByUsername(username);
        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("username"), equalTo(username));
    }

    @Test
    void shouldReturn404WhenUserNotFound() {
        Response response = userClient.getUserByUsername("nonexistentuser123");
        response.then().statusCode(404);
    }

    @Test
    void shouldLoginUser() {
        String username = generateUsername();
        User user = createUser(username, username + "@test.com", "password123");
        userClient.createUser(user);
        Response response = userClient.login(username, "password123");
        response.then().statusCode(200);
        assertThat(response.jsonPath().getString("message"), containsString("logged in"));
    }

    @Test
    void shouldLogoutUser() {
        Response response = userClient.logout();
        response.then().statusCode(200);
    }

    @Test
    void shouldCreateUserList() {
        String username1 = generateUsername();
        String username2 = generateUsername();
        User user1 = createUser(username1, username1 + "@test.com", "pass1");
        User user2 = createUser(username2, username2 + "@test.com", "pass2");
        User[] users = new User[]{user1, user2};
        Response response = userClient.createUserList(users);
        response.then().statusCode(200);
    }
}
