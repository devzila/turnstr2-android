package com.adroidtech.turnstr2.chat.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.adroidtech.turnstr2.Activity.SplashActivtiy;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.WebServices.OkHttpRequest;
import com.adroidtech.turnstr2.chat.adapters.AllFriendList_Adapter;
import com.adroidtech.turnstr2.chat.adapters.FriendList_Adapter;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Request;

/**
 * Created by narinder on 18/12/17.
 */

public class AllFriendList extends AppCompatActivity implements AsyncCallback {


    private RecyclerView recyclerView;
    private AllFriendList_Adapter mAdapter;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
         sharedPreference = new SharedPreference(getApplicationContext());

        getVideoRequest();
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


        Log.e("TAG" ,"getAsyncResult............."+jsonObject);
        if(txt.trim().equals("members"))
        {

        }

    }

    private void getVideoRequest() {

        JSONObject mJson = new JSONObject();

        HashMap<String, String> extraHeaders=new HashMap<>();
        extraHeaders.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.MEMBERS_URL,mJson, extraHeaders).execute();
        Log.e("Tag", "test................");



    }


}

