package com.example.team3_covid19.Bookmark;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.team3_covid19.room.CovidRepository;
import com.example.team3_covid19.room.Data;

import java.util.List;

public class FavViewModel extends AndroidViewModel {

    private FavRepository favRepository;
    private Boolean isExist;
    private final LiveData<List<Data>> allDatas;

    public FavViewModel(Application application) {
        super(application);
        favRepository = new FavRepository(application);
        allDatas = favRepository.getAllDatas();
    }

    public LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }

    public void setFav(Data data){
        favRepository.setFav(data);
    }

    public boolean isCountryExist(String country){
        return favRepository.isCountryExist(country);
    }

    public void insert(List<Data> data){
        favRepository.insert(data);
    }
/*public void update(User user){
        userRepository.update(user);
    }*/

    public void deleteAll(){
        favRepository.deleteAll();
    }
    public void delete(String country){
        favRepository.delete(country);
    }
}

