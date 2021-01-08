package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommetListDataModal;
import com.wdp.Modal.PostLikesDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsCommentsListApiService implements Callback<CommetListDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;
    private Boolean flag;

    public PostsCommentsListApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String token, String postid,boolean flg,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<CommetListDataModal> call = apiInterface.getCommentList(token,postid);
        call.enqueue(this);
        flag = flg;
        if (flag){
            setProgressDialog();
        }

    }



    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<CommetListDataModal> call, Response<CommetListDataModal> response) {
        CommetListDataModal commetListDataModal = response.body();
        if (flag){
            progressDialog.dismiss();
        }
        if (commetListDataModal != null) {
            if (commetListDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = commetListDataModal;
                apiResponseListner.onSuccess(ApiConstants.posts_commentlist_tag, superCastClass);
            } else {
                apiResponseListner.onFailure(commetListDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<CommetListDataModal> call, Throwable t) {
        if (flag){
            progressDialog.dismiss();
        }
        t.getMessage();
    }

}
