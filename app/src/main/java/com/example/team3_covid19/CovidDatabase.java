package com.example.team3_covid19;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Data.class},version = 1)
public abstract class CovidDatabase extends RoomDatabase {
    public abstract DataDao dataDao();
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(1);
}
