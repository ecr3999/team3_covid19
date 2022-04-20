package com.example.team3_covid19.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.team3_covid19.room.CovidDatabase;
import com.example.team3_covid19.room.Data;
import com.example.team3_covid19.room.DataDao;

import java.util.List;

public class CovidRepository {

    private DataDao dataDao;
    private LiveData<List<Data>> allDatas;
    //private List<Data> allDatas;

    public CovidRepository(Application application){
        CovidDatabase database = CovidDatabase.getDatabase(application);
        dataDao = database.dataDao();
        allDatas = dataDao.getAll();
    }

    LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }

    /*List<Data> getAllDatas(){
        return allDatas;
    }*/

    void insert(Data data){
        CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.insert(data);
            }
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

    void delete(){
        CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteAll();
            }
        });
    }

    void countData(){
        CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.countAllData();
            }
        });
    }
}
