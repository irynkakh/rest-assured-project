package endpoint;

import configuration.Configuration;
import dto.Category;
import dto.Pets;
import io.restassured.response.Response;

import java.math.BigInteger;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class AddPetsToStoreEndpoint extends Configuration {

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
        Pets jsonConfig = jsonConfiguration(id, category, name, photoUrls, tags, status);
        Response response = given().header("Content-Type", "application/json").body(jsonConfig).
                when().post("/pet");
        return response;
    }

    private Pets jsonConfiguration(BigInteger id, Category category, String name, ArrayList<String> photoUrls,
                                   ArrayList<Category> tags, String status) {
        Pets rootObject = new Pets();
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
