package com.example.news2.data.model;

import com.google.gson.annotations.SerializedName;

public class Metadata {
    private String id;
    @SerializedName("private")
    private boolean prvt;
    private String createdAt;
    private String name;
}
