package com.radhika.weatherapp.Network;

import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.Models.WeatherAPIResultList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * manages the network call
 */
public interface WeatherApiInterface {

    /**
     * get the current weather details
     *
     * @param cityname
     * @param APIKey
     * @return
     */
    @GET("weather")
    Call<WeatherAPIResult> getWeatherData(@Query("q") String cityname, @Query("appid") String APIKey);

    /**
     * get the forecast details of current and upcoming date
     *
     * @param cityName
     * @param AppID
     * @param units
     * @return
     */
    @GET("forecast")
    Call<WeatherAPIForecastResult> getWeatherForecastData(@Query("q") String cityName, @Query("appid") String AppID, @Query("units") String units);

    /**
     * pull the data of specific cities
     *
     * @param cityId
     * @param units
     * @param appId
     * @return
     */
    @GET("group")
    Call<WeatherAPIResultList> updateWeatherInfo(@Query("id") String cityId, @Query("units") String units, @Query("appid") String appId);
}
