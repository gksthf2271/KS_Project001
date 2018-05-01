package com.example.rlagk.ks_project001;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.TextView;

import com.example.rlagk.ks_project001.Fragment.Fragment_Main;
import com.example.rlagk.ks_project001.View.CustPagerTransformer;
import com.example.rlagk.ks_project001.utils.ImageLoaderUtility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{
        public static final String TAG = "MainActivity";
        private TextView indicatorTv;
        private View positionView;
        private ViewPager viewPager;
        private List<Fragment_Main> fragments = new ArrayList<>();
        private final String[] imageArray = {"drawable://image1.jpg", "drawable://image2.jpg", "drawable://image3.jpg", "drawable://image4.jpg", "drawable://image5.jpg"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            /*positionView = findViewById(R.id.position_view);
            dealStatusBar();*/
            ImageLoaderUtility.getInstance().setmContext(this);
            ImageLoaderUtility.getInstance().initImageLoader();


            fillViewPager();
        }

        private void fillViewPager() {
            indicatorTv = (TextView) findViewById(R.id.indicator_tv);
            viewPager = (ViewPager) findViewById(R.id.viewpager);

            viewPager.setPageTransformer(false, new CustPagerTransformer(this));

            for (int i = 0; i < 10; i++) {
                fragments.add(new Fragment_Main());
            }

            viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    Fragment_Main fragment = fragments.get(position % 10);
                    fragment.bindData(imageArray[position % imageArray.length]);
                    return fragment;
                }

                @Override
                public int getCount() {
                    return 10;
                }
            });

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    Log.d(TAG,"onPageScrolled");
                }

                @Override
                public void onPageSelected(int position) {
                    Log.d(TAG,"onPageSelFected");
                    updateIndicatorTv();
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    Log.d(TAG,"onPageScrollStateChanged");

                }
            });

            updateIndicatorTv();
        }

        private void updateIndicatorTv() {
            int totalNum = viewPager.getAdapter().getCount();
            int currentItem = viewPager.getCurrentItem() + 1;
            indicatorTv.setText(Html.fromHtml("<font color='#12edf0'>" + currentItem + "</font>  /  " + totalNum));
        }

        private void dealStatusBar() {
                int statusBarHeight = getStatusBarHeight();
                ViewGroup.LayoutParams lp = positionView.getLayoutParams();
                lp.height = statusBarHeight;
                positionView.setLayoutParams(lp);
        }

        private int getStatusBarHeight() {
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int x = 0, statusBarHeight = 0;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return statusBarHeight;
        }


}

