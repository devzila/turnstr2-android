package com.adroidtech.turnstr2.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.chatUtils.PreferenceUtils;
import com.adroidtech.turnstr2.Utils.chatUtils.PushUtils;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

import java.util.ArrayList;
import java.util.List;

public class SplashActivtiy extends Activity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activtiy);


        if (checkAndRequestPermissions(this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    callApp();
                }
            }, SPLASH_TIME_OUT);
        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                callApp();
//            }
//        }, SPLASH_TIME_OUT);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 124: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(SplashActivtiy.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callApp();
                    }
                }, SPLASH_TIME_OUT);
                return;
            }
            default:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        callApp();
                    }
                }, SPLASH_TIME_OUT);
                break;
        }
    }

    private void callApp() {
        SharedPreference sharedPreference = new SharedPreference(SplashActivtiy.this);
        if (sharedPreference.getBoolean(PreferenceKeys.IS_LOGIN)) {
//            Intent intent = new Intent(this, ProfileActivity.class);
//            startActivity(intent);

            LoginDetailModel loginDetailModel = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);

            connectToSendBird(String.valueOf(loginDetailModel.getUser().getId()), loginDetailModel.getUser().getFirstName());
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        //finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("TAG ",  "Splash Activity.........");
    }

    /**
     * Check camera permission when we lounch camera.
     *
     * @param activity
     * @return
     */
    public static boolean checkAndRequestPermissions(Activity activity) {
        int permissionCamera = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);
        int permissionAudio = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        int permissionWriteStorage = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionReadStorage = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (permissionAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
        }
        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionReadStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 124);
            return false;
        }
        return true;
    }

    private void connectToSendBird(final String userId, final String userNickname) {
        // Show the loading indicator
        //showProgressBar(true);
        //mConnectButton.setEnabled(false);

        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                // Callback received; hide the progress bar.
                showProgressBar(false);

                if (e != null) {
                    // Error!
                    Toast.makeText(
                            SplashActivtiy.this, "" + e.getCode() + ": " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show login failure snackbar
                    showSnackbar("Login to SendBird failed");
                    // mConnectButton.setEnabled(true);
                    PreferenceUtils.setConnected(SplashActivtiy.this, false);
                    return;
                }

                Log.e("TAG", "Connected ......................." + user.getUserId() + "  ...  " + user.getProfileUrl());
                PreferenceUtils.setUserId(SplashActivtiy.this, user.getUserId());
                PreferenceUtils.setNickname(SplashActivtiy.this, user.getNickname());
                PreferenceUtils.setProfileUrl(SplashActivtiy.this, user.getProfileUrl());
                PreferenceUtils.setConnected(SplashActivtiy.this, true);

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);
                updateCurrentUserPushToken();

                startActivity(new Intent(SplashActivtiy.this, HomePageActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));//ProfileActivity.class));
                finish();
            }
        });
    }

    /**
     * Update the user's push token.
     */
    private void updateCurrentUserPushToken() {
        PushUtils.registerPushTokenForCurrentUser(SplashActivtiy.this, null);
    }

    /**
     * Updates the user's nickname.
     *
     * @param userNickname The new nickname of the user.
     */
    private void updateCurrentUserInfo(final String userNickname) {
        SendBird.updateCurrentUserInfo(userNickname, null, new SendBird.UserInfoUpdateHandler() {
            @Override
            public void onUpdated(SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(
                            SplashActivtiy.this, "" + e.getCode() + ":" + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show update failed snackbar
                    showSnackbar("Update user nickname failed");

                    return;
                }

                PreferenceUtils.setNickname(SplashActivtiy.this, userNickname);
            }
        });
    }


    // Displays a Snackbar from the bottom of the screen
    private void showSnackbar(String text) {
//        Snackbar snackbar = Snackbar.make(mLoginLayout, text, Snackbar.LENGTH_SHORT);
//
//        snackbar.show();
    }

    // Shows or hides the ProgressBar
    private void showProgressBar(boolean show) {
//        if (show) {
//            mProgressBar.show();
//        } else {
//            mProgressBar.hide();
//        }
    }


}

