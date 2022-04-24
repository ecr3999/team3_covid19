package com.example.team3_covid19.CovidMenu.room;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class CovidViewModel extends AndroidViewModel {
    private CovidRepository covidRepository;
    private final LiveData<List<Data>> allDatas;

    public CovidViewModel(Application application) {
        super(application);
        covidRepository = new CovidRepository(application);
        allDatas = covidRepository.getAllDatas();
    }
    public LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }
    public void insert(List<Data> data){
        covidRepository.insert(data);
    }

    public void deleteAll(){
        covidRepository.delete();
    }
}
