package com.example.rlagk.ks_project001.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.Fragment.Fragment_CreateDiary;
import com.example.rlagk.ks_project001.Fragment.Fragment_DiaryList;
import com.example.rlagk.ks_project001.Fragment.Fragment_DiaryList_DateSelect;
import com.example.rlagk.ks_project001.Fragment.Fragment_Home;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.MenuView;
import com.example.rlagk.ks_project001.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    public static final String TAG = "MainActivity";
    private static volatile MainActivity sInstance;

    @BindView(R.id.menu_view)
    MenuView mMenuView;
    protected static final int NAV_DRAWER_ITEM_INVALID = -1;

    public static MainActivity getInstance(){
        if (sInstance == null){
            sInstance = new MainActivity();
        }
        return sInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DatabaseManager.getInstance().init(this);
        initLayout();
    }

    private void initLayout() {
        mMenuView.setMenuClickListener(this);
        Utils.loadFragment(getSupportFragmentManager(), Fragment_Home.getInstance(), R.id.fragment_container, false);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private Fragment_DiaryList_DateSelect.ClickListener mClickListener = new Fragment_DiaryList_DateSelect.ClickListener() {
        @Override
        public void onClickDate(String date) {
            Log.d(TAG,"onClickDate ::: " + date);
            Fragment_DiaryList fragment_diaryList = Fragment_DiaryList.getInstance();
            fragment_diaryList.setSearchDate(date);
            Utils.loadFragment(getSupportFragmentManager(), fragment_diaryList, R.id.fragment_container, false);
        }
    };

    @Override
    public void onClickHome() {
        Utils.loadFragment(getSupportFragmentManager(), Fragment_Home.getInstance(), R.id.fragment_container, false);
    }

    @Override
    public void onClickWrite() {
        Utils.loadFragment(getSupportFragmentManager(), Fragment_CreateDiary.getInstance(), R.id.fragment_container, false);
    }

    @Override
    public void onClickList() {
        Utils.loadFragment(getSupportFragmentManager(), Fragment_DiaryList_DateSelect.getInstance(), R.id.fragment_container, false);
    }

    @Override
    public void onClickSetting() {
        startActivity(new Intent(getApplication(), SettingsActivity.class));
    }
}

