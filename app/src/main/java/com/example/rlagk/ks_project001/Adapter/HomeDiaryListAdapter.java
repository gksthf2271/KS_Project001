package com.example.rlagk.ks_project001.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeDiaryListAdapter extends BaseAdapter {
    public static final String TAG = HomeDiaryListAdapter.class.getName();
    private Context mContext;
    private int mLayout;
    private List<HorImageItem> mHorImageItemList = null;
    private LayoutInflater mLayoutInflater;
    private int mGridHeight;
    private int mGridWidth;

    public HomeDiaryListAdapter(Context context, int layout, List<HorImageItem> horImageItemList, int gridViewWidth, int gridViewHeight) {
        this.mContext = context;
        this.mLayout = layout;
        this.mHorImageItemList = horImageItemList;
        this.mGridWidth = gridViewWidth;
        this.mGridHeight = gridViewHeight;
        mLayoutInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mHorImageItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHorImageItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Log.d(TAG,"convertView is null!");
            convertView = mLayoutInflater.inflate(mLayout, null);
        }
        ConstraintLayout rootLayout = (ConstraintLayout) convertView.findViewById(R.id.root_layout);
        ImageView iv = (ImageView) convertView.findViewById(R.id.diaryImg);
        TextView diaryDate = (TextView) convertView.findViewById(R.id.diaryText_date);
        TextView diaryTitle = (TextView) convertView.findViewById(R.id.diaryText_title);

        rootLayout.setLayoutParams(new ConstraintLayout.LayoutParams(mGridWidth - 100 ,500));
        Glide.with(convertView.getContext())
                .load(mHorImageItemList.get(position).getUri())
                .placeholder(R.drawable.close)
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
                .into(iv);
        diaryDate.setText(mHorImageItemList.get(position).getDate());
        diaryTitle.setText(mHorImageItemList.get(position).getTitle());
        return convertView;
    }
}