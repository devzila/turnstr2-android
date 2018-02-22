package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.Adapter.MyStoryAdapter;
import com.adroidtech.turnstr2.CubeView.CubeSurfaceColored;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.CustomeViews.OnLoadMoreListener;
import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.Models.VideoStoryModel;
import com.adroidtech.turnstr2.Models.ViewUserDetailModel;
import com.adroidtech.turnstr2.Models.UserFav5Model;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.BitmapUtils;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.chat.groupchannel.GroupChannelActivity;
import com.google.gson.Gson;
import com.sendbird.android.shadow.com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UserProfileViewActivity extends Activity implements AsyncCallback, OnLoadMoreListener, View.OnClickListener, MyStoryAdapter.OnItemClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private ArrayList<Bitmap> mBbitmap1 = new ArrayList<>();
    private FrameLayout layout_frame_main;
    private SharedPreference sharedPreference;
    private UserFav5Model userDetail;
    private FrameLayout layoutFrame;
    private CubeSurfaceColored view1, view2, view3;
    private RecyclerView recycleView;
    private FrameLayout fram_fav1, fram_fav2, fram_fav3;
    private LinearLayout ll_fav5_users;
    private LinearLayout turnt_stories;
    private List<UserFav5Model> favStorysList;
    private List<UserFav5Model> allPopularStory;
    private List<MyStoryModel> allStorylisting;
    private TextView txtPosts;
    private TextView txtFollowers;
    private TextView txtFamily;
    private NestedScrollView nested_scroll;
    private ArrayList<Integer> mAllLoadedPages = new ArrayList<>();
    private int mPagLoaded = 1;
    private int lastItemIndexForScroll;
    private int mPageNo = 0;
    private int total_pages = 0;
    private int current_page = 0;
    private int mNextPage = 1;
    private ArrayList<VideoStoryModel> allVideoStoryModel = new ArrayList<>();
    private List<MyStoryModel> mAllStorylisting = new ArrayList<>();
    private MyStoryAdapter myStoryAdapter;
    private TextView favFive;
    private TextView follow;
    private ViewUserDetailModel userDetailModel;
    private TextView txtUsername;
    private TextView name_stories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_profile);
        viewIntail();
        getIntentData();
        getUserDetail();
        loadAllImagesToCube();
        getFav5FromServer();
        getAllVideos();
        getAllStorieFromServer();
//        getPopularStorieFromServer();
    }

    private void getAllVideos() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.GET_VIDEOS_MEMBERS
                .replace("_MEMBER_ID_", userDetail.getId() + ""), null, headers).execute();
    }

    private void updateVideoStorieViews(ArrayList<VideoStoryModel> allVideoStoryModel) {
        try {
            turnt_stories.removeAllViews();
            for (int i = 0; i < allVideoStoryModel.size(); i++) {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.video_thumb_view, turnt_stories, false);
                final ImageView thumb_image = (ImageView) view.findViewById(R.id.thumb_image);
                thumb_image.setTag(allVideoStoryModel.get(i));
                Picasso.with(this).load(allVideoStoryModel.get(i).getUrl()).into(thumb_image);
//                try {
//                    thumb_image.setImageBitmap(BitmapUtils.retriveVideoFrameFromVideo(allVideoStoryModel.get(i).getUrl()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                thumb_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VideoStoryModel userData = (VideoStoryModel) thumb_image.getTag();
                        Intent intent = new Intent(UserProfileViewActivity.this, ViewVideoActivity.class);
                        intent.putExtra("VIDEO_DATA", userData);
                        startActivity(intent);
                        Toast.makeText(UserProfileViewActivity.this, "Page OPen", Toast.LENGTH_SHORT).show();
                    }
                });
                turnt_stories.addView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getUserDetail() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        JSONObject mJson = new JSONObject();
        new CommonAsync(this, "GET", new AsyncCallback() {
            @Override
            public void getAsyncResult(String jsonObject, String txt) {
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonObject);
                    if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                        userDetailModel = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("member"), ViewUserDetailModel.class);
                        uiDataUpdate(userDetailModel);
                    } else {
                        Toast.makeText(UserProfileViewActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, GeneralValues.GET_MEMBERS_DATA.replace("_MEMBER_ID_", userDetail.getId() + ""), mJson, headers).execute();
    }

    private void getIntentData() {
        Intent recivedDataIntent = getIntent();
        if (recivedDataIntent.hasExtra("USER_DATA")) {
            userDetail = (UserFav5Model) recivedDataIntent.getSerializableExtra("USER_DATA");

        }
    }

    private void postFollowUser() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        JSONObject mJson = new JSONObject();
        new CommonAsync(this, mJson, new AsyncCallback() {
            @Override
            public void getAsyncResult(String jsonObject, String txt) {
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonObject);
                    if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                        userDetailModel.setFollowing(true);
                        follow.setBackgroundResource(R.drawable.sold_blue_rounded);
                        follow.setTextColor(getResources().getColor(R.color.white));
                        follow.setText("UnFollow");
                    } else {
                        Toast.makeText(UserProfileViewActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, GeneralValues.MEMBERS_FOLLOW.replace("_MEMBER_ID_", userDetail.getId() + ""), headers).execute();
    }

    private void postUnFollowUser() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        JSONObject mJson = new JSONObject();
        new CommonAsync(this, mJson, new AsyncCallback() {
            @Override
            public void getAsyncResult(String jsonObject, String txt) {
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonObject);
                    if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                        userDetailModel.setFollowing(false);
                        follow.setBackgroundResource(R.drawable.border_blue_rounded);
                        follow.setTextColor(getResources().getColor(R.color.text_blue));
                        follow.setText("Follow");
                    } else {
                        Toast.makeText(UserProfileViewActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, GeneralValues.MEMBERS_UNFOLLOW.replace("_MEMBER_ID_", userDetail.getId() + ""), headers).execute();
    }

    private void deleteFavFiveUser() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        JSONObject mJson = new JSONObject();
        new CommonAsync(this, "DELETE", new AsyncCallback() {
            @Override
            public void getAsyncResult(String jsonObject, String txt) {
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonObject);
                    if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                        userDetailModel.setFavourite(false);
                        favFive.setBackgroundResource(R.drawable.border_blue_rounded);
                        favFive.setTextColor(getResources().getColor(R.color.text_blue));
                    } else {
                        Toast.makeText(UserProfileViewActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, GeneralValues.MEMBERS_FAVOURITES.replace("_MEMBER_ID_", userDetail.getId() + ""), mJson, headers).execute();
    }

    private void postFavFiveUser() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        JSONObject mJson = new JSONObject();
        new CommonAsync(this, mJson, new AsyncCallback() {
            @Override
            public void getAsyncResult(String jsonObject, String txt) {
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonObject);
                    if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                        userDetailModel.setFavourite(true);
                        favFive.setBackgroundResource(R.drawable.sold_blue_rounded);
                        favFive.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        Toast.makeText(UserProfileViewActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, GeneralValues.MEMBERS_FAVOURITES.replace("_MEMBER_ID_", userDetail.getId() + ""), headers).execute();
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
                new CommonAsync(this, "GET", this, GeneralValues.GET_ALL_MEMBERS_STORIES
                        .replace("_MEMBER_ID_", userDetail.getId() + ""), mJson, headers).execute();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getPopularStorieFromServer() {
        if (allPopularStory == null || allPopularStory.size() == 0) {
            HashMap<String, String> headers = new HashMap<>();
//        headers.put("auth_token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJpYXQiOiIyMDE3LTA2LTE0IDA2OjU0OjM0IFVUQyJ9.M7pgzA4ktNVvuDFvKMqESJfHmLQCobp0WNjC6k2Kjac");
            headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
            new CommonAsync(this, "GET", this, GeneralValues.GET_POPULARS_STORIES, null, headers).execute();
        } else {
            updatePopularStorieViews(allPopularStory);
        }
    }

    private void getFav5FromServer() {
        if (favStorysList == null || favStorysList.size() == 0) {
            HashMap<String, String> headers = new HashMap<>();
//        headers.put("auth_token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJpYXQiOiIyMDE3LTA2LTE0IDA2OjU0OjM0IFVUQyJ9.M7pgzA4ktNVvuDFvKMqESJfHmLQCobp0WNjC6k2Kjac");
            headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
            new CommonAsync(this, "GET", this, GeneralValues.GET_USER_FAVE5
                    .replace("_MEMBER_ID_", userDetail.getId() + ""), null, headers).execute();
        } else {
            updateFav5Views(favStorysList);
        }
    }

    private void uiDataUpdate(ViewUserDetailModel userDetail) {
        try {
            if (userDetailModel.getFollowing()) {
                follow.setBackgroundResource(R.drawable.sold_blue_rounded);
                follow.setTextColor(getResources().getColor(R.color.white));
                follow.setText("UnFollow");
            } else {
                follow.setText("Follow");
                follow.setBackgroundResource(R.drawable.border_blue_rounded);
                follow.setTextColor(getResources().getColor(R.color.text_blue));
            }
            if (userDetailModel.getFavourite()) {
                favFive.setBackgroundResource(R.drawable.sold_blue_rounded);
                favFive.setTextColor(getResources().getColor(R.color.white));
            } else {
                favFive.setBackgroundResource(R.drawable.border_blue_rounded);
                favFive.setTextColor(getResources().getColor(R.color.text_blue));
            }
            txtFollowers.setText(userDetail.getFollowerCount() + "");
            txtPosts.setText(userDetail.getPostCount() + "");
            txtUsername.setText(userDetail.getFirstName().toUpperCase() + " FAVE 5");
            name_stories.setText(userDetail.getFirstName().toUpperCase() + " STORIES");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewIntail() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycleView = (RecyclerView) findViewById(R.id.recycler_listview);
        ll_fav5_users = (LinearLayout) findViewById(R.id.ll_fav5_users);
        turnt_stories = (LinearLayout) findViewById(R.id.turnt_stories);
        txtUsername = (TextView) findViewById(R.id.username_fav);
        txtPosts = (TextView) findViewById(R.id.txt_posts);
        favFive = (TextView) findViewById(R.id.fav_five);
        name_stories = (TextView) findViewById(R.id.name_stories);
        follow = (TextView) findViewById(R.id.follow);
        favFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDetailModel.getFavourite()) {
                    deleteFavFiveUser();
                } else {
                    postFavFiveUser();
                }
            }
        });
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDetailModel.getFollowing()) {
                    postUnFollowUser();
                } else {
                    postFollowUser();
                }
            }
        });
        txtFollowers = (TextView) findViewById(R.id.txt_followers);
        txtFamily = (TextView) findViewById(R.id.txt_family);
        nested_scroll = (NestedScrollView) findViewById(R.id.nested_scroll);
        nested_scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int dx, int dy, int oldScrollX, int oldScrollY) {
                if (dy > 0 && oldScrollY < dy) {
                    GridLayoutManager linearLayoutManager = (GridLayoutManager) recycleView.getLayoutManager();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    if (totalItemCount == (lastVisibleItem + 1)) {
                        UserProfileViewActivity.this.onLoadMore();
                        lastItemIndexForScroll = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    }
                }
            }
        });

        GridLayoutManager mGridManager = new GridLayoutManager(this, 3);
        recycleView.setLayoutManager(mGridManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        sharedPreference = new SharedPreference(this);
        findViewById(R.id.nav_contact).setOnClickListener(this);
        findViewById(R.id.nav_box).setOnClickListener(this);
        findViewById(R.id.nav_image).setOnClickListener(this);
        findViewById(R.id.nav_video).setOnClickListener(this);
        findViewById(R.id.nav_chat).setOnClickListener(this);


        layoutFrame = (FrameLayout) findViewById(R.id.layout_frame);
        view = new Cubesurfaceview(UserProfileViewActivity.this, mBbitmap, false);
        layoutFrame.addView(view);
        layoutFrame.setOnClickListener(this);
    }


    private void loadAllImagesToCube() {
        try {
            final Stack<String> strings1 = new Stack<>();
            strings1.push(userDetail.getAvatarFace1());
            strings1.push(userDetail.getAvatarFace2());
            strings1.push(userDetail.getAvatarFace3());
            strings1.push(userDetail.getAvatarFace4());
            strings1.push(userDetail.getAvatarFace5());
            strings1.push(userDetail.getAvatarFace6());
            new URLImageParser(strings1, new URLImageParser.AsyncCallback() {
                @Override
                public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                    mBbitmap1 = bitmap;
                    layoutFrame.removeAllViews();
                    view1 = new CubeSurfaceColored(UserProfileViewActivity.this, mBbitmap1, true, layoutFrame, "1:0.6f:0");
                    view1.setZOrderOnTop(false);
                    //                layoutFrame.setOnClickListener(new View.OnClickListener() {
                    //                    @Override
                    //                    public void onClick(View v) {
                    //                        startActivity(new Intent(HomePageActivity.this, MyStoryActivity.class));
                    //                    }
                    //                });
                    layoutFrame.addView(view1);
                    //                addTextView();

                }
            }).execute();
        } catch (Exception e) {

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
//            loadAllImagesToCube();
//            getFav5FromServer();
//            getAllStorieFromServer();+
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
            case (R.id.nav_contact):
                startActivity(new Intent(UserProfileViewActivity.this, ProfileActivity.class));
                finish();
                break;
            case (R.id.nav_box):
                startActivity(new Intent(UserProfileViewActivity.this, HomePageActivity.class));
                finish();
                break;
            case (R.id.nav_image):
//                startActivity(new Intent(HomePageActivity.this,. class));
                break;
            case (R.id.nav_video):
//                startActivity(new Intent(HomePageActivity.this,. class));
                break;
            case (R.id.nav_chat):
                startActivity(new Intent(UserProfileViewActivity.this, GroupChannelActivity.class));
                finish();
                break;

            case R.id.layout_frame:
                startActivity(new Intent(UserProfileViewActivity.this, MyStoryActivity.class));
                break;
            case R.id.edit_profile:
                startActivity(new Intent(UserProfileViewActivity.this, EditProfileActivity.class));
                break;

        }
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data", jsonObject.toString());
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                if (txt.contains("/videos")) {
                    Type listType = new TypeToken<ArrayList<VideoStoryModel>>() {
                    }.getType();
                    allVideoStoryModel = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("stories"), listType);
                    updateVideoStorieViews(allVideoStoryModel);
                } else if (txt.contains("/populars")) {
                    Type listType = new TypeToken<ArrayList<UserFav5Model>>() {
                    }.getType();
                    allPopularStory = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("members"), listType);
                    updatePopularStorieViews(allPopularStory);
                } else if (txt.contains("/favourites")) {
                    Type listType = new TypeToken<ArrayList<UserFav5Model>>() {
                    }.getType();
                    favStorysList = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("favourites"), listType);
                    updateFav5Views(favStorysList);
                } else if (txt.contains("/stories")) {
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
                        myStoryAdapter = new MyStoryAdapter(UserProfileViewActivity.this, mAllStorylisting, this);
                        recycleView.setAdapter(myStoryAdapter);
                    }
                    mNextPage = 1 + mPagLoaded;
                    total_pages = jsonObject1.getJSONObject("data").getInt("total_pages");
                    current_page = jsonObject1.getJSONObject("data").getInt("current_page");
                }
            } else {
                Toast.makeText(UserProfileViewActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//
    }


    private void updatePopularStorieViews(List<UserFav5Model> allStorylist) {
        try {
            turnt_stories.removeAllViews();
            for (int i = 0; i < allStorylist.size(); i++) {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.fav_view_user, turnt_stories, false);
                final FrameLayout fram_fav = (FrameLayout) view.findViewById(R.id.fram_fav);
                CubeSurfaceColored view2 = new CubeSurfaceColored(UserProfileViewActivity.this, null, false, fram_fav, "1:1:1");
                view2.setZOrderOnTop(false);
                fram_fav.addView(view2);
                fram_fav.setTag(allStorylist.get(i));
                view.findViewById(R.id.name).setVisibility(View.GONE);
                Stack<String> allUrls = new Stack<>();
                allUrls.push(allStorylist.get(i).getAvatarFace1());
                allUrls.push(allStorylist.get(i).getAvatarFace2());
                allUrls.push(allStorylist.get(i).getAvatarFace3());
                allUrls.push(allStorylist.get(i).getAvatarFace4());
                allUrls.push(allStorylist.get(i).getAvatarFace5());
                allUrls.push(allStorylist.get(i).getAvatarFace6());
                new URLImageParser(allUrls, new URLImageParser.AsyncCallback() {
                    @Override
                    public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                        fram_fav.removeAllViews();
                        CubeSurfaceColored view = new CubeSurfaceColored(UserProfileViewActivity.this, bitmap, false, fram_fav, "1:1:1");
                        view.setZOrderOnTop(false);
                        fram_fav.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(UserProfileViewActivity.this, "User Page OPen", Toast.LENGTH_SHORT).show();
                                userDetail = (UserFav5Model) fram_fav.getTag();
                                Intent intent = new Intent(UserProfileViewActivity.this, UserProfileViewActivity.class);
                                intent.putExtra("USER_DATA", userDetail);
                                startActivity(intent);
                                finish();
//                                startActivity(new Intent(HomePageActivity.this, MyStoryActivity.class));
                            }
                        });
                        fram_fav.addView(view);
//                addTextView();

                    }
                }).execute();
                turnt_stories.addView(view);
            }
        } catch (Exception e) {

        }
    }


    private void updateFav5Views(List<UserFav5Model> allStorylist) {
        try {
            ll_fav5_users.removeAllViews();
            for (int i = 0; i < allStorylist.size(); i++) {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.fav_view_user, ll_fav5_users, false);
                final FrameLayout fram_fav = (FrameLayout) view.findViewById(R.id.fram_fav);
                CubeSurfaceColored view2 = new CubeSurfaceColored(UserProfileViewActivity.this, null, false, fram_fav, "1:1:1");
                view2.setZOrderOnTop(false);
                fram_fav.addView(view2);
                fram_fav.setTag(allStorylist.get(i));
                TextView name = (TextView) view.findViewById(R.id.name);
                name.setText(allStorylist.get(i).getFirstName());
                Stack<String> allUrls = new Stack<>();
                allUrls.push(allStorylist.get(i).getAvatarFace1());
                allUrls.push(allStorylist.get(i).getAvatarFace2());
                allUrls.push(allStorylist.get(i).getAvatarFace3());
                allUrls.push(allStorylist.get(i).getAvatarFace4());
                allUrls.push(allStorylist.get(i).getAvatarFace5());
                allUrls.push(allStorylist.get(i).getAvatarFace6());
                new URLImageParser(allUrls, new URLImageParser.AsyncCallback() {
                    @Override
                    public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                        fram_fav.removeAllViews();
                        CubeSurfaceColored view = new CubeSurfaceColored(UserProfileViewActivity.this, bitmap, false, fram_fav, "1:1:1");
                        view.setZOrderOnTop(false);
                        fram_fav.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                userDetail = (UserFav5Model) fram_fav.getTag();
                                Intent intent = new Intent(UserProfileViewActivity.this, UserProfileViewActivity.class);
                                intent.putExtra("USER_DATA", userDetail);
                                startActivity(intent);
                                finish();
//                                startActivity(new Intent(HomePageActivity.this, MyStoryActivity.class));
                            }
                        });
                        fram_fav.addView(view);
//                addTextView();

                    }
                }).execute();
                ll_fav5_users.addView(view);
            }
        } catch (Exception e) {

        }
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
