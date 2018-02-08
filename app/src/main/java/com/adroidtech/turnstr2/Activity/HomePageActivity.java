package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
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
import com.adroidtech.turnstr2.Models.UserFav5Model;
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

public class HomePageActivity extends Activity implements AsyncCallback, OnLoadMoreListener, View.OnClickListener, MyStoryAdapter.OnItemClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private ArrayList<Bitmap> mBbitmap1 = new ArrayList<>();
    private FrameLayout layout_frame_main;
    private SharedPreference sharedPreference;
    private LoginDetailModel userDetail;
    private TextView editProfile;
    private LinearLayout search;
    private FrameLayout layoutFrame;
    private TextView txtUsername;
    private TextView txtAbout;
    private TextView txtAddress;
    private TextView txtEmail;
    private CubeSurfaceColored view1, view2, view3;
    private View btnChat;
    private ImageView my_story;
    private RecyclerView recycleView;
    private FrameLayout fram_fav1, fram_fav2, fram_fav3;
    private LinearLayout ll_fav5_users;
    private LinearLayout turnt_stories;
    private List<UserFav5Model> favStorysList;
    private List<UserFav5Model> allPopularStory;
    private List<MyStoryModel> mAllStorylisting = new ArrayList<>();
    private TextView txtPosts;
    private TextView txtFollowers;
    private TextView txtFamily;
    private int lastItemIndexForScroll = 0;
    private NestedScrollView nested_scroll;
    private int mPageNo = 0;
    private int total_pages = 0;
    private int current_page = 0;
    private int mNextPage = 0;
    private MyStoryAdapter myStoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        viewIntail();
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        getFav5FromServer();
        getAllStorieFromServer();
        getPopularStorieFromServer();
    }

    private void getAllStorieFromServer() {
        try {
            JSONObject mJson = new JSONObject();
            mJson.put("page", mNextPage + "");
            HashMap<String, String> headers = new HashMap<>();
            headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
            new CommonAsync(this, "GET", this, GeneralValues.GET_ALL_STORIES, null, headers).execute();
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
                    .replace("_MEMBER_ID_", userDetail.getUser().getId() + ""), null, headers).execute();
        } else {
            updateFav5Views(favStorysList);
        }
    }

    private void uiDataUpdate(LoginDetailModel userDetail) {
        txtAbout.setText(userDetail.getUser().getBio());
        txtAddress.setText(userDetail.getUser().getAddress());
        txtEmail.setText(userDetail.getUser().getEmail());

        txtFamily.setText(userDetail.getUser().getFamilyCount() + "");
        txtFollowers.setText(userDetail.getUser().getFollowerCount() + "");
        txtPosts.setText(userDetail.getUser().getPostCount() + "");

        txtUsername.setText(userDetail.getUser().getFirstName() + "");
        if (userDetail.getUser().getIsVerified()) {
            txtUsername.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.star_verification, 0);
        } else {
            txtUsername.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }

    private void viewIntail() {
        recycleView = (RecyclerView) findViewById(R.id.recycler_listview);
        ll_fav5_users = (LinearLayout) findViewById(R.id.ll_fav5_users);
        turnt_stories = (LinearLayout) findViewById(R.id.turnt_stories);
        nested_scroll = (NestedScrollView) findViewById(R.id.nested_scroll);
        nested_scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int dx, int dy, int oldScrollX, int oldScrollY) {
                if (dy > 0) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recycleView.getLayoutManager();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (totalItemCount == (lastVisibleItem + 1)) {
                        HomePageActivity.this.onLoadMore();
                        lastItemIndexForScroll = linearLayoutManager.findFirstVisibleItemPosition();
                    }
                }
            }
        });
        GridLayoutManager mGridManager = new GridLayoutManager(this, 3);
        recycleView.setLayoutManager(mGridManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (totalItemCount == (lastVisibleItem + 1)) {
                        HomePageActivity.this.onLoadMore();
                        lastItemIndexForScroll = linearLayoutManager.findFirstVisibleItemPosition();
                    }
                }
            }
        });
        sharedPreference = new SharedPreference(this);
        findViewById(R.id.nav_contact).setOnClickListener(this);
        findViewById(R.id.nav_box).setOnClickListener(this);
        findViewById(R.id.nav_image).setOnClickListener(this);
        findViewById(R.id.nav_video).setOnClickListener(this);
        findViewById(R.id.nav_chat).setOnClickListener(this);
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
                startActivity(new Intent(HomePageActivity.this, ProfileActivity.class));
                finish();
                break;
            case (R.id.nav_box):
//                startActivity(new Intent(HomePageActivity.this,.class));
                break;
            case (R.id.nav_image):
//                startActivity(new Intent(HomePageActivity.this,. class));
                break;
            case (R.id.nav_video):
//                startActivity(new Intent(HomePageActivity.this,. class));
                break;
            case (R.id.nav_chat):
                startActivity(new Intent(HomePageActivity.this, GroupChannelActivity.class));
                finish();
                break;

            case R.id.layout_frame:
                startActivity(new Intent(HomePageActivity.this, MyStoryActivity.class));
                break;
            case R.id.edit_profile:
                startActivity(new Intent(HomePageActivity.this, EditProfileActivity.class));
                break;
        }
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data", jsonObject.toString());
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                if (txt.contains("/populars")) {
                    Type listType = new TypeToken<ArrayList<UserFav5Model>>() {
                    }.getType();
                    allPopularStory = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("members"), listType);
                    updatePopularStorieViews(allPopularStory);
                } else if (txt.contains("/favourites")) {
                    Type listType = new TypeToken<ArrayList<UserFav5Model>>() {
                    }.getType();
                    favStorysList = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("favourites"), listType);
                    updateFav5Views(favStorysList);
                } else if (txt.equalsIgnoreCase(GeneralValues.GET_ALL_STORIES)) {
                    Type listType = new TypeToken<ArrayList<MyStoryModel>>() {
                    }.getType();
                    List<MyStoryModel> allStorylisting = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("stories"), listType);
                    int size = mAllStorylisting.size();
                    this.mAllStorylisting.addAll(allStorylisting);
                    if (myStoryAdapter != null) {
                        myStoryAdapter.notifyItemRangeChanged(size, allStorylisting.size());
                    } else {
                        myStoryAdapter = new MyStoryAdapter(HomePageActivity.this, mAllStorylisting, this);
                        recycleView.setAdapter(myStoryAdapter);
                    }
                    mNextPage = jsonObject1.getJSONObject("data").getInt("next_page");
                    total_pages = jsonObject1.getJSONObject("data").getInt("total_pages");
                    current_page = jsonObject1.getJSONObject("data").getInt("current_page");

                }
            } else {
                Toast.makeText(HomePageActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
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
                CubeSurfaceColored view2 = new CubeSurfaceColored(HomePageActivity.this, null, false, fram_fav, "1:1:1");
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
                        CubeSurfaceColored view = new CubeSurfaceColored(HomePageActivity.this, bitmap, false, fram_fav, "1:1:1");
                        view.setZOrderOnTop(false);
                        fram_fav.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UserFav5Model userData = (UserFav5Model) fram_fav.getTag();
                                Intent intent = new Intent(HomePageActivity.this, UserProfileViewActivity.class);
                                intent.putExtra("USER_DATA", userData);
                                startActivity(intent);
                                Toast.makeText(HomePageActivity.this, "Page OPen", Toast.LENGTH_SHORT).show();
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
                CubeSurfaceColored view2 = new CubeSurfaceColored(HomePageActivity.this, null, false, fram_fav, "1:1:1");
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
                        CubeSurfaceColored view = new CubeSurfaceColored(HomePageActivity.this, bitmap, false, fram_fav, "1:1:1");
                        view.setZOrderOnTop(false);
                        fram_fav.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UserFav5Model userData = (UserFav5Model) fram_fav.getTag();
                                Intent intent = new Intent(HomePageActivity.this, UserProfileViewActivity.class);
                                intent.putExtra("USER_DATA", userData);
                                startActivity(intent);
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
        getAllStorieFromServer();
        Toast.makeText(this, "Load data", Toast.LENGTH_SHORT).show();
    }
}
