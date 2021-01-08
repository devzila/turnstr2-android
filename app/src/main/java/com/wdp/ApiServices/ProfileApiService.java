package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.ProfileDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileApiService implements Callback<ProfileDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public ProfileApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String token,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<ProfileDataModal> call = apiInterface.get_Profile(token);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<ProfileDataModal> call, Response<ProfileDataModal> response) {
        ProfileDataModal profileDataModal = response.body();
        progressDialog.dismiss();
        if (profileDataModal != null) {
            if (profileDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = profileDataModal;
                apiResponseListner.onSuccess(ApiConstants.profile_TAG, superCastClass);
            } else {
                apiResponseListner.onFailure(profileDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<ProfileDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
