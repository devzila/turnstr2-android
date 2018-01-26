package com.adroidtech.turnstr2.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.Adapter.CommentsList_Adapter;
import com.adroidtech.turnstr2.Adapter.MyStoryAdapter;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.CommentsModel;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.Utils;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.WebServices.OkHttpRequestSender;
import com.adroidtech.turnstr2.chat.adapters.AllFriendList_Adapter;
import com.adroidtech.turnstr2.chat.groupchannel.GroupChannelActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sendbird.android.shadow.com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ViewStoryActivity extends Activity implements AsyncCallback, View.OnClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private ArrayList<Bitmap> mBbitmap1 = new ArrayList<>();
    private FrameLayout layout_frame_main;
    private SharedPreference sharedPreference;
    private MyStoryModel.User userDetail;
    private Cubesurfaceview view1;
    private TextView btnChat;
    private ImageView my_story;
    private FrameLayout layoutFrame;
    private List<MyStoryModel.Medium> cubeMedia;
    private ImageView like;
    private MyStoryModel myStoryModel;
    private ImageView share;
    private ImageView comments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);
        myStoryModel = (MyStoryModel) getIntent().getSerializableExtra("DATA");
        sharedPreference = new SharedPreference(this);
        userDetail = myStoryModel.getUser();
        cubeMedia = myStoryModel.getMedia();
        viewIntail();
        getStorieDetails();
//        getProfileDataFromServer();
        loadAllImagesToCube();
    }

    private void getStorieDetails() {
        JSONObject mJson = new JSONObject();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.GET_STORIES_DETAIL
                .replace("_STORY_ID_", myStoryModel.getId() + ""), mJson, headers).execute();
    }

    private void viewIntail() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
        like = (ImageView) findViewById(R.id.like);
        like.setOnClickListener(this);
        comments = (ImageView) findViewById(R.id.comments);
        comments.setOnClickListener(this);
        share = (ImageView) findViewById(R.id.share);
        share.setOnClickListener(this);
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
        try {
            for (int i = 0; i < cubeMedia.size(); i++)
                if (cubeMedia.get(i).getThumbUrl() != null && cubeMedia.get(i).getThumbUrl().length() > 1)
                    strings.push(cubeMedia.get(i).getThumbUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new URLImageParser(strings, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap = bitmap;
                layout_frame_main.removeAllViews();
                view = new Cubesurfaceview(ViewStoryActivity.this, mBbitmap, true, layout_frame_main);
                layout_frame_main.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewStoryActivity.this, ViewFullScrrenStoryActivity.class);
                        intent.putExtra("DATA_STORY", myStoryModel);
                        startActivity(intent);
                        Toast.makeText(ViewStoryActivity.this, "OPen New Act", Toast.LENGTH_SHORT).show();
                    }
                });
                layout_frame_main.addView(view);
            }
        }).execute();
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
            case R.id.like:
                postLikeComment();
                break;
            case R.id.comments:
                openCommentsDialog();
                break;
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
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

    private void openCommentsDialog() {
        try {
            final Dialog dialog = new Dialog(this, R.style.MaterialDialogSheet);
            dialog.setContentView(R.layout.dialog_comment_list);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            dialog.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            TextView post_comment = (TextView) dialog.findViewById(R.id.post_comment);
            final EditText comment = (EditText) dialog.findViewById(R.id.et_comment);
            final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view);
            final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.gravity = Gravity.CENTER;
            dialog.getWindow().setAttributes(params);
            post_comment.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override

                public void onClick(View v) {
                    String comm = comment.getText().toString().trim();
                    if (comm.length() > 0) {
                        //                    comment[body]
                        try {
                            ArrayMap<String, String> formField = new ArrayMap<>();
                            formField.put("comment[body]", comm);
                            new OkHttpRequestSender(ViewStoryActivity.this, new AsyncCallback() {
                                @Override
                                public void getAsyncResult(String jsonObject, String txt) {
                                    try {
                                        JSONObject jsonObject1 = new JSONObject(jsonObject);
                                        if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                                            getAllComments(recyclerView);
                                            Utils.hideKeyboard(ViewStoryActivity.this, comment);
                                            comment.setText("");
                                            comment.clearFocus();
                                        } else {
                                            Toast.makeText(ViewStoryActivity.this, "Sorry, Comment not Added", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, GeneralValues.BASE_URL + GeneralValues.POST_STORIE_COMMENTS
                                    .replace("_STORY_ID_", myStoryModel.getId() + ""), formField,
                                    sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN), "POST").execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            dialog.show();
            getAllComments(recyclerView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllComments(final RecyclerView recyclerView) {
        try {
            JSONObject mJson = new JSONObject();
            HashMap<String, String> headers = new HashMap<>();
            headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
            new CommonAsync(this, "GET", new AsyncCallback() {
                @Override
                public void getAsyncResult(String jsonObject, String txt) {
                    try {
                        JSONObject jsonObject1 = new JSONObject(jsonObject);
                        if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                            try {
                                Type listType = new TypeToken<ArrayList<CommentsModel>>() {
                                }.getType();
                                List<CommentsModel> allComments = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("comments"), listType);
                                Toast.makeText(ViewStoryActivity.this, allComments.size() + " Comments", Toast.LENGTH_SHORT).show();
                                allComments.size();
                                CommentsList_Adapter adapter = new CommentsList_Adapter(getApplicationContext(), allComments);
                                recyclerView.setAdapter(adapter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {

                    }
                }
            }, GeneralValues.GET_STORIE_COMMENTS
                    .replace("_STORY_ID_", myStoryModel.getId() + ""), mJson, headers).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postLikeComment() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        JSONObject mJson = new JSONObject();
        new CommonAsync(this, mJson, this, GeneralValues.STORIES_LIKES
                .replace("_STORY_ID_", myStoryModel.getId() + ""), headers).execute();
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data", jsonObject.toString());
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                if (txt.contains("/likes")) {
                    Toast.makeText(this, jsonObject1.getString("message"), Toast.LENGTH_SHORT).show();
                    myStoryModel.setHasLiked(!myStoryModel.getHasLiked());
                    updateLike();
                } else if (txt.contains(" / v1 / stories")) {
                    myStoryModel = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("story"), MyStoryModel.class);
                    updateLike();
                }
            } else {
                Toast.makeText(ViewStoryActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (
                JSONException e)

        {
            e.printStackTrace();
        }

    }

    private void updateLike() {
        if (myStoryModel.getHasLiked()) {
            like.setImageResource(R.drawable.ic_liked);
        } else {
            like.setImageResource(R.drawable.ic_like);
        }
    }

}
