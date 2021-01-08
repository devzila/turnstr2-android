package com.wdp.ApiServices;

import android.content.Context;
import android.util.Log;


import com.wdp.Agora.LiveBroadcast;
import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveBroadCastApiService implements Callback<LiveBroadcast> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public LiveBroadCastApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String header, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<LiveBroadcast> call = apiInterface.getLiveBroadcast(header,"application/json");
        call.enqueue(this);
       // setProgressDialog();
    }

    public void Connect1(String header, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<LiveBroadcast> call = apiInterface.getRemoveLiveBroadcast(header,"application/json");
        call.enqueue(this);
        // setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<LiveBroadcast> call, Response<LiveBroadcast> response) {
        LiveBroadcast activityModal = response.body();
        Log.d("body","-->" + response.body());
        if (activityModal != null) {
            if (activityModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = activityModal;
                apiResponseListner.onSuccess("livebroadcast", superCastClass);
            } else {
                apiResponseListner.onFailure(activityModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<LiveBroadcast> call, Throwable t) {
       // progressDialog.dismiss();
        t.getMessage();
    }

}
