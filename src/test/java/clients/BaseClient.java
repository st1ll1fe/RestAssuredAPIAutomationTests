package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {
    protected final RequestSpecification baseSpec;

    protected BaseClient(RequestSpecification spec) {
        this.baseSpec = spec;
    }

    protected RequestSpecification spec() {
        return RestAssured.given().spec(baseSpec);
    }

    protected Response get(String path) {
        return spec().when().get(path);
    }

    protected Response post(String path, Object body) {
        return spec().body(body).when().post(path);
    }

    protected Response put(String path, Object body) {
        return spec().body(body).when().put(path);
    }

    protected Response delete(String path) {
        return spec().when().delete(path);
    }
}
