import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.AddPetsToStoreApiSteps;
import steps.FindPetsByIdApiSteps;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
@Concurrent
public class AddPetsToStoreCheck {

    private String id;
    private String categoryId;
    private String categoryName;
    private String name;
    private String photoUrls;
    private String tagsId;
    private String tagsName;
    private String status;

    @Steps
    private FindPetsByIdApiSteps findPetsByIdApiSteps;
    @Steps
    private AddPetsToStoreApiSteps addPetsToStoreApiSteps;

    public AddPetsToStoreCheck(String id, String categoryId, String categoryName, String name,
                               String photoUrls, String tagsId, String tagsName, String status) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tagsId = tagsId;
        this.tagsName = tagsName;
        this.status = status;

    }

    @TestData(columnNames = "id, category_id, category_name, name, photo_urls, tags_id, tags_name, status")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"1", "123", "category1", "dog", "urlsTest1", "111", "tags1", "available"},
                {"2", "124", "category2", "cat", "urlsTest2", "112", "tags2", "pending"},
                {"3", "125", "category1", "cow", "urlsTest3, urlsTest4, urlsTest5", "113", "tags1", "available"},
                {"4", "126", "category2", "pig", "urlsTest36", "114", "tags2", "pending"}

        });
    }

    @Test
    public void userCanAddPetsToTheStore() {
        Response response = addPetsToStoreApiSteps.addPetsToStore(id, categoryId, categoryName, name, photoUrls, tagsId, tagsName, status);
        findPetsByIdApiSteps.checkId(response, id);
        findPetsByIdApiSteps.checkName(response, name);
    }
}