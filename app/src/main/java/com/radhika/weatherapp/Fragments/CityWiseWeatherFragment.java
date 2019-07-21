package com.radhika.weatherapp.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.ViewModels.WeatherViewModel;

import java.util.List;
import java.util.Objects;


public class CityWiseWeatherFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private RecyclerView rvCiyWiseData;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_city_wise_weather, container, false);
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
        weatherViewModel.getWeatherInfo(city).observe(this, new Observer<WeatherAPIResult>() {
            @Override
            public void onChanged(WeatherAPIResult weatherAPIResult) {
                List<com.radhika.weatherapp.Models.List> lists = weatherAPIResult.getList();
                CityWiseWeatherDetailsAdapter cityWiseWeatherDetails = new CityWiseWeatherDetailsAdapter(lists,getActivity());
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
