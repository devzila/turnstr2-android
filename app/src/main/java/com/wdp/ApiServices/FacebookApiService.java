package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FacebookApiService implements Callback<LoginResDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public FacebookApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String fb_user_access_token, String device_id, String device_token, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<LoginResDataModal> call = apiInterface.facebook(fb_user_access_token,device_id,device_token);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<LoginResDataModal> call, Response<LoginResDataModal> response) {
        LoginResDataModal loginResDataModal = response.body();
        progressDialog.dismiss();
        if (loginResDataModal != null) {
            if (loginResDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = loginResDataModal;
                apiResponseListner.onSuccess(ApiConstants.login_tag, superCastClass);
            } else {
                apiResponseListner.onFailure(loginResDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<LoginResDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
