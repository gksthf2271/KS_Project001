package com.example.rlagk.ks_project001.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DiaryListItem;
import com.example.rlagk.ks_project001.View.DiaryListView;

import java.util.ArrayList;
import java.util.List;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.ViewHolder> {

    private static final String TAG = DiaryListAdapter.class.getName();
    private Context mContext;
    private ArrayList mItems;

    public DiaryListAdapter(ArrayList items, Context context) {
        mItems = items;
        mContext = context;
    }

    @NonNull
    @Override
    public DiaryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_diary_list, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryListAdapter.ViewHolder holder, int position) {
        holder.mDiaryText_date.setText((Integer) mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryListAdapter.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = DiaryListAdapter.TAG + ".ViewHolder";

        public ImageView mDiaryImg;
        public TextView mDiaryText_date;
        public TextView mDiaryText_title;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDiaryImg = itemView.findViewById(R.id.diaryImg);
            mDiaryText_date = itemView.findViewById(R.id.diaryText_date);
            mDiaryText_title = itemView.findViewById(R.id.diaryText_title);
        }

    }

}
