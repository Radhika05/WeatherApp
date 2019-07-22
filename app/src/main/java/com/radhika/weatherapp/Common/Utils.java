package com.radhika.weatherapp.Common;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getDayFromDate(String date) throws ParseException {
        String result = "";
        if(date!=null){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date inputDate = inFormat.parse(date);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            return outFormat.format(inputDate);
        }
        return result;
    }

    public static String convertKelvinToCel(Double kelvinVal){
        if(kelvinVal != 0){
            Double cel = (kelvinVal - 273.0);
            return cel.toString();
        }
        else {
            return "";
        }
    }

}
