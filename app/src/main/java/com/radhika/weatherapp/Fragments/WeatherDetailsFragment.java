package com.radhika.weatherapp.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.weatherapp.Adapters.CitiesAdapter;
import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.ViewModels.WeatherViewModel;

import java.util.List;

public class WeatherDetailsFragment extends Fragment {

    private TextView cityName;
    private RecyclerView rvCities;
    private WeatherViewModel weatherViewModel;
    public WeatherDetailsFragment() {
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);
        initViews(view);
        weatherViewModel.getAllCities().observe(this, new Observer<List<Cities>>() {
                    @Override
                    public void onChanged(List<Cities> cities) {
                        if(cities==null){
                            Toast.makeText(getActivity(),"No City Added",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            bindRecyclerView(cities);
                        }
                    }
                });
                bindUiData();
        return view;
    }

    private void bindRecyclerView(List<Cities> cities) {
        CitiesAdapter homeAdapter = new CitiesAdapter(cities);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvCities.setLayoutManager(mLayoutManager);
        rvCities.setItemAnimator(new DefaultItemAnimator());
        rvCities.setAdapter(homeAdapter);
    }

    private void bindUiData() {
        String tag = getTag();
        if (!TextUtils.isEmpty(tag)) {
            cityName.setText(tag);
        }
    }

    private void initViews(View view) {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        cityName = view.findViewById(R.id.tv_city);
        rvCities = view.findViewById(R.id.rv_city);
    }
}