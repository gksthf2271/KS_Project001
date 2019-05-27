package com.example.rlagk.ks_project001.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.rlagk.ks_project001.Fragment.BaseFragment;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_BaseList extends BaseFragment implements DiaryListView.OnSelectListener {

    public static final String TAG = Fragment_BaseList.class.getName();
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_base_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }


    private void loadFragment(@NonNull BaseFragment fragment) {
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

//Todo: Diary Item Btn click 시 아래 소스 수행되도록 수정
    @Override
    public void onItemClick(View v, int position, DiaryListItem item) {
        // Start the detail activity in single pane mode.
//        Intent detailIntent = new Intent(this, ArticleDetailActivity.class);
//        detailIntent.putExtra("Title", item.getTitle());
//        detailIntent.putExtra("Date", item.getDate());
//        detailIntent.putExtra("ImageUri", item.getImage());
//        detailIntent.putExtra("Description", item.getDescription());
//        startActivity(detailIntent);
    }

}
