package com.radhika.weatherapp.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.radhika.weatherapp.Common.FragmentsManager;
import com.radhika.weatherapp.Fragments.WeatherDetailsFragment;
import com.radhika.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherDetailsFragment weatherDetailsFragment =  new WeatherDetailsFragment();
        FragmentsManager.replaceFragment(this,weatherDetailsFragment,R.id.fragment_container,false);
    }
}
