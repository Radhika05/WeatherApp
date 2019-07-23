package com.radhika.weatherapp.Common;

import android.annotation.SuppressLint;
import android.util.Log;

import com.radhika.weatherapp.Models.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getDayFromDate(String date) throws ParseException {
        String result = "";
        if(date!=null){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inputDate = inFormat.parse(date);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            assert inputDate != null;
            return outFormat.format(inputDate);
        }
        return result;
    }


    public static String todayDate(String dateTime) throws ParseException {
        String result = "";
        if(dateTime!=null){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inputDate = inFormat.parse(dateTime);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
            assert inputDate != null;
            return outFormat.format(inputDate);
        }
        return result;
    }

    public static String getTimeFromString(String dateTime){
        String hour = "";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(dateTime);
            assert date != null;
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
            hour = outFormat.format(date);
        } catch (ParseException e) {
            Log.d("ParseException",e.toString());
        }
        return hour;
    }
}
