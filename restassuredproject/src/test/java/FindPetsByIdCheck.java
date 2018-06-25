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
    private final String code;

    @Steps
    private FindPetsByIdApiSteps findPetsByIdApiSteps;

    public FindPetsByIdCheck(String comment, String id, String result, String code) {
        this.comment = comment;
        this.id = id;
        this.result = result;
        this.code = code;
    }

    @TestData(columnNames = "test description, id, result, status")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"query with id=1", "1", "1", "200"},
                {"query with id=10", "10", "10", "200"},
                {"query with id=23471", "23471", "23471", "200"},
                {"query with id=bla", "bla", "Pet not found", "404"},
                {"query with id=000", "000", "Pet not found", "404"},

        });
    }

    @Test
    public void userCanCheckIdValueInFindById() {
        findPetsByIdApiSteps.checkStatusCode(id, code);
        Object actualId = findPetsByIdApiSteps.getFindByIdMapping(id);
        findPetsByIdApiSteps.checkId(actualId, result);
    }
}
