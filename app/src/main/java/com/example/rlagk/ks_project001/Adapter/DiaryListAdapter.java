package com.example.rlagk.ks_project001.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.ViewHolder>{

    private static final String TAG = DiaryListAdapter.class.getName();
    private Context mContext;
    private ArrayList<DiaryListItem> mItems;
    private int mLastPosition = -1;

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
//        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams)holder.itemView.getLayoutParams();
//        layoutParams.height = layoutParams.width;
//        holder.itemView.requestLayout();
        String firstImage = Utils.firstUri(mItems.get(position).getImageUri());
        if(firstImage == null) {
            return;
        }
        Glide.with(mContext)
                .load(firstImage)
                .centerCrop()
                .into(holder.mDiaryBackground);

        holder.mDiaryText_date.setText(mItems.get(position).getDate());
        holder.mDiaryText_title.setText(mItems.get(position).getTitle());
        holder.mDiaryText_description.setText(mItems.get(position).getDescription());
        setAnimation(holder.mDiaryBackground, position);
//        mViewHolder = holder;
//        mPosition = position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = DiaryListAdapter.TAG + ".ViewHolder";
        private final WeakReference<DiaryListAdapter> mWeakReference;
        private int mPosition;
        public ImageView mDiaryBackground;
        public TextView mDiaryText_date;
        public TextView mDiaryText_title;
        public TextView mDiaryText_description;

        ViewHolder(@NonNull View itemView, DiaryListAdapter diaryListAdapter) {
            super(itemView);
            mDiaryBackground = (ImageView) itemView.findViewById(R.id.diaryImg);
            mDiaryText_date = (TextView) itemView.findViewById(R.id.diaryText_date);
            mDiaryText_title = (TextView) itemView.findViewById(R.id.diaryText_title);
            mDiaryText_description = (TextView) itemView.findViewById(R.id.diaryText_description);
            mWeakReference = new WeakReference<>(diaryListAdapter);
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            mLastPosition = position;
        }
    }
}
