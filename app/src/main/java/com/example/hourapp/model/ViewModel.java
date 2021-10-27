package com.example.hourapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hourapp.data.Repo;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private static Repo repo;
    public final LiveData<List<Hour>> allRecords;

    public ViewModel(@NonNull Application application) {
        super(application);

        repo = new Repo(application);
        allRecords = repo.getAllRecords();
    }

    public LiveData<List<Hour>> getAllRecords() {
        return repo.getAllRecords();
    }

    public static void insert(Hour hour) {
        repo.insert(hour);
    }

    public LiveData<Hour> get(int id) {
        return repo.get(id);
    }

    public static void update(Hour hour) {
        repo.update(hour);
    }

    public static void delete(Hour hour) {
        repo.delete(hour);
    }

}
