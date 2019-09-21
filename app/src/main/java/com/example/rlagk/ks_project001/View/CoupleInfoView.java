package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.rlagk.ks_project001.Activity.MainActivity;
import com.example.rlagk.ks_project001.Activity.SettingsActivity;
import com.example.rlagk.ks_project001.R;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoupleInfoView extends ConstraintLayout {
    private static final String TAG = CoupleInfoView.class.getName();

    public CoupleInfoView(Context context) {
        super(context);
    }

    public CoupleInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoupleInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Log.d(TAG,"initView(...)");
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_couple_info, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

//    @OnClick(R.id.leftImage)
//    void onLeftImageClick(View view){
//        Log.d(TAG,"onLeftImageClick(...)");
//    }
//    @OnClick(R.id.rightImage)
//    void onRightImageClick(View view){
//        Log.d(TAG,"onRightImageClick(...)");
//    }

    @OnClick({R.id.group_couple_info, R.id.rightImage, R.id.leftImage, R.id.txt_count})
    void onClickCoupleInfoBar(View view){
        Log.d(TAG,"onClickCoupleInfoBar(...) ::: " + view.getId());
        Intent intent = new Intent(getContext(), SettingsActivity.class);
        getContext().startActivity(intent);
    }
    @OnClick(R.id.txt_count)
    void onDateCountImageClick(View view){
        Log.d(TAG,"onDateCountImageClick(...)");
    }

    public void hideInfoView() {
        this.setVisibility(View.GONE);
    }

    public void showInfoView() {
        this.setVisibility(View.VISIBLE);
    }
}
