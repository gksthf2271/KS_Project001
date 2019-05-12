//package com.example.rlagk.ks_project001.Activity;
//
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.support.v7.app.ActionBar;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.EditText;
//
//import com.example.rlagk.ks_project001.DB.Contact;
//import com.example.rlagk.ks_project001.DB.DBHelperUtils;
//import com.example.rlagk.ks_project001.DB.DatabaseManager;
//import com.example.rlagk.ks_project001.Fragment.Fragment_CreateDiary;
//import com.example.rlagk.ks_project001.R;
//
///**
// * Activity demonstrates some GUI functionalities from the Android support library.
// *
// * Created by Andreas Schrade on 14.12.2015.
// */
//public class ViewSamplesActivity extends BaseActivity {
//
//    public static long ID = 0;
//    private DBHelperUtils mDBHelperUtils;
//
//    public static final String EXTRA_IMAGE_URL = "detailImageUrl";
//    public static final String ADDRESS1_TRANSITION_NAME = "address1";
//    public static final String RATINGBAR_TRANSITION_NAME = "ratingBar";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_samples);
//        setupToolbar();
//        Fragment_CreateDiary fragment =  Fragment_CreateDiary.newInstance();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//        mDBHelperUtils = DatabaseManager.getInstance().getDB();
//    }
//
//
//    private void setupToolbar() {
//        final ActionBar ab = getActionBarToolbar();
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//        ab.setDisplayHomeAsUpEnabled(true);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.sample_actions, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                openDrawer();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected int getSelfNavDrawerItem() {
//        return R.id.nav_samples;
//    }
//
//    @Override
//    public boolean providesActivityToolbar() {
//        return true;
//    }
//
//    private void saveDiary(View view){
//        Log.d(TAG,"saveBTNClick(...) ");
//        ID++;
//
//        EditText diaryTitle = view.findViewById(R.id.diaryTitle);
//        EditText diaryDate = view.findViewById(R.id.diaryDate);
//        EditText diaryText = view.findViewById(R.id.diaryText);
//        Contact contact = new Contact();
//
//        contact.setDate(diaryDate.getText().toString());
//        contact.setTitle(diaryTitle.getText().toString());
//        contact.setDescription(diaryText.getText().toString());
//        contact.setId(String.valueOf(SystemClock.currentThreadTimeMillis()));
//
//        if(mDBHelperUtils == null){
//            mDBHelperUtils = DatabaseManager.getInstance().getDB();
//        }
//        mDBHelperUtils.addContact(contact);
//
//        diaryText.setText("");
//        diaryDate.setText("");
//        diaryTitle.setText("");
//    }
//}
