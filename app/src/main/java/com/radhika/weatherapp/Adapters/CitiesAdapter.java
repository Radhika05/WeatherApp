package com.radhika.weatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.weatherapp.Fragments.WeatherDetailsFragment;
import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.RecyclerViewClickListener;
import com.radhika.weatherapp.ViewModels.WeatherViewModel;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> implements View.OnClickListener {

    private List<Cities> lstCities;
    private Cities city;
    private WeatherViewModel weatherViewModel;
    private RecyclerViewClickListener recyclerViewClickListener;

    public CitiesAdapter(List<Cities> lstCities, RecyclerViewClickListener recyclerViewClickListener, FragmentActivity activity) {
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.lstCities = lstCities;
        Context context = activity;
        weatherViewModel = ViewModelProviders.of((FragmentActivity)activity).get(WeatherViewModel.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cities_list, parent, false);
        return new CitiesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        city = lstCities.get(position);
        if (city != null) {
            holder.tvCityName.setText(city.getName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherViewModel.setCity(city.getName());
                recyclerViewClickListener.onClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstCities.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCityName;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            tvCityName = itemView.findViewById(R.id.tv_city_name);
        }
    }
}
