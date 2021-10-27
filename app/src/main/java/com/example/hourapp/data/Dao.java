package com.example.hourapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hourapp.model.Hour;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Hour hour);

    @Update
    void update(Hour hour);

    @Query("SELECT * FROM hour_table WHERE hour_table.id == :id")
    LiveData<Hour> get(int id);

    @Query("SELECT * FROM hour_table ORDER BY date_no ASC")
    LiveData<List<Hour>> getAllRecords();

    @Delete
    void delete(Hour hour);
}
