package com.radhika.weatherapp.ViewModels;

import android.app.Application;

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

    /**
     * fetch the api data from repositary
     * pass unique appid for as parameter
     *
     * @param cityName
     * @return
     */
    public MutableLiveData<WeatherAPIResult> getWeatherInfo(String cityName) {
        return weatherAPIRepositary.getWeatherData(cityName, "9ecde7b8e13078924e82d5bc2a8f48ec");
    }

    /**
     * fetch the api data from repositary
     *
     * @param cityName
     * @return
     */
    public MutableLiveData<WeatherAPIForecastResult> getWeatherForeCastData(String cityName) {
        return weatherAPIRepositary.getWeatherForeCastData(cityName, "9ecde7b8e13078924e82d5bc2a8f48ec");
    }

    /**
     * fetch the cities from repositary
     *
     * @param activity
     * @return
     */
    public LiveData<List<Cities>> getAllCities(Application activity) {
        weatherAPIRepositary = new WeatherAPIRepositary(activity);
        LiveData<List<Cities>> listCities = weatherAPIRepositary.getAllCities();
        return listCities;
    }

    /**
     * perform database operation from repositary
     *
     * @param cities
     */
    public void insertOrUpdate(Cities cities) {
        weatherAPIRepositary.insertOrUpdate(cities);
    }

    /**
     * return the updates string value
     *
     * @return
     */
    public MutableLiveData<String> getCity() {
        return cities;
    }

    /**
     * set the mutable type value
     *
     * @param city
     */
    public void setCity(String city) {
        cities.setValue(city);
    }

    /**
     * fetch the api data from repositary
     *
     * @param cityId
     * @return
     */
    public MutableLiveData<WeatherAPIResultList> updateWeatherInfo(String cityId) {
        return weatherAPIRepositary.updateWeatherInfo(cityId, "9ecde7b8e13078924e82d5bc2a8f48ec");
    }

    /**
     * get the list of updated cities
     *
     * @param cities
     */
    public void updateCities(Cities cities) {
        weatherAPIRepositary.update(cities);
    }

    /**
     * delete the city from database
     *
     * @param cities
     */
    public void delete(Cities cities) {
        weatherAPIRepositary.delete(cities);
    }

    /**
     * check if that city is exist in database or not
     *
     * @param city
     * @return
     */
    public String checkCityExist(String city) {
        return weatherAPIRepositary.checkCityExist(city);
    }

}
