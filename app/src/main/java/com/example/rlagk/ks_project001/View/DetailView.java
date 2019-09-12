package com.example.rlagk.ks_project001.View;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rlagk.ks_project001.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class DetailView extends LinearLayout {

    private static final String TAG = DetailView.class.getName();

    @BindView(R.id.diaryText)
    EditText mDiaryText;

    @BindView(R.id.group_date)
    TextInputLayout mDateInputLayout;

    @BindView(R.id.diaryDate)
    TextView mDiaryDate;

    private fabClickListener mListener;

    public DetailView(Context context) {
        this(context, null, 0);
    }

    public DetailView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cview_detail, this);
        ButterKnife.bind(this);
        mDiaryDate.setOnClickListener(mDateClickListener);
        int maxLength = 100;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        mDiaryText.setFilters(fArray);
        mDiaryText.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure(...)");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(TAG, "onFocusChanged(...)");
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mDiaryText.getWindowToken(), 0);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow(...)");

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow(...)");
    }

    public String getDescription() {
        return mDiaryText.getText().toString();
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View view) {
        onClickFab();
    }


    public void setListener(fabClickListener fabClickListener) {
        Log.d(TAG, "fabClickListener(...)");
        this.mListener = fabClickListener;
    }

    public interface fabClickListener {
        public void clickFab();
    }

    private void onClickFab() {
        Log.d(TAG, "onClickFab(...)");
        if (null != mListener) {
            mListener.clickFab();
        }
    }

    DatePickerDialog.OnDateSetListener mDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month++;
            String time = year
                    + "/"
                    + ((month < 10) ? ("0" + month) : month)
                    + "/"
                    + ((dayOfMonth < 10) ? ("0" + dayOfMonth) : dayOfMonth);
            Log.d(TAG, "date ::: " + time);
            mDiaryDate.setText(time);
        }
    };

    TextView.OnClickListener mDateClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = new DatePickerDialog(
                    getContext(),
                    R.style.DialogTheme,
                    mDatePickerListener,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show();
        }
    };

    TextWatcher textWatcher = new TextWatcher() {
        String previousString = "";

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mDiaryText.getLineCount() >= 4) {
                mDiaryText.setText(previousString);
                mDiaryText.setSelection(mDiaryText.length());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            previousString = s.toString();
        }
    };

}
