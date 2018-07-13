package steps;

import endpoint.AddPetsToStoreEndpoint;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AddPetsToStoreApiSteps {
    AddPetsToStoreEndpoint addPetsToStoreEndpoint = new AddPetsToStoreEndpoint();

    @Step
    public Response addPetsToStore(String id, String idCat, String nameCategory, String name, String photoUrls,
                                   String idTag, String nameTag, String status) {
        Response response = addPetsToStoreEndpoint.addPets(id, idCat, nameCategory, name, photoUrls,
                idTag, nameTag, status);
        assertThat("Pet is not added to a store", response.statusCode(), equalTo(200));
        return response;
    }
}
