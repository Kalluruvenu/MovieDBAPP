package com.example.vk.piratedmovies;

/**
 * Created by VK on 6/10/2017.
 */

public class Movies {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    private String title;
    private String original_title;
    private String image_path;

}
