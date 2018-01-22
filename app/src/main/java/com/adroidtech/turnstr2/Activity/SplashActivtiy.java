package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

        SharedPreference sharedPreference = new SharedPreference(SplashActivtiy.this);
        if (sharedPreference.getBoolean(PreferenceKeys.IS_LOGIN)) {
//            Intent intent = new Intent(this, ProfileActivity.class);
//            startActivity(intent);

            LoginDetailModel loginDetailModel=sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);

            connectToSendBird(String.valueOf(loginDetailModel.getUser().getId()), loginDetailModel.getUser().getFirstName());
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        //finish();
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

                Log.e("TAG", "Connected ......................."+user.getUserId()+"  ...  " + user.getProfileUrl());
                PreferenceUtils.setUserId(SplashActivtiy.this, user.getUserId());
                PreferenceUtils.setNickname(SplashActivtiy.this, user.getNickname());
                PreferenceUtils.setProfileUrl(SplashActivtiy.this, user.getProfileUrl());
                PreferenceUtils.setConnected(SplashActivtiy.this, true);

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);
                updateCurrentUserPushToken();

                startActivity(new Intent(SplashActivtiy.this, HomePageActivity.class));//ProfileActivity.class));
                // finish();
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
     * @param userNickname  The new nickname of the user.
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

