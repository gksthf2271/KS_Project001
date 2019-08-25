package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.rlagk.ks_project001.R;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiaryListEmptyView extends ConstraintLayout {
    private static final String TAG = DiaryListEmptyView.class.getName();
    private EmptyIconClickListener mEmptyIconClickListener;

    @BindView(R.id.img_icon)
    ImageView mIcon;

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
        startAnimation();
    }

    @OnClick(R.id.img_icon)
    void onIconClick(View view){
        Log.d(TAG,"OnIconClick!!! ::: " + view);
        mEmptyIconClickListener.onClickEmptyIcon();
    }
    public interface EmptyIconClickListener {
        void onClickEmptyIcon();
    }

    public void setEmptyIconClickListener(EmptyIconClickListener listener) {
        mEmptyIconClickListener = listener;
    }

    private void startAnimation(){
        Log.d(TAG,"startAnimation!!!");
        RotateAnimation ra1 = new RotateAnimation(0, 15, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra1.setDuration(500);
        ra1.setFillAfter(true);
        mIcon.startAnimation(ra1);

        RotateAnimation ra2 = new RotateAnimation(15, -15, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra2.setDuration(1000);
        ra2.setFillAfter(true);

        RotateAnimation ra3 = new RotateAnimation(-15, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra3.setDuration(500);
        ra3.setFillAfter(true);

        ra1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIcon.startAnimation(ra2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ra2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIcon.startAnimation(ra3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
