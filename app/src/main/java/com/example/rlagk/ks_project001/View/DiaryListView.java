package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.rlagk.ks_project001.Adapter.DiaryListAdapter;
import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiaryListView extends LinearLayout{

    private static final String TAG = DiaryListView.class.getName();

    @BindView(R.id.recyclerview_list)
    RecyclerView mRecyclerView;
    
    private DiaryListAdapter mDiaryListAdapter;
    private ArrayList<DiaryListItem> diaryListViewArrayList;

    public DiaryListView(Context context) {
        this(context, null, 0);
    }
    public DiaryListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiaryListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG,"onFinishInflate(...)");
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_diary_list, this);
        ButterKnife.bind(this);

        diaryListViewArrayList = new ArrayList<>();
        List<Contact> itemList = new ArrayList<>();
        DBHelperUtils dbHelperUtils = DatabaseManager.getInstance().getDB();
        itemList.addAll(dbHelperUtils.getAllContacts());

        for (int i = 0; i < itemList.size(); i++) {
            diaryListViewArrayList.add(new DiaryListItem(R.drawable.image5, itemList.get(i).getDate(), itemList.get(i).getTitle()));
        }

        mDiaryListAdapter = new DiaryListAdapter(diaryListViewArrayList, getContext());
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lim);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mDiaryListAdapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getContext(),new LinearLayoutManager(getContext()).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG,"onMeasure(...)");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(TAG,"onFocusChanged(...)");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG,"onAttachedToWindow(...)");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG,"onDetachedFromWindow(...)");
    }
}
