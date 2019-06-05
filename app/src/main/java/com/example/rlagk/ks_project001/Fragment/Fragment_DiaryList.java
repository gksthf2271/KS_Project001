package com.example.rlagk.ks_project001.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListView;
import com.example.rlagk.ks_project001.utils.Utils;

import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_DiaryList extends BaseFragment {

    private static volatile Fragment_DiaryList sInstance;

    public static final String TAG = Fragment_CreateDiary.class.getName();
    @BindView(R.id.cDiaryListView)
    DiaryListView mDiaryListView;

    public Fragment_DiaryList(){

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
            Utils.loadFragment(getFragmentManager(), Fragment_DiaryDetail.newInstance(item), R.id.fragment_container);
        }
    };
}
