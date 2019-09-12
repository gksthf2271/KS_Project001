package com.example.rlagk.ks_project001.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
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
    private static final int MSG_RETRY = 0;

    private static final int mGridViewHeight = 500;


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
            initView(convertView, position);
        }
        return convertView;
    }

    private void initView(View convertView, int position) {
        ConstraintLayout rootLayout = (ConstraintLayout) convertView.findViewById(R.id.root_layout);
        ConstraintLayout InfoGroupParentLayout = (ConstraintLayout) convertView.findViewById(R.id.group_parent);
        ImageView iv = (ImageView) convertView.findViewById(R.id.diaryImg);
        TextView diaryDate = (TextView) convertView.findViewById(R.id.diaryText_date);
        TextView diaryTitle = (TextView) convertView.findViewById(R.id.diaryText_title);

        rootLayout.setLayoutParams(new ConstraintLayout.LayoutParams(mGridWidth+100,mGridViewHeight));

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(mGridWidth+100, mGridViewHeight/3);
        layoutParams.bottomToBottom = rootLayout.getId();
        InfoGroupParentLayout.setLayoutParams(layoutParams);

        Glide.with(convertView.getContext())
                .load(mHorImageItemList.get(position).getUri())
                .placeholder(R.drawable.close)
                .error(R.drawable.img_error)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d(TAG, "onLoadFailed(...) GlideException!!! " + e);
                        mHandler.sendEmptyMessageDelayed(MSG_RETRY,1000);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d(TAG, "onResourceReady(...)");
                        return false;
                    }
                })
                .into(iv);
        String date = mHorImageItemList.get(position).getDate();
        if (date.equals("") || date.length() < 8) {
            diaryDate.setText(date);
        } else {
            date = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6,date.length());
            diaryDate.setText(date);
        }
        diaryTitle.setText(mHorImageItemList.get(position).getTitle());
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG,"MSG : " + msg);
            switch (msg.what) {
                case MSG_RETRY:
                    Log.d(TAG,"Retry!");
                    break;
                default:
                    break;
            }
        }
    };
}