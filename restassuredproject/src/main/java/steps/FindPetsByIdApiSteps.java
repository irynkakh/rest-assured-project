package steps;

import endpoint.FindPetsByIdEndpoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FindPetsByIdApiSteps {
    FindPetsByIdEndpoint findPetsByIdEndpoint = new FindPetsByIdEndpoint();
    private JsonPath jsonPathEvaluator;

    @Step
    public Response getFindById(String apiKey, String id) {
        return findPetsByIdEndpoint.getFindById(apiKey, id);
    }

    @Step
    public void checkId(Response response, String result) {
        jsonPathEvaluator = response.jsonPath();
        Integer id = jsonPathEvaluator.get("id");
        assertThat("Id is not as expected", String.valueOf(id), equalTo(result));
    }

    @Step
    public void checkName(Response response, String result) {
        jsonPathEvaluator = response.jsonPath();
        String name = jsonPathEvaluator.get("name");
        assertThat("Name is not as expected", name, equalTo(result));
    }
}
