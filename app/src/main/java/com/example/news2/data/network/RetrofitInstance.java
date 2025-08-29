package com.example.news2.data.network;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "https://api.jsonbin.io/v3/b/";

    private static Retrofit instance = null;


    public static final synchronized NewsApi getInstance() {

        if (instance == null) {
            instance = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return instance.create(NewsApi.class);
    }
}
