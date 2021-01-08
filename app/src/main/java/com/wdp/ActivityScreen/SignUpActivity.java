package com.wdp.ActivityScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;
import com.wdp.ApiServices.RegistrationApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.SendBird.ConnectionManager;
import com.wdp.Utility.CommanUtility;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

public class SignUpActivity extends Activity implements View.OnClickListener, ApiResponseListner {
    TextView txt_login, txt_next;
    EditText edt_name, edt_email, edt_username, edt_password, edt_confirm_password;
    CheckBox checkbox;
    GoogleProgressDialog progressDialog;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        intiView();
        clickListner();
    }

    private void clickListner() {
        txt_next.setOnClickListener(this);
        txt_login.setOnClickListener(this);


    }

    private void connectSignUp(String name, String email, String username, String password, String device_id, String device_token) {
        if (NetworkCheck.isConnected(this)) {
            RegistrationApiService getAllStoryViewApiService = new RegistrationApiService(this);
            getAllStoryViewApiService.Connect(name, email, username, password, device_id, device_token, this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void intiView() {
        txt_login = findViewById(R.id.txt_login);
        txt_next = findViewById(R.id.txt_next);
        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        edt_confirm_password = findViewById(R.id.edt_confirm_password);
        checkbox = findViewById(R.id.checkbox);
    }

    @Override
    public void onClick(View view) {
        if (view == txt_login) {
            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else if (view == txt_next) {
            if (TextUtils.isEmpty(edt_name.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.pls_enter_name), Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(edt_email.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.pls_enter_email), Toast.LENGTH_LONG).show();
            } else if (!CommanUtility.isEmailValid(edt_email.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.enter_valid_formet), Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(edt_username.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.pls_enter_username), Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(edt_password.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.pls_enter_password), Toast.LENGTH_LONG).show();
            } else if (edt_password.getText().toString().length() < 8) {
                Toast.makeText(this, getResources().getString(R.string.password_to_short), Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(edt_confirm_password.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.pls_enter_confirm_password), Toast.LENGTH_LONG).show();
            } else if (!edt_password.getText().toString().equalsIgnoreCase(edt_confirm_password.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.password_does_not_match), Toast.LENGTH_LONG).show();
            } else if (!checkbox.isChecked()) {
                Toast.makeText(this, getResources().getString(R.string.please_check_terms_and_privacy), Toast.LENGTH_LONG).show();
            } else {
                String name = edt_name.getText().toString().trim();
                String email = edt_email.getText().toString().trim();
                String username = edt_username.getText().toString().trim();
                String password = edt_password.getText().toString().trim();
                String device_id = "android";
                String device_token = commonSharedPreference.getFCMToken(this);
                connectSignUp(name, email, username, password, device_id, device_token);
            }

        }

    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.signup_tag)) {
            LoginResDataModal loginResDataModal = (LoginResDataModal) superCastClass;
            commonSharedPreference.setisLoginSharedPref(SignUpActivity.this, true);
            commonSharedPreference.setSubscriberLoginSharedPref(SignUpActivity.this, loginResDataModal);
            connectToSendBird(loginResDataModal.getData().getUser().getId(), loginResDataModal.getData().getUser().getName());
        }

    }

    private void connectToSendBird(final String userId, final String userNickname) {
        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(userNickname)) {
            return;
        }
        // Show the loading indicator
        progressDialog = new GoogleProgressDialog(SignUpActivity.this);
        progressDialog.showDialog();
        ConnectionManager.login(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                // Callback received; hide the progress bar.
                progressDialog.dismiss();

                if (e != null) {
                    // Error!
                    Toast.makeText(SignUpActivity.this, "" + e.getCode() + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);
                //PushUtils.registerPushHandler(new MyFirebaseMessagingService());

                // Proceed to MainActivity
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateCurrentUserInfo(final String userNickname) {
        SendBird.updateCurrentUserInfo(userNickname, null, new SendBird.UserInfoUpdateHandler() {
            @Override
            public void onUpdated(SendBirdException e) {
                if (e != null) {
                    // Error!
                    Toast.makeText(SignUpActivity.this, "" + e.getCode() + ":" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
