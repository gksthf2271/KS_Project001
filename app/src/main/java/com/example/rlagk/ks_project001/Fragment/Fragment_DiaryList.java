package com.example.rlagk.ks_project001.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.rlagk.ks_project001.Adapter.HomeDiaryListAdapter;
import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListEmptyView;
import com.example.rlagk.ks_project001.dummy.DummyContent;
import com.example.rlagk.ks_project001.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import butterknife.BindView;


public class Fragment_DiaryList extends BaseFragment {

    private static volatile Fragment_DiaryList sInstance;

    public static final String TAG = Fragment_DiaryList.class.getName();
    @BindView(R.id.gridView)
    GridView mGridView;

    @BindView(R.id.cDiaryListEmptyView)
    DiaryListEmptyView mDiaryListEmptyView;
    private List<Contact> mContactList;

    String mSearchDate = null;
    private HomeDiaryListAdapter mHomeDiaryListAdapter = null;
    private ArrayList<HorImageItem> mHorImageViewList = null;

    public Fragment_DiaryList(){

    }

    public static Fragment_DiaryList getInstance(){
        if (sInstance == null){
            sInstance = new Fragment_DiaryList();
        }
        return sInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_diary);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mContactList = null;
        mHorImageViewList = new ArrayList<>();
        mContactList = DatabaseManager.getInstance().getDB().getContacts(mSearchDate);
        if (DummyContent.isDebug) {
            mGridView.setVisibility(View.VISIBLE);
            mDiaryListEmptyView.setVisibility(View.GONE);
            initView();
            return;
        }
        if (isDiaryListEmpty()) {
            mGridView.setVisibility(View.GONE);
            mDiaryListEmptyView.setEmptyIconClickListener(mEmptyIconClickListener);
            mDiaryListEmptyView.setVisibility(View.VISIBLE);
        } else {
            mGridView.setVisibility(View.VISIBLE);
            mDiaryListEmptyView.setVisibility(View.GONE);
            initView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
        mContactList = null;
    }

    private void initView() {
        int height = mGridView.getHeight();
        int width = mGridView.getRequestedColumnWidth();
        for (Contact contact : mContactList) {
            if(contact == null) {
                return;
            }
            mHorImageViewList.add(new HorImageItem(contact));
        }
        if (DummyContent.isDebug) {
            DummyContent dummyContent = new DummyContent(this.getContext());
            mHomeDiaryListAdapter = new HomeDiaryListAdapter(getContext(), R.layout.adapter_home_list_dairy, dummyContent.ITEMS, width, height);
        } else {
            mHomeDiaryListAdapter = new HomeDiaryListAdapter(getContext(), R.layout.adapter_home_list_dairy, mHorImageViewList, width, height);
        }
        mGridView.setAdapter(mHomeDiaryListAdapter);
        mGridView.setOnItemClickListener(mItemClickListener);
    }

    private GridView.OnItemClickListener mItemClickListener = new GridView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d(TAG,"Position ::: " + position);
            if(DummyContent.isDebug){
                DummyContent dummyContent = new DummyContent(view.getContext());
                Utils.loadFragment(getFragmentManager(), Fragment_DiaryDetail.newInstance(dummyContent.ITEMS.get(position)), R.id.fragment_container, true);
            } else {
                Utils.loadFragment(getFragmentManager(), Fragment_DiaryDetail.newInstance(mHorImageViewList.get(position)), R.id.fragment_container, true);
            }
        }
    };

    public void setSearchDate(String date){
        if (date == null){
            Log.d(TAG,"date is null");
            return;
        }
        Log.d(TAG,"setSearchDate ::: " + date);
        mSearchDate = date;
    }

    public String getSearchDate(){
        Log.d(TAG,"getSearchDate ::: " + mSearchDate);
        return mSearchDate;
    }

    private boolean isDiaryListEmpty() {
        if (mContactList != null && mContactList.isEmpty()){
            return true;
        }
        return false;
    }

    DiaryListEmptyView.EmptyIconClickListener mEmptyIconClickListener = new DiaryListEmptyView.EmptyIconClickListener() {
        @Override
        public void onClickEmptyIcon() {
            Log.d(TAG,"onClickEmptyIcon(...)");
            Utils.loadFragment(getFragmentManager(), Fragment_CreateDiary.getInstance(), R.id.fragment_container,true);
        }
    };
}
