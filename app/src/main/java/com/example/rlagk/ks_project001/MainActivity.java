package com.example.rlagk.ks_project001;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.Fragment.Fragment_Main;
import com.example.rlagk.ks_project001.View.CustPagerTransformer;
import com.example.rlagk.ks_project001.View.DragLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final String TAG = "MainActivity";
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private static volatile MainActivity sInstance;
    protected static final int NAV_DRAWER_ITEM_INVALID = -1;
    private DrawerLayout drawerLayout;
    private Toolbar actionBarToolbar;
    private List<Fragment_Main> fragments = new ArrayList<>();

    public static MainActivity newInstance() {
        return new MainActivity();
    }

    public static MainActivity getInstance(){
        if (sInstance == null){
            sInstance = new MainActivity();
        }
        return sInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init(this);
        fillViewPager();
    }

    public void init(Context context) {
        Log.d(TAG, "init(...)");
        DatabaseManager.getInstance().init(this);

        viewPager.setPageTransformer(false, new CustPagerTransformer(this));
        Fragment_Main.STATE_PWD = DragLayout.STATE_PWD_FAILED;
        for (int i = 0; i < 1; i++) {
            fragments.add(new Fragment_Main());
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment_Main fragment = fragments.get(position);
                //fragment.bindData(imageArray[position % imageArray.length]);
                return fragment;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
    }

    private void fillViewPager() {
        Log.d(TAG, "fillViewPager(...)");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelFected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged");

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}

