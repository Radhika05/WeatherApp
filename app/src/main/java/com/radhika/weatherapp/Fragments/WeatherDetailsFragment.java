package com.radhika.weatherapp.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.radhika.weatherapp.R;

public class WeatherDetailsFragment extends Fragment {

    private TextView cityName;

    public WeatherDetailsFragment() {
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);
        initViews(view);
        bindUiData();
        return view;
    }

    private void bindUiData() {
        String tag = getTag();
        if (!TextUtils.isEmpty(tag)) {
            cityName.setText(tag);
        }
    }

    private void initViews(View view) {
        cityName = view.findViewById(R.id.tv_city);
    }
}