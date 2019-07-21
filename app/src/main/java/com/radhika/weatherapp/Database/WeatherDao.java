package com.radhika.weatherapp.Database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.radhika.weatherapp.Models.Cities;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(List<Cities> cities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cities cities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Cities maps);

    @Delete
    void delete(Cities maps);

    @Query("SELECT * FROM Cities")
    public LiveData<List<Cities>> getCities();

}
