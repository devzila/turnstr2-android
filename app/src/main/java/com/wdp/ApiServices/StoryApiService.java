package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;

import com.wdp.Modal.StoryDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryApiService implements Callback<StoryDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public StoryApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String token,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<StoryDataModal> call = apiInterface.getStory(token);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<StoryDataModal> call, Response<StoryDataModal> response) {
        StoryDataModal storyDataModal = response.body();
        progressDialog.dismiss();
        if (storyDataModal != null) {
            if (storyDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = storyDataModal;
                apiResponseListner.onSuccess(ApiConstants.storty_TAG, superCastClass);
            } else {
                apiResponseListner.onFailure(storyDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<StoryDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
