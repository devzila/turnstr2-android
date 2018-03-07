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

public class MyStoryActivity extends Activity implements AsyncCallback, View.OnClickListener, OnLoadMoreListener, MyStoryAdapter.OnItemClickListener {
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
    private ArrayList<Integer> mAllLoadedPages = new ArrayList<>();
    private ArrayList<VideoStoryModel> allVideoStoryModel = new ArrayList<>();
    private int mPageNo = 0;
    private int total_pages = 0;
    private int current_page = 0;
    private int mNextPage = 1;
    private List<MyStoryModel> mAllStorylisting = new ArrayList<>();
    private int mPagLoaded = 1;
    private MyStoryAdapter myStoryAdapter;
    private int lastItemIndexForScroll = 0;
    private NestedScrollView nested_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_story);
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        viewIntail();
        getAllStorieFromServer();
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
                new CommonAsync(this, "GET", this, GeneralValues.USER_STORIES, mJson, headers).execute();
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
                        MyStoryActivity.this.onLoadMore();
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
                startActivity(new Intent(MyStoryActivity.this, CreateStoryActivity.class));
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
//                recreate();
                isStoryCreated = false;
                mAllStorylisting.clear();
                myStoryAdapter = null;
                mNextPage = 1;
                mAllLoadedPages.clear();
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

                    List<MyStoryModel> allStorylisting = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("stories"), listType);
                    int size = mAllStorylisting.size();
                    this.mAllStorylisting.addAll(allStorylisting);
                    if (myStoryAdapter != null) {
                        for (int i = 0; i < allStorylisting.size(); i++) {
                            this.mAllStorylisting.add(allStorylisting.get(i));
                            myStoryAdapter.notifyItemChanged(mAllStorylisting.size());
                        }
                    } else {
                        myStoryAdapter = new MyStoryAdapter(MyStoryActivity.this, mAllStorylisting, this);
                        recycleView.setAdapter(myStoryAdapter);
                    }
                    mNextPage = 1 + mPagLoaded;
                    total_pages = jsonObject1.getJSONObject("data").getInt("total_pages");
                    current_page = jsonObject1.getJSONObject("data").getInt("current_page");
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

    @Override
    public void onLoadMore() {
        if (mPagLoaded < total_pages)
            getAllStorieFromServer();
    }
}
