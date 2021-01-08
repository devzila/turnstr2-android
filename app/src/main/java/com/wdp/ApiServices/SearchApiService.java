package com.wdp.ApiServices;

import android.content.Context;

import com.wdp.Interface.APIClient;
import com.wdp.Interface.APIInterface;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.SearchDataModal;
import com.wdp.turnstr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchApiService implements Callback<SearchDataModal> {
    private APIInterface apiInterface;
    private ApiResponseListner apiResponseListner;
    private GoogleProgressDialog progressDialog;
    private Context context;
    private Boolean flag;

    public SearchApiService(Context context) {
        this.context = context;
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }



    public void Connect(String url,String token,String page,boolean flg,ApiResponseListner apiResponseListner) {
        flag = flg;
        this.apiResponseListner = apiResponseListner;
        Call<SearchDataModal> call = apiInterface.Search(url,token,page);
        call.enqueue(this);
        if (flag){
            setProgressDialog();
        }

    }

    private void setProgressDialog() {
        progressDialog = new GoogleProgressDialog(context);
        progressDialog.showDialog();
    }

    @Override
    public void onResponse(Call<SearchDataModal> call, Response<SearchDataModal> response) {
        SearchDataModal searchDataModal = response.body();
        if (flag){
            progressDialog.dismiss();
        }
        if (searchDataModal != null) {
            if (searchDataModal.isSuccess().equalsIgnoreCase("true")) {
                SuperCastClass superCastClass = searchDataModal;
                apiResponseListner.onSuccess(ApiConstants.search_view_TAG, superCastClass);
            } else {
                apiResponseListner.onFailure(searchDataModal.getMessage());
            }
        } else {
            apiResponseListner.onException(context.getResources().getString(R.string.server_data_not_found));
        }
    }

    @Override
    public void onFailure(Call<SearchDataModal> call, Throwable t) {
        if (flag){
            progressDialog.dismiss();
        }
        t.getMessage();
    }

}
