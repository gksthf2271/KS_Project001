package com.example.rlagk.ks_project001.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.rlagk.ks_project001.Fragment.Fragment_CreateDiary;
import com.example.rlagk.ks_project001.Fragment.Fragment_DiaryDetail;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;

import androidx.annotation.NonNull;

public class ArticleDetailActivity extends BaseActivity {
    public static final String TAG = ArticleDetailActivity.class.getName();

    public static final String EXTRA_IMAGE_URL = "detailImageUrl";

    public static final String IMAGE_TRANSITION_NAME = "transitionImage";
    public static final String ADDRESS1_TRANSITION_NAME = "address1";
    public static final String RATINGBAR_TRANSITION_NAME = "ratingBar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Show the Up button in the action bar.
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent().getStringExtra("Title") == null){
            Fragment_CreateDiary fragment =  Fragment_CreateDiary.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        } else {
            String str = getIntent().getStringExtra("ImageUri");
            DiaryListItem diaryListItem = new DiaryListItem(
                    getIntent().getLongExtra("Id",0),
                    Uri.parse(str),
                    getIntent().getStringExtra("Title"),
                    getIntent().getStringExtra("Description"),
                    getIntent().getStringExtra("Date"));
            Fragment_DiaryDetail fragment = Fragment_DiaryDetail.newInstance(diaryListItem);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClickHome() {

    }

    @Override
    public void onClickWrite() {

    }

    @Override
    public void onClickList() {

    }

    @Override
    public void onClickSetting() {

    }
}
