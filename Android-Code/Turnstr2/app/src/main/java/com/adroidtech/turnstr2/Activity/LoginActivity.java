package com.adroidtech.turnstr2.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
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
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements AsyncCallback {


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView facebookLogin;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
//        facebookLoginIntialization();
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        Button signup = (Button) findViewById(R.id.signup);
        Button facebook_signup = (Button) findViewById(R.id.facebook_signup);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
        } else if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
        } else {
            callLoginAPI(email, password);
        }
    }

    private void callLoginAPI(String username, String password) {
        JSONObject mJson = new JSONObject();
        try {
            mJson.put("login", username);
            mJson.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CommonAsync(this, mJson, this, GeneralValues.LOGIN_URL, "LOGIN_URL").execute();
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
                LoginDetailModel data = new Gson().fromJson(jsonObject1.getString("data"), LoginDetailModel.class);
                startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {

        }
    }


//    private CallbackManager callbackManager;
//    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
//        @Override
//        public void onSuccess(LoginResult loginResult) {
//            Log.e("Login Result", loginResult.toString());
//            final AccessToken fb_token = loginResult.getAccessToken();
//            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
//                    new GraphRequest.GraphJSONObjectCallback() {
//                        @Override
//                        public void onCompleted(JSONObject object, GraphResponse response) {
//                            Log.e("Response", response.toString());
//                            Log.e("Json Object", object.toString());
//                            FacebookUserModel facebookUserModel = new Gson().fromJson(object.toString(), FacebookUserModel.class);
//                            facebookUserModel.setAccessToken(fb_token.getToken());
//                            faceBookSocialLoginApi(facebookUserModel.getId(), fb_token.getToken());
//                            //makeLOngRequest(facebookUserModel);
//                        }
//                    });
//            Bundle parameters = new Bundle();
//            parameters.putString("fields", "id,name,link,email,birthday,gender,first_name,last_name,location");
//            request.setParameters(parameters);
//            request.executeAsync();
//        }
//
//        @Override
//        public void onCancel() {
//            Toast.makeText(LoginActivity.this, "onCancel in FB login", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onError(FacebookException error) {
//            Toast.makeText(LoginActivity.this, "Error in FB login", Toast.LENGTH_SHORT).show();
//        }
//
//    };

    private void faceBookSocialLoginApi(String user_id, String token) {
        JSONObject mJson = new JSONObject();
        try {
            mJson.put("user_id", user_id);
            mJson.put("access_token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CommonAsync(this, mJson, this, GeneralValues.FACEBOOK_LOGIN, "LOGIN_URL").execute();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }

//    private void facebookLoginIntialization() {
//        facebookLogin = (TextView) findViewById(R.id.facebook);
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        facebookLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginButton.performClick();
//            }
//        });
//        callbackManager = CallbackManager.Factory.create();
////        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
//        loginButton.registerCallback(callbackManager, callback);
//    }


    private void googleLogin() {
//        googleBtnSignIn = (SignInButton) findViewById(R.id.btn_sign_in_google);
//        googlePlusLogin = (TextView) findViewById(R.id.googleplus);
//        googlePlusLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(new Intent(LoginActivity.this, GooglePlusLogin.class), GOOGLE_LOGIN);
//            }
//        });
    }

}

