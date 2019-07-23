package com.radhika.weatherapp.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cities {

    @PrimaryKey
    @ColumnInfo(name = "city_id")
    private int cityId;

    @NonNull
    @ColumnInfo(name = "city")
    private String name;

    @ColumnInfo(name = "temperature")
    private Double temperature;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "icon")
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
