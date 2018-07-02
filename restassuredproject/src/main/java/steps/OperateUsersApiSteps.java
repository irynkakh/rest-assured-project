package steps;

import dto.UserDetails;
import endpoint.OperateUsersIntoSystemEndpoint;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class OperateUsersApiSteps {
    OperateUsersIntoSystemEndpoint operateUsersIntoSystemEndpoint = new OperateUsersIntoSystemEndpoint();

    @Step
    public void checkIsUserCreated(String firstName, String lastName, String username, String password, int userStatus, String phone, String email) {
        Response response = operateUsersIntoSystemEndpoint.createUser(firstName, lastName, username, password, userStatus, phone, email);
        assertThat("Status code is not as expected", response.getStatusCode(), equalTo(200));
    }

    @Step
    public UserDetails getInfoAboutUser(String username) {
        UserDetails response = operateUsersIntoSystemEndpoint.getUser(username);
        return response;
    }

    @Step
    public void checkUserName(UserDetails user, String username) {
        assertThat("There is no info about such user", user.getUsername(), equalTo(username));
    }

    @Step
    public void checkIsUserLogged(String username, String password) {
        Response response = operateUsersIntoSystemEndpoint.loginUser(username, password);
        assertThat("User is not logged into the system", response.getStatusCode(), equalTo(200));
    }

    @Step
    public void checkIsUserDeleted(String username) {
        Response response = operateUsersIntoSystemEndpoint.deleteUser(username);
        assertThat("User is not deleted", response.getStatusCode(), equalTo(200));
        assertThat("User is still not deleted", operateUsersIntoSystemEndpoint.getUserByUserName(username).getStatusCode(), equalTo(404));
    }

    @Step
    public void checkIsUserLoggedOut() {
        Response response = operateUsersIntoSystemEndpoint.logOutUser();
        assertThat("User is not logged out", response.getStatusCode(), equalTo(200));
    }
}
