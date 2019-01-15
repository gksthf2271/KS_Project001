package com.example.rlagk.ks_project001;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.rlagk.ks_project001.Fragment.Fragment_ShareDiary;

import butterknife.BindView;

public class DetailActivity extends FragmentActivity {
    public static final String TAG = "DetailActivity";

    public static final String EXTRA_IMAGE_URL = "detailImageUrl";

    public static final String IMAGE_TRANSITION_NAME = "transitionImage";
    public static final String ADDRESS1_TRANSITION_NAME = "address1";
    public static final String RATINGBAR_TRANSITION_NAME = "ratingBar";

    @BindView(R.id.fragment_container)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate(...)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        loadFragment(Fragment_ShareDiary.newInstance());
    }


    private void loadFragment(@NonNull Fragment fragment) {
        Log.v(TAG, "loadFragment(...)  " + fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager == null) {
            Log.w(TAG, "Failed to load a fragment (null FragmentManager)");
            return;
        }

        String className = fragment.getClass().getName();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, className)
                .addToBackStack(className)
                .commit();
    }

}
