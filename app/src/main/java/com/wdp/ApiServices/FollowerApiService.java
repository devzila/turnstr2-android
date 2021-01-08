package com.wdp.ApiServices;

import android.content.Context;


import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.FollowerDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowerApiService implements Callback<FollowerDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public FollowerApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String header,String page, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<FollowerDataModal> call = apiInterface.getFollower(header,page);
        call.enqueue(this);
        setProgressDialog();
    }

    public void Connect1(String url,String header,String page, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<FollowerDataModal> call = apiInterface.getFollowerMember(url,header,page);
        call.enqueue(this);
        setProgressDialog();
    }


    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<FollowerDataModal> call, Response<FollowerDataModal> response) {
        FollowerDataModal followingDataModal = response.body();
        progressDialog.dismiss();
        if (followingDataModal != null) {
            if (followingDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = followingDataModal;
                apiResponseListner.onSuccess(ApiConstants.me_follower, superCastClass);
            } else {
                apiResponseListner.onFailure(followingDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<FollowerDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
