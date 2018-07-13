import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.FindPetsByStatusApiSteps;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
@Concurrent
public class FindPetsByStatusCheck {
    private final String comment;
    private final String query;
    private final String result;

    @Steps
    private FindPetsByStatusApiSteps findPetsByStatusApiSteps;

    public FindPetsByStatusCheck(String comment, String query, String result) {
        this.comment = comment;
        this.query = query;
        this.result = result;
    }

    @TestData(columnNames = "test description, query, result")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"query with available status", "available", "available"},
                {"query with pending status", "pending", "pending"},
                {"query with sold status", "sold", "sold"}
        });
    }

    @Test
    public void userCanCheckStatusValueInFindByStatus() {
        Response actualStatus = findPetsByStatusApiSteps.getFindByStatus(query);
        findPetsByStatusApiSteps.checkStatus(actualStatus, result);
    }
}
