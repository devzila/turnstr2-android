package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.Adapter.MyStoryAdapter;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.chat.groupchannel.GroupChannelActivity;
import com.google.gson.Gson;
import com.sendbird.android.shadow.com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MyStoryActivity extends Activity implements AsyncCallback, View.OnClickListener, MyStoryAdapter.OnItemClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private SharedPreference sharedPreference;
    private FrameLayout layoutFrame;
    private RecyclerView recycleView;
    private LoginDetailModel userDetail;
    final Stack<String> strings = new Stack<>();
    private TextView txtPosts;
    private TextView txtFollowers;
    private TextView txtFamily;
    public static Boolean isStoryCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_story);
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        viewIntail();
        getAllStorieFromServer();
//        getProfileDataFromServer();
    }

    private void getAllStorieFromServer() {
        HashMap<String, String> headers = new HashMap<>();
//        headers.put("auth_token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJpYXQiOiIyMDE3LTA2LTE0IDA2OjU0OjM0IFVUQyJ9.M7pgzA4ktNVvuDFvKMqESJfHmLQCobp0WNjC6k2Kjac");
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.USER_STORIES, null, headers).execute();
    }

    private void viewIntail() {
        recycleView = (RecyclerView) findViewById(R.id.recycler_listview);
        GridLayoutManager mGridManager = new GridLayoutManager(this, 3);
        recycleView.setLayoutManager(mGridManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        layoutFrame = (FrameLayout) findViewById(R.id.layout_frame);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.new_story).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyStoryActivity.this, CreateStoryActivity.class));
            }
        });
        view = new Cubesurfaceview(MyStoryActivity.this, mBbitmap, false);
        layoutFrame.addView(view);
        strings.push(userDetail.getUser().getAvatarFace1());
        strings.push(userDetail.getUser().getAvatarFace2());
        strings.push(userDetail.getUser().getAvatarFace3());
        strings.push(userDetail.getUser().getAvatarFace4());
        strings.push(userDetail.getUser().getAvatarFace5());
        strings.push(userDetail.getUser().getAvatarFace6());
        new URLImageParser(strings, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap = bitmap;
                layoutFrame.removeAllViews();
                view = new Cubesurfaceview(MyStoryActivity.this, mBbitmap, true);
                layoutFrame.addView(view);
            }
        }).execute();
        txtPosts = (TextView) findViewById(R.id.txt_posts);
        txtFollowers = (TextView) findViewById(R.id.txt_followers);
        txtFamily = (TextView) findViewById(R.id.txt_family);
        txtFamily.setText(userDetail.getUser().getFamilyCount() + "");
        txtFollowers.setText(userDetail.getUser().getFollowerCount() + "");
        txtPosts.setText(userDetail.getUser().getPostCount() + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (isStoryCreated) {
                isStoryCreated = false;
                getAllStorieFromServer();
            }
//            if (view != null) view.onResume();
        } catch (Exception e) {

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
//            if (view != null) view.onPause();
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data", jsonObject.toString());
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                if (txt.equalsIgnoreCase(GeneralValues.USER_STORIES)) {
                    Type listType = new TypeToken<ArrayList<MyStoryModel>>() {
                    }.getType();
                    List<MyStoryModel> allStorylist = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("stories"), listType);
                    MyStoryAdapter myStoryAdapter = new MyStoryAdapter(MyStoryActivity.this, allStorylist, this);
                    recycleView.setAdapter(myStoryAdapter);
                }
            } else {
                Toast.makeText(MyStoryActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//
    }

    @Override
    public void ClickAction(int position) {

    }
}
