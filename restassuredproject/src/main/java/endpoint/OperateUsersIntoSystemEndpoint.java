package endpoint;

import configuration.Configuration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OperateUsersIntoSystemEndpoint extends Configuration {

    public Response createUser(String firstName, String lastName, String username, String password, int userStatus,
                               String phone, String email) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("username", username);
        jsonAsMap.put("firstName", firstName);
        jsonAsMap.put("lastName", lastName);
        jsonAsMap.put("email", email);
        jsonAsMap.put("password", password);
        jsonAsMap.put("phone", phone);
        jsonAsMap.put("userStatus", userStatus);

        Response response = given().contentType(ContentType.JSON).body(jsonAsMap).when().post("/user");
        return response;
    }

    public Response getUserByUserName(String username) {
        Response response = given().pathParam("username", username).
                when().get("/user/{username}");
        return response;
    }


    public Response loginUser(String username, String password) {
        Response response = given().parameters("username", username, "password", password).
                when().get("/user/login");
        return response;
    }

    public Response deleteUser(String username) {
        Response response = given().pathParam("username", username).
                when().delete("/user/{username}");
        return response;
    }

    public Response logOutUser() {
        Response response = given().
                when().get("/user/logout");
        return response;
    }
}
