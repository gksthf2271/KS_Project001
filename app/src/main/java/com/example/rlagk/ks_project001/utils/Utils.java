package com.example.rlagk.ks_project001.utils;

import android.util.Log;

import com.example.rlagk.ks_project001.Fragment.Fragment_CreateDiary;
import com.example.rlagk.ks_project001.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Utils {
    private static final String TAG = "Utils";

    public static String firstUri(String uri) {
        if (uri.equals(null) || uri.equals("")){
            return null;
        }
        if (!uri.contains("[") && !uri.contains("]")) {
            return uri;
        }
        String bundleString = uri.substring(1, uri.lastIndexOf("]"));
        String result[] = bundleString.split(",");
        if (result == null) {
            Log.i(TAG, "result is null!");
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
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (fragment instanceof Fragment_CreateDiary) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_down);
        }
        fragmentTransaction.replace(container_id, fragment, className);

        if (stack)
            fragmentTransaction.addToBackStack(className);

        fragmentTransaction.commit();
    }

    public static void loadFragment(FragmentManager manager, Fragment fragment, int container_id) {
        loadFragment(manager, fragment, container_id, true);
    }

    public static String splitString(String str) {
        if (str.equals("[]")){
            return str;
        }
        String[] result = str.split("\\[");
        if (!result[0].equals("")) {
            return str;
        }
        result = result[1].split("]");
        return result[0];
    }
}
