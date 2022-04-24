package com.example.team3_covid19.Bookmark.room;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.team3_covid19.CovidMenu.room.Data;
import com.example.team3_covid19.CovidMenu.room.DataDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FavRepository {
    private DataDao dataDao;
    private Data data = new Data();
    private LiveData<List<Data>> allDatas;
    private Boolean flag = false;
    private Boolean finish = false;

    public FavRepository(Application application) {
        FavDatabase database = FavDatabase.getDatabase(application);
        dataDao = database.dataDao();
        allDatas = dataDao.getAll();
    }

    LiveData<List<Data>> getAllDatas() {
        return allDatas;
    }

    void setFav(Data data) {
        FavDatabase.databaseWriteExecutor.execute(() -> {
            Data dataroom;
            dataroom = dataDao.getData(data.getCountry());
            if (dataroom != null) {
                if (dataroom.country.equalsIgnoreCase(data.getCountry())) {
                    dataDao.delete(data.country);
                    flag = true;
                }
                else{
                    dataDao.insert(data);
                }
            }
        });
    }

    Boolean isCountryExist(int id) {
        Future future = FavDatabase.databaseWriteExecutor.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return dataDao.isCountryExist(id);
            }
        });
        try {
            return (Boolean) future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    return false;
    }
//            @Override
//            public Boolean call() throws Exception {
//                        Boolean flag = dataDao.isCountryExist(id);
//                if (flag)
//                    Log.e("TAG","true");
//                else
//                    Log.e("TAG","false");
//
//                return dataDao.isCountryExist(id);
//            }
//        });
//        if (future.get();)
//            Log.e("TAG2","true");
//        else
//            Log.e("TAG2","false");
//        return future.isDone();

//        FavDatabase.databaseWriteExecutor.execute(new Callable<Boolean flag>());
//        FavDatabase.databaseWriteExecutor.execute(() -> {
//
//            flag = dataDao.isCountryExist(country);
//        });
//        return flag;


    void insert(List<Data> data) {
        FavDatabase.databaseWriteExecutor.execute(() -> {
            dataDao.insertAll(data);
        });
    }

    void delete(String country) {
        FavDatabase.databaseWriteExecutor.execute(() -> {
            dataDao.delete(country);
        });
    }

    void deleteAll() {
        FavDatabase.databaseWriteExecutor.execute(() -> {
            dataDao.deleteAll();
        });
    }
}
