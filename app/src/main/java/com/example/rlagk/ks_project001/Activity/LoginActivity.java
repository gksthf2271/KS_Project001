package com.example.rlagk.ks_project001.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.rlagk.ks_project001.R;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {
    public static final String TAG = "LoginActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initLayout();
    }

    private void initLayout() {
    }

    @OnClick(R.id.btn_login)
    void onClickLoginButton(View view) {
        Log.d(TAG,"Login!");
        startActivity(new Intent(getApplication(), MainActivity.class));
    }
}
