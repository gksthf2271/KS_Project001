package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.rlagk.ks_project001.R;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.ButterKnife;

public class DiaryListEmptyView extends ConstraintLayout {
    private static final String TAG = DiaryListEmptyView.class.getName();

    public DiaryListEmptyView(Context context) {
        super(context);
    }

    public DiaryListEmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DiaryListEmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Log.d(TAG,"initView(...)");
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_diary_list_empty, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }
}
