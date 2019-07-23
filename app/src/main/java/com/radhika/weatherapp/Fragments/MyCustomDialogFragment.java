package com.radhika.weatherapp.Fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.ViewModels.WeatherViewModel;

import java.util.Objects;

public class MyCustomDialogFragment extends DialogFragment implements View.OnClickListener {

    private WeatherViewModel weatherViewModel;
    private EditText etAdd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_custom_dialog, container, false);
        Button btnAdd = view.findViewById(R.id.bt_add);
        etAdd = view.findViewById(R.id.et_city);
        weatherViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(WeatherViewModel.class);
        btnAdd.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_add) {
            if (!TextUtils.isEmpty(etAdd.getText())) {
                weatherViewModel.getWeatherInfo(etAdd.getText().toString().trim()).observe(this, new Observer<WeatherAPIResult>() {
                    @Override
                    public void onChanged(WeatherAPIResult weatherAPIResult) {
                        assert weatherAPIResult != null;
                        Log.i("weatherAPIResult", weatherAPIResult.toString());
                        Cities cities = new Cities();
                        cities.setCityId(weatherAPIResult.getId());
                        cities.setTemperature(weatherAPIResult.getMain().getTemp());
                        cities.setDescription(weatherAPIResult.getWeather().get(0).getDescription());
                        cities.setIcon(weatherAPIResult.getWeather().get(0).getIcon());
                        cities.setName(weatherAPIResult.getName());
                        weatherViewModel.insertCities(cities);
                        dismiss();
                    }
                });
            } else {
                Toast.makeText(getContext(), "Please enter city name", Toast.LENGTH_LONG).show();
            }
        }
    }
}
