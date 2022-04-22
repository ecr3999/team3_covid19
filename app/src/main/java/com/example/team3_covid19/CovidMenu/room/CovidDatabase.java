package com.example.team3_covid19.CovidMenu.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Data.class},version = 1, exportSchema = false)
public abstract class CovidDatabase extends RoomDatabase {
    public abstract DataDao dataDao();
    private static volatile CovidDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 2;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CovidDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CovidDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CovidDatabase.class, "Covid19")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
