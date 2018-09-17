package com.example.rlagk.ks_project001;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.Fragment.Fragment_Main;
import com.example.rlagk.ks_project001.View.DetailView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IdentityHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends FragmentActivity {
    public static final String TAG = "DetailActivity";

    public static final String EXTRA_IMAGE_URL = "detailImageUrl";

    public static final String IMAGE_TRANSITION_NAME = "transitionImage";
    public static final String ADDRESS1_TRANSITION_NAME = "address1";
    public static final String RATINGBAR_TRANSITION_NAME = "ratingBar";
    public static long ID = 0;

    @BindView(R.id.btnCancel)
    Button mCancelBtn;
    @BindView(R.id.btnSave)
    Button mSaveBtn;
    @BindView(R.id.cDetailView)
    LinearLayout mDetailView;

    private DBHelperUtils mDBHelperUtils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate(...)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        ButterKnife.bind(this);
    }

    private void init(){
        mDBHelperUtils = new DBHelperUtils(this);
    }

    @OnClick(R.id.btnCancel)
    public void cancelBTNClick(){
        Log.d(TAG,"cancelBTNClick(...)");
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnSave)
    public void saveBTNClick(){
        ID++;
        Log.d(TAG,"saveBTNClick(...) " + ID);
        long nowTime = System.currentTimeMillis();
        Date date = new Date(nowTime);
        EditText diaryText = mDetailView.findViewById(R.id.diaryText);
        SimpleDateFormat idFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Contact contact = new Contact();
        contact.setDate(simpleDateFormat.format(date));
        contact.setTitle(diaryText.getText().toString());
        contact.setDescription(diaryText.getText().toString());
        contact.setId(String.valueOf(idFormat.format(date)) + ID);
        mDBHelperUtils.addContact(contact);
        diaryText.setText("");
    }
}
