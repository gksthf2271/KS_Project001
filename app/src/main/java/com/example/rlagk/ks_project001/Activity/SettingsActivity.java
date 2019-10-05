package com.example.rlagk.ks_project001.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.rlagk.ks_project001.Fragment.Fragment_Home;
import com.example.rlagk.ks_project001.Fragment.Fragment_Settings;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.utils.Utils;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceFragment;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init(){
        loadSettingFragment();
    }

    private void loadSettingFragment(){
        Utils.loadFragment(getSupportFragmentManager(), Fragment_Settings.getInstance(), R.id.fragment_container, false);
    }

    @Override
    public void onClickHome() {

    }

    @Override
    public void onClickWrite() {

    }

    @Override
    public void onClickList() {

    }

    @Override
    public void onClickSetting() {

    }
}
