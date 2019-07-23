package com.radhika.weatherapp.Models;

import java.util.HashMap;
import java.util.Map;

public class WeatherAPIForecastResult {
    private String cod;
    private Double message;
    private Integer cnt;
    private java.util.List<List> list = null;
    private City city;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public WeatherAPIForecastResult() {
    }

    public WeatherAPIForecastResult(String cod, Double message, Integer cnt, java.util.List<List> list, City city) {
        super();
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
