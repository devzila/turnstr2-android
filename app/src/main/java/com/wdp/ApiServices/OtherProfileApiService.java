package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.OtherProfileDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherProfileApiService implements Callback<OtherProfileDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public OtherProfileApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void Connect(String token,String user_id,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<OtherProfileDataModal> call = apiInterface.getOtherProfile(token,user_id);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<OtherProfileDataModal> call, Response<OtherProfileDataModal> response) {
        OtherProfileDataModal otherProfileDataModal = response.body();
        progressDialog.dismiss();
        if (otherProfileDataModal != null) {
            if (otherProfileDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = otherProfileDataModal;
                apiResponseListner.onSuccess(ApiConstants.other_profile_TAG, superCastClass);
            } else {
                apiResponseListner.onFailure(otherProfileDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<OtherProfileDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
