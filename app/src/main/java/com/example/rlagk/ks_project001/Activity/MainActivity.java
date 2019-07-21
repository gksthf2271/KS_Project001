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
import com.example.rlagk.ks_project001.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final String TAG = "MainActivity";
    private static volatile MainActivity sInstance;

    protected static final int NAV_DRAWER_ITEM_INVALID = -1;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar mToolbar;

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
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Utils.loadFragment(getSupportFragmentManager(), Fragment_Home.getInstance(), R.id.fragment_container, false);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
        Fragment_DiaryList_DateSelect.getInstance().setClickListener(mClickListener);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Utils.loadFragment(getSupportFragmentManager(), Fragment_Home.getInstance(), R.id.fragment_container, false);
                break;
            case R.id.nav_list:
                Utils.loadFragment(getSupportFragmentManager(), Fragment_DiaryList_DateSelect.getInstance(), R.id.fragment_container, false);
                break;
            case R.id.nav_write:
                Utils.loadFragment(getSupportFragmentManager(), Fragment_CreateDiary.getInstance(), R.id.fragment_container, false);
                break;
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.nav_write2:
                startActivity(new Intent(this, ArticleDetailActivity.class));
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
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
}

