package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.ProfileDataModal;
import com.wdp.Modal.ProfileEditDataModal;
import com.wdp.turnstr.R;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditApiService implements Callback<ProfileEditDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public ProfileEditApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(RequestBody name, RequestBody username, RequestBody bio, RequestBody phone, RequestBody website,
                        MultipartBody.Part part1, MultipartBody.Part part2,
                        MultipartBody.Part part3, MultipartBody.Part part4,
                        MultipartBody.Part part5, MultipartBody.Part part6, MultipartBody.Part background,String token, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<ProfileEditDataModal> call = apiInterface.updateProfile(name,username,bio,phone,website,part1,part2,part3,part4,part5,part6,background,token);
        call.enqueue(this);
        setProgressDialog();
    }

    public void Connect1(RequestBody name, RequestBody username, RequestBody bio, RequestBody phone, RequestBody website,String token, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<ProfileEditDataModal> call = apiInterface.updateProfile1(name,username,bio,phone,website,token);
        call.enqueue(this);
        setProgressDialog();
    }

    public void Connect2(RequestBody name, RequestBody username, RequestBody bio, RequestBody phone, RequestBody website, MultipartBody.Part background, String token, ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<ProfileEditDataModal> call = apiInterface.updateProfile2(name,username,bio,phone,website,background,token);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<ProfileEditDataModal> call, Response<ProfileEditDataModal> response) {
        ProfileEditDataModal profileEditDataModal = response.body();
        progressDialog.dismiss();
        if (profileEditDataModal != null) {
            if (profileEditDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = profileEditDataModal;
                apiResponseListner.onSuccess(ApiConstants.profileedit_TAG, superCastClass);
            } else {
                apiResponseListner.onFailure(profileEditDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<ProfileEditDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
