package com.wdp.ActivityScreen;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.wdp.ApiServices.ForgotPasswordApiService;
import com.wdp.ApiServices.LoginApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.ForgotpasswordDataModal;
import com.wdp.Utility.CommanUtility;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

public class ForgotPasswordActivity extends Activity implements View.OnClickListener, ApiResponseListner {
    AppCompatButton btn_next;
    EditText edt_email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        initView();
        clickListner();
    }

    private void initView(){
        btn_next = findViewById(R.id.btn_next);
        edt_email = findViewById(R.id.edt_email);
    }

    private void clickListner(){
        btn_next.setOnClickListener(this);
    }

    private void connectForgotPassoword(ForgotpasswordDataModal forgotpasswordDataModal) {
        if (NetworkCheck.isConnected(this)) {
            ForgotPasswordApiService forgotPasswordApiService = new ForgotPasswordApiService(this);
            forgotPasswordApiService.Connect(forgotpasswordDataModal, this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btn_next){
            if (TextUtils.isEmpty(edt_email.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.pls_enter_email), Toast.LENGTH_LONG).show();
            } else if (!CommanUtility.isEmailValid(edt_email.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.enter_valid_formet), Toast.LENGTH_LONG).show();
            }
            else {
                ForgotpasswordDataModal forgotpasswordDataModal = new ForgotpasswordDataModal();
                forgotpasswordDataModal.setEmail(edt_email.getText().toString());
                connectForgotPassoword(forgotpasswordDataModal);
            }
        }

    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.FORGET_PASSWORD_TAG)){
            ForgotpasswordDataModal forgotpasswordDataModal = (ForgotpasswordDataModal) superCastClass;
            Toast.makeText(this,forgotpasswordDataModal.getMessage(),Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }
}
