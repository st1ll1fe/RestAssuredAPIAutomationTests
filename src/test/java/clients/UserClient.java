package clients;

import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import models.User;

public class UserClient extends BaseClient {
    private static final String USER_ENDPOINT = "/user";

    public UserClient(RequestSpecification spec) {
        super(spec);
    }

    public Response getUserByUsername(String username) {
        return get(USER_ENDPOINT + "/" + username);
    }

    public Response createUser(User user) {
        return post(USER_ENDPOINT, user);
    }

    public Response createUserList(User[] users) {
        return post(USER_ENDPOINT + "/createWithList", users);
    }

    public Response login(String username, String password) {
        return spec().queryParam("username", username)
                .queryParam("password", password)
                .get(USER_ENDPOINT + "/login");
    }

    public Response logout() {
        return get(USER_ENDPOINT + "/logout");
    }
}
