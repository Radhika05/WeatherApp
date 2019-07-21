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

import com.radhika.weatherapp.Models.List;
import com.radhika.weatherapp.Models.Weather;
import com.radhika.weatherapp.R;
import com.squareup.picasso.Picasso;

public class CityWiseWeatherDetailsAdapter extends RecyclerView.Adapter<CityWiseWeatherDetailsAdapter.ViewHolder> {

    private java.util.List<List> listLists;
    Context context;

    public CityWiseWeatherDetailsAdapter(java.util.List<List> lists, FragmentActivity activity) {
        this.listLists = lists;
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
        java.util.List<Weather> weather = listLists.get(position).getWeather();
        if(listLists!=null){
            holder.tvDate.setText(String.valueOf(listLists.get(position).getDt_txt()));
            holder.tvTemp.setText(weather.get(0).getDescription());
            String iconUrl = "http://openweathermap.org/img/w/" + weather.get(0).getIcon() + ".png";
            Picasso.with(context).load(iconUrl).into(holder.imgIcon);
        }
    }

    @Override
    public int getItemCount() {
        return listLists.size();
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
