package com.example.news2.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.news2.data.model.NewsDbModel;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    public void insertNews(NewsDbModel newsDbModel);

    @Delete
    public void deleteNews(NewsDbModel newsDbModel);

    @Query("select * from saved_news")
    public LiveData<List<NewsDbModel>> getAllNews();

    @Query("select * from saved_news where id ==:id")
    public LiveData<NewsDbModel> getNews(int id);
}
