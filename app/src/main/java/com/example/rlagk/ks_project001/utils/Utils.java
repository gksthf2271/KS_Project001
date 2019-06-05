package com.example.rlagk.ks_project001.utils;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

    public static void loadFragment(FragmentManager manager, Fragment fragment, int container_id, boolean stack) {
        Log.v(TAG, "loadFragment(...)  " + fragment);

        if (manager == null) {
            Log.w(TAG, "Fragment manager is null");
            return;
        }

        String className = fragment.getClass().getName();
        FragmentTransaction fragmentTransaction= manager.beginTransaction();
        fragmentTransaction.replace(container_id, fragment, className);

        if (stack)
            fragmentTransaction.addToBackStack(className);

        fragmentTransaction.commit();
    }

    public static void loadFragment(FragmentManager manager, Fragment fragment, int container_id) {
        loadFragment(manager, fragment, container_id, true);
    }


}
