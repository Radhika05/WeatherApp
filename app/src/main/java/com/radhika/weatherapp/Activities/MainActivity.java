package com.radhika.weatherapp.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.weatherapp.Adapters.CitiesAdapter;
import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCities;
    private List<Cities> lstCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        bindRecyclerView();
    }

    private void bindRecyclerView() {
        lstCities = new ArrayList<>();
        lstCities.add(new Cities("Nashik"));
        lstCities.add(new Cities("Pune"));
        lstCities.add(new Cities("Mumbai"));
        CitiesAdapter homeAdapter = new CitiesAdapter(lstCities);
        //@SuppressLint("WrongConstant")
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCities.setLayoutManager(mLayoutManager);
        rvCities.setItemAnimator(new DefaultItemAnimator());
        rvCities.setAdapter(homeAdapter);
    }

    private void initViews() {
        rvCities = findViewById(R.id.rv_cities);
    }
}
