package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.Adapter.MyStoryAdapter;
import com.adroidtech.turnstr2.Adapter.VideoStoryAdapter;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.CustomeViews.OnLoadMoreListener;
import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.Models.VideoStoryModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.google.gson.Gson;
import com.sendbird.android.shadow.com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MyVideoStoryActivity extends Activity implements AsyncCallback, View.OnClickListener, OnLoadMoreListener, MyStoryAdapter.OnItemClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private SharedPreference sharedPreference;
    private FrameLayout layoutFrame;
    private RecyclerView recycleView;
    private LoginDetailModel userDetail;
    final Stack<String> strings = new Stack<>();
    public static Boolean isStoryCreated = false;
    private ArrayList<Integer> mAllLoadedPages = new ArrayList<>();
    private ArrayList<VideoStoryModel> allVideoStoryModel = new ArrayList<>();
    private int mPageNo = 0;
    private int total_pages = 0;
    private int current_page = 0;
    private int mNextPage = 1;
    private int mPagLoaded = 1;
    private VideoStoryAdapter myStoryAdapter;
    private int lastItemIndexForScroll = 0;
    private NestedScrollView nested_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_story);
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        viewIntail();
        getAllStorieFromServer();
//        getProfileDataFromServer();
    }

    private void getAllStorieFromServer() {
        try {
            if (!mAllLoadedPages.contains(mNextPage)) {
                mPagLoaded = mNextPage;
                mAllLoadedPages.add(mNextPage);
                JSONObject mJson = new JSONObject();
                mJson.put("page", mNextPage + "");
                HashMap<String, String> headers = new HashMap<>();
                headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
                new CommonAsync(this, "GET", this, GeneralValues.GET_VIDEOS_MEMBERS
                        .replace("_MEMBER_ID_", userDetail.getUser().getId() + ""), mJson, headers).execute();

            }
        } catch (JSONException e) {

        }
    }

    private void viewIntail() {
        recycleView = (RecyclerView) findViewById(R.id.recycler_listview);
        GridLayoutManager mGridManager = new GridLayoutManager(this, 3);
        nested_scroll = (NestedScrollView) findViewById(R.id.nested_scroll);
        recycleView.setLayoutManager(mGridManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        nested_scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int dy, int oldScrollX, int oldScrollY) {
                if (dy > 0 && oldScrollY < dy) {
                    GridLayoutManager linearLayoutManager = (GridLayoutManager) recycleView.getLayoutManager();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    if (totalItemCount == (lastVisibleItem + 1)) {
                        MyVideoStoryActivity.this.onLoadMore();
                        lastItemIndexForScroll = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    }
                }
            }
        });

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
                startActivity(new Intent(MyVideoStoryActivity.this, CreateStoryActivity.class));
            }
        });
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
                view = new Cubesurfaceview(MyVideoStoryActivity.this, mBbitmap, true);
                layoutFrame.addView(view);
            }
        }).execute();

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
                Type listType = new TypeToken<ArrayList<VideoStoryModel>>() {
                }.getType();

                ArrayList<VideoStoryModel> allStorylisting = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("stories"), listType);
                int size = allVideoStoryModel.size();
                this.allVideoStoryModel.addAll(allStorylisting);
                if (myStoryAdapter != null) {
                    for (int i = 0; i < allStorylisting.size(); i++) {
                        this.allVideoStoryModel.add(allStorylisting.get(i));
                        myStoryAdapter.notifyItemChanged(allVideoStoryModel.size());
                    }
                } else {
                    myStoryAdapter = new VideoStoryAdapter(MyVideoStoryActivity.this, allVideoStoryModel);
                    recycleView.setAdapter(myStoryAdapter);
                }
                mNextPage = 1 + mPagLoaded;
                total_pages = jsonObject1.getJSONObject("data").getInt("total_pages");
                current_page = jsonObject1.getJSONObject("data").getInt("current_page");
            } else {
                Toast.makeText(MyVideoStoryActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//
    }

    @Override
    public void ClickAction(int position) {

    }

    @Override
    public void onLoadMore() {
        if (mPagLoaded < total_pages)
            getAllStorieFromServer();
    }
}