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


public class Fragment_DiaryList extends BaseFragment {

    private static volatile Fragment_DiaryList sInstance;

    public static final String TAG = Fragment_DiaryList.class.getName();
    @BindView(R.id.cDiaryListView)
    DiaryListView mDiaryListView;

    String mSearchDate = null;

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
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_secretdiary);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDiaryListView.setDiaryListener(mSelectListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    private void popBackStack() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }

    DiaryListView.ListViewListener mSelectListener = new DiaryListView.ListViewListener() {
        @Override
        public void onUpdateItemList(String selectResult) {
            Log.d(TAG,"onUpdateItemList!!! ::: " + selectResult);
        }

        @Override
        public void onItemClick(View v, int position, DiaryListItem item) {
            Log.d(TAG,"onItemClick!!! ::: " + position + "\n item ::: " + item);
            Utils.loadFragment(getFragmentManager(), Fragment_DiaryDetail.newInstance(item), R.id.fragment_container);
        }
    };

    public void setSearchDate(String date){
        if (date == null){
            Log.d(TAG,"date is null");
            return;
        }
        Log.d(TAG,"setSearchDate ::: " + date);
        mSearchDate = date;
    }

    public String getSearchDate(){
        Log.d(TAG,"getSearchDate ::: " + mSearchDate);
        return mSearchDate;
    }
}
