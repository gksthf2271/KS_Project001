package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rlagk.ks_project001.Activity.SettingsActivity;
import com.example.rlagk.ks_project001.CoupleInfoListener;
import com.example.rlagk.ks_project001.R;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoupleInfoView extends ConstraintLayout{

    private static final String TAG = CoupleInfoView.class.getName();

    @BindView(R.id.leftImage)
    ImageView mLeftImage;
    @BindView(R.id.rightImage)
    ImageView mRightImage;
    @BindView(R.id.txt_count)
    TextView mDateCount;

    CoupleInfoListener mCoupleInfoListener;

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

        mCoupleInfoListener = new CoupleInfoListener() {
            @Override
            public void onUpdateCoupleViwe(@Nullable Uri leftUri, @Nullable Uri rightUri, @Nullable String date) {
                if (date != null && !date.equals(""))
                    mDateCount.setText(date + "일째 연애중");
                loadImage(mLeftImage, leftUri);
                loadImage(mRightImage, rightUri);
            }

            @Override
            public void onClearCoupleView(boolean isRight, Uri uri) {

            }
        };
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

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

    public CoupleInfoListener getCoupleListener() {
        return mCoupleInfoListener;
    }

    private void loadImage(ImageView view, Uri uri){
        if (view == null || uri.toString().equals("") || uri == null) {
            return;
        }

        Glide.with(view.getContext())
                .load(uri)
                .placeholder(R.drawable.ic_default_face)
                .error(R.drawable.ic_default_face)
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
                .into(view);
    }
}
