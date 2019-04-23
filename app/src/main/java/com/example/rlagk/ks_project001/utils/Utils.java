package com.example.rlagk.ks_project001.utils;

import android.util.Log;

public class Utils {
    private static final String TAG = "Utils";
    public static String firstUri(String uri) {
        String bundleString = uri.substring(1,uri.lastIndexOf("]"));
        String result[] = bundleString.split(",");
        if (result == null) {
            Log.i(TAG,"result is null!");
            return null;
        }
        return result[0];
    }
}
