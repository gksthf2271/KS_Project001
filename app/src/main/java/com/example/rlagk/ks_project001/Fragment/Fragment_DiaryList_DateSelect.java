package com.example.rlagk.ks_project001.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.rlagk.ks_project001.R;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Fragment_DiaryList_DateSelect extends BaseFragment {
    private static volatile Fragment_DiaryList_DateSelect sInstance;
    public static final String TAG = Fragment_DiaryList_DateSelect.class.getName();

    private DatePickerDialog mDatePickerDialog;
    private ClickListener mClickListener;

    public static Fragment_DiaryList_DateSelect getInstance(){
        if (sInstance == null){
            sInstance = new Fragment_DiaryList_DateSelect();
        }
        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_select_date);
        return rootView;
    }

    @Override
    public void onStart() {
        Log.d(TAG,"onStart");
        super.onStart();
        showDatePicker();
    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume");
        super.onResume();
        showDatePicker();
    }

    DatePickerDialog.OnDateSetListener mDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month++;
            String time = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
            Log.d(TAG,"date ::: " + time);
            mClickListener.onClickDate(time);
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
                mDatePickerListener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        mDatePickerDialog.show();
    }

    public void setClickListener(ClickListener listener){
        Log.d(TAG,"setClickListener");
        mClickListener = listener;
    }

    public interface ClickListener {
        void onClickDate(String date);
    }
}
