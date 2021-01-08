package com.wdp.ApiServices;

import android.content.Context;
import android.util.Log;


import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommanDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePostApiService implements Callback<CommanDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public DeletePostApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String url, String header, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<CommanDataModal> call = apiInterface.deletePost(url,header);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<CommanDataModal> call, Response<CommanDataModal> response) {
        CommanDataModal commanDataModal = response.body();
        progressDialog.dismiss();
        Log.d("body","-->" + response.body());
        if (commanDataModal != null) {
            if (commanDataModal.getSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = commanDataModal;
                apiResponseListner.onSuccess(ApiConstants.MePostdelete_TAG, superCastClass);
            } else {
                apiResponseListner.onFailure(commanDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<CommanDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}