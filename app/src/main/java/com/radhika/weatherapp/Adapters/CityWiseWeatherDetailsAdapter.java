package com.radhika.weatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.radhika.weatherapp.Models.Weather;
import com.radhika.weatherapp.R;
import com.squareup.picasso.Picasso;

public class CityWiseWeatherDetailsAdapter extends RecyclerView.Adapter<CityWiseWeatherDetailsAdapter.ViewHolder> {

    private java.util.List<Weather> weatherList;
    Context context;

    public CityWiseWeatherDetailsAdapter(java.util.List<Weather> weatherList, FragmentActivity activity) {
        this.weatherList = weatherList;
        this.context = activity;
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
        Weather weather = weatherList.get(position);
        if(weather!=null){
            holder.tvDate.setText(String.valueOf(weather.getMain()));
            holder.tvTemp.setText(weather.getDescription());
            String iconUrl = "http://openweathermap.org/img/w/" + weather.getIcon() + ".png";
            Picasso.with(context).load(iconUrl).into(holder.imgIcon);
        }
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate,tvTemp;
        private ImageView imgIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        public void initViews(View view){
            tvDate = view.findViewById(R.id.txt_date);
            tvTemp = view.findViewById(R.id.txt_temp);
            imgIcon = view.findViewById(R.id.img_icon);
        }
    }
}
