package com.example.rlagk.ks_project001.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rlagk.ks_project001.DetailActivity;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.AspectRatioCardView;
import com.example.rlagk.ks_project001.View.DragLayout;
import com.example.rlagk.ks_project001.View.LockView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Main extends Fragment implements DragLayout.GotoDetailListener, DragLayout.OnTouchListener{
    public static final String TAG = "Fragment_Main";

    @BindView(R.id.aspectRatioCardView)
    AspectRatioCardView mAspectRatioCardView;
    @BindView(R.id.drag_layout)
    DragLayout mDragLayout;

    private LockView mLockView;
    private TextView address1;
    private RatingBar ratingBar;
    private ImageView mLove;

    public static int STATE_PWD = 0;

    private static volatile Fragment_Main sInstance;

    public static Fragment_Main newInstance() {
        return new Fragment_Main();
    }

    public static Fragment_Main getInstance() {
        if (sInstance == null) {
            synchronized (Fragment_Main.class) {
                if (sInstance == null) {
                    sInstance = new Fragment_Main();
                }
            }
        }
        return sInstance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this,rootView);
        mLockView = mAspectRatioCardView.findViewById(R.id.lockView);
        mLove = mAspectRatioCardView.findViewById(R.id.love);
        address1 = mDragLayout.findViewById(R.id.address4);
        ratingBar = mDragLayout.findViewById(R.id.rating);

        mLockView.setBtnCallbacklistener(lockViewBtnCallback);
        mLove.setVisibility(View.GONE);
        mLockView.setVisibility(View.VISIBLE);

        mDragLayout.setGotoDetailListener(this);
        return rootView;
    }

    @Override
    public void gotoDetail() {
        Log.d(TAG,"gotoDetail(...)");
        Activity activity = (Activity) getContext();
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                new Pair(address1, DetailActivity.ADDRESS1_TRANSITION_NAME),
                new Pair(ratingBar, DetailActivity.RATINGBAR_TRANSITION_NAME)
        );
        Intent intent = new Intent(activity, DetailActivity.class);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private LockView.btnClickCallbackListener lockViewBtnCallback = new LockView.btnClickCallbackListener(){
        @Override
        public void onBtnClickCallback() {
            mLove.setVisibility(View.VISIBLE);
            mLockView.setVisibility(View.GONE);
        }
    };


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG,"view : " + v + "\n event : " + event);
        if(STATE_PWD == DragLayout.STATE_PWD_FAILED){
            Log.d(TAG,"PWD not success!");
            return true;
        }
        return false;
    }
}
