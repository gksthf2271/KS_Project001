package com.example.rlagk.ks_project001.DB;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

public class DatabaseManager {
    private static final String TAG = "DatabaseManager";
    private static volatile DatabaseManager sInstance;

    private DBHelperUtils mDBHelperUtils;

    public static DatabaseManager getInstance() {
        if (sInstance == null) {
            synchronized (DatabaseManager.class) {
                if (sInstance == null) {
                    sInstance = new DatabaseManager();
                }
            }
        }
        return sInstance;
    }

    public void init(@NonNull Context context) {
        Log.v(TAG, "init(...)");
        mDBHelperUtils = new DBHelperUtils(context);
    }

    public DBHelperUtils getDB(){
        return mDBHelperUtils;
    }
}
