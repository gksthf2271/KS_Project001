package com.example.rlagk.ks_project001.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rlagk.ks_project001.R;

/**
 * Created by rlagk on 2018-04-10.
 */

public class Fragment_DiaryList extends Fragment{

    private static volatile Fragment_DiaryList sInstance;

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
        init();
        return inflater.inflate(R.layout.fragment_secretdiary, container, false);
    }

    private void init() {

    }

}
