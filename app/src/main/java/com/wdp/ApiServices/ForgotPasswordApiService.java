package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.ForgotpasswordDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordApiService implements Callback<ForgotpasswordDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public ForgotPasswordApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(ForgotpasswordDataModal forgotpasswordDataModal, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<ForgotpasswordDataModal> call = apiInterface.forgotpassword(forgotpasswordDataModal);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<ForgotpasswordDataModal> call, Response<ForgotpasswordDataModal> response) {
        ForgotpasswordDataModal forgotpasswordDataModal = response.body();
        progressDialog.dismiss();
        if (forgotpasswordDataModal != null) {
            if (forgotpasswordDataModal.getSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = forgotpasswordDataModal;
                apiResponseListner.onSuccess(ApiConstants.forgotpassowrd_tag, superCastClass);
            } else {
                apiResponseListner.onFailure(forgotpasswordDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<ForgotpasswordDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
