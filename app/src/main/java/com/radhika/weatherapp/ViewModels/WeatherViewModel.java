package com.radhika.weatherapp.ViewModels;

import android.app.Application;
import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.Models.WeatherAPIResultList;
import com.radhika.weatherapp.Network.WeatherAPIRepositary;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    private final MutableLiveData<String> cities = new MutableLiveData<String>();
    private WeatherAPIRepositary weatherAPIRepositary = new WeatherAPIRepositary();
    private LiveData<List<Cities>> listCities;

    public MutableLiveData<WeatherAPIResult> getWeatherInfo(String cityName){
        return weatherAPIRepositary.getWeatherData(cityName,"9ecde7b8e13078924e82d5bc2a8f48ec");
    }

    public MutableLiveData<WeatherAPIForecastResult> getWeatherForeCastData(String cityName){
        return weatherAPIRepositary.getWeatherForeCastData(cityName,"9ecde7b8e13078924e82d5bc2a8f48ec");
    }

    public LiveData<List<Cities>> getAllCities(Application activity){
        weatherAPIRepositary = new WeatherAPIRepositary(activity);
        listCities = weatherAPIRepositary.getAllCities();
        return listCities;
    }

    public void insertCities(Cities cities){
        weatherAPIRepositary.insert(cities);
    }

    public void insertOrUpdate(Cities cities){
        weatherAPIRepositary.insertOrUpdate(cities);
    }

    public void setCity(String city) {
        cities.setValue(city);
    }

    public MutableLiveData<String> getCity() {
        return cities;
    }

    public MutableLiveData<WeatherAPIResultList> updateWeatherInfo(String cityId) {
        return weatherAPIRepositary.updateWeatherInfo(cityId,"9ecde7b8e13078924e82d5bc2a8f48ec");
    }

    public void updateCities(Cities cities) {
        weatherAPIRepositary.update(cities);
    }

    public void delete(Cities cities) {
        weatherAPIRepositary.delete(cities);
    }

    public String checkCityExist(String city) {
       return weatherAPIRepositary.checkCityExist(city);
    }

}
