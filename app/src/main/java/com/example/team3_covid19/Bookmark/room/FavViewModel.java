package com.example.team3_covid19.Bookmark.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.team3_covid19.CovidMenu.room.Data;

import java.util.List;

public class FavViewModel extends AndroidViewModel {
    private FavRepository favRepository;
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
    public boolean isCountryExist(int id){
        return favRepository.isCountryExist(id);
    }
    public void insert(List<Data> data){
        favRepository.insert(data);
    }
    public void deleteAll(){
        favRepository.deleteAll();
    }
    public void delete(String country){
        favRepository.delete(country);
    }
}

