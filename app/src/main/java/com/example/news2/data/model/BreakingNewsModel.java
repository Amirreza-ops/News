package com.example.news2.data.model;

public class BreakingNewsModel {
    private String imageUrl;
    private String title;
    private String category;


    public BreakingNewsModel(String imageUrl, String title, String category) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.category = category;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
