package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import org.json.JSONObject;
import org.w3c.dom.Text;

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
    private LinearLayout search;
    private TextView txtPosts;
    private TextView txtFollowers;
    private TextView txtFamily;
    private FrameLayout layoutFrame;
    private TextView txtUsername;
    private TextView txtAbout;
    private TextView txtAddress;
    private TextView txtEmail;
    private Cubesurfaceview view1;
    private TextView btnChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        viewIntail();
        uiDataUpdate(userDetail);
//        getProfileDataFromServer();
        loadAllImagesToCube();
    }

    private void getProfileDataFromServer() {
        JSONObject mJson = new JSONObject();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.LOGIN_URL, mJson, headers).execute();
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

        btnChat=(TextView)findViewById(R.id.btnChat);
        btnChat.setOnClickListener(this);

        editProfile = (TextView) findViewById(R.id.edit_profile);
        search = (LinearLayout) findViewById(R.id.search);
        txtPosts = (TextView) findViewById(R.id.txt_posts);
        txtFollowers = (TextView) findViewById(R.id.txt_followers);
        txtFamily = (TextView) findViewById(R.id.txt_family);
        txtUsername = (TextView) findViewById(R.id.txt_username);
        txtAbout = (TextView) findViewById(R.id.txt_about);
        txtAddress = (TextView) findViewById(R.id.txt_address);
        txtEmail = (TextView) findViewById(R.id.txt_email);
        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
        view = new Cubesurfaceview(ProfileActivity.this, mBbitmap);
        layout_frame_main.addView(view);
        layoutFrame = (FrameLayout) findViewById(R.id.layout_frame);
        view = new Cubesurfaceview(ProfileActivity.this, mBbitmap);
        layoutFrame.addView(view);
        editProfile.setOnClickListener(this);
    }

    private void loadAllImagesToCube() {
        final Stack<String> strings = new Stack<>();
        final Stack<String> strings1 = new Stack<>();
//        strings.push(userDetail.getUser().getAvatarFace1());
//        strings.push(userDetail.getUser().getAvatarFace2());
//        strings.push(userDetail.getUser().getAvatarFace3());
//        strings.push(userDetail.getUser().getAvatarFace4());
//        strings.push(userDetail.getUser().getAvatarFace5());
//        strings.push(userDetail.getUser().getAvatarFace6());
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");

        new URLImageParser(strings, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap = bitmap;
                layout_frame_main.removeAllViews();
                view = new Cubesurfaceview(ProfileActivity.this, mBbitmap);
                layout_frame_main.addView(view);
            }
        }).execute();
        strings1.push("https://lorempixel.com/25/25/");
        strings1.push("https://lorempixel.com/25/25/");
        strings1.push("https://lorempixel.com/25/25/");
        strings1.push("https://lorempixel.com/25/25/");
        strings1.push("https://lorempixel.com/25/25/");
        strings1.push("https://lorempixel.com/25/25/");
        new URLImageParser(strings1, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap1 = bitmap;
                layoutFrame.removeAllViews();
                view1 = new Cubesurfaceview(ProfileActivity.this, mBbitmap1);
                layoutFrame.addView(view1);

            }
        }).execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (view != null) view.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (view != null) view.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_profile:
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
                break;
            case R.id.btnChat:
                startActivity(new Intent(ProfileActivity.this, GroupChannelActivity.class));
                break;
        }
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data :", jsonObject.toString());
    }
}