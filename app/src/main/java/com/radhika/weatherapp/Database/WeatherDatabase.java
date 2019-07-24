package com.radhika.weatherapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.radhika.weatherapp.Models.Cities;

/**
 * Database Name : weather_database
 * Version : 1
 */
@Database(entities = Cities.class, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();

    private static WeatherDatabase instance;

    /**
     * initilized the database
     * @param context
     * @return
     */
    public static synchronized WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WeatherDatabase.class, "weather_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
