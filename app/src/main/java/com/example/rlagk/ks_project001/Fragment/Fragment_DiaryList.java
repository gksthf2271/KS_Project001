package com.example.rlagk.ks_project001.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rlagk on 2018-04-10.
 */

public class Fragment_DiaryList extends Fragment{

    private static volatile Fragment_DiaryList sInstance;

    public static final String TAG = Fragment_ShareDiary.class.getName();
    @BindView(R.id.btnCancel)
    Button mCancelBtn;
    @BindView(R.id.btnSave)
    Button mSaveBtn;
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
        init();
        ButterKnife.bind(this, v);
        return v;
    }

    private void init() {

    }

    @OnClick(R.id.btnCancel)
    public void onCancelButtonClick(){
        Log.d(TAG,"cancelBTNClick");
        popBackStack();
    }

    private void popBackStack() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }

}
