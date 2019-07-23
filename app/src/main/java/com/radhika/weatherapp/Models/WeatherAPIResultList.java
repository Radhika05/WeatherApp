package com.radhika.weatherapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherAPIResultList {
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<WeatherAPIResult> list = null;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<WeatherAPIResult> getList() {
        return list;
    }

    public void setList(java.util.List<WeatherAPIResult> list) {
        this.list = list;
    }
}
