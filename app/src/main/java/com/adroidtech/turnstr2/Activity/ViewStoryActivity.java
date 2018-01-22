package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ViewStoryActivity extends Activity implements AsyncCallback, View.OnClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private ArrayList<Bitmap> mBbitmap1 = new ArrayList<>();
    private FrameLayout layout_frame_main;
    private SharedPreference sharedPreference;
    private LoginDetailModel userDetail;
    private Cubesurfaceview view1;
    private TextView btnChat;
    private ImageView my_story;
    private FrameLayout layoutFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        viewIntail();
//        getProfileDataFromServer();
        loadAllImagesToCube();
    }

    private void getProfileDataFromServer() {
        JSONObject mJson = new JSONObject();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.LOGIN_URL, mJson, headers).execute();
    }

    private void viewIntail() {
        btnChat = (TextView) findViewById(R.id.btnChat);
        btnChat.setOnClickListener(this);
        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
        view = new Cubesurfaceview(ViewStoryActivity.this, mBbitmap, false);
        layout_frame_main.addView(view);
        layoutFrame = (FrameLayout) findViewById(R.id.layout_frame);
        view = new Cubesurfaceview(ViewStoryActivity.this, mBbitmap, false);
        layoutFrame.addView(view);
        layoutFrame.setOnClickListener(this);
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

        new URLImageParser(strings, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap = bitmap;
                layout_frame_main.removeAllViews();
                view = new Cubesurfaceview(ViewStoryActivity.this, mBbitmap, true);
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
                view1 = new Cubesurfaceview(ViewStoryActivity.this, mBbitmap1, false);
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ViewStoryActivity.this, MyStoryActivity.class));
                    }
                });
                layoutFrame.addView(view1);
//                addTextView();

            }
        }).execute();
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
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
            case R.id.layout_frame:
                startActivity(new Intent(ViewStoryActivity.this, MyStoryActivity.class));
                break;
            case R.id.edit_profile:
                startActivity(new Intent(ViewStoryActivity.this, EditProfileActivity.class));
                break;
            case R.id.btnChat:
                startActivity(new Intent(ViewStoryActivity.this, GroupChannelActivity.class));
                break;
        }
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data :", jsonObject.toString());
    }
}
