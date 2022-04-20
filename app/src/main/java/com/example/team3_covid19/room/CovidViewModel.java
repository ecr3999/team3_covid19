package com.example.team3_covid19.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CovidViewModel extends AndroidViewModel {

    private CovidRepository covidRepository;
    private final LiveData<List<Data>> allDatas;
    //private final List<Data> allDatas;

    public CovidViewModel(@NonNull Application application) {
        super(application);
        covidRepository = new CovidRepository(application);
        allDatas = covidRepository.getAllDatas();
    }

    public LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }
    /*public List<Data> getAllDatas(){
        return allDatas;
    }*/
    public void insert(Data data){
        covidRepository.insert(data);
    }
    /*public void update(User user){
        userRepository.update(user);
    }*/
    public void delete(){
        covidRepository.delete();
    }

    public void countData(){
        covidRepository.countData();
    }
}
