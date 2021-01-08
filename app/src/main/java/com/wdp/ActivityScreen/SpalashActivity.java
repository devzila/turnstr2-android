package com.wdp.ActivityScreen;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.facebook.login.Login;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

public class SpalashActivity extends Activity {
    int SPLASH_TIME_OUT = 3000;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    TextView txtBuildVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        txtBuildVersion = findViewById(R.id.txtBuildVersion);
        txtBuildVersion.setText("1.10");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
              getPermission();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ProceedToNext();
            } else {
                Toast.makeText(SpalashActivity.this, getString(R.string.go_to_settings_enable_permissions), Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {
            ProceedToNext();
        }
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
    }
    private void ProceedToNext() {
        if (commonSharedPreference.getisLoginSharedPref(this)){
            Intent i = new Intent(SpalashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        else {
            Intent i = new Intent(SpalashActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}
