package com.wdp.ApiServices;

import android.content.Context;
import android.util.Log;


import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CreatePosts_model;
import com.wdp.Modal.PostDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostApiService implements Callback<PostDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public PostApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(CreatePosts_model guid, String header, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<PostDataModal> call = apiInterface.getStoryPostDataModal(guid,header,"application/json");
        call.enqueue(this);
        setProgressDialog();
    }

    public void Connect(String url,CreatePosts_model guid, String header, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<PostDataModal> call = apiInterface.getEditCaption(url,header,guid);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<PostDataModal> call, Response<PostDataModal> response) {
        PostDataModal postDataModal = response.body();
        progressDialog.dismiss();
        Log.d("body","-->" + response.body());
        if (postDataModal != null) {
            if (postDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = postDataModal;
                apiResponseListner.onSuccess(ApiConstants.ME_POSTS_Tag, superCastClass);
            } else {
                apiResponseListner.onFailure(postDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<PostDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
