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

/**
 * WeatherDao : accessing database operation
 */
@Dao
public interface WeatherDao {

    /**
     * insert the city into database
     *
     * @param cities
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cities cities);

    /**
     * update the cities
     *
     * @param cities
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Cities cities);

    /**
     * check if perticular city already exist in db
     *
     * @param city
     * @return
     */
    @Query("SELECT * from Cities WHERE city = :city")
    Cities getItemById(String city);

    /**
     * Delete the city from database
     *
     * @param cities
     */
    @Delete
    void delete(Cities cities);

    /**
     * get list of cities
     *
     * @return
     */
    @Query("SELECT * FROM Cities")
    public LiveData<List<Cities>> getCities();

    /**
     * check if city exist in database
     *
     * @param city
     * @return
     */
    @Query("SELECT city FROM Cities WHERE city = :city LIMIT 1")
    String checkCityExist(String city);


}
