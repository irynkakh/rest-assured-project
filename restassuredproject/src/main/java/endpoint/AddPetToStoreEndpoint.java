package endpoint;

import dto.Category;
import dto.RootObject;
import io.restassured.response.Response;

import java.math.BigInteger;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class AddPetToStoreEndpoint {

    public Response addPets(String idString, String idCatString, String nameCategory, String name, String photoUrlsString,
                            String idTagString, String nameTag, String status) {
        BigInteger id = new BigInteger(idString);
        BigInteger idCat = new BigInteger(idCatString);
        BigInteger idTag = new BigInteger(idTagString);
        Category category = categoryConfiguration(idCat, nameCategory);
        Category tagsCategory = categoryConfiguration(idTag, nameTag);
        ArrayList<Category> tags = new ArrayList<>();
        tags.add(tagsCategory);
        ArrayList<String> photoUrls = photoUrlsConfiguration(photoUrlsString);
        RootObject jsonConfig = jsonConfiguration(id, category, name, photoUrls, tags, status);
        Response response = given().header("Content-Type", "application/json").body(jsonConfig).
                when().post("https://petstore.swagger.io/v2/pet");
        return response;
    }

    private RootObject jsonConfiguration(BigInteger id, Category category, String name, ArrayList<String> photoUrls,
                                         ArrayList<Category> tags, String status) {
        RootObject rootObject = new RootObject();
        rootObject.setId(id);
        rootObject.setCategory(category);
        rootObject.setName(name);
        rootObject.setPhotoUrls(photoUrls);
        rootObject.setTags(tags);
        rootObject.setName(name);
        rootObject.setStatus(status);
        return rootObject;
    }

    private Category categoryConfiguration(BigInteger id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    private ArrayList<String> photoUrlsConfiguration(String photoUrlsParameter) {
        ArrayList<String> photoUrls = new ArrayList<>();
        for (String parameter : photoUrlsParameter.split(",")) {
            photoUrls.add(parameter);
        }
        return photoUrls;
    }
}
