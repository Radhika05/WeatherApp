package com.radhika.weatherapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.radhika.weatherapp.Common.FragmentsManager;
import com.radhika.weatherapp.Fragments.MyCustomDialogFragment;
import com.radhika.weatherapp.Fragments.WeatherDetailsFragment;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        WeatherDetailsFragment weatherDetailsFragment = new WeatherDetailsFragment();
        FragmentsManager.replaceFragment(this, weatherDetailsFragment, R.id.fragment_container, true);
        binding.imgAddCity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_add_city) {
            MyCustomDialogFragment myCustomDialogFragment = new MyCustomDialogFragment();
            myCustomDialogFragment.show(getSupportFragmentManager(), "example");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        WeatherDetailsFragment weatherDetailsFragment = new WeatherDetailsFragment();
        FragmentsManager.replaceFragment(this, weatherDetailsFragment, R.id.fragment_container, true);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}
