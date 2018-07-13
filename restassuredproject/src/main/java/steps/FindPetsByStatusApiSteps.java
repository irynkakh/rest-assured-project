package steps;

import endpoint.FindPetsByStatusEndpoint;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FindPetsByStatusApiSteps {
    FindPetsByStatusEndpoint findPetsByStatusEndpoint = new FindPetsByStatusEndpoint();

    @Step
    public Response getFindByStatus(String query) {
        return findPetsByStatusEndpoint.getFindByStatus(query);
    }

    @Step
    public void checkStatus(Response response, String result) {
        List<String> actualStatus = response.path("status");
        for (String status : actualStatus)
            assertThat("Status is not as expacted", status, equalTo(result));
    }
}
