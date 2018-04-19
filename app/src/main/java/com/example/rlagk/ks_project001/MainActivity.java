package com.example.rlagk.ks_project001;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.rlagk.ks_project001.Fragment.Fragment_Main;
import com.example.rlagk.ks_project001.Fragment.Fragment_ShareDiary;
import com.example.rlagk.ks_project001.Fragment.Fragment_SecretDiary;
import com.example.rlagk.ks_project001.View.CustPagerTransformer;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity{
        public static final String TAG = "MainActivity";
        private TextView indicatorTv;
        private View positionView;
        private ViewPager viewPager;
        private List<Fragment_Main> fragments = new ArrayList<>(); // 供ViewPager使用
        private final String[] imageArray = {"assets://image1.jpg", "assets://image2.jpg", "assets://image3.jpg", "assets://image4.jpg", "assets://image5.jpg"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            positionView = findViewById(R.id.position_view);
            dealStatusBar();
            initImageLoader();


            fillViewPager();
        }

        private void fillViewPager() {
            indicatorTv = (TextView) findViewById(R.id.indicator_tv);
            viewPager = (ViewPager) findViewById(R.id.viewpager);

            // 1. viewPager添加parallax效果，使用PageTransformer就足够了
            viewPager.setPageTransformer(false, new CustPagerTransformer(this));

            // 2. viewPager添加adapter
            for (int i = 0; i < 10; i++) {
                // 预先准备10个fragment
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
                }

                @Override
                public void onPageSelected(int position) {
                    updateIndicatorTv();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

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

        @SuppressWarnings("deprecation")
        private void initImageLoader() {
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                    .memoryCacheExtraOptions(480, 800)
                    .threadPoolSize(3)
                    .threadPriority(Thread.NORM_PRIORITY - 1)
                    .tasksProcessingOrder(QueueProcessingType.FIFO)
                    .denyCacheImageMultipleSizesInMemory()
                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                    .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
                    .discCacheSize(50 * 1024 * 1024)
                    .discCacheFileCount(100)
                    .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                    .imageDownloader(new BaseImageDownloader(this)) // default
                    .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                    .writeDebugLogs().build();

            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(config);
        }

}

