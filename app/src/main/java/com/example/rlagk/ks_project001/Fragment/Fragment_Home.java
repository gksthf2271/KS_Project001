package com.example.rlagk.ks_project001.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rlagk.ks_project001.Adapter.AddDiaryImageAdapter;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class Fragment_Home extends BaseFragment {
    private static volatile Fragment_Home sInstance;
    public static final String TAG = Fragment_Home.class.getName();
    @BindView(R.id.horizontalImageRecyclerView)
    RecyclerView mRecyclerView;

    private AddDiaryImageAdapter mHorImageViewAdapter;
    private ArrayList<HorImageItem> mHorImageViewList;

    public static Fragment_Home getInstance() {
        if (sInstance == null) {
            sInstance = new Fragment_Home();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_home);
        return rootView;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    private void initView() {
        mHorImageViewAdapter = new AddDiaryImageAdapter(mHorImageViewList, getContext());
        LinearLayoutManager lim = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(lim);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mHorImageViewAdapter);
    }

    private void getDiaryList(int maxCount) {

    }
}