package com.example.rlagk.ks_project001.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesUtils {
    public static final String TAG = "ServiceUtils";

    public static final String PREF_FILE_NAME = "CoupleInfo";
    public static final String PREF_KEY_COUPLE_INFO = "KeyCoupleInfo";

    public static void savePref(Context context, String prefFileName, String key, Object obj) {
        if (context == null) {
            Log.d(TAG,"context is null");
            return;
        }

        SharedPreferences pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof String){
            editor.putString(key, obj.toString());
        }
        editor.apply();
    }

    public static String getPref(Context context, String PrefFileName, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PrefFileName, Context.MODE_PRIVATE);
        String result = prefs.getString(key, null);
        return result;
    }

}



