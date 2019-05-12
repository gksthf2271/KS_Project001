package com.example.rlagk.ks_project001.Fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_DiaryList extends BaseFragment {

    private static volatile Fragment_DiaryList sInstance;

    public static final String TAG = Fragment_CreateDiary.class.getName();
    @BindView(R.id.cDiaryListView)
    DiaryListView mDiaryListView;

    public Fragment_DiaryList(){

    }
    public static Fragment_DiaryList newInstance() {
        return new Fragment_DiaryList();
    }

    public static Fragment_DiaryList getInstance(){
        if (sInstance == null){
            sInstance = new Fragment_DiaryList();
        }
        return sInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_secretdiary, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDiaryListView.setDiaryListener(mSelectListener);
    }

    private void popBackStack() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }

    DiaryListView.OnSelectListener mSelectListener = new DiaryListView.OnSelectListener() {
        @Override
        public void onItemClick(View v, int position, DiaryListItem item) {
            Log.d(TAG,"onItemClick!!! ::: " + position + "\n item ::: " + item);
            loadFragment(Fragment_DiaryDetail.newInstance(item));
        }
    };


    private void loadFragment(@NonNull BaseFragment fragment) {
        Log.v(TAG, "loadFragment(...)  " + fragment);
        FragmentManager fragmentManager = getFragmentManager();

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
