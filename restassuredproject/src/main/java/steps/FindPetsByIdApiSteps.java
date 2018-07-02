package steps;

import dto.ErrorMessage;
import dto.RootObject;
import endpoint.FindPetsByIdEndpoind;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FindPetsByIdApiSteps {
    FindPetsByIdEndpoind findByIdEndpoint = new FindPetsByIdEndpoind();

    @Step
    public Object getFindByIdMapping(String id) {
        return findByIdEndpoint.getFindByIdMapping(id);
    }

    @Step
    public void checkStatusCode(String id, String statusCode) {
        Response response = findByIdEndpoint.getFindById(id);
        assertThat("Status code is not as expected", String.valueOf(response.statusCode()), equalTo(statusCode));
    }

    @Step
    public void checkId(Object rootId, String result) {
        if (rootId.getClass().isInstance(RootObject.class)) {
            RootObject rootObject = RootObject.class.cast(rootId);
            if (rootObject.getId() != null) {
                assertThat("Id is not as expected", rootObject.getId()/*.toString()*/, equalTo(result));
            }
        } else if (rootId.getClass().isInstance(ErrorMessage.class)) {
            ErrorMessage errorMessage = ErrorMessage.class.cast(rootId);
            if (errorMessage.getCode() == 1) {
                assertThat("Error message is not as expected", errorMessage.getMessage(), equalTo(result));
            } else if (errorMessage.getCode() == 404) {
                assertThat("Error message is not as expected", errorMessage.getMessage(), equalTo(result));
            }
        }
    }
}
