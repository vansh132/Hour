package com.example.hourapp.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hourapp.data.Dao;
import com.example.hourapp.model.Hour;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Hour.class}, version = 1, exportSchema = false)
public abstract class HourRoomDatabase extends RoomDatabase {

    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile HourRoomDatabase INSTANCE;

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(() -> {
                        Dao dao = INSTANCE.dao();

                        Hour hour = new Hour("23/10/21", 6, "Hello World!!");
                        dao.insert(hour);


                    });
                }
            };

    public static HourRoomDatabase getDatabse(final Context context) {
        if (INSTANCE == null) {
            synchronized (HourRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HourRoomDatabase.class, "hour_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract Dao dao();
}
