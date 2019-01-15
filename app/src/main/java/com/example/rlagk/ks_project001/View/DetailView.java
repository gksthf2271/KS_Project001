package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.example.rlagk.ks_project001.DetailActivity;
import com.example.rlagk.ks_project001.R;

import org.w3c.dom.Attr;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class DetailView extends LinearLayout {

    private static final String TAG = DetailView.class.getName();

    @BindView(R.id.diaryText)
    EditText mDiaryText;

    public DetailView(Context context) {
        this(context, null, 0);
    }
    public DetailView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_detail, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Window window = ((DetailActivity) getContext()).getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        String imageUrl = ((DetailActivity) getContext()).getIntent().getStringExtra(DetailActivity.EXTRA_IMAGE_URL);
//        ImageLoader.getInstance().displayImage(imageUrl, imageView);

        ViewCompat.setTransitionName(mDiaryText,DetailActivity.ADDRESS1_TRANSITION_NAME);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG,"onMeasure(...)");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(TAG,"onFocusChanged(...)");
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mDiaryText.getWindowToken(),0);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG,"onAttachedToWindow(...)");

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG,"onDetachedFromWindow(...)");
    }

    public String getDescription(){
        return mDiaryText.getText().toString();
    }
}
