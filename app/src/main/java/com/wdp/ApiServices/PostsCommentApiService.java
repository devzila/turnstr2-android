package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.PostCommentModal;
import com.wdp.Modal.PostLikesDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsCommentApiService implements Callback<PostCommentModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public PostsCommentApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String token, String postid,String comment,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<PostCommentModal> call = apiInterface.postComment(token,postid,comment);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<PostCommentModal> call, Response<PostCommentModal> response) {
        PostCommentModal postDataModal = response.body();
        progressDialog.dismiss();
        if (postDataModal != null) {
            if (postDataModal.getSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = postDataModal;
                apiResponseListner.onSuccess(ApiConstants.posts_comment_tag, superCastClass);
            } else {
                apiResponseListner.onFailure(postDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<PostCommentModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
