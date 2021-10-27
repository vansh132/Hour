package com.example.hourapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.hourapp.model.Hour;
import com.example.hourapp.util.HourRoomDatabase;

import java.util.List;

public class Repo {
    private Dao dao;
    private LiveData<List<Hour>> allRecords;

    public Repo(Application application) {
        HourRoomDatabase db = HourRoomDatabase.getDatabse(application);
        dao =db.dao();
        allRecords = dao.getAllRecords();
    }

    public LiveData<List<Hour>> getAllRecords() {
        return allRecords;
    }

    public void insert(Hour hour) {
        HourRoomDatabase.databaseWriteExecutor.execute(() -> {
            dao.insert(hour);
        });
    }

    public void delete(Hour hour) {
        HourRoomDatabase.databaseWriteExecutor.execute(() -> {
            dao.delete(hour);
        });
    }

    public LiveData<Hour> get(int id) {
        return dao.get(id);
    }

    public void update(Hour hour) {
        HourRoomDatabase.databaseWriteExecutor.execute(() -> {
            dao.update(hour);
        });
    }

}
