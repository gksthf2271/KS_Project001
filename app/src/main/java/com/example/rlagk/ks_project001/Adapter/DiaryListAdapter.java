package com.example.rlagk.ks_project001.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListItem;
import com.example.rlagk.ks_project001.View.DiaryListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.ViewHolder>{

    private static final String TAG = DiaryListAdapter.class.getName();
    private Context mContext;
    private ArrayList<DiaryListItem> mItems;
    private int mLastPosition = -1;

    private final Object mDiaryItemListLock = new Object();
    private Listener mListener;

    public DiaryListAdapter(ArrayList<DiaryListItem> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @NonNull
    @Override
    public DiaryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_diary_list, parent, false);
        ViewHolder holder = new ViewHolder(v, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryListAdapter.ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder(...)");
        holder.mDiaryImg.setImageResource(mItems.get(position).getImage());
        holder.mDiaryText_date.setText(mItems.get(position).getDate());
        holder.mDiaryText_title.setText(mItems.get(position).getTitle());
        setAnimation(holder.mDiaryImg, position);
//        mViewHolder = holder;
//        mPosition = position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        private static final String TAG = DiaryListAdapter.TAG + ".ViewHolder";
        private final WeakReference<DiaryListAdapter> mWeakReference;
        private int mPosition;
        public ImageView mDiaryImg;
        public TextView mDiaryText_date;
        public TextView mDiaryText_title;

        ViewHolder(@NonNull View itemView, DiaryListAdapter diaryListAdapter) {
            super(itemView);
            mDiaryImg = (ImageView) itemView.findViewById(R.id.diaryImg);
            mDiaryText_date = (TextView) itemView.findViewById(R.id.diaryText_date);
            mDiaryText_title = (TextView) itemView.findViewById(R.id.diaryText_title);
            mWeakReference = new WeakReference<>(diaryListAdapter);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG,"onClick(...)");
            DiaryListAdapter optionAdapter = mWeakReference.get();
            if (v != null && optionAdapter !=null ) {
                mPosition = getAdapterPosition();
                optionAdapter.notifyItemClicked(mPosition);
            }
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            mLastPosition = position;
        }
    }

    private void notifyItemClicked(int position) {
        Log.v(TAG, "notifyOptionItemClicked(...)");
        if (mListener != null) {
            mListener.onOptionItemClicked(position);
        }
    }

    public interface Listener {
        void onOptionItemClicked(int position);
    }
}
