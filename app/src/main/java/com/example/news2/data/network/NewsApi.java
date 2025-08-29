package com.example.news2.data.network;

import com.example.news2.data.model.NewsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("68444cfa8a456b7966aa8d22")
    Single<NewsResponse> getTopHeadlines(@Header("X-Master-Key") String masterKey);
    @GET("68461cac8a456b7966ab0aa2")
    Single<NewsResponse>getHealthNews(@Header("X-Master-Key") String masterKey);
    @GET("68461de58960c979a5a6c9cb")
    Single<NewsResponse>getSportNews(@Header("X-Master-Key") String masterKey);
    @GET("68461e4d8960c979a5a6c9e0")
    Single<NewsResponse>getBusinessNews(@Header("X-Master-Key") String masterKey);

    Single<NewsResponse> getNewsByCategory(@Query("access_key") String access_key,
                                           @Query("categories") String categories,
                                           @Query("languages") String language);
}
