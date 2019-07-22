package com.radhika.weatherapp.Network;

import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.Models.WeatherAPIResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {

    @GET("forecast")
    Call<WeatherAPIResult> getWeather(@Query("q") String cityName);


    @GET("weather")
    Call<WeatherAPIResult>getWeatherData(@Query("q") String cityname, @Query("APPID") String APIKey);

    Call<WeatherAPIForecastResult>getWeatherForecastData(@Query("q") String cityName, @Query("appid") String AppID, @Query("count") Integer count);
}
