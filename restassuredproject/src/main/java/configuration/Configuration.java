package configuration;

import io.restassured.RestAssured;

public class Configuration {
    private final String baseUrl = "https://petstore.swagger.io/v2";

    public Configuration() {
        RestAssured.baseURI = baseUrl;
    }
}
