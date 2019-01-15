package com.example.rlagk.ks_project001.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.example.rlagk.ks_project001.MainActivity;
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
        mImageButton.setVisibility(View.GONE);
        showGallery();
    }

    private void showGallery() {
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            Log.d(TAG,"Permission check");
            TedPermission.with(getContext())
                    .setPermissionListener(permissionlistener)
                    .setRationaleMessage("갤러리 접근 권한 요청")
                    .setDeniedMessage("접근 권한 취소\n[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .check();
            return;
        }
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getContext())
                .setOnMultiImageSelectedListener(new TedBottomPicker.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(ArrayList<Uri> uriList) {
                        Log.d(TAG, "onImagesSelected");
                    }
                })
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        Log.d(TAG, "onImageSeleted");
                    }
                })
                .setPeekHeight(2000)
                .setSelectMaxCount(5)
                .setSelectedForeground(R.drawable.icon_selected)
                .create();
//        tedBottomPicker.show((MainActivity.getInstance()).getSupportFragmentManager());
        tedBottomPicker.show(Fragment_ShareDiary.getInstance().getFragManager());
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getContext(), "권한 허가", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getContext(), "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
