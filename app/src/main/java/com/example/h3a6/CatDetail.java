package com.example.h3a6;

public class CatDetail {
    //breeds = cat information
    private Cats[] breeds;
    private String id;
    //url = image
    private String url;

    public Cats[] getBreeds() {
        return breeds;
    }

    public void setBreeds(Cats[] breeds) {
        this.breeds = breeds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
