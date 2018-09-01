package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rlagk.ks_project001.DetailActivity;
import com.example.rlagk.ks_project001.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LockView extends LinearLayout{
    private static final String TAG = DetailView.class.getName();

    @BindView(R.id.pwdView)
    LinearLayout mPwdView;
    @BindView(R.id.lockBtn)
    Button mLockBtn;
    @BindView(R.id.lockEditText)
    EditText mEditText;
    @BindView(R.id.successView)
    ImageView mSuccessView;


    public LockView(Context context) {
        this(context, null, 0);
    }
    public LockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_lock, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG,"onFinishInflate(...)");
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

    @OnClick(R.id.lockBtn)
    void onButtonClicked(){
        Log.d(TAG,"onButtonClicked(...)");
        String dummyPwd = "123";
        if(mEditText.getText().equals(dummyPwd)){
            mPwdView.setVisibility(View.GONE);
            mSuccessView.setVisibility(View.VISIBLE);
        }
    }

}
