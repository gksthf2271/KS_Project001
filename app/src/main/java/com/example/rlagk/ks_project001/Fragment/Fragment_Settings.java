package com.example.rlagk.ks_project001.Fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.utils.SharedPreferencesUtils;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;

public class Fragment_Settings extends BaseFragment {
    private static final String TAG = Fragment_Settings.class.getName();

    private static volatile Fragment_Settings sInstance;

    @BindView(R.id.txt_dateSelector)
    TextView mTextDateSelector;

    @BindView(R.id.txt_count)
    TextView mTxtCount;

    @BindView(R.id.txt_currentDate)
    TextView mCurrentDateView;

    @BindView(R.id.addRightImage)
    ImageView mAddRightImage;

    @BindView(R.id.addLeftImage)
    ImageView mAddLeftImage;

    public static final String KEY_COUPLE_INFO = "coupleInfo";
    public static final String KEY_COUPLE_DATE = "coupleDate";
    public static final String KEY_LEFT_IMAGE_URI = "coupleLeftImageUri";
    public static final String KEY_RIGHT_IMAGE_URI = "coupleRightImageUri";

    Date mDate = null;
    String mCurrentDateString = null;
    private DatePickerDialog mDatePickerDialog;
    private JSONObject mCoupleInfo = new JSONObject();
    private JSONObject mCoupleInfoDataObject = new JSONObject();

    private Uri mRightImageUri = null;
    private Uri mLeftImageUri = null;
    private static final long mValue = 24*60*60*1000;

    public Fragment_Settings() {}

    public static Fragment_Settings getInstance() {
        if (sInstance == null) {
            sInstance = new Fragment_Settings();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView(...)");
        View view = inflateAndBind(inflater, container, R.layout.fragment_settings);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDate = new Date();
        mCurrentDateString = new SimpleDateFormat("yyyy.MM.dd").format(mDate);
        Log.d(TAG,"CurrentDate is " + mCurrentDateString);
        mCurrentDateView.setText(mCurrentDateString);

        initView();
    }

    private void initView(){
        String coupleInfo = SharedPreferencesUtils.getPref(getContext(), SharedPreferencesUtils.PREF_FILE_NAME, SharedPreferencesUtils.PREF_KEY_COUPLE_INFO);
        if (coupleInfo == null) return;
        JSONObject readCoupleInfo = null;
        try {
            readCoupleInfo = new JSONObject(coupleInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String date = null;
        String leftImageUri = null;
        String rightImageUri = null;
        try {
            readCoupleInfo = readCoupleInfo.getJSONObject(KEY_COUPLE_INFO);
            date = readCoupleInfo.get(KEY_COUPLE_DATE).toString();
            leftImageUri = readCoupleInfo.get(KEY_LEFT_IMAGE_URI).toString();
            rightImageUri = readCoupleInfo.get(KEY_RIGHT_IMAGE_URI).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (date == null || date.equals("")) {
            mTextDateSelector.setText(mCurrentDateString);
        } else {
            mTextDateSelector.setText(date);
            mTxtCount.setText(updateDate(date));
        }

        if (leftImageUri != null) {
            loadImage(mAddLeftImage, Uri.parse(leftImageUri));
            mAddLeftImage.setVisibility(View.GONE);
        } else {
            mAddLeftImage.setVisibility(View.VISIBLE);
        }
        if (rightImageUri != null) {
            loadImage(mAddLeftImage, Uri.parse(rightImageUri));
            mAddRightImage.setVisibility(View.GONE);
        } else {
            mAddRightImage.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.addViewGroup1, R.id.addViewGroup2, R.id.addLeftImage, R.id.addRightImage})
    public void onClickAddImage(View view){
        Log.d(TAG,"onClickAddImage(...) ::: " + view.getId());
        switch (view.getId()){
            case R.id.addViewGroup2:
            case R.id.addRightImage:
                selectImage(true);
                break;
            case R.id.addViewGroup1:
            case R.id.addLeftImage:
                selectImage(false);
                break;
        }
    }

    @OnClick({R.id.txt_dateSelector, R.id.txt_count})
    public void onClickDateSelector(View view) {
        Log.d(TAG,"onClickDateSelector(...)");
        showDatePicker();
    }

    @OnClick(R.id.ok)
    public void onClickOk(View view){
        Log.d(TAG,"onClickSave");
        try {
            mCoupleInfo.put(KEY_COUPLE_DATE, mTextDateSelector.getText());
            mCoupleInfo.put(KEY_LEFT_IMAGE_URI, mLeftImageUri);
            mCoupleInfo.put(KEY_RIGHT_IMAGE_URI, mRightImageUri);
            mCoupleInfoDataObject.put(KEY_COUPLE_INFO, mCoupleInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferencesUtils.savePref(getContext(), SharedPreferencesUtils.PREF_FILE_NAME,
                SharedPreferencesUtils.PREF_KEY_COUPLE_INFO, mCoupleInfoDataObject.toString());
        getActivity().finish();
    }

    @OnClick(R.id.cancel)
    public void onClickCancel(View view){
        Log.d(TAG,"onClickCancel");
        getActivity().finish();
    }

    private String updateDate(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long longResultmDate = mDate.getTime() - date.getTime();
        return String.valueOf(longResultmDate / mValue + 1);
    }

    DatePickerDialog.OnDateSetListener mDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month++;
            String time = Integer.toString(year) + "." + Integer.toString(month) + "." + Integer.toString(dayOfMonth);
            Log.d(TAG,"date ::: " + time);
            mDatePickerDialog = null;
            mTextDateSelector.setText(time);
            mTxtCount.setText(updateDate(time));
        }
    };

    DatePickerDialog.OnCancelListener mDatePickerCancelListener = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            Log.d(TAG,"onCancel");
            mDatePickerDialog = null;
        }
    };

    private void showDatePicker() {
        Log.d(TAG,"showDatePicker ::: " + mDatePickerDialog );
        Calendar now = Calendar.getInstance();
        if (mDatePickerDialog != null) {
            Log.d(TAG,"Duplicated Dialog!");
            return;
        }
        mDatePickerDialog = new DatePickerDialog(
                getContext(),
                R.style.DialogTheme,
                mDatePickerListener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        mDatePickerDialog.setOnCancelListener(mDatePickerCancelListener);
        mDatePickerDialog.show();
    }

    private void selectImage(boolean isRight){
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
                            if (isRight) {
                                loadImage(mAddRightImage, uri);
                            } else {
                                loadImage(mAddLeftImage, uri);
                            }
                        } else {
                            Log.d(TAG,"uri is null");
                            return;
                        }
                    }
                });
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

    private void loadImage(ImageView view, Uri uri){
        if (view == null || uri == null) {
            return;
        }

        Glide.with(view.getContext())
                .load(uri)
                .placeholder(R.drawable.close)
                .error(R.drawable.img_error)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d(TAG, "onLoadFailed(...) GlideException!!! " + e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d(TAG, "onResourceReady(...)");
                        if (view == mAddRightImage) {
                            mRightImageUri = uri;
                        } else {
                            mLeftImageUri = uri;
                        }
                        return false;
                    }
                })
                .into(view);
    }
}
