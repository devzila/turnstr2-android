package com.wdp.ActivityScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;
import com.wdp.ApiServices.FacebookApiService;
import com.wdp.ApiServices.LoginApiService;
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

import java.util.Arrays;

public class LoginActivity extends Activity implements View.OnClickListener, ApiResponseListner {
    TextView txt_forgot, txt_sign_up,txt_facebook;
    AppCompatButton btn_login;
    EditText edt_email, edt_password;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    LoginButton login_button;
    CallbackManager callbackManager;
    GoogleProgressDialog progressDialog;
    LoginResDataModal loginResDataModal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intiView();
        clickListner();
    }


    private void intiView() {
        txt_forgot = findViewById(R.id.txt_forgot);
        txt_sign_up = findViewById(R.id.txt_sign_up);
        btn_login = findViewById(R.id.btn_login);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        login_button = findViewById(R.id.login_button);
        txt_facebook = findViewById(R.id.txt_facebook);
    }


    private void clickListner() {
        btn_login.setOnClickListener(this);
        txt_facebook.setOnClickListener(this);
        txt_forgot.setOnClickListener(this);
        txt_sign_up.setOnClickListener(this);
        txt_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });


    }

    private void facebookAuthentication() {
        login_button.setReadPermissions(Arrays.asList("email", "public_profile"));
        //   loginButton.setPermissions(Arrays.asList("user_photos", "user_birthday", "email", "public_profile"));
        callbackManager = CallbackManager.Factory.create();
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Log.d("Facebook","------->" + "accessToken");
                USELOginInformationFacebook(accessToken);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "cancel login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void USELOginInformationFacebook(AccessToken accessToken) {
        String access_token_ = accessToken.getToken();
        String device_id = "android";
        String device_token = commonSharedPreference.getFCMToken(this);
        connectFacebook(access_token_,device_id,device_token);
        /*GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    if (object.has("id"))
                        id = object.getString("id");
                    if (object.has("email"))
                        email = object.getString("email");
                    if (object.has("first_name"))
                        first_name = object.getString("first_name");
                    if (object.has("last_name"))
                        last_name = object.getString("last_name");
                    if (object.has("name"))
                        user_name = object.getString("name");
                    if (object.has("picture"))
                        profile_image = object.getJSONObject("picture").getJSONObject("data").getString("url");
                    age = "";
                    gender = "";
                    Log.d("facebook","---->" +  id);
                    connectSocialLogin();

                } catch (JSONException e) {
                    Log.d("facebook","---->" +  e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,name,email,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();*/
    }

    private void connectLogin(String email, String password, String device_id, String device_token) {
        if (NetworkCheck.isConnected(this)) {
            LoginApiService loginApiService = new LoginApiService(this);
            loginApiService.Connect(email, password, device_id, device_token, this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }
    private void connectFacebook(String fb_user_access_token, String device_id, String device_token) {
        if (NetworkCheck.isConnected(this)) {
            FacebookApiService facebookApiService = new FacebookApiService(this);
            facebookApiService.Connect(fb_user_access_token, device_id, device_token, this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectToSendBird(final String userId, final String userNickname) {
        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(userNickname)) {
            return;
        }
        // Show the loading indicator
        progressDialog = new GoogleProgressDialog(LoginActivity.this);
        progressDialog.showDialog();
        ConnectionManager.login(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                //Callback received; hide the progress bar.
                progressDialog.dismiss();

                if (e != null) {
                    // Error!
                    Toast.makeText(LoginActivity.this, "" + e.getCode() + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);
                //PushUtils.registerPushHandler(new MyFirebaseMessagingService());

                // Proceed to MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
                    Toast.makeText(LoginActivity.this, "" + e.getCode() + ":" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (btn_login == view) {
            checkValidation();


        } else if (view == txt_sign_up) {
            Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else if (view == txt_forgot) {
            Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        else if (view == txt_facebook){
            login_button.performClick();
            facebookAuthentication();
        }

    }

    private void checkValidation(){
        if (TextUtils.isEmpty(edt_email.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.pls_enter_email), Toast.LENGTH_LONG).show();
        } else if (!CommanUtility.isEmailValid(edt_email.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.enter_valid_formet), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(edt_password.getText().toString())) {
            Toast.makeText(this, getResources().getString(R.string.pls_enter_password), Toast.LENGTH_LONG).show();
        } else {
            String email = edt_email.getText().toString().trim();
            String password = edt_password.getText().toString().trim();
            String device_id = "android";
            String device_token = commonSharedPreference.getFCMToken(this);
            connectLogin(email, password, device_id, device_token);
        }
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.login_tag)) {
            LoginResDataModal loginResDataModal = (LoginResDataModal) superCastClass;
            commonSharedPreference.setisLoginSharedPref(LoginActivity.this, true);
            commonSharedPreference.setSubscriberLoginSharedPref(LoginActivity.this, loginResDataModal);
            connectToSendBird(loginResDataModal.getData().getUser().getId(), loginResDataModal.getData().getUser().getName());
        }

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
