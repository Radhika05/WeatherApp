package com.radhika.weatherapp.Network;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.radhika.weatherapp.Database.WeatherDao;
import com.radhika.weatherapp.Database.WeatherDatabase;
import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.Models.WeatherAPIResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherAPIRepositary {

    private static Retrofit retrofit;
    private static WeatherDao weatherDao;
    private WeatherAPIRepositary instance;
    private LiveData<List<Cities>> allCities;
    private MutableLiveData<WeatherAPIResult> weatherAPIResultMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<WeatherAPIForecastResult> weatherAPIForecastResultMutableLiveData = new MutableLiveData<>();

    public WeatherAPIRepositary(Application activity) {
        WeatherDatabase weatherDatabase = WeatherDatabase.getInstance(activity);
        weatherDao = weatherDatabase.weatherDao();
    }

    public WeatherAPIRepositary(){

    }

    private static Retrofit getInstance() {
        if (retrofit == null) {
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


    public MutableLiveData<WeatherAPIForecastResult> getWeatherForeCastData(String cityName, String AppId) {
        final Call<WeatherAPIForecastResult> weatherAPIResultCall = getAPIService().getWeatherForecastData(cityName, AppId);
        weatherAPIResultCall.enqueue(new Callback<WeatherAPIForecastResult>() {
            @Override
            public void onResponse(Call<WeatherAPIForecastResult> call, Response<WeatherAPIForecastResult> response) {
                weatherAPIForecastResultMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<WeatherAPIForecastResult> call, Throwable t) {
                Log.i("failure", t.toString());
            }
        });
        return getWeatherAPIForecastResultMutableLiveData();
    }

    public MutableLiveData<WeatherAPIResult> getWeatherData(String cityName, String AppId) {
        final Call<WeatherAPIResult> weatherAPIResultCall = getAPIService().getWeatherData(cityName, AppId);
        weatherAPIResultCall.enqueue(new Callback<WeatherAPIResult>() {
            @Override
            public void onResponse(Call<WeatherAPIResult> call, retrofit2.Response<WeatherAPIResult> response) {
                weatherAPIResultMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<WeatherAPIResult> call, Throwable t) {
                Log.i("failure", t.toString());
            }
        });
        return getResponse();
    }

    private MutableLiveData<WeatherAPIResult> getResponse() {
        return weatherAPIResultMutableLiveData;
    }

    private MutableLiveData<WeatherAPIForecastResult> getWeatherAPIForecastResultMutableLiveData() {
        return weatherAPIForecastResultMutableLiveData;
    }

    public void insert(Cities cities) {
        new InsertCityAsync(weatherDao).execute(cities);
    }

    public void update(Cities cities) {
        new UpdateCityAsync(weatherDao).execute(cities);
    }

    public void delete(Cities cities) {
        new DeleteCityAsync(weatherDao).execute(cities);
    }

    public LiveData<List<Cities>> getAllCities() {
        allCities = weatherDao.getCities();
        return allCities;
    }

    public static class InsertCityAsync extends AsyncTask<Cities, Void, Void> {
        WeatherDao weatherDao;

        private InsertCityAsync(WeatherDao weatherDao) {
            this.weatherDao = weatherDao;
        }

        @Override
        protected Void doInBackground(Cities... cities) {
            weatherDao.insert(cities[0]);
            return null;
        }
    }

    public static class UpdateCityAsync extends AsyncTask<Cities, Void, Void> {
        WeatherDao weatherDao;

        private UpdateCityAsync(WeatherDao weatherDao) {
            this.weatherDao = weatherDao;
        }

        @Override
        protected Void doInBackground(Cities... cities) {
            weatherDao.update(cities[0]);
            return null;
        }
    }

    public static class DeleteCityAsync extends AsyncTask<Cities, Void, Void> {
        WeatherDao weatherDao;

        private DeleteCityAsync(WeatherDao weatherDao) {
            this.weatherDao = weatherDao;
        }

        @Override
        protected Void doInBackground(Cities... cities) {
            weatherDao.delete(cities[0]);
            return null;
        }
    }
}
