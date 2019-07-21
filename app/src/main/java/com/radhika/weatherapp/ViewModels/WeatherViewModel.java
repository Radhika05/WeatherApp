package com.radhika.weatherapp.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.Network.WeatherAPIRepositary;

import java.util.List;
import java.util.Objects;

public class WeatherViewModel extends ViewModel {
    private WeatherAPIRepositary weatherAPIRepositary=new WeatherAPIRepositary();

    public MutableLiveData<WeatherAPIResult> getWeatherInfo(){
        MutableLiveData<WeatherAPIResult> listWeatherData = weatherAPIRepositary.getCityWiseWeatherReport("Nashik");
        if(listWeatherData !=null){
            Log.i("listWeatherData", listWeatherData.toString());
        }
        return listWeatherData;
    }

    public MutableLiveData<List<Cities>> getAllCities(){
        MutableLiveData<List<Cities>> listMutableCitiesData = weatherAPIRepositary.getAllCities();
        return listMutableCitiesData;

    }
}
