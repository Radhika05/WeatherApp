package com.radhika.weatherapp.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.radhika.weatherapp.Common.FragmentsManager;
import com.radhika.weatherapp.Fragments.MyCustomDialogFragment;
import com.radhika.weatherapp.Fragments.WeatherDetailsFragment;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        WeatherDetailsFragment weatherDetailsFragment =  new WeatherDetailsFragment();
        FragmentsManager.replaceFragment(this,weatherDetailsFragment,R.id.fragment_container,false);
        binding.fbAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fb_add) {
            MyCustomDialogFragment myCustomDialogFragment = new MyCustomDialogFragment();
            myCustomDialogFragment.show(getSupportFragmentManager(), "example");
        }
    }
}
