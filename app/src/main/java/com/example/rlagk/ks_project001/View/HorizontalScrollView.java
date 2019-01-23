package com.example.rlagk.ks_project001.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rlagk.ks_project001.Adapter.HorImageViewAdapter;
import com.example.rlagk.ks_project001.Fragment.Fragment_ShareDiary;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;

public class HorizontalScrollView extends LinearLayout {
    private static final String TAG = HorizontalScrollView.class.getName();

    @BindView(R.id.horizontalImageRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.addImageBtn)
    ImageButton mImageButton;

    private HorImageViewAdapter mHorImageViewAdapter;
    private ArrayList<HorImageItem> mHorImageViewList;

    public android.widget.HorizontalScrollView mHorizontalScrollView;

    private showGalleryListener mListener;

    public HorizontalScrollView(Context context) {
        super(context);
    }

    public HorizontalScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_horizontal_scroll, this);
        ButterKnife.bind(this);
        mImageButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initView();
        mHorImageViewList = new ArrayList<>();
    }

    @OnClick(R.id.addImageBtn)
    void addImageBtnClick(){
        Log.d(TAG,"addImageBtnClick(...)");
        showGallery();
    }

    private void showGallery() {
        Log.d(TAG,"showGallery(...)");
        showGalleryCallback();
    }


    public interface showGalleryListener{
        void showGalleryCallback();
    }

    private void showGalleryCallback(){
        if (mListener != null) {
            mListener.showGalleryCallback();
        }
    }

    public void showGalleryCallbackListener(showGalleryListener listener) {
        mListener = listener;
    }

    public void updateAddImage(boolean visible){
        if (visible) {
            if (mImageButton.getVisibility() == View.GONE) {
                mImageButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (mImageButton.getVisibility() == View.VISIBLE) {
                mImageButton.setVisibility(View.GONE);
            }
        }
    }
}
