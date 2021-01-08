package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.PostCommentModal;
import com.wdp.Modal.PostReportDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostReportApiService implements Callback<PostReportDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public PostReportApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String token,String id, String reason,String text,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<PostReportDataModal> call = apiInterface.postReport(token,id,reason,text);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<PostReportDataModal> call, Response<PostReportDataModal> response) {
        PostReportDataModal postReportDataModal = response.body();
        progressDialog.dismiss();
        if (postReportDataModal != null) {
            if (postReportDataModal.getSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = postReportDataModal;
                apiResponseListner.onSuccess(ApiConstants.posts_reason_tag, superCastClass);
            } else {
                apiResponseListner.onFailure(postReportDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<PostReportDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
