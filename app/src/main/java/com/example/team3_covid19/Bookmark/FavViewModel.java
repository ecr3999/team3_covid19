package com.example.team3_covid19.Bookmark;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.team3_covid19.room.CovidRepository;
import com.example.team3_covid19.room.Data;

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

    public void insert(List<Data> data){
        favRepository.insert(data);
    }
/*public void update(User user){
        userRepository.update(user);
    }*/

    public void delete(){
        favRepository.delete();
    }
}

