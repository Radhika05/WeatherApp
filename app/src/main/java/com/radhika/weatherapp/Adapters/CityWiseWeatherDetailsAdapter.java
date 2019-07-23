package com.radhika.weatherapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.radhika.weatherapp.Common.Utils;
import com.radhika.weatherapp.Models.List;
import com.radhika.weatherapp.Models.Main;
import com.radhika.weatherapp.Models.Weather;
import com.radhika.weatherapp.Models.WeatherAPIForecastResult;
import com.radhika.weatherapp.R;
import com.squareup.picasso.Picasso;



public class CityWiseWeatherDetailsAdapter extends RecyclerView.Adapter<CityWiseWeatherDetailsAdapter.ViewHolder> {

    private java.util.List<List> lists;
    private Context context;
    private WeatherAPIForecastResult weatherAPIForecastResult;

    public CityWiseWeatherDetailsAdapter(java.util.List<List> lists, FragmentActivity activity, WeatherAPIForecastResult weatherAPIForecastResult) {
        this.lists = lists;
        this.context = activity;
        this.weatherAPIForecastResult =weatherAPIForecastResult;
    }

    @NonNull
    @Override
    public CityWiseWeatherDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cities_weather_details, parent, false);
        return new CityWiseWeatherDetailsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityWiseWeatherDetailsAdapter.ViewHolder holder, int position) {
        Weather weather  = lists.get(position).getWeather().get(0);
        if(weather!=null){
            holder.mWeatherTitle.setText(String.valueOf(weather.getMain()));
            holder.mWeatherDes.setText(weather.getDescription());
            String iconUrl = "http://openweathermap.org/img/w/" + weather.getIcon() + ".png";
            Picasso.with(context).load(iconUrl).into(holder.mWeatherIcon);
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mWeatherDes;
        private Chip mWeatherTitle;
        private ImageView mWeatherIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        public void initViews(View view){
            mWeatherDes = view.findViewById(R.id.txt_weather_des);
            mWeatherTitle = view.findViewById(R.id.cp_weather_title);
            mWeatherIcon = view.findViewById(R.id.img_weather_icon);
        }
    }
}
