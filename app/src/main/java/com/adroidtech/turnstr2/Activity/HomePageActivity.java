package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.Adapter.MyStoryAdapter;
import com.adroidtech.turnstr2.CubeView.CubeSurfaceColored;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;

import com.adroidtech.turnstr2.GoLive.StartGoLive_Activity;

import com.adroidtech.turnstr2.CustomeViews.OnLoadMoreListener;

import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.Models.UserFav5Model;
import com.adroidtech.turnstr2.Models.VideoStoryModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.BitmapUtils;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.Utils;
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
    private int mNextPage = 1;
    private MyStoryAdapter myStoryAdapter;
    private int mPagLoaded = 1;
    private EditText et_search;
    private ArrayList<Integer> mAllLoadedPages = new ArrayList<>();
    private ArrayList<VideoStoryModel> allVideoStoryModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        viewIntail();
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        getFav5FromServer();
        getAllVideos();
        getAllStorieFromServer();
//        getPopularStorieFromServer();
    }

    private void getAllVideos() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.GET_VIDEOS, null, headers).execute();
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
                new CommonAsync(this, "GET", this, GeneralValues.GET_ALL_STORIES, mJson, headers).execute();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getPopularStorieFromServer() {
        if (allPopularStory == null || allPopularStory.size() == 0) {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
            new CommonAsync(this, "GET", this, GeneralValues.GET_POPULARS_STORIES, null, headers).execute();
        } else {
            updatePopularStorieViews(allPopularStory);
        }
    }

    private void getFav5FromServer() {
        if (favStorysList == null || favStorysList.size() == 0) {
            HashMap<String, String> headers = new HashMap<>();
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
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.FLAG_EDITOR_ACTION)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    try {
                        String searchText = et_search.getText().toString();
                        Intent intent = new Intent(HomePageActivity.this, SearchStoryActivity.class);
                        intent.putExtra("SEARCH_TEXT", searchText);
                        startActivity(intent);

//                        searchApi(searchText);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
        recycleView = (RecyclerView) findViewById(R.id.recycler_listview);
        ll_fav5_users = (LinearLayout) findViewById(R.id.ll_fav5_users);
        turnt_stories = (LinearLayout) findViewById(R.id.turnt_stories);
        nested_scroll = (NestedScrollView) findViewById(R.id.nested_scroll);
        nested_scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int dx, int dy, int oldScrollX, int oldScrollY) {
                if (dy > 0 && oldScrollY < dy) {
                    try {
                        GridLayoutManager linearLayoutManager = (GridLayoutManager) recycleView.getLayoutManager();
                        int totalItemCount = linearLayoutManager.getItemCount();
                        int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                        if (totalItemCount == (lastVisibleItem + 1)) {
                            HomePageActivity.this.onLoadMore();
                            lastItemIndexForScroll = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                        }
                    } catch (Exception e) {
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
    }

    private void searchApi(String searchText) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.SEARCH_STORIES + searchText, null, headers).execute();

    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            Utils.hideKeyboard(this);
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
                startActivity(new Intent(HomePageActivity.this, StartGoLive_Activity.class));
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
                } else if (txt.equalsIgnoreCase(GeneralValues.GET_ALL_STORIES)) {
                    Type listType = new TypeToken<ArrayList<MyStoryModel>>() {
                    }.getType();
                    List<MyStoryModel> allStorylisting = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("stories"), listType);
                    int size = mAllStorylisting.size();
                    if (myStoryAdapter != null) {
                        for (int i = 0; i < allStorylisting.size(); i++) {
                            this.mAllStorylisting.add(allStorylisting.get(i));
                            myStoryAdapter.notifyItemChanged(mAllStorylisting.size());
                        }
//                        myStoryAdapter.notifyItemRangeChanged(size, allStorylisting.size());
                    } else {
                        this.mAllStorylisting.addAll(allStorylisting);
                        myStoryAdapter = new MyStoryAdapter(HomePageActivity.this, mAllStorylisting, this);
                        recycleView.setAdapter(myStoryAdapter);
                    }
                    mNextPage = 1 + mPagLoaded;
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
                        try {
                            VideoStoryModel userData = (VideoStoryModel) thumb_image.getTag();
                            Intent intent = new Intent(HomePageActivity.this, ViewVideoActivity.class);
                            intent.putExtra("VIDEO_DATA", userData);
                            startActivity(intent);
                            Toast.makeText(HomePageActivity.this, "Page OPen", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {

                        }
                    }
                });
                turnt_stories.addView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void updatePopularStorieViews(List<UserFav5Model> allStorylist) {
        try {
            turnt_stories.removeAllViews();
            for (int i = 0; i < allStorylist.size(); i++) {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.fav_view_user, turnt_stories, false);
                final FrameLayout fram_fav = (FrameLayout) view.findViewById(R.id.fram_fav);
                CubeSurfaceColored view2 = new CubeSurfaceColored(HomePageActivity.this, null, false, fram_fav, "1:1:1");
                view2.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
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
                        view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
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
            e.printStackTrace();
        }
    }


    private void updateFav5Views(List<UserFav5Model> allStorylist) {
        try {
            ll_fav5_users.removeAllViews();
            for (int i = 0; i < allStorylist.size(); i++) {
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.fav_view_user, ll_fav5_users, false);
                final FrameLayout fram_fav =
                        (FrameLayout) view.findViewById(R.id.fram_fav);
//                CubeSurfaceColored view2 = new CubeSurfaceColored(HomePageActivity.this, null, false, fram_fav, "1:1:1");
//                view2.setZOrderOnTop(false);
//                view2.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
//                fram_fav.addView(view2);
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
                        try {
                            fram_fav.removeAllViews();
                            CubeSurfaceColored view = new CubeSurfaceColored(HomePageActivity.this, bitmap, false, fram_fav, "1:1:1");
                            view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                            view.setZOrderOnTop(false);
                            view.setZOrderMediaOverlay(true);
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
                        } catch (Exception e) {

                        }
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
