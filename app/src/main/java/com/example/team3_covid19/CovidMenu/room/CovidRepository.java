package com.example.team3_covid19.CovidMenu.room;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class CovidRepository {
    private DataDao dataDao;
    private LiveData<List<Data>> allDatas;

    public CovidRepository(Application application){
        CovidDatabase database = CovidDatabase.getDatabase(application);
        dataDao = database.dataDao();
        allDatas = dataDao.getAll();
    }

    LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }

    void insert(List<Data> data) {
        CovidDatabase.databaseWriteExecutor.execute(() -> {
            dataDao.insertAll(data);
        });
    }

    void delete() {
        CovidDatabase.databaseWriteExecutor.execute(() -> {
            dataDao.deleteAll();
        });
    }
}
