package com.example.rlagk.ks_project001.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class AddDiaryImageAdapter extends RecyclerView.Adapter<AddDiaryImageAdapter.ViewHolder>{

    private static final String TAG = AddDiaryImageAdapter.class.getName();
    private Context mContext;
    private ArrayList<HorImageItem> mItems;
    private int mLastPosition = -1;

    public AddDiaryImageAdapter(ArrayList<HorImageItem> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @NonNull
    @Override
    public AddDiaryImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hor_add_list, parent, false);
        ViewHolder holder = new ViewHolder(v, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddDiaryImageAdapter.ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder(...) ::: " + mItems.get(position).getUri());

        Glide.with(mContext)
                .load(mItems.get(position).getUri())
                .placeholder(R.drawable.close)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d(TAG,"onLoadFailed(...) GlideException!!! " + e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d(TAG,"onResourceReady(...)");
                        return false;
                    }
                })
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = AddDiaryImageAdapter.TAG + ".ViewHolder";
        private final WeakReference<AddDiaryImageAdapter> mWeakReference;
        private ImageView mImageView;

        ViewHolder(@NonNull View itemView, AddDiaryImageAdapter horImageViewAdapter) {
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
