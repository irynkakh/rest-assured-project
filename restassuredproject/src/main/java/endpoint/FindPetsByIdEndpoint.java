package endpoint;

import configuration.Configuration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FindPetsByIdEndpoint extends Configuration {

    public Response getFindById(String apiKey, String id) {
        Response response = given().header("api_key", apiKey).pathParam("pet", id).when().get("/pet/{pet}");
        return response;
    }

}
