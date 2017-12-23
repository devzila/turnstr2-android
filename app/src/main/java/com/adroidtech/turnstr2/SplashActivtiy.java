package com.adroidtech.turnstr2;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivtiy extends Activity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activtiy);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callApp();
            }
        }, SPLASH_TIME_OUT);

    }


    private void callApp() {
        Intent intent = null;
        SharedPreference sharedPreference = new SharedPreference(SplashActivtiy.this);
        if (sharedPreference.getBoolean(PreferenceKeys.IS_LOGIN)) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }

}
