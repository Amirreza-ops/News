package com.example.news2.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.news2.data.model.News;
import com.example.news2.data.model.NewsDbModel;
import com.example.news2.data.model.NewsResponse;
import com.example.news2.data.network.RetrofitInstance;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {

    private static final String MASTER_KEY = "$2a$10$iqhpL8k6.Qaip9PxmRWUmOXxvUncJWyzT7QkUu1VKrLxNnsEk7LUG";
    private static final String LANG = "en";
    private static Repository repositoryInstance = null;


    public static final Repository getInstance() {
        if (repositoryInstance == null) {
            repositoryInstance = new Repository();
        }

        return repositoryInstance;
    }


    public LiveData<List<NewsDbModel>> getTopHeadlines(CompositeDisposable disposable) {

        MutableLiveData<List<NewsDbModel>> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().getTopHeadlines(MASTER_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NewsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull NewsResponse newsResponse) {
                        liveData.setValue(newsResponse.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;
    }

    public LiveData<List<NewsDbModel>> getHealthNews(CompositeDisposable disposable) {

        MutableLiveData<List<NewsDbModel>> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().getHealthNews(MASTER_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NewsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull NewsResponse newsResponse) {
                        liveData.setValue(newsResponse.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;
    }


    public LiveData<List<NewsDbModel>> getSportNews(CompositeDisposable disposable) {

        MutableLiveData<List<NewsDbModel>> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().getSportNews(MASTER_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NewsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull NewsResponse newsResponse) {
                        liveData.setValue(newsResponse.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;

    }


    public LiveData<List<NewsDbModel>> getBusinessNews(CompositeDisposable disposable) {
        MutableLiveData<List<NewsDbModel>> liveData = new MutableLiveData<>();

        RetrofitInstance.getInstance().getBusinessNews(MASTER_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NewsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull NewsResponse newsResponse) {
                        liveData.setValue(newsResponse.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        return liveData;
    }
}
