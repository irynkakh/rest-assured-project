package endpoint;

import configuration.Configuration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FindPetsByStatusEndpoint extends Configuration {

    public Response getFindByStatus(String query) {
        Response response = given().param("status", query).when().get("/pet/findByStatus");
        return response;
    }
}
