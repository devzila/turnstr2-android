package com.adroidtech.turnstr2.Activity;

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
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A login screen that offers login via email/password.
 */
public class SignUpActivity extends AppCompatActivity implements AsyncCallback {


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private AutoCompleteTextView firstname;
    private AutoCompleteTextView username;
    private EditText confiPassword;
    private Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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
        } else if (TextUtils.isEmpty(confiPass) && !password.equals(confiPass)) {
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
                LoginDetailModel data = new Gson().fromJson(jsonObject, LoginDetailModel.class);
                Toast.makeText(SignUpActivity.this, "Login : " + data.getUser().getFirstName(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SignUpActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {

        }
    }

}

