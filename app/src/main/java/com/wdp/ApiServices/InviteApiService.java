package com.wdp.ApiServices;

import android.content.Context;
import android.util.Log;


import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.InviteDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InviteApiService implements Callback<InviteDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public InviteApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String url,String header, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<InviteDataModal> call = apiInterface.Invite(url,header);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<InviteDataModal> call, Response<InviteDataModal> response) {
        InviteDataModal inviteDataModal = response.body();
        progressDialog.dismiss();
        Log.d("body","-->" + response.body());
        if (inviteDataModal != null) {
            if (inviteDataModal.getSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = inviteDataModal;
                apiResponseListner.onSuccess("Invite", superCastClass);
            } else {
                apiResponseListner.onFailure(inviteDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<InviteDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
