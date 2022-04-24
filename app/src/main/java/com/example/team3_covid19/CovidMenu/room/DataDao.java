package com.example.team3_covid19.CovidMenu.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM data")
    LiveData<List<Data>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Data> datas);

    @Query("DELETE FROM data")
    void deleteAll();

    @Query("SELECT * FROM data where country = :country")
    Data getData(String country);

    @Query("SELECT EXISTS(SELECT * FROM data WHERE country = :country)")
    boolean isCountryExist(String country);

    @Query("DELETE FROM data where country = :country")
    void delete(String country);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Data data);
}
