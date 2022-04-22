package com.example.team3_covid19.Bookmark.room;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.team3_covid19.CovidMenu.room.Data;
import com.example.team3_covid19.CovidMenu.room.DataDao;

import java.util.List;

public class FavRepository {

    private DataDao dataDao;
    private Data data = new Data();
    private LiveData<List<Data>> allDatas;
    private Boolean flag = false;
    //private List<Data> allDatas;

    public FavRepository(Application application) {
        FavDatabase database = FavDatabase.getDatabase(application);
        dataDao = database.dataDao();
        allDatas = dataDao.getAll();
    }

    LiveData<List<Data>> getAllDatas() {
        return allDatas;
    }

    /*List<Data> getAllDatas(){
        return allDatas;
    }*/

    /*void insert(Data data){
        CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.insert(data);
            }
        });
    }*/

    void setFav(Data data) {
        FavDatabase.databaseWriteExecutor.execute(() -> {
            Data dataroom;
            dataroom = dataDao.getData(data.getCountry());
            if (dataroom != null) {
                if (dataroom.country.equalsIgnoreCase(data.getCountry())) {
                    Log.e("TAG", "exist in favorite");
                    dataDao.delete(data.country);
                    flag = true;
                }
                else{
                    dataDao.insert(data);
                }
            }
        });
    }

    boolean isCountryExist(String country) {

        FavDatabase.databaseWriteExecutor.execute(() -> {

            flag = dataDao.isCountryExist(country);
            Log.e("TAG", "exist in favorite");
        });

        if(flag == true)
            Log.e("TAG2", "exist in favorite");
        else
            Log.e("TAG2", "not exist in favorite");

        return flag;
    }



    void insert(List<Data> data) {
        FavDatabase.databaseWriteExecutor.execute(() -> {
            dataDao.insertAll(data);
        });
    }

    /*void update(Data data){
        CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.update(data);
            }
        });
    }*/


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
