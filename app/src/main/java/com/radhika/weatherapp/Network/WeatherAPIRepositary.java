package com.radhika.weatherapp.Network;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhika.weatherapp.Database.WeatherDao;
import com.radhika.weatherapp.Database.WeatherDatabase;
import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.Models.WeatherAPIResult;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherAPIRepositary {

    private  WeatherAPIRepositary instance;
    private static Retrofit retrofit;
    private static WeatherDao weatherDao;
    WeatherDatabase mapsDatabase;
    private  LiveData<List<Cities>> allCities;
    private MutableLiveData<WeatherAPIResult> weatherAPIResultMutableLiveData = new MutableLiveData<>();

    public WeatherAPIRepositary(Application activity){
            mapsDatabase = WeatherDatabase.getInstance(activity);
            weatherDao = mapsDatabase.weatherDao();

    }

    public WeatherAPIRepositary(){

    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            /*OkHttpClient okClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    Request original = chain.request();

                                    // Request customization: add request headers
                                    Request.Builder requestBuilder = original.newBuilder()
                                            .header("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                                            .addHeader("X-RapidAPI-Key", "ceba0b15bdmsh8399215b9e295b1p15714fjsn57fde7755e5c")
                                            .method(original.method(), original.body());

                                    Request request = requestBuilder.build();
                                    return chain.proceed(request);
                                }
                            })
                    .build();*/
            //String baseUrl = "https://community-open-weather-map.p.rapidapi.com";
            String baseUrl = "http://api.openweathermap.org/data/2.5/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static WeatherApiInterface getAPIService() {
        return getInstance().create(WeatherApiInterface.class);
    }

    public MutableLiveData<WeatherAPIResult> getWeatherData(String cityName,String AppId) {
        final Call<WeatherAPIResult> weatherAPIResultCall = getAPIService().getWeatherData(cityName,AppId);
        weatherAPIResultCall.enqueue(new Callback<WeatherAPIResult>() {
            @Override
            public void onResponse(Call<WeatherAPIResult> call, retrofit2.Response<WeatherAPIResult> response) {
                weatherAPIResultMutableLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<WeatherAPIResult> call, Throwable t) {
                Log.i("failure",t.toString());
            }
        });
        return getResponse();
    }

    private MutableLiveData<WeatherAPIResult> getResponse() {
        return weatherAPIResultMutableLiveData;
    }

    public void insert(Cities cities){
        new InsertCityAsync(weatherDao).execute(cities);
    }

    public void update(Cities cities){
        new UpdateCityAsync(weatherDao).execute(cities);
    }

    public void delete(Cities cities){
        new DeleteCityAsync(weatherDao).execute(cities);
    }

    public LiveData<List<Cities>> getAllCities(){
        allCities = weatherDao.getCities();
        return allCities;
    }

    public static class InsertCityAsync extends AsyncTask<Cities, Void, Void> {
       WeatherDao weatherDao;
        private InsertCityAsync(WeatherDao weatherDao){
            this.weatherDao = weatherDao;
        }
        @Override
        protected Void doInBackground(Cities... cities) {
            weatherDao.insert(cities[0]);
            return null;
        }
    }

    public static class UpdateCityAsync extends AsyncTask<Cities, Void, Void>{
        WeatherDao weatherDao;
        private UpdateCityAsync(WeatherDao weatherDao){
            this.weatherDao = weatherDao;
        }
        @Override
        protected Void doInBackground(Cities... cities) {
            weatherDao.update(cities[0]);
            return null;
        }
    }

    public static class DeleteCityAsync extends AsyncTask<Cities, Void, Void>{
        WeatherDao weatherDao;
        private DeleteCityAsync(WeatherDao weatherDao){
            this.weatherDao = weatherDao;
        }
        @Override
        protected Void doInBackground(Cities... cities) {
            weatherDao.delete(cities[0]);
            return null;
        }
    }
}
