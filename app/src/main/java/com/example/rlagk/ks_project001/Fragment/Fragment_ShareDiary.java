package com.example.rlagk.ks_project001.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.MainActivity;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DetailView;
import com.example.rlagk.ks_project001.View.HorizontalScrollView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;

/**
 * Created by rlagk on 2018-04-10.
 */

public class Fragment_ShareDiary extends Fragment {
    public static final String TAG = Fragment_ShareDiary.class.getName();
    @BindView(R.id.cDetailView)
    DetailView mDetailView;
    @BindView(R.id.cHorizontalScrollView)
    HorizontalScrollView mHorizontalScrollView;

    private DBHelperUtils mDBHelperUtils;
    public static long ID = 0;

    private String mTitle;
    private String mText;
    private int mImageResId;
    private String mDescription;
    private boolean mFlag = false;

    private static volatile Fragment_ShareDiary sInstance;

    private List<Uri> mImageUriList;

    public Fragment_ShareDiary(){
        mImageUriList = new ArrayList<>();
    }
    public static Fragment_ShareDiary newInstance() {
        return new Fragment_ShareDiary();
    }

    public static Fragment_ShareDiary getInstance(){
        if (sInstance == null){
            sInstance = new Fragment_ShareDiary();
        }
        return sInstance;
    }

    private DetailView.fabClickListener onFabClickListener = new DetailView.fabClickListener() {
        @Override
        public void clickFab() {
            Log.d(TAG, "saveBTNClick(...) ");
            ID++;

            EditText diaryTitle = mDetailView.findViewById(R.id.diaryTitle);
            EditText diaryDate = mDetailView.findViewById(R.id.diaryDate);
            EditText diaryText = mDetailView.findViewById(R.id.diaryText);
            Contact contact = new Contact();

            contact.setDate(diaryDate.getText().toString());
            contact.setTitle(diaryTitle.getText().toString());
            contact.setDescription(diaryText.getText().toString());
            contact.setId(String.valueOf(SystemClock.currentThreadTimeMillis()));

            if (mDBHelperUtils == null) {
                mDBHelperUtils = DatabaseManager.getInstance().getDB();
            }
            mDBHelperUtils.addContact(contact);

            diaryText.setText("");
            diaryDate.setText("");
            diaryTitle.setText("");

        }
    };

    private HorizontalScrollView.showGalleryListener showGalleryCallbackListener = new HorizontalScrollView.showGalleryListener() {
        @Override
        public void showGalleryCallback() {
            Log.d(TAG,"showGalleryCallback");
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
                            if(uriList != null && uriList.size() > 0){
                                mHorizontalScrollView.updateAddImage(false);
                                mImageUriList.addAll(uriList);
                            } else {
                                Log.d(TAG,"uriList is null");
                                return;
                            }
                        }
                    })
                    .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                        @Override
                        public void onImageSelected(Uri uri) {
                            Log.d(TAG, "onImageSeleted");
                            if(uri != null){
                                mHorizontalScrollView.updateAddImage(false);
                                mImageUriList.add(uri);
                            } else {
                                Log.d(TAG,"uri is null");
                                return;
                            }
                        }
                    })
                    .setOnErrorListener(new TedBottomPicker.OnErrorListener() {
                        @Override
                        public void onError(String message) {
                            Log.d(TAG,"onError");
                        }
                    })
                    .setPeekHeight(2000)
                    .setSelectMaxCount(5)
                    .setSelectedForeground(R.drawable.icon_selected)
                    .create();
            tedBottomPicker.show(getFragmentManager());
        }

    };

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sharediary, container, false);
        ButterKnife.bind(this,v);

        mHorizontalScrollView.showGalleryCallbackListener(showGalleryCallbackListener);
        if (getArguments() != null) {
            mTitle = (String) getArguments().get("Title");
            mText = (String) getArguments().get("Text");
            mImageResId = (Integer) getArguments().get("ImageResId");
            mDescription = (String) getArguments().get("Description");
        }
        init();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mDetailView != null) {
            EditText view = mDetailView.findViewById(R.id.diaryText);
            view.setText(mDescription);
        }
    }

    private void init(){
        mDBHelperUtils = DatabaseManager.getInstance().getDB();
        mDetailView.setListener(onFabClickListener);
    }

    private void loadFragment(@NonNull BaseFragment fragment) {
        Log.v(TAG, "loadFragment(...)  " + fragment);
        FragmentManager fragmentManager = getFragManager();
        if (fragmentManager == null) {
            Log.w(TAG, "Failed to load a fragment (null FragmentManager)");
            return;
        }

        String className = fragment.getClass().getName();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, className)
                .addToBackStack(className)
                .commit();
    }

    public FragmentManager getFragManager(){
        FragmentManager fragmentManager = MainActivity.getInstance().getFragmentManager();
        return fragmentManager;
    }
}
