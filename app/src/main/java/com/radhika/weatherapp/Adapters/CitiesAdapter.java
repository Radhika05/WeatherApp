package com.radhika.weatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.weatherapp.Models.Cities;
import com.radhika.weatherapp.R;
import com.radhika.weatherapp.Interface.RecyclerViewClickListener;
import com.radhika.weatherapp.ViewModels.WeatherViewModel;
import com.squareup.picasso.Picasso;

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
        weatherViewModel = ViewModelProviders.of((FragmentActivity) activity).get(WeatherViewModel.class);
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
            holder.tvTemperature.setText(String.format("%.2f", (city.getTemperature() - 273.15)) + (char) 0x00B0);
            holder.tvDescription.setText(city.getDescription());
            String iconUrl = "http://openweathermap.org/img/w/" + city.getIcon() + ".png";
            Picasso.with(holder.imgIcon.getContext()).load(iconUrl).into(holder.imgIcon);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                weatherViewModel.setCity(lstCities.get(position).getName());
                recyclerViewClickListener.onClick();
            }
        });
    }

    public void setLstCities(List<Cities> lstCities){
        this.lstCities = lstCities;
    }

    @Override
    public int getItemCount() {
        return lstCities.size();
    }

    @Override
    public void onClick(View view) {

    }

    public Cities getCityAt(int adapterPosition) {
        return lstCities.get(adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCityName, tvTemperature, tvDescription;
        private ImageView imgIcon;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            tvCityName = itemView.findViewById(R.id.tv_city_name);
            tvTemperature = itemView.findViewById(R.id.tv_temperature);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgIcon = itemView.findViewById(R.id.img_weather_icon);
        }
    }
}
