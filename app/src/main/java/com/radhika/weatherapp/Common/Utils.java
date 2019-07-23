package com.radhika.weatherapp.Common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.radhika.weatherapp.Models.List;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static String getDayFromDate(String date) throws ParseException {
        String result = "";
        if (date != null) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inputDate = inFormat.parse(date);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            assert inputDate != null;
            return outFormat.format(inputDate);
        }
        return result;
    }

    public static Date getDateFromDateTimeString(String dateTimeString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(formatter.format(simpleDateFormat.parse(dateTimeString)));
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String todayDate(String dateTime) throws ParseException {
        String result = "";
        if (dateTime != null) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inputDate = inFormat.parse(dateTime);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
            assert inputDate != null;
            return outFormat.format(inputDate);
        }
        return result;
    }

    public static String getTimeFromString(String dateTime) {
        String hour = "";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(dateTime);
            assert date != null;
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
            hour = outFormat.format(date);
        } catch (ParseException e) {
            Log.d("ParseException", e.toString());
        }
        return hour;
    }

    public static String getDateFromString(String dtTxt) {
        String dateString = "";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(dtTxt);
            assert date != null;
            @SuppressLint("SimpleDateFormat") SimpleDateFormat outFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateString = outFormat.format(date);
        } catch (ParseException e) {
            Log.d("ParseException", e.toString());
        }
        return dateString;
    }

    public static java.util.List<List> uniqueRecords(java.util.List<com.radhika.weatherapp.Models.List> toList) {
        java.util.List<List> lstList = new ArrayList<>();
        Map<String, List> uniqueMap = new HashMap<String, List>();
        for (com.radhika.weatherapp.Models.List to : toList) {
            String dateString = getDateFromString(to.getDtTxt());
            if (!uniqueMap.containsKey(dateString)) {
                uniqueMap.put(dateString, to);
                lstList.add(to);
            }
        }
        return lstList;
    }
}
