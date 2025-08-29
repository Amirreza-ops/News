package com.example.news2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.news2.data.local.DAO;
import com.example.news2.data.local.NewsDB;
import com.example.news2.data.model.News;
import com.example.news2.data.model.NewsDbModel;
import com.example.news2.data.repository.Repository;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class NewsViewModel extends ViewModel {

    private CompositeDisposable disposable = new CompositeDisposable();


    public LiveData<List<NewsDbModel>> getTopHeadlines() {
        return Repository.getInstance().getTopHeadlines(disposable);
    }

    public LiveData<List<NewsDbModel>> getHealthNews(){
        return Repository.getInstance().getHealthNews(disposable);
    }

    public LiveData<List<NewsDbModel>> getSportNews(){
        return Repository.getInstance().getSportNews(disposable);
    }

    public LiveData<List<NewsDbModel>> getBusinessNews(){
        return Repository.getInstance().getBusinessNews(disposable);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
