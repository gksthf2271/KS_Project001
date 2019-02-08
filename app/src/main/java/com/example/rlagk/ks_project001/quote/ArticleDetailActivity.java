package com.example.rlagk.ks_project001.quote;

import android.os.Bundle;

import com.example.rlagk.ks_project001.BaseActivity;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;

/**
 * Simple wrapper for {@link ArticleDetailFragment}
 * This wrapper is only used in single pan mode (= on smartphones)
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ArticleDetailActivity extends BaseActivity {
    public static final String TAG = ArticleDetailActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Show the Up button in the action bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        detailIntent.putExtra("Title",item.getTitle());
//        detailIntent.putExtra("Date", item.getDate());
//        detailIntent.putExtra("ImageResId",item.getImage());
//        detailIntent.putExtra("Description", item.getDescription());
        DiaryListItem diaryListItem = new DiaryListItem(
                getIntent().getIntExtra("ImageResId",-1),
                getIntent().getStringExtra("Title")
                ,getIntent().getStringExtra("Description")
                ,getIntent().getStringExtra("Date"));
        ArticleDetailFragment fragment =  ArticleDetailFragment.newInstance(diaryListItem);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}
