package endpoint;

import dto.RootObject;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FindPetsByStatusEndpoint {

    public RootObject[] getFindByStatus(String query) {
        Response response = given().param("status", query).
                when().get("http://petstore.swagger.io/v2/pet/findByStatus");
        return response.getBody().as(RootObject[].class);
    }
}
