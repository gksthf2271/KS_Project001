package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.rlagk.ks_project001.R;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuView extends LinearLayout {
    private static final String TAG = MenuView.class.getName();

    private MenuItemClickListener mListener;

    private Context mContext;

    public MenuView(Context context) {
        super(context);
    }

    public MenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        Log.d(TAG, "initView(...)");
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_menu, this);
        ButterKnife.bind(this);
        mContext = inflater.getContext();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    @OnClick(R.id.menu_home)
    void onClickHome(View v) {
        Log.d(TAG, "onClickHome(...)");
        mListener.onClickHome();
    }

    @OnClick(R.id.menu_write)
    void onClickWrite(View v) {
        Log.d(TAG, "onClickWrite(...)");
        mListener.onClickWrite();
    }

    @OnClick(R.id.menu_list)
    void onClickList(View v) {
        Log.d(TAG, "onClickList(...)");
        mListener.onClickList();
    }

//    @OnClick(R.id.menu_setting)
//    void onClickSetting(View v) {
//        Log.d(TAG, "onClickSetting(...)");
//        mListener.onClickSetting();
//    }

    public void setMenuClickListener(MenuItemClickListener listener) {
        mListener = listener;
    }


    public interface MenuItemClickListener {
        void onClickHome();

        void onClickWrite();

        void onClickList();

        void onClickSetting();
    }
}
