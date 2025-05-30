package com.example.news2.data.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.news2.utils.application;

public class SliderItem {
    private String imageUrl;
    private String title;

    public SliderItem(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }



    @BindingAdapter("android:load")
    public static void loadIMG(ImageView imageView, String url) {
        Glide.with(application.getContext()).load(url).into(imageView);
    }
}
