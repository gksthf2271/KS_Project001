package com.example.rlagk.ks_project001.Activity;


import com.example.rlagk.ks_project001.View.MenuView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseActivity extends AppCompatActivity implements MenuView.MenuItemClickListener {
    public static final String TAG = BaseActivity.class.getName();


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
