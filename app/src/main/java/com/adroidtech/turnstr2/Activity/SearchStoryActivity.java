package com.adroidtech.turnstr2.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.Adapter.MembersAdapter;
import com.adroidtech.turnstr2.Adapter.MyStoryAdapter;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.CustomeViews.OnLoadMoreListener;
import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.Models.UserFav5Model;
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

public class SearchStoryActivity extends Activity implements AsyncCallback, View.OnClickListener, MyStoryAdapter.OnItemClickListener, MembersAdapter.OnItemClickListener {
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
    private String searchText;
    private int mPageNo = 0;
    private int total_pages = 0;
    private int current_page = 0;
    private int mNextPage = 1;
    private MyStoryAdapter myStoryAdapter;
    private int mPagLoaded = 1;
    private EditText et_search;
    private ArrayList<Integer> mAllLoadedPages = new ArrayList<>();
    private List<MyStoryModel> mAllStorylisting = new ArrayList<>();
    private int lastItemIndexForScroll = 0;
    private RecyclerView recyclerMembers;
    private NestedScrollView nestedScroll;
    private MembersAdapter membersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sharedPreference = new SharedPreference(this);
        searchText = getIntent().getStringExtra("SEARCH_TEXT");
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        viewIntail();
        searchApi(searchText);
//        getProfileDataFromServer();
    }

    private void searchApi(String searchText) {
        try {
            if (!mAllLoadedPages.contains(mNextPage)) {
                mPagLoaded = mNextPage;
                mAllLoadedPages.add(mNextPage);
                JSONObject mJson = new JSONObject();
                mJson.put("keyword", searchText);
                mJson.put("page", mNextPage + "");
                HashMap<String, String> headers = new HashMap<>();
                headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
                new CommonAsync(this, "GET", this, GeneralValues.SEARCH_STORIES, mJson, headers).execute();
            }
        } catch (JSONException e) {

        }
    }

    @SuppressLint("NewApi")
    private void viewIntail() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.append(searchText != null ? searchText : "");
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.FLAG_EDITOR_ACTION)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    try {
                        String Text = et_search.getText().toString();
                        if (!Text.equalsIgnoreCase(searchText)) {
                            searchText = Text;
                            mAllLoadedPages.clear();
                            mNextPage = 1;
                            mPagLoaded = 1;
                            mAllStorylisting.clear();
                            searchApi(searchText);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
        recyclerMembers = (RecyclerView) findViewById(R.id.recycler_members_list);
        recycleView = (RecyclerView) findViewById(R.id.recycler_listview);
        nestedScroll = (NestedScrollView) findViewById(R.id.nested_scroll);

        GridLayoutManager mGridManager = new GridLayoutManager(this, 3);
        recycleView.setLayoutManager(mGridManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());

        GridLayoutManager mGridManag = new GridLayoutManager(this, 3);
        recyclerMembers.setLayoutManager(mGridManag);
        recyclerMembers.setItemAnimator(new DefaultItemAnimator());
        layoutFrame = (FrameLayout) findViewById(R.id.layout_frame);
        view = new Cubesurfaceview(SearchStoryActivity.this, mBbitmap, false);
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
                view = new Cubesurfaceview(SearchStoryActivity.this, mBbitmap, true);
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
                Type listType = new TypeToken<ArrayList<MyStoryModel>>() {
                }.getType();
                List<MyStoryModel> allStorylisting = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("stories"), listType);

                Type listTypeM = new TypeToken<ArrayList<UserFav5Model>>() {
                }.getType();
                List<UserFav5Model> allUserListing = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("members"), listTypeM);

                membersAdapter = new MembersAdapter(SearchStoryActivity.this, allUserListing, this);
                recyclerMembers.setAdapter(membersAdapter);

                myStoryAdapter = new MyStoryAdapter(SearchStoryActivity.this, allStorylisting, this);
                recycleView.setAdapter(myStoryAdapter);
            } else {
                Toast.makeText(SearchStoryActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
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
