package com.wdp.ApiServices;

import android.content.Context;

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

public class FavouriteFiveApiService implements Callback<CommanDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public FavouriteFiveApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String token,String url, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<CommanDataModal> call = apiInterface.getFavouritesFive(token,url);
        call.enqueue(this);
        setProgressDialog();
    }

    public void Connect1(String token,String url,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<CommanDataModal> call = apiInterface.getFavouritesFive1(token,url);
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
        if (commanDataModal != null) {
            if (commanDataModal.getSuccess().equalsIgnoreCase("true") || commanDataModal.getSuccess().equalsIgnoreCase("false")) {
                SuperCastClass superCastClass = commanDataModal;
                apiResponseListner.onSuccess(ApiConstants.favfive_TAG, superCastClass);
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
