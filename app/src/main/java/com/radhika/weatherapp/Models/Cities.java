package com.radhika.weatherapp.Models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cities {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "city_id")
    private int cityId;

    @NonNull
    @ColumnInfo(name = "city")
    private String name;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
