package com.example.rlagk.ks_project001.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.rlagk.ks_project001.Adapter.HomeDiaryListAdapter;
import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.CoupleInfoView;
import com.example.rlagk.ks_project001.View.DiaryListEmptyView;
import com.example.rlagk.ks_project001.dummy.DummyContent;
import com.example.rlagk.ks_project001.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;

public class Fragment_Home extends BaseFragment{
    private static volatile Fragment_Home sInstance;
    public static final String TAG = Fragment_Home.class.getName();
    @BindView(R.id.gridView)
    GridView mGridView;
//    @BindView(R.id.horizontalImageRecyclerView)
//    RecyclerView mRecyclerView;

//    @BindView(R.id.txt_description)
//    TextView mDescriptionView;
    @BindView(R.id.cCoupleView)
    CoupleInfoView mCoupleInfoView;
    @BindView(R.id.cDiaryListEmptyView)
    DiaryListEmptyView mDiaryListEmptyView;

//    private AddDiaryImageAdapter mHorImageViewAdapter;
    private HomeDiaryListAdapter mHomeDiaryListAdapter = null;
    private ArrayList<HorImageItem> mHorImageViewList = null;
    private List<Contact> mContactList;

    public static Fragment_Home getInstance() {
        if (sInstance == null) {
            sInstance = new Fragment_Home();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_home);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        mContactList = null;
        mHorImageViewList = new ArrayList<>();
        initView();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mContactList = null;
        mHorImageViewList = null;
    }

    private void initView() {
        mContactList = DatabaseManager.getInstance().getDB().getContacts(100);
        int height = mGridView.getHeight();
        int width = mGridView.getRequestedColumnWidth();

        if (mContactList.size() <= 0 && !DummyContent.isDebug) {
            mGridView.setVisibility(View.GONE);
            mDiaryListEmptyView.setEmptyIconClickListener(mEmptyIconClickListener);
            mDiaryListEmptyView.setVisibility(View.VISIBLE);
            return;
        } else {
            mGridView.setVisibility(View.VISIBLE);
            mDiaryListEmptyView.setVisibility(View.GONE);
        }

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
        mGridView.setOnScrollListener(mGirdViewScrollListener);
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

    AbsListView.OnScrollListener mGirdViewScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE : // 스크롤이 정지되어 있는 상태야.
                    Log.d(TAG,"SCROLL_STATE_IDLE!");
                    mCoupleInfoView.showInfoView();
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL : // 스크롤이 터치되어 있을 때 상태
                    Log.d(TAG,"SCROLL_STATE_TOUCH_SCROLL!");
                    mCoupleInfoView.hideInfoView();
                    break;
                case AbsListView.OnScrollListener.SCROLL_STATE_FLING : // 스크롤이 움직이고 있을때 상태
                    Log.d(TAG,"SCROLL_STATE_FLING!");
                    mCoupleInfoView.hideInfoView();
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
            Log.d(TAG, "onScroll(...) arg1 : " + arg1 + ", arg2 : " + arg2 + ", arg3 : " + arg3);
        }
    };
    DiaryListEmptyView.EmptyIconClickListener mEmptyIconClickListener = new DiaryListEmptyView.EmptyIconClickListener() {
        @Override
        public void onClickEmptyIcon() {
            Log.d(TAG, "onClickEmptyIcon(...)");
            Utils.loadFragment(getFragmentManager(), Fragment_CreateDiary.getInstance(), R.id.fragment_container, false);
        }
    };
}