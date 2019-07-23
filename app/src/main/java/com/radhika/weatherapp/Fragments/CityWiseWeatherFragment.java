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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.radhika.weatherapp.Common.Utils.getDateFromDateTimeString;

public class CityWiseWeatherFragment extends Fragment {

    private WeatherViewModel weatherViewModel;
    private RecyclerView rvCiyWiseData, rvCiyWiseDataAll;
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
        List<com.radhika.weatherapp.Models.List> todaysList = new ArrayList<>();
        List<com.radhika.weatherapp.Models.List> upComingList = new ArrayList<>();
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
            for (int i = 0; i < lists.size(); i++) {
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date();
                Date todayWithZeroTime = formatter.parse(formatter.format(today));
                if (todayWithZeroTime.equals(getDateFromDateTimeString(lists.get(i).getDtTxt()))) {
                    todaysList.add(lists.get(i));
                } else if (todayWithZeroTime.compareTo(getDateFromDateTimeString(lists.get(i).getDtTxt())) < 0) {
                    upComingList.add(lists.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CityWiseWeatherDetailsAdapter cityWiseWeatherDetails = new CityWiseWeatherDetailsAdapter(todaysList, getActivity(), weatherAPIForecastResult, "current");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvCiyWiseData.setLayoutManager(mLayoutManager);
        rvCiyWiseData.setItemAnimator(new DefaultItemAnimator());
        rvCiyWiseData.setAdapter(cityWiseWeatherDetails);

        CityWiseWeatherDetailsAdapter cityWiseWeatherDetailsAll = new CityWiseWeatherDetailsAdapter(Utils.uniqueRecords(upComingList), getActivity(), weatherAPIForecastResult, "all");
        RecyclerView.LayoutManager mLayoutManagerAll = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvCiyWiseDataAll.setLayoutManager(mLayoutManagerAll);
        rvCiyWiseDataAll.setItemAnimator(new DefaultItemAnimator());
        rvCiyWiseDataAll.setAdapter(cityWiseWeatherDetailsAll);
    }

    private void initViews(View view) {
        rvCiyWiseData = view.findViewById(R.id.rv_city_details);
        rvCiyWiseDataAll = view.findViewById(R.id.rv_city_details_all);
        weatherViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity())).get(WeatherViewModel.class);
    }
}
