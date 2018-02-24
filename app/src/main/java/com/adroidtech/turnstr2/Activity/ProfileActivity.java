package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.CubeView.CubeSurfaceColored;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.chat.groupchannel.GroupChannelActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ProfileActivity extends Activity implements AsyncCallback, View.OnClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private ArrayList<Bitmap> mBbitmap1 = new ArrayList<>();
    private FrameLayout layout_frame_main;
    private SharedPreference sharedPreference;
    private LoginDetailModel userDetail;
    private TextView editProfile;
    private TextView txt_logout;
    private TextView txtPosts;
    private TextView txtFollowers;
    private TextView txtFamily;
    private FrameLayout layoutFrame;
    private TextView txtUsername;
    private TextView txtAbout;
    private TextView txtName;
    private TextView txtWebsite;
    private CubeSurfaceColored view1;
    private TextView btnChat;
    private ImageView my_story;
    private TextView myVideos;
    private TextView my_photos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        viewIntail();
//        getProfileDataFromServer();
//        uiDataUpdate(userDetail);
//        loadAllImagesToCube();
    }

    private void getProfileDataFromServer() {
        JSONObject mJson = new JSONObject();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.LOGIN_URL, mJson, headers).execute();
    }

    private void uiDataUpdate(LoginDetailModel userDetail) {

        txtAbout.setText(userDetail.getUser().getBio());
        txtName.setText(userDetail.getUser().getFirstName());
        txtWebsite.setText(userDetail.getUser().getWebsite());
        txtFamily.setText(userDetail.getUser().getFamilyCount() + "");
        txtFollowers.setText(userDetail.getUser().getFollowerCount() + "");
        txtPosts.setText(userDetail.getUser().getPostCount() + "");

        txtUsername.setText(userDetail.getUser().getUsername() + "");
        if (userDetail.getUser().getIsVerified()) {
            txtUsername.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.star_verification, 0);
        } else {
            txtUsername.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }

    private void viewIntail() {
        findViewById(R.id.my_story).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MyStoryActivity.class));
            }
        });
        editProfile = (TextView) findViewById(R.id.edit_profile);
        myVideos = (TextView) findViewById(R.id.my_videos);
        my_photos = (TextView) findViewById(R.id.my_photos);
        myVideos.setOnClickListener(this);
        txt_logout = (TextView) findViewById(R.id.txt_logout);
        txtPosts = (TextView) findViewById(R.id.txt_posts);
        txtFollowers = (TextView) findViewById(R.id.txt_followers);
        txtFamily = (TextView) findViewById(R.id.txt_family);
        txtUsername = (TextView) findViewById(R.id.txt_username);
        txtAbout = (TextView) findViewById(R.id.txt_about);
        txtName = (TextView) findViewById(R.id.txt_name);
        txtWebsite = (TextView) findViewById(R.id.txt_website);
        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
        view = new Cubesurfaceview(ProfileActivity.this, mBbitmap, false);
        layout_frame_main.addView(view);
        layoutFrame = (FrameLayout) findViewById(R.id.layout_frame);
        view1 = new CubeSurfaceColored(ProfileActivity.this, mBbitmap1, true, layoutFrame, "1:0.6f:0");
        view1.setZOrderOnTop(false);
        layoutFrame.addView(view1);
        txt_logout.setOnClickListener(this);
        layoutFrame.setOnClickListener(this);
        editProfile.setOnClickListener(this);
        findViewById(R.id.nav_contact).setOnClickListener(this);
        findViewById(R.id.nav_box).setOnClickListener(this);
        findViewById(R.id.nav_image).setOnClickListener(this);
        findViewById(R.id.nav_video).setOnClickListener(this);
        findViewById(R.id.nav_chat).setOnClickListener(this);
    }

    private void loadAllImagesToCube() {
        final Stack<String> strings = new Stack<>();
        final Stack<String> strings1 = new Stack<>();
        strings.push(userDetail.getUser().getAvatarFace1());
        strings.push(userDetail.getUser().getAvatarFace2());
        strings.push(userDetail.getUser().getAvatarFace3());
        strings.push(userDetail.getUser().getAvatarFace4());
        strings.push(userDetail.getUser().getAvatarFace5());
        strings.push(userDetail.getUser().getAvatarFace6());
//        strings.push("https://lorempixel.com/100/100/");
//        strings.push("https://lorempixel.com/100/100/");
//        strings.push("https://lorempixel.com/100/100/");
//        strings.push("https://lorempixel.com/100/100/");
//        strings.push("https://lorempixel.com/100/100/");
//        strings.push("https://lorempixel.com/100/100/");

        new URLImageParser(strings, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap = bitmap;
                layout_frame_main.removeAllViews();
                view = new Cubesurfaceview(ProfileActivity.this, mBbitmap, true);
                layout_frame_main.addView(view);
            }
        }).execute();
        strings1.push(userDetail.getUser().getAvatarFace1());
        strings1.push(userDetail.getUser().getAvatarFace2());
        strings1.push(userDetail.getUser().getAvatarFace3());
        strings1.push(userDetail.getUser().getAvatarFace4());
        strings1.push(userDetail.getUser().getAvatarFace5());
        strings1.push(userDetail.getUser().getAvatarFace6());
        new URLImageParser(strings1, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap1 = bitmap;
                layoutFrame.removeAllViews();
                view1 = new CubeSurfaceColored(ProfileActivity.this, mBbitmap1, true, layoutFrame, "1:0.6f:0");
                view1.setZOrderOnTop(false);
//                view1 = new Cubesurfaceview(ProfileActivity.this, mBbitmap1, false);
//                view1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(ProfileActivity.this, MyStoryActivity.class));
//                    }
//                });
                layoutFrame.addView(view1);
//                addTextView();

            }
        }).execute();
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
            uiDataUpdate(userDetail);
            loadAllImagesToCube();

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
            case R.id.my_videos:
                startActivity(new Intent(this, MyVideoStoryActivity.class));
                break;
            case (R.id.nav_contact):
//                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                break;
            case (R.id.nav_box):
                startActivity(new Intent(ProfileActivity.this, HomePageActivity.class));
                finish();
                break;
            case (R.id.nav_image):
//                startActivity(new Intent(HomePageActivity.this,. class));
                break;
            case (R.id.nav_video):
//                startActivity(new Intent(HomePageActivity.this,. class));
                break;
            case (R.id.nav_chat):
                startActivity(new Intent(ProfileActivity.this, GroupChannelActivity.class));
                finish();
                break;
            case R.id.layout_frame:
                startActivity(new Intent(ProfileActivity.this, MyStoryActivity.class));
                break;
            case R.id.edit_profile:
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
                break;
            case R.id.txt_logout:
                new SharedPreference(ProfileActivity.this).clearSharedPreference();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
                break;
        }
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                userDetail = new Gson().fromJson(jsonObject1.getString("data"), LoginDetailModel.class);
                sharedPreference.putSerializableObject(PreferenceKeys.USER_DETAIL, userDetail);
                uiDataUpdate(userDetail);
                loadAllImagesToCube();
            } else {
                Toast.makeText(ProfileActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
