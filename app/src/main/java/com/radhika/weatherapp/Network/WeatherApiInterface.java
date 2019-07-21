package com.radhika.weatherapp.Network;

import com.radhika.weatherapp.Models.WeatherAPIResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {

    @GET("forecast")
    Call<WeatherAPIResult> getWeather(@Query("q") String cityName);
}
