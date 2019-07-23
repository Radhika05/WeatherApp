package com.radhika.weatherapp.Network;

import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.Models.WeatherAPIResultList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {
    @GET("weather")
    Call<WeatherAPIResult>getWeatherData(@Query("q") String cityname, @Query("APPID") String APIKey);

    @GET("forecast")
    Call<WeatherAPIForecastResult>getWeatherForecastData(@Query("q") String cityName, @Query("appid") String AppID,@Query("units") String units);

    @GET("group")
    Call<WeatherAPIResultList> updateWeatherInfo(@Query("id") String cityId, @Query("units") String units, @Query("appid") String appId);
}
