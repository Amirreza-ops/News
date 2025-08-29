package com.example.news2.data.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.news2.data.model.NewsDbModel;

@Database(entities = {NewsDbModel.class}, version = 1)
public abstract class NewsDB extends RoomDatabase {


    public abstract DAO getDAO();

    private static NewsDB instance;


    public static synchronized NewsDB getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NewsDB.class, "News DataBase")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }





}
