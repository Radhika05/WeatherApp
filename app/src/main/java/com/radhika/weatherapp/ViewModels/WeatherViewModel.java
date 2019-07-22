package com.radhika.weatherapp.ViewModels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.Network.WeatherAPIRepositary;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    private final MutableLiveData<String> cities = new MutableLiveData<String>();
    private WeatherAPIRepositary weatherAPIRepositary = new WeatherAPIRepositary();

    public MutableLiveData<WeatherAPIResult> getWeatherInfo(String cityName){
        return weatherAPIRepositary.getWeatherData(cityName,"9ecde7b8e13078924e82d5bc2a8f48ec");
    }

    public MutableLiveData<WeatherAPIForecastResult> getWeatherForeCastData(String cityName){
        return weatherAPIRepositary.getWeatherForeCastData(cityName,"9ecde7b8e13078924e82d5bc2a8f48ec");
    }

    public LiveData<List<Cities>> getAllCities(Application activity){
        weatherAPIRepositary = new WeatherAPIRepositary(activity);
        return weatherAPIRepositary.getAllCities();
    }

    public void insertCities(Cities cities){
        weatherAPIRepositary.insert(cities);
    }

    public boolean setCity(String city) {
        cities.setValue(city);
        return true;
    }

    public MutableLiveData<String> getCity() {
        return cities;
    }
}
