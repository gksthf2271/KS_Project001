package com.example.rlagk.ks_project001.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rlagk.ks_project001.DB.Contact;
import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.DetailView;
import com.example.rlagk.ks_project001.View.HorizontalScrollView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

/**
 * Created by rlagk on 2018-04-10.
 */

public class Fragment_CreateDiary extends Fragment {
    public static final String TAG = Fragment_CreateDiary.class.getName();
    @BindView(R.id.cDetailView)
    DetailView mDetailView;
    @BindView(R.id.cHorizontalScrollView)
    HorizontalScrollView mHorizontalScrollView;

    private DBHelperUtils mDBHelperUtils;
    public static long ID = 0;

    private String mTitle;
    private String mText;
    private String mDescription;
    private PopupWindow mPopupWindow ;

    private static volatile Fragment_CreateDiary sInstance;

    private Uri mImageUri;

    public Fragment_CreateDiary(){
        mImageUri = null;
    }
    public static Fragment_CreateDiary newInstance() {
        return new Fragment_CreateDiary();
    }

    public static Fragment_CreateDiary getInstance(){
        if (sInstance == null){
            sInstance = new Fragment_CreateDiary();
        }
        return sInstance;
    }

    private DetailView.fabClickListener onFabClickListener = new DetailView.fabClickListener() {
        @Override
        public void clickFab() {
            Log.d(TAG, "saveBTNClick(...)");

            EditText diaryTitle = mDetailView.findViewById(R.id.diaryTitle);
            TextView diaryDate = mDetailView.findViewById(R.id.diaryDate);
            EditText diaryText = mDetailView.findViewById(R.id.diaryText);

            if(diaryTitle.getText().toString().equals("") && diaryText.getText().toString().equals("")){
                showConflictPopup();
                return;
            }

            String inputText = diaryDate.getText().toString();
            Contact contact = new Contact();

            ID++;
            if(inputText.equals("") || inputText == null) {
                Date dt = new Date();
                SimpleDateFormat full_sdf = new SimpleDateFormat("yyyyMMdd");
                inputText = full_sdf.format(dt);
            }

            contact.setDate(inputText.replace("/",""));
            contact.setTitle(diaryTitle.getText().toString());
            contact.setDescription(diaryText.getText().toString());
            contact.setId(System.currentTimeMillis());
            contact.setImageUriList(mImageUri);

            if (mDBHelperUtils == null) {
                mDBHelperUtils = DatabaseManager.getInstance().getDB();
            }
            mDBHelperUtils.addContact(contact);

            diaryText.setText("");
            diaryDate.setText("");
            diaryTitle.setText("");
            mImageUri = null;
            mHorizontalScrollView.clearView();
        }
    };

    private HorizontalScrollView.showGalleryListener showGalleryCallbackListener = new HorizontalScrollView.showGalleryListener() {
        @Override
        public void showGalleryCallback() {
            Log.d(TAG,"showGalleryCallback");
            int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
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

            TedBottomPicker.with(getActivity())
                    .show(new TedBottomSheetDialogFragment.OnImageSelectedListener() {
                        @Override
                        public void onImageSelected(Uri uri) {
                            Log.d(TAG, "onImageSeleted");
                            if(uri != null){
                                List<Uri> uris = new ArrayList<>();
                                uris.add(uri);
                                mHorizontalScrollView.updateAddImage(false,uris);
                                mImageUri = uri;
                            } else {
                                Log.d(TAG,"uri is null");
                                return;
                            }
                        }
                    });

//            TedBottomPicker.with(getActivity())
//                    .setPeekHeight(1600)
//                    .showTitle(false)
//                    .setCompleteButtonText("Done")
//                    .setEmptySelectionText("No Select")
//                    .setSelectedUriList(mImageUri)
//                    .showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
//                        @Override
//                        public void onImagesSelected(List<Uri> uriList) {
//                            Log.d(TAG, "onImagesSelected");
//                            if(uriList != null && uriList.size() > 0){
//                                mHorizontalScrollView.updateAddImage(false, uriList);
//                                mImageUri.addAll(uriList);
//                            } else {
//                                Log.d(TAG,"uriList is null");
//                                return;
//                            }
//                        }
//                    });
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

        mHorizontalScrollView.addGalleryCallbackListener(showGalleryCallbackListener);
        if (getArguments() != null) {
            mTitle = (String) getArguments().get("Title");
            mText = (String) getArguments().get("Text");
            mImageUri = (Uri) getArguments().get("ImageUri");
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
        mImageUri = null;
        mDBHelperUtils = DatabaseManager.getInstance().getDB();
        mDetailView.setListener(onFabClickListener);
    }

    private void showConflictPopup() {
        View popupView = getLayoutInflater().inflate(R.layout.activity_main_finish, null);
        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        TextView textView = (TextView) popupView.findViewById(R.id.txt_title);
        textView.setText("내용을 입력하세요.");
        Button cancel = (Button) popupView.findViewById(R.id.Cancel);
        cancel.setVisibility(View.GONE);
        Button ok = (Button) popupView.findViewById(R.id.Ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }
}
