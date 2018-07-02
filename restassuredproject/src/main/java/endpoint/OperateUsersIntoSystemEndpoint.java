package endpoint;

import dto.UserDetails;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OperateUsersIntoSystemEndpoint {

    public Response createUser(String firstName, String lastName, String username, String password, int userStatus, String phone, String email) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("username", username);
        jsonAsMap.put("firstName", firstName);
        jsonAsMap.put("lastName", lastName);
        jsonAsMap.put("email", email);
        jsonAsMap.put("password", password);
        jsonAsMap.put("phone", phone);
        jsonAsMap.put("userStatus", userStatus);

        Response response = given().contentType(ContentType.JSON).body(jsonAsMap).
                when().post("https://petstore.swagger.io/v2/user");
        return response;
    }

    public Response getUserByUserName(String username) {
        Response response = given().pathParam("username", username).
                when().get("https://petstore.swagger.io/v2/user/{username}");

        return response;
    }

    public UserDetails getUser(String username) {
        Response response = getUserByUserName(username);
        return response.getBody().as(UserDetails.class);
    }

    public Response loginUser(String username, String password) {
        Response response = given().parameters("username", username, "password", password).
                when().get("http://petstore.swagger.io/v2/user/login");
        return response;
    }

    public Response deleteUser(String username) {
        Response response = given().pathParam("username", username).
                when().delete("https://petstore.swagger.io/v2/user/{username}");
        return response;
    }

    public Response logOutUser() {
        Response response = given().
                when().get("http://petstore.swagger.io/v2/user/logout");
        return response;
    }
}
