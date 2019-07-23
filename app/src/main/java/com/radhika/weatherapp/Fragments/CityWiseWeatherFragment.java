package com.radhika.weatherapp.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.weatherapp.Adapters.CityWiseWeatherDetailsAdapter;
import com.radhika.weatherapp.Common.Utils;
import com.radhika.weatherapp.Models.Main;
import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.ViewModels.WeatherViewModel;
import com.radhika.weatherapp.databinding.FragmentCityWiseWeatherBinding;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;


public class CityWiseWeatherFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private RecyclerView rvCiyWiseData;
    private FragmentCityWiseWeatherBinding cityWiseWeatherBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cityWiseWeatherBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_city_wise_weather, container, false);
        View view = cityWiseWeatherBinding.getRoot();
        initViews(view);
        weatherViewModel.getCity().observe(Objects.requireNonNull(getActivity()), new Observer<String>() {
            @Override
            public void onChanged(String city) {
                bindRecyclerView(city);
            }
        });
        return view;
    }

    private void bindRecyclerView(String city) {
        weatherViewModel.getWeatherForeCastData(city).observe(this, new Observer<WeatherAPIForecastResult>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(WeatherAPIForecastResult weatherAPIForecastResult) {
                BindView(weatherAPIForecastResult);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void BindView(WeatherAPIForecastResult weatherAPIForecastResult) {
        List<com.radhika.weatherapp.Models.List> todaysList;
        List<com.radhika.weatherapp.Models.List> upComingList;
        List<com.radhika.weatherapp.Models.List> lists = weatherAPIForecastResult.getList();
        Main main = lists.get(0).getMain();
        cityWiseWeatherBinding.txtCity.setText(weatherAPIForecastResult.getCity().getName());
        cityWiseWeatherBinding.txtTemp.setText(String.valueOf(Math.round(main.getTemp())) + (char) 0x00B0);
        cityWiseWeatherBinding.txtMaxVal.setText(String.valueOf(Math.round(main.getTempMax())) + (char) 0x00B0);
        cityWiseWeatherBinding.txtMinVal.setText(String.valueOf(Math.round(main.getTempMin())) + (char) 0x00B0);
        cityWiseWeatherBinding.txtHumidityVal.setText(main.getHumidity() + " %");
        cityWiseWeatherBinding.txtWindVal.setText(lists.get(0).getWind().getSpeed() + " m/sec");
        try {
            cityWiseWeatherBinding.txtDate.setText(Utils.getDayFromDate(lists.get(0).getDtTxt()));
            cityWiseWeatherBinding.txtTime.setText(Utils.getTimeFromString(lists.get(0).getDtTxt()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lists.size(); i++) {

        }
        CityWiseWeatherDetailsAdapter cityWiseWeatherDetails = new CityWiseWeatherDetailsAdapter(lists, getActivity(), weatherAPIForecastResult);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvCiyWiseData.setLayoutManager(mLayoutManager);
        rvCiyWiseData.setItemAnimator(new DefaultItemAnimator());
        rvCiyWiseData.setAdapter(cityWiseWeatherDetails);

    }

    private void initViews(View view) {
        rvCiyWiseData = view.findViewById(R.id.rv_city_details);
        weatherViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity())).get(WeatherViewModel.class);
    }
}
