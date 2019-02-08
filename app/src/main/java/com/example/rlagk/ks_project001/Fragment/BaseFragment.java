package com.example.rlagk.ks_project001.Fragment;

import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * The base class for all fragment classes.
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class BaseFragment extends Fragment {

    public static final String TAG = BaseFragment.class.getName();

    /**
     * Inflates the layout and binds the view via ButterKnife.
     * @param inflater the inflater
     * @param container the layout container
     * @param layout the layout resource
     * @return the inflated view
     */
    public View inflateAndBind(LayoutInflater inflater, ViewGroup container, int layout) {
        View view = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, view);

        Log.d(TAG, ">>> view inflated");
        return view;
    }
}
