package com.example.rlagk.ks_project001.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.rlagk.ks_project001.DB.DBHelperUtils;
import com.example.rlagk.ks_project001.DB.DatabaseManager;
import com.example.rlagk.ks_project001.Fragment.Fragment_CreateDiary;
import com.example.rlagk.ks_project001.Fragment.Fragment_DiaryDetail;
import com.example.rlagk.ks_project001.Fragment.Fragment_DiaryList;
import com.example.rlagk.ks_project001.Fragment.Fragment_DiaryList_DateSelect;
import com.example.rlagk.ks_project001.Fragment.Fragment_Home;
import com.example.rlagk.ks_project001.R;
import com.example.rlagk.ks_project001.View.MenuView;
import com.example.rlagk.ks_project001.utils.Utils;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    public static final String TAG = "MainActivity";
    private static volatile MainActivity sInstance;

    @BindView(R.id.menu_view)
    MenuView mMenuView;
    protected static final int NAV_DRAWER_ITEM_INVALID = -1;
    private PopupWindow mPopupWindow ;

    public static MainActivity getInstance(){
        if (sInstance == null){
            sInstance = new MainActivity();
        }
        return sInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DatabaseManager.getInstance().init(this);
        initLayout();
    }

    private void initLayout() {
        mMenuView.setMenuClickListener(this);
        Fragment_DiaryList_DateSelect.getInstance().setClickListener(mClickListener);
        Utils.loadFragment(getSupportFragmentManager(), Fragment_Home.getInstance(), R.id.fragment_container, false);
    }

    private Fragment_DiaryList_DateSelect.ClickListener mClickListener = new Fragment_DiaryList_DateSelect.ClickListener() {
        @Override
        public void onClickDate(String date) {
            Log.d(TAG,"onClickDate ::: " + date);
            Fragment_DiaryList fragment_diaryList = Fragment_DiaryList.getInstance();
            fragment_diaryList.setSearchDate(date);
            DBHelperUtils dbHelperUtils = DatabaseManager.getInstance().getDB();
//            if (dbHelperUtils.getContacts(date).size() == 0) {
//                Toast.makeText(getApplication(),"없다",3000);
//                Utils.loadFragment(getSupportFragmentManager(), new Fragment_CreateDiary(), R.id.fragment_container, false);
//            } else {
            Utils.loadFragment(getSupportFragmentManager(), fragment_diaryList, R.id.fragment_container, true);
//            }
        }
    };

    @Override
    public void onClickHome() {
        Utils.loadFragment(getSupportFragmentManager(), Fragment_Home.getInstance(), R.id.fragment_container, false);
    }

    @Override
    public void onClickWrite() {
        Utils.loadFragment(getSupportFragmentManager(), Fragment_CreateDiary.getInstance(), R.id.fragment_container, true);
    }

    @Override
    public void onClickList() {
        Utils.loadFragment(getSupportFragmentManager(), Fragment_DiaryList_DateSelect.getInstance(), R.id.fragment_container, false);
    }

    @Override
    public void onClickSetting() {
        startActivity(new Intent(getApplication(), SettingsActivity.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getCurrentFragment() instanceof Fragment_Home) {
                showFinishPopup();
            } else if (getCurrentFragment() instanceof Fragment_DiaryDetail) {
                getSupportFragmentManager().popBackStack();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public Fragment getCurrentFragment() {
        return Utils.currentFragment(getSupportFragmentManager(), R.id.fragment_container);
    }

    private void showFinishPopup() {
        View popupView = getLayoutInflater().inflate(R.layout.activity_main_finish, null);
        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);


        Button cancel = (Button) popupView.findViewById(R.id.Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        Button ok = (Button) popupView.findViewById(R.id.Ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

