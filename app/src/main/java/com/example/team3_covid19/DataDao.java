package com.example.team3_covid19;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM data")
    List<Data> getAll();

    @Query("SELECT * FROM data WHERE id IN (:ids)")
    List<Data> loadAllByIds(int[] ids);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Data...datas);

    @Query("DELETE FROM data")
    void deleteAll();
}
