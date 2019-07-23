package com.radhika.weatherapp.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.radhika.weatherapp.Adapters.CitiesAdapter;
import com.radhika.weatherapp.Common.FragmentsManager;
import com.radhika.weatherapp.Interface.RecyclerViewClickListener;
import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.Models.WeatherAPIResult;
import com.radhika.weatherapp.Models.WeatherAPIResultList;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.ViewModels.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WeatherDetailsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvCities;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WeatherViewModel weatherViewModel;
    private List<Cities> lstCities;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);
        initViews(view);
        weatherViewModel.getAllCities(Objects.requireNonNull(getActivity()).getApplication()).observe(this, new Observer<List<Cities>>() {
            @Override
            public void onChanged(List<Cities> cities) {
                lstCities = cities;
                if (cities == null || cities.size() == 0) {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "No City Added", Toast.LENGTH_SHORT).show();
                } else {
                    bindRecyclerView(cities);
                }
            }
        });
        return view;
    }

    private void bindRecyclerView(List<Cities> cities) {
        CitiesAdapter cityAdapter = new CitiesAdapter(cities, new RecyclerViewClickListener() {
            @Override
            public void onClick() {
                try {
                    CityWiseWeatherFragment cityWiseWeatherFragment = new CityWiseWeatherFragment();
                    FragmentsManager.addFragment(getActivity(), cityWiseWeatherFragment, R.id.fragment_container, true);
                } catch (Exception error) {
                    Log.d("error", error.toString());
                }
            }
        }, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvCities.setLayoutManager(mLayoutManager);
        rvCities.setItemAnimator(new DefaultItemAnimator());
        rvCities.setAdapter(cityAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                weatherViewModel.delete(cityAdapter.getCityAt(position));
                lstCities.remove(position);
                cityAdapter.notifyItemRemoved(position);
                cityAdapter.notifyItemChanged(position, lstCities.size());
                cityAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "City deleted successfully.", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(rvCities);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initViews(View view) {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        rvCities = view.findViewById(R.id.rv_city);
        swipeRefreshLayout = view.findViewById(R.id.swp_city_list);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.MAGENTA, Color.RED, Color.GREEN);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        if (lstCities != null && lstCities.size() > 0) {
            String cityId = "";
            for (int index = 0; index < (lstCities.size() > 20 ? 20 : lstCities.size()); index++) {
                cityId += lstCities.get(index).getCityId() + ",";
            }
            cityId = cityId.substring(0, cityId.length() - 1);
            weatherViewModel.updateWeatherInfo(cityId).observe(this, new Observer<WeatherAPIResultList>() {
                @Override
                public void onChanged(WeatherAPIResultList weatherAPIResultList) {
                    List<Cities> lstCity = new ArrayList<>();
                    for (WeatherAPIResult weatherAPIResult : weatherAPIResultList.getList()) {
                        Cities cities = new Cities();
                        for (Cities item : lstCities) {
                            if (item.getCityId() == weatherAPIResult.getId()) {
                                cities.setId(item.getId());
                                break;
                            }
                        }
                        cities.setCityId(weatherAPIResult.getId());
                        cities.setTemperature(weatherAPIResult.getMain().getTemp() + 273.15);
                        cities.setDescription(weatherAPIResult.getWeather().get(0).getDescription());
                        cities.setIcon(weatherAPIResult.getWeather().get(0).getIcon());
                        cities.setName(weatherAPIResult.getName());
                        weatherViewModel.updateCities(cities);
                        lstCity.add(cities);
                    }
                    bindRecyclerView(lstCity);
                }
            });
        }
    }
}