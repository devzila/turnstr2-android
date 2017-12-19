package com.adroidtech.turnstr2.chat.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.WebServices.OkHttpRequest;
import com.adroidtech.turnstr2.chat.adapters.AllFriendList_Adapter;
import com.adroidtech.turnstr2.chat.adapters.FriendList_Adapter;

import okhttp3.Request;

/**
 * Created by narinder on 18/12/17.
 */

public class AllFriendList extends AppCompatActivity implements AsyncCallback {


    private RecyclerView recyclerView;
    private AllFriendList_Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new AllFriendList_Adapter(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {

    }


    private void getVideoRequest() {

        try {
            Request request = new Request.Builder()
                    //.url(GeneralValues.BASE_URL + GeneralValues.VIDEO_REQUEST + "?z=11&lat1=30.85102179338146&lat2=30.760197764380482&lng1=76.25823631816411&lng2=77.18452110820317")
                    .url(GeneralValues.BASE_URL +GeneralValues.MEMBERS_URL )
                    .get()
                    .addHeader("auth_token", sharedPreference.getString(PreferenceKeys.GLOBAL_TOKEN))
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .addHeader("cache-control", "no-cache")
                    .build();
          //  new CommonAsync(this, mJson, this, GeneralValues.LOGIN_URL, "LOGIN_URL").execute();
            //new OkHttpRequest(this, , "Video Loading...", "VIDEO_REQUEST", this, request).execute();
            new OkHttpRequest(getApplicationContext(), "Data loading....", this, request,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

