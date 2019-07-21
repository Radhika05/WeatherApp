package com.radhika.weatherapp.ViewModels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.Network.WeatherAPIRepositary;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    private WeatherAPIRepositary weatherAPIRepositary = new WeatherAPIRepositary();

    public MutableLiveData<WeatherAPIResult> getWeatherInfo(String cityName){
        return weatherAPIRepositary.getCityWiseWeatherReport(cityName);
    }

    public LiveData<List<Cities>> getAllCities(Application activity){
        weatherAPIRepositary = new WeatherAPIRepositary(activity);
        return weatherAPIRepositary.getAllCities();
    }

    public void insertCities(Cities cities){
        weatherAPIRepositary.insert(cities);
    }
}
