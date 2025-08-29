package com.example.news2.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {


    @SerializedName("metadata")
    private Metadata metadata;
    @SerializedName("record")
    private List<NewsDbModel> data;


    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<NewsDbModel> getData() {
        return data;
    }

    public void setData(List<NewsDbModel> data) {
        this.data = data;
    }
}
