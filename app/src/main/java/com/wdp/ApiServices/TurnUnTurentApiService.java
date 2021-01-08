package com.wdp.ApiServices;

import android.content.Context;
import android.service.controls.actions.CommandAction;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommanDataModal;
import com.wdp.Modal.FavouriteslistDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurnUnTurentApiService implements Callback<CommanDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public TurnUnTurentApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String token,String url,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<CommanDataModal> call = apiInterface.getUnTurent(token,url);
        call.enqueue(this);
        setProgressDialog();
    }

    public void Connect1(String token,String url,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<CommanDataModal> call = apiInterface.getTurent(token,url);
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
            if (commanDataModal.getSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = commanDataModal;
                apiResponseListner.onSuccess(ApiConstants.turn_unturn_TAG, superCastClass);
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
