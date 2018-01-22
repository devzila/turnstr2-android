package com.adroidtech.turnstr2.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.chatUtils.PreferenceUtils;
import com.adroidtech.turnstr2.Utils.chatUtils.PushUtils;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.chat.groupchannel.GroupChannelActivity;
import com.google.gson.Gson;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A login screen that offers login via email/password.
 */
public class SignUpActivity extends AppCompatActivity implements AsyncCallback {

//turnstr_staging

    //
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private AutoCompleteTextView firstname;
    private AutoCompleteTextView username;
    private EditText confiPassword;
    private Button signUp;
    private SharedPreference sharedPreference;
    private LoginDetailModel userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sharedPreference = new SharedPreference(getApplicationContext());
        // Set up the login form.
        firstname = (AutoCompleteTextView) findViewById(R.id.firstname);
        username = (AutoCompleteTextView) findViewById(R.id.username);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        confiPassword = (EditText) findViewById(R.id.confi_password);
        confiPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.confi_password || id == EditorInfo.IME_NULL) {
                    attemptSignUp();
                    return true;
                }
                return false;
            }
        });

        Button signUp = (Button) findViewById(R.id.sign_up);
        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptSignUp() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String user = username.getText().toString();
        String name = firstname.getText().toString();
        String password = mPasswordView.getText().toString();

        String confiPass = confiPassword.getText().toString();
        if (TextUtils.isEmpty(name)) {
            firstname.setError("Enter first name");
        } else if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
        } else if (TextUtils.isEmpty(user)) {
            username.setError("Enter user name");
            username.requestFocus();
        } else if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
        } else if (!password.equals(confiPass)) {
            confiPassword.setError("Password not matched");
        } else {
            callSignUpAPI(name, user, email, password);
        }
    }

    private void callSignUpAPI(String name, String user, String email, String password) {
        JSONObject mJson = new JSONObject();
        try {
            mJson.put("first_name", name);
            mJson.put("username", user);
            mJson.put("email", email);
            mJson.put("password", password);
            mJson.put("device_name", "Android");
            mJson.put("device_push_token", sharedPreference.getString(PreferenceKeys.FIREBASE_TOKEN));
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CommonAsync(this, mJson, this, GeneralValues.SIGNUP_URL, "LOGIN_URL").execute();
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data", jsonObject.toString());
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                if (txt.equalsIgnoreCase(GeneralValues.SIGNUP_URL)) {
                    userData = new Gson().fromJson(jsonObject1.getString("data"), LoginDetailModel.class);
                    sharedPreference.putString(PreferenceKeys.APP_AUTH_TOKEN, userData.getAuthToken());
                    sharedPreference.putSerializableObject(PreferenceKeys.USER_DETAIL, userData);
                    sendDeviceInfoToServer();
                    //startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                } else if (txt.equalsIgnoreCase(GeneralValues.CREATE_NEW_DEVICE)) {
                    sharedPreference.putBoolean(PreferenceKeys.IS_LOGIN, true);
                    connectToSendBird(String.valueOf(userData.getUser().getId()), userData.getUser().getFirstName());
                }
            } else {
                Toast.makeText(SignUpActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {

        }
    }

    private void sendDeviceInfoToServer() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        JSONObject mJson = new JSONObject();
        try {
            mJson.put("device_ios", "Android : " + Build.VERSION.RELEASE);
            mJson.put("device_name", "Android");
            mJson.put("device_push_token", sharedPreference.getString(PreferenceKeys.FIREBASE_TOKEN));
            mJson.put("device_udid", "");
            mJson.put("voip_token", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CommonAsync(this, mJson, this, GeneralValues.CREATE_NEW_DEVICE, headers).execute();
    }

    /**
     * Attempts to connect a user to SendBird.
     *
     * @param userId       The unique ID of the user.
     * @param userNickname The user's nickname, which will be displayed in chats.
     */
    private void connectToSendBird(final String userId, final String userNickname) {
        // Show the loading indicator
        showProgressBar(true);
        //mConnectButton.setEnabled(false);

        SendBird.connect(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                // Callback received; hide the progress bar.
                showProgressBar(false);

                if (e != null) {
                    // Error!
                    Toast.makeText(
                            SignUpActivity.this, "" + e.getCode() + ": " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show login failure snackbar
                    showSnackbar("Login to SendBird failed");
                    // mConnectButton.setEnabled(true);
                    PreferenceUtils.setConnected(SignUpActivity.this, false);
                    return;
                }

                Log.e("TAG", "Connected ......................." + user.getUserId() + "  ...  " + user.getProfileUrl());
                PreferenceUtils.setNickname(SignUpActivity.this, user.getNickname());
                PreferenceUtils.setProfileUrl(SignUpActivity.this, user.getProfileUrl());
                PreferenceUtils.setConnected(SignUpActivity.this, true);
                // Update the user's nickname
                updateCurrentUserInfo(userNickname);
                updateCurrentUserPushToken();

                // Proceed to ImageFillterSettingActivity
                // Intent intent = new Intent(LoginActivity.this, AllFriendList.class);

                startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));
//                Intent intent = new Intent(SignUpActivity.this, GroupChannelActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Update the user's push token.
     */
    private void updateCurrentUserPushToken() {
        PushUtils.registerPushTokenForCurrentUser(SignUpActivity.this, null);
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
                            SignUpActivity.this, "" + e.getCode() + ":" + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show update failed snackbar
                    showSnackbar("Update user nickname failed");

                    return;
                }

                PreferenceUtils.setNickname(SignUpActivity.this, userNickname);
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

