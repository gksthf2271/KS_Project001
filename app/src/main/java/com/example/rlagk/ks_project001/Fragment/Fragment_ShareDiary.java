package com.example.rlagk.ks_project001.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.MainActivity;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DetailView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rlagk on 2018-04-10.
 */

public class Fragment_ShareDiary extends Fragment {
    public static final String TAG = Fragment_ShareDiary.class.getName();
    @BindView(R.id.btnCancel)
    Button mCancelBtn;
    @BindView(R.id.btnSave)
    Button mSaveBtn;
    @BindView(R.id.cDetailView)
    DetailView mDetailView;

    private DBHelperUtils mDBHelperUtils;
    public static long ID = 0;

    private String mTitle;
    private String mText;
    private int mImageResId;
    private String mDescription;

    private static volatile Fragment_ShareDiary sInstance;

    public Fragment_ShareDiary(){
    }
    public static Fragment_ShareDiary newInstance() {
        return new Fragment_ShareDiary();
    }

    public static Fragment_ShareDiary getInstance(){
        if (sInstance == null){
            sInstance = new Fragment_ShareDiary();
        }
        return sInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sharediary, container, false);
        if (getArguments() != null) {
            mTitle = (String) getArguments().get("Title");
            mText = (String) getArguments().get("Text");
            mImageResId = (Integer) getArguments().get("ImageResId");
            mDescription = (String) getArguments().get("Description");
        }
        init();
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mDetailView != null) {
            EditText view = mDetailView.findViewById(R.id.diaryText);
            view.setText(mDescription);
        }
    }

    private void init(){
        mDBHelperUtils = DatabaseManager.getInstance().getDB();
    }

    @OnClick(R.id.btnCancel)
    public void cancelBTNClick(){
        Log.d(TAG,"cancelBTNClick(...)");
        Intent intent = new Intent();
        intent.setClass(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnSave)
    public void saveBTNClick(){
        Log.d(TAG,"saveBTNClick(...) ");
        ID++;
        long nowTime = System.currentTimeMillis();
        Date date = new Date(nowTime);
        EditText diaryText = mDetailView.findViewById(R.id.diaryText);
        SimpleDateFormat idFormat = new SimpleDateFormat("yyyyMMddss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Contact contact = new Contact();
        contact.setDate(simpleDateFormat.format(date));
        contact.setTitle(diaryText.getText().toString());
        contact.setDescription(diaryText.getText().toString());
//        contact.setId(String.valueOf(idFormat.format(date)) + ID);
        contact.setId(String.valueOf(SystemClock.currentThreadTimeMillis()));
        mDBHelperUtils.addContact(contact);
        diaryText.setText("");
        loadFragment(Fragment_DiaryList.newInstance());
    }

    private void loadFragment(@NonNull Fragment fragment) {
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
