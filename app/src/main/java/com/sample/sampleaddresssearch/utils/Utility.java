package com.sample.sampleaddresssearch.utils;

public class Utility {
    public static String getLatLong(Double lat,Double lon)
    {
        return String.format("%s , %s",lat,lon);
    }


    public static boolean isEmpty(String str) {
        return str == null || str.equals("") || str.equals("null");
    }

}
