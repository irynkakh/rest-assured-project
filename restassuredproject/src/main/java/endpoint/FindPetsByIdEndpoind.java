package endpoint;

import dto.ErrorMessage;
import dto.RootObject;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FindPetsByIdEndpoind {

    public Response getFindById(String id) {
        Response response = given().pathParam("pet", id).
                when().get("http://petstore.swagger.io/v2/pet/{pet}");
        return response;
    }

    public Object getFindByIdMapping(String id) {
        Response response = getFindById(id);
        if (response.getStatusCode() == 200) {
            return response.getBody().as(RootObject.class);
        } else if (response.getStatusCode() == 400 || response.getStatusCode() == 404) {
            return response.getBody().as(ErrorMessage.class);
        }
        return null;
    }
}
