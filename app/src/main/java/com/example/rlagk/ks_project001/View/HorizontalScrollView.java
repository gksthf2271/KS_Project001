package com.example.rlagk.ks_project001.View;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.rlagk.ks_project001.Adapter.AddDiaryImageAdapter;
import com.example.rlagk.ks_project001.Item.HorImageItem;
import com.example.rlagk.ks_project001.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HorizontalScrollView extends LinearLayout {
    private static final String TAG = HorizontalScrollView.class.getName();

    @BindView(R.id.horizontalImageRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.addImageBtn)
    ImageButton mImageButton;

    private AddDiaryImageAdapter mHorImageViewAdapter;
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
//        Fragment_CreateDiary.getInstance().setImageCallbackListener(mImageCallbackListener);
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

    public void updateAddImage(boolean visible, List<Uri> imageUri){
        if (visible) {
            if (mImageButton.getVisibility() == View.GONE) {
                mRecyclerView.setVisibility(View.GONE);
                mImageButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (mImageButton.getVisibility() == View.VISIBLE) {
                mImageButton.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                for (Uri uri : imageUri)
                mHorImageViewList.add(new HorImageItem(uri));
                updateView();
            }
        }
    }

    private void updateView(){
        mHorImageViewAdapter = new AddDiaryImageAdapter(mHorImageViewList, getContext());
        LinearLayoutManager lim = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(lim);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mHorImageViewAdapter);
    }

    public void clearView(){
        if (mImageButton.getVisibility() == View.GONE) {
            mHorImageViewList.clear();
            mRecyclerView.setVisibility(View.GONE);
            mImageButton.setVisibility(View.VISIBLE);
        }
    }

//    private Fragment_CreateDiary.showGalleryImageCallbackListener mImageCallbackListener = new Fragment_CreateDiary.showGalleryImageCallbackListener() {
//        @Override
//        public void showGalleryImageCallback(List<Uri> uriList) {
//            Log.d(TAG,"showGalleryImageCallback(...)");
//        }
//    };
}
