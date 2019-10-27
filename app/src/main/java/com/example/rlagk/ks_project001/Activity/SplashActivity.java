package com.example.rlagk.ks_project001.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.rlagk.ks_project001.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends Activity {
    public static final String TAG = "SplashActivity";
    private final static int MSG_SPLASH = 0;
    private final static long sSplashTime = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG,"MSG : " + msg);
                switch (msg.what) {
                    case MSG_SPLASH:
                        Log.d(TAG,"SPLASH END");
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        SplashActivity.this.finish();
                        break;
                    default:
                        break;
                }
            }
        };
        hd.sendEmptyMessageDelayed(MSG_SPLASH, sSplashTime);
    }

    @Override
    public void onBackPressed() {
        //BackKey Lock...
    }
}
