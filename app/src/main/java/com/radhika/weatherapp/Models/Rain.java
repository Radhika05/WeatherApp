package com.radhika.weatherapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Rain {
    @SerializedName("3h")
    private Double _3h;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @param _3h
     */
    public Rain(Double _3h) {
        super();
        this._3h = _3h;
    }

    public Double get3h() {
        Double val = 0d;
        if(_3h!=null){
            return _3h;
        }
        else {
            return val;
        }
    }

    public void set3h(Double _3h) {
        this._3h = _3h;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
