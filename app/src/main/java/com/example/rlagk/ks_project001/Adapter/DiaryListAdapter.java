package com.example.rlagk.ks_project001.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rlagk.ks_project001.Item.DiaryListItem;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_diary_list2, parent, false);
        ViewHolder holder = new ViewHolder(v, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryListAdapter.ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder(...)");
//        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams)holder.itemView.getLayoutParams();
//        layoutParams.height = layoutParams.width;
//        holder.itemView.requestLayout();
//        String firstImage = Utils.firstUri(mItems.get(position).getImageUri());
        Uri firstImage = mItems.get(position).getImageUri();
        if(firstImage == null) {
            return;
        } else if (firstImage.toString().equals("")) {
            Log.d(TAG,"TEST,firstImage is blank");
//            Drawable myIcon = mContext.getResources().getDrawable( R.drawable.p3, null);
            Uri path = Uri.parse("android.resource://com.example.rlagk.ks_project001/" + R.drawable.p3);
            firstImage = path;
        }
        Glide.with(mContext)
                .load(firstImage)
                .placeholder(R.drawable.close)
                .error(R.drawable.img_error)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d(TAG, "onLoadFailed(...) GlideException!!! " + e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d(TAG, "onResourceReady(...)");
                        return false;
                    }
                })
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
