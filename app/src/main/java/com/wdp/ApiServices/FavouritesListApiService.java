package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;

import com.wdp.Modal.FavouriteslistDataModal;
import com.wdp.Modal.ProfileDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesListApiService implements Callback<FavouriteslistDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;

    public FavouritesListApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String token,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<FavouriteslistDataModal> call = apiInterface.getFavourites(token);
        call.enqueue(this);
        setProgressDialog();
    }

   public void Connect1(String token,String userid,ApiResponseListner apiResponseListner) {
        this.apiResponseListner = apiResponseListner;
        Call<FavouriteslistDataModal> call = apiInterface.getFavourites(token,userid);
        call.enqueue(this);
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<FavouriteslistDataModal> call, Response<FavouriteslistDataModal> response) {
        FavouriteslistDataModal favouritesDataModal = response.body();
        progressDialog.dismiss();
        if (favouritesDataModal != null) {
            if (favouritesDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = favouritesDataModal;
                apiResponseListner.onSuccess(ApiConstants.favourites_TAG, superCastClass);
            } else {
                apiResponseListner.onFailure(favouritesDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<FavouriteslistDataModal> call, Throwable t) {
        progressDialog.dismiss();
        t.getMessage();
    }

}
