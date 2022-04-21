package com.example.team3_covid19.Bookmark;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.team3_covid19.Bookmark.FavDatabase;
import com.example.team3_covid19.room.Data;
import com.example.team3_covid19.room.DataDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Data.class},version = 1, exportSchema = false)
public abstract class FavDatabase extends RoomDatabase {
    public abstract DataDao dataDao();
    private static volatile FavDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 2;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FavDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavDatabase.class, "Favorites")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
