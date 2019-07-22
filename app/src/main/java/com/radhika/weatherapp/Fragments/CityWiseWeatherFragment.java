package com.radhika.weatherapp.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radhika.weatherapp.Adapters.CityWiseWeatherDetailsAdapter;
import com.radhika.weatherapp.Common.Utils;
import com.radhika.weatherapp.Models.Main;
import com.radhika.weatherapp.Models.Weather;
import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.ViewModels.WeatherViewModel;
import com.radhika.weatherapp.databinding.FragmentCityWiseWeatherBinding;

import java.io.UTFDataFormatException;
import java.util.List;
import java.util.Objects;

import okhttp3.internal.Util;


public class CityWiseWeatherFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private RecyclerView rvCiyWiseData;
    private FragmentCityWiseWeatherBinding cityWiseWeatherBinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cityWiseWeatherBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_city_wise_weather, container, false);
        View view =  cityWiseWeatherBinding.getRoot();
        initViews(view);
        weatherViewModel.getCity().observe(Objects.requireNonNull(getActivity()), new Observer<String>() {
            @Override
            public void onChanged(String city) {
                Log.d("onChannges",city);
                bindRecyclerView(city);
            }
        });
        return view;
    }

    private void bindRecyclerView(String city){
        weatherViewModel.getWeatherForeCastData(city).observe(this, new Observer<WeatherAPIForecastResult>() {
            @Override
            public void onChanged(WeatherAPIForecastResult weatherAPIForecastResult) {
                List<com.radhika.weatherapp.Models.List> lists = weatherAPIForecastResult.getList();
                Main main = lists.get(0).getMain();
                cityWiseWeatherBinding.txtTemp.setText(Utils.convertKelvinToCel(main.getTemp()));
                //cityWiseWeatherBinding.txtMaxVal.setText(Utils.convertKelvinToCel(main.getTempMax()));
              //  cityWiseWeatherBinding.txtMinVal.setText(Utils.convertKelvinToCel(main.getTempMin()));
                CityWiseWeatherDetailsAdapter cityWiseWeatherDetails = new CityWiseWeatherDetailsAdapter(lists,getActivity(),weatherAPIForecastResult);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                rvCiyWiseData.setLayoutManager(mLayoutManager);
                rvCiyWiseData.setItemAnimator(new DefaultItemAnimator());
                rvCiyWiseData.setAdapter(cityWiseWeatherDetails);
            }
        });

    }

    private void initViews(View view) {
        rvCiyWiseData = view.findViewById(R.id.rv_city_details);
        weatherViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity())).get(WeatherViewModel.class);
    }

}
