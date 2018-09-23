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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rlagk.ks_project001.DetailActivity;
import com.example.rlagk.ks_project001.Fragment.Fragment_Main;
import com.example.rlagk.ks_project001.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LockView extends RelativeLayout{
    private static final String TAG = LockView.class.getName();

    @BindView(R.id.pwdView)
    LinearLayout mPwdView;
    @BindView(R.id.lockBtn)
    Button mLockBtn;
    @BindView(R.id.lockEditText)
    EditText mEditText;

    private btnClickCallbackListener mListener;

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
    void onButtonClicked() {
        Log.d(TAG, "onButtonClicked(...)");
        String dummyPwd = "123";
        String inputData = mEditText.getText().toString();
        if (inputData.equals(dummyPwd)) {
            Fragment_Main.STATE_PWD = DragLayout.STATE_PWD_SUCCESS;
            onBtnClickCallback();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mPwdView.getWindowToken(), 0);

        } else {
            Fragment_Main.STATE_PWD = DragLayout.STATE_PWD_FAILED;
        }
    }

    public interface btnClickCallbackListener{
        void onBtnClickCallback();
    }

    void onBtnClickCallback(){
        if (mListener != null) {
            mListener.onBtnClickCallback();
        }
    }

    public void setBtnCallbacklistener(btnClickCallbackListener listener){
        mListener = listener;
    }
}
