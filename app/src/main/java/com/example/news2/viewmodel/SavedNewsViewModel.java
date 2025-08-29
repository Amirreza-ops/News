package com.example.news2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.news2.data.local.DAO;
import com.example.news2.data.local.NewsDB;
import com.example.news2.data.model.NewsDbModel;

import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class SavedNewsViewModel extends AndroidViewModel {

    private DAO dao;




    public SavedNewsViewModel(@NonNull Application application) {
        super(application);

        dao = NewsDB.getInstance(application.getApplicationContext()).getDAO();
    }


    public LiveData<List<NewsDbModel>> getSavedNews(){
        return dao.getAllNews();
    }

    public void saveNews(NewsDbModel news){
        Schedulers.io().scheduleDirect(() -> dao.insertNews(news));
    }

    public LiveData<NewsDbModel> getNews(int id){
        return dao.getNews(id);
    }

    public void deleteNews(NewsDbModel news){
        Schedulers.io().scheduleDirect(() -> dao.deleteNews(news));
    }
}
