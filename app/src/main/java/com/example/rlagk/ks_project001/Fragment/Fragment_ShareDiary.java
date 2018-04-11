package com.example.rlagk.ks_project001.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rlagk.ks_project001.R;

/**
 * Created by rlagk on 2018-04-10.
 */

public class Fragment_ShareDiary extends Fragment {

    public Fragment_ShareDiary(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sharediary, container, false);
    }
}
