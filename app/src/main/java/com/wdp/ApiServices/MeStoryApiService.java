package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeStoryApiService implements Callback<MeStoryDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public MeStoryApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String token,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<MeStoryDataModal> call = apiInterface.getMeStory(token);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<MeStoryDataModal> call, Response<MeStoryDataModal> response) {
        MeStoryDataModal meStoryDataModal = response.body();
        progressDialog.dismiss();
        if (meStoryDataModal != null) {
            if (meStoryDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = meStoryDataModal;
                apiResponseListner.onSuccess(ApiConstants.mestoryview_tag, superCastClass);
            } else {
                apiResponseListner.onFailure(meStoryDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<MeStoryDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
