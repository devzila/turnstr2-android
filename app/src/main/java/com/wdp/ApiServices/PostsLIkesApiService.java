package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.PostDataModal;
import com.wdp.Modal.PostLikesDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsLIkesApiService implements Callback<PostLikesDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public PostsLIkesApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String token, String postid,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<PostLikesDataModal> call = apiInterface.createLikes(token,postid);
        call.enqueue(this);
        //setProgressDialog();
    }

    public void Connect1(String token, String postid,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<PostLikesDataModal> call = apiInterface.postdisLikes(token,postid);
        call.enqueue(this);
        //setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<PostLikesDataModal> call, Response<PostLikesDataModal> response) {
        PostLikesDataModal postDataModal = response.body();
       // progressDialog.dismiss();
        if (postDataModal != null) {
            if (postDataModal.getSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = postDataModal;
                apiResponseListner.onSuccess(ApiConstants.posts_likes_tag, superCastClass);
            } else {
                apiResponseListner.onFailure(postDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<PostLikesDataModal> call, Throwable t) {
        //progressDialog.dismiss();
        t.getMessage();
    }

}
