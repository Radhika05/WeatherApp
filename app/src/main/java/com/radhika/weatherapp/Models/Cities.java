package com.radhika.weatherapp.Models;

public class Cities {
    private String name;

    public Cities(String cityName) {
        this.name = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
