package dto;

import java.math.BigInteger;
import java.util.ArrayList;

public class RootObject {
    private BigInteger id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Category> tags;
    private String status;

    public BigInteger getId() {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhotoUrls() {
        return this.photoUrls;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public ArrayList<Category> getTags() {
        return this.tags;
    }

    public void setTags(ArrayList<Category> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
