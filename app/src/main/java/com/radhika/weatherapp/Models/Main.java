package com.radhika.weatherapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Main {
    @SerializedName("temp")
    private Double temp;
    @SerializedName("pressure")
    private Double pressure;
    @SerializedName("humidity")
    private Integer humidity;
    @SerializedName("temp_min")
    private Double temp_Min;
    @SerializedName("temp_max")
    private Double temp_Max;
    @SerializedName("sea_level")
    private Double sea_Level;
    @SerializedName("grnd_level")
    private Double grnd_Level;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Main() {
    }

    /**
     *
     * @param seaLevel
     * @param humidity
     * @param pressure
     * @param grndLevel
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Main(Double temp, Double pressure, Integer humidity, Double tempMin, Double tempMax, Double seaLevel, Double grndLevel) {
        super();
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_Min = tempMin;
        this.temp_Max = tempMax;
        this.sea_Level = seaLevel;
        this.grnd_Level = grndLevel;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return temp_Min;
    }

    public void setTempMin(Double tempMin) {
        this.temp_Min = tempMin;
    }

    public Double getTempMax() {
        return temp_Max;
    }

    public void setTempMax(Double tempMax) {
        this.temp_Max = tempMax;
    }

    public Double getSeaLevel() {
        return sea_Level;
    }

    public void setSeaLevel(Double seaLevel) {
        this.sea_Level = seaLevel;
    }

    public Double getGrndLevel() {
        return grnd_Level;
    }

    public void setGrndLevel(Double grndLevel) {
        this.grnd_Level = grndLevel;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
