package com.radhika.weatherapp.Models;

import java.util.HashMap;
import java.util.Map;

public class WeatherAPIResult {
    private String message;
    private String cod;
    private Integer count;
    private java.util.List<com.radhika.weatherapp.Models.List> list = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<com.radhika.weatherapp.Models.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.radhika.weatherapp.Models.List> list) {
        this.list = list;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
