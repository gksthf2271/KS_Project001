package com.example.rlagk.ks_project001.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rlagk.ks_project001.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends BaseFragment {
    private static final String TAG = SettingsFragment.class.getName();

    private static volatile SettingsFragment sInstance;

    @BindView(R.id.txt_dateSelector)
    TextView mTextDateSelector;

    public SettingsFragment() {}

    public static SettingsFragment getInstance() {
        if (sInstance == null) {
            sInstance = new SettingsFragment();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView(...)");
        View view = inflateAndBind(inflater, container, R.layout.fragment_settings);
        ButterKnife.bind(view);
        return view;
    }

    @OnClick(R.id.ok)
    private void onClickOk(View view){

    }

    @OnClick(R.id.cancel)
    private void onClickCancel(View view){

    }
}
