package com.example.rlagk.ks_project001;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.rlagk.ks_project001.Fragment.Fragment_Main;
import com.example.rlagk.ks_project001.View.CustPagerTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    public static final String TAG = "MainActivity";
    @BindView(R.id.indicator_tv)
    TextView indicatorTv;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<Fragment_Main> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        fillViewPager();
    }

    public void init() {
        Log.d(TAG, "init(...)");
        viewPager.setPageTransformer(false, new CustPagerTransformer(this));

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
                updateIndicatorTv();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged");

            }
        });
        updateIndicatorTv();
    }




    private void updateIndicatorTv() {
        Log.d(TAG, "updateIndicatorTv(...)");
        int totalNum = viewPager.getAdapter().getCount();
        int currentItem = viewPager.getCurrentItem() + 1;
        indicatorTv.setText(currentItem + "  /  " + totalNum);
    }
}

