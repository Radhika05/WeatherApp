package com.radhika.weatherapp.Database;

import androidx.lifecycle.LiveData;
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
    void update(Cities cities);

    @Query("SELECT * from Cities WHERE city = :city")
    Cities getItemById(String city);

    @Delete
    void delete(Cities cities);

    @Query("SELECT * FROM Cities")
    public LiveData<List<Cities>> getCities();

    @Query("SELECT city FROM Cities WHERE city = :city LIMIT 1")
    String checkCityExist(String city);


}
