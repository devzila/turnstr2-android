package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostApiService implements Callback<MyPostDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public MyPostApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String token,String page,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<MyPostDataModal> call = apiInterface.MyPosts(token,page);
        call.enqueue(this);
        setProgressDialog();
    }

    public void Connect1(String token,String userid,String page,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<MyPostDataModal> call = apiInterface.getOtherUserpost(token,userid,page);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<MyPostDataModal> call, Response<MyPostDataModal> response) {
        MyPostDataModal myPostDataModal = response.body();
        progressDialog.dismiss();
        if (myPostDataModal != null) {
            if (myPostDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = myPostDataModal;
                apiResponseListner.onSuccess(ApiConstants.my_post_TAG, superCastClass);
            } else {
                apiResponseListner.onFailure(myPostDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<MyPostDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
