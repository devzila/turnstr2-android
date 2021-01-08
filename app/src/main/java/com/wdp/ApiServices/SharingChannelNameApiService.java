package com.wdp.ApiServices;

import android.content.Context;
import android.util.Log;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.FollowingDataModal;
import com.wdp.Modal.SharingChannelModel;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharingChannelNameApiService implements Callback<SharingChannelModel> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public SharingChannelNameApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String header, String url, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<SharingChannelModel> call = apiInterface.SharingChannelName(header, url);
        call.enqueue(this);
        setProgressDialog();
    }

/*    public void Connect1(String url,String header,String page, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<FollowingDataModal> call = apiInterface.getFollowingFollowingMember(url,header,page);
        call.enqueue(this);
        setProgressDialog();
    }*/


    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<SharingChannelModel> call, Response<SharingChannelModel> response) {
        SharingChannelModel sharingChannelModel = response.body();
        progressDialog.dismiss();
        if (sharingChannelModel != null) {
            if (sharingChannelModel.isSuccess()) {
                SuperCastClass superCastClass = sharingChannelModel;
                apiResponseListner.onSuccess(ApiConstants.sharing_channel, superCastClass);
            } else {
                apiResponseListner.onFailure(sharingChannelModel.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<SharingChannelModel> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}

