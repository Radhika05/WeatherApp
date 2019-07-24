package com.radhika.weatherapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.radhika.weatherapp.Common.FragmentsManager;
import com.radhika.weatherapp.Fragments.MyCustomDialogFragment;
import com.radhika.weatherapp.Fragments.WeatherDetailsFragment;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean doubleBackToExitPressedOnce;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        WeatherDetailsFragment weatherDetailsFragment = new WeatherDetailsFragment();
        FragmentsManager.replaceFragment(this, weatherDetailsFragment, R.id.fragment_container, false);
        binding.imgAddCity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_add_city) {
            MyCustomDialogFragment myCustomDialogFragment = new MyCustomDialogFragment();
            myCustomDialogFragment.show(getSupportFragmentManager(), "myCustomDialogFragment");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        binding.imgAddCity.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                finish();
                return;
            }
            this.doubleBackToExitPressedOnce=true;
            Toast.makeText(this,R.string.back_press,Toast.LENGTH_SHORT).show();
    }
}
