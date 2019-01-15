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
//            int permissionCheck = ContextCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            if(permissionCheck == PackageManager.PERMISSION_DENIED){
//                Log.d(TAG,"Permission check");
//                TedPermission.with(getContext())
//                        .setPermissionListener(permissionlistener)
//                        .setRationaleMessage("갤러리 접근 권한 요청")
//                        .setDeniedMessage("접근 권한 취소\n[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
//                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        .check();
//                return;
//            }
//            TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getContext())
//                    .setOnMultiImageSelectedListener(new TedBottomPicker.OnMultiImageSelectedListener() {
//                        @Override
//                        public void onImagesSelected(ArrayList<Uri> uriList) {
//                            Log.d(TAG, "onImagesSelected");
//                        }
//                    })
//                    .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
//                        @Override
//                        public void onImageSelected(Uri uri) {
//                            Log.d(TAG, "onImageSeleted");
//                        }
//                    })
//                    .setPeekHeight(2000)
//                    .setSelectMaxCount(10)
//                    .setSelectedForeground(R.drawable.icon_selected)
//                    .create();
//            tedBottomPicker.show(getFragmentManager());
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

//    PermissionListener permissionlistener = new PermissionListener() {
//        @Override
//        public void onPermissionGranted() {
//            Toast.makeText(getContext(), "권한 허가", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
//            Toast.makeText(getContext(), "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//        }
//    };
}
