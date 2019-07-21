package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rlagk.ks_project001.Adapter.DiaryListAdapter;
import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.Fragment.Fragment_DiaryList;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DiaryListView extends LinearLayout {

    private static final String TAG = DiaryListView.class.getName();

    @BindView(R.id.recyclerview_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.diaryText_title)
    TextView mTitleView;
    
    private DiaryListAdapter mDiaryListAdapter;
    private ArrayList<DiaryListItem> mDiaryListViewArrayList;

    private ListViewListener mSelectListener;


    public DiaryListView(Context context) {
        this(context, null, 0);
    }
    public DiaryListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiaryListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_diary_list, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG,"onFinishInflate(...)");

        initView();
        updateListView();

        mDiaryListAdapter = new DiaryListAdapter(mDiaryListViewArrayList, getContext());
        LinearLayoutManager lim = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(lim);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mDiaryListAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "onItemClick");
                long id = mDiaryListViewArrayList.get(position).getId();
                Uri imageUri = mDiaryListViewArrayList.get(position).getImageUri();
                String title = mDiaryListViewArrayList.get(position).getTitle();
                String date = mDiaryListViewArrayList.get(position).getDate();
                String description = mDiaryListViewArrayList.get(position).getDescription();
                DiaryListItem item = new DiaryListItem(id, imageUri, title, description, date);
                notifyItemSelected(view, item, position);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Log.d(TAG,"onLongItemClick(...)");
            }
        }));
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

    public void setDiaryListener(@Nullable ListViewListener l) {
        mSelectListener = l;
    }

    public interface ListViewListener {
        void onUpdateItemList(String selectResult);
        void onItemClick(View v, int position, DiaryListItem item);
    }

    private void notifyItemSelected(View view, @NonNull DiaryListItem diaryItem, int position) {
        Log.v(TAG, "notifyItemSelected(...)  position=" + position);
        if (mSelectListener != null) {
            mSelectListener.onItemClick(view, position, diaryItem);
        }
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }

    private void updateListView() {
        Log.d(TAG,"updateListView");
        mDiaryListViewArrayList = new ArrayList<>();
        List<Contact> itemList = new ArrayList<>();
        String searchDate = Fragment_DiaryList.getInstance().getSearchDate();
        if (searchDate == null) {
            Log.d(TAG,"searchDate is null");

            return;
        }
        mTitleView.setText(searchDate);
        DBHelperUtils dbHelperUtils = DatabaseManager.getInstance().getDB();
//        if (dbHelperUtils == null) {
//            dbHelperUtils = DatabaseManager.getInstance().getDB();
////            dbHelperUtils = new DBHelperUtils(getContext());
//        }
        itemList.addAll(dbHelperUtils.getContacts(searchDate));

        for (int i = 0; i < itemList.size(); i++) {
            Log.d(TAG,"item ::: " + itemList.get(i).toString());
            mDiaryListViewArrayList.add(new DiaryListItem(itemList.get(i).getId(), itemList.get(i).getImageUriList(), itemList.get(i).getTitle(), itemList.get(i).getDescription(), itemList.get(i).getDate()));
        }
        mDiaryListAdapter = new DiaryListAdapter(mDiaryListViewArrayList, getContext());
        mRecyclerView.setAdapter(mDiaryListAdapter);
    }
}
