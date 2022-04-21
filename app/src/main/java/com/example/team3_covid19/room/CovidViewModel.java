package com.example.team3_covid19.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CovidViewModel extends AndroidViewModel {

    private CovidRepository covidRepository;
    private final LiveData<List<Data>> allDatas;
    //private final MutableLiveData<Data> _str = new MutableLiveData<>();
    //private final List<Data> allDatas;

    public CovidViewModel(Application application) {
        super(application);
        covidRepository = new CovidRepository(application);
        allDatas = covidRepository.getAllDatas();
    }

/*public MutableLiveData<Data> getStr() {
        return allDatas;
    }*/



    public LiveData<List<Data>> getAllDatas(){
        return allDatas;
    }

    /*public List<Data> getAllDatas(){
        return allDatas;
    }*/
    public void insert(List<Data> data){
        covidRepository.insert(data);
    }
/*public void update(User user){
        userRepository.update(user);
    }*/

}
