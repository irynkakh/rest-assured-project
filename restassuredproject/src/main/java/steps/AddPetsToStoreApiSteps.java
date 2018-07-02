package steps;

import endpoint.AddPetToStoreEndpoint;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddPetsToStoreApiSteps {
    AddPetToStoreEndpoint addPetToStoreEndpoint = new AddPetToStoreEndpoint();

    @Step
    public void addPetsToStore(String id, String idCat, String nameCategory, String name, String photoUrls, String idTag, String nameTag, String status) {
        Response response = addPetToStoreEndpoint.addPets(id, idCat, nameCategory, name, photoUrls, idTag, nameTag, status);
        assertThat("Pet is not added to a store", response.statusCode(), equalTo(200));
    }
}
