import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.FindPetsByIdApiSteps;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
@Concurrent
public class FindPetsByIdCheck {
    private final String comment;
    private final String id;
    private final String result;

    @Steps
    private FindPetsByIdApiSteps findPetsByIdApiSteps;

    public FindPetsByIdCheck(String comment, String id, String result) {
        this.comment = comment;
        this.id = id;
        this.result = result;
    }

    @TestData(columnNames = "test description, id, result")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"query with id=2", "2", "2"},
                {"query with id=10", "10", "10"},
                {"query with id=23471", "23471", "23471"}
        });
    }

    @Test
    public void userCanCheckIdValueInFindById() {
        String api_key = "2222";
        Response actualId = findPetsByIdApiSteps.getFindById(api_key, id);
        findPetsByIdApiSteps.checkId(actualId, result);
    }
}
