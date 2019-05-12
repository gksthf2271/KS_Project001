package com.example.rlagk.ks_project001.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class HorImageViewAdapter extends RecyclerView.Adapter<HorImageViewAdapter.ViewHolder>{

    private static final String TAG = HorImageViewAdapter.class.getName();
    private Context mContext;
    private ArrayList<HorImageItem> mItems;
    private int mLastPosition = -1;

    public HorImageViewAdapter(ArrayList<HorImageItem> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @NonNull
    @Override
    public HorImageViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hor_add_list, parent, false);
        ViewHolder holder = new ViewHolder(v, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HorImageViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder(...)");
        Glide.with(mContext)
                .load(mItems.get(position).getUri())
                .centerCrop()
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = HorImageViewAdapter.TAG + ".ViewHolder";
        private final WeakReference<HorImageViewAdapter> mWeakReference;
        private ImageView mImageView;

        ViewHolder(@NonNull View itemView, HorImageViewAdapter horImageViewAdapter) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.addView);
            mWeakReference = new WeakReference<>(horImageViewAdapter);
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
