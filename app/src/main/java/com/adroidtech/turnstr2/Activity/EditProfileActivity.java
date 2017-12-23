package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.Stack;

public class EditProfileActivity extends Activity implements View.OnClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
        view = new Cubesurfaceview(EditProfileActivity.this, mBbitmap);
        layout_frame_main.addView(view);
//        viewIntail();
//        uiDataUpdate(userDetail);
        loadAllImagesToCube();
    }

//    private void uiDataUpdate(LoginDetailModel userDetail) {
//        txtAbout.setText(userDetail.getUser().getBio());
//        txtAddress.setText(userDetail.getUser().getAddress());
//        txtEmail.setText(userDetail.getUser().getEmail());
//        txtFamily.setText(userDetail.getUser().getFamilyCount() + "");
//        txtFollowers.setText(userDetail.getUser().getFollowerCount() + "");
//        txtPosts.setText(userDetail.getUser().getPostCount() + "");
//        txtUsername.setText(userDetail.getUser().getFirstName() + "");
//        if (userDetail.getUser().getIsVerified()) {
//            txtUsername.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.star_verification, 0);
//        } else {
//            txtUsername.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//        }
//    }
//
//    private void viewIntail() {
//        editProfile = (TextView) findViewById(R.id.edit_profile);
//        search = (LinearLayout) findViewById(R.id.search);
//        txtPosts = (TextView) findViewById(R.id.txt_posts);
//        txtFollowers = (TextView) findViewById(R.id.txt_followers);
//        txtFamily = (TextView) findViewById(R.id.txt_family);
//        layoutFrame = (FrameLayout) findViewById(R.id.layout_frame);
//        txtUsername = (TextView) findViewById(R.id.txt_username);
//        txtAbout = (TextView) findViewById(R.id.txt_about);
//        txtAddress = (TextView) findViewById(R.id.txt_address);
//        txtEmail = (TextView) findViewById(R.id.txt_email);
//        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
//        editProfile.setOnClickListener(this);
//    }

    private void loadAllImagesToCube() {
        final Stack<String> strings = new Stack<>();
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
                view = new Cubesurfaceview(EditProfileActivity.this, mBbitmap);
                layout_frame_main.addView(view);
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

                break;
        }
    }
}
