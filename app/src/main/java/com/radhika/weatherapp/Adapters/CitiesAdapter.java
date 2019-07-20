package com.radhika.weatherapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.weatherapp.Fragments.WeatherDetailsFragment;
import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.R;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    private List<Cities> lstCities;
    private Cities city;

    public CitiesAdapter(List<Cities> lstCities) {
        this.lstCities = lstCities;
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
    }

    @Override
    public int getItemCount() {
        return lstCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvCityName;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
            fnClick(itemView);
        }

        private void fnClick(View itemView) {
            itemView.setOnClickListener(this);
        }

        private void initViews(View itemView) {
            tvCityName = itemView.findViewById(R.id.tv_city_name);
        }

        @Override
        public void onClick(View view) {
            WeatherDetailsFragment fgWeather = new WeatherDetailsFragment();
        }
    }
}
