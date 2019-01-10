package com.example.rlagk.ks_project001.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListItem;
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
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDiaryListView.setDiaryListener(mSelectListener);
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

    DiaryListView.OnSelectListener mSelectListener = new DiaryListView.OnSelectListener() {
        @Override
        public void onItemClick(View v, int position, DiaryListItem item) {
            Log.d(TAG,"onItemClick!!! ::: " + position + "\n item ::: " + item);
            loadFragment(Fragment_ShareDiary.newInstance(), item);
        }
    };


    private void loadFragment(@NonNull Fragment fragment, DiaryListItem item) {
        Log.v(TAG, "loadFragment(...)  " + fragment);
        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager == null) {
            Log.w(TAG, "Failed to load a fragment (null FragmentManager)");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("Title",item.getTitle());
        bundle.putString("Date", item.getDate());
        bundle.putInt("ImageResId",item.getImage());
        bundle.putString("Description", item.getDescription());
        fragment.setArguments(bundle);

        String className = fragment.getClass().getName();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, className)
                .addToBackStack(className)
                .commit();
    }
}
