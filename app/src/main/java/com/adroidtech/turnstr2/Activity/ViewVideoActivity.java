package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.adroidtech.turnstr2.Adapter.CommentsList_Adapter;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.CommentsModel;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.Models.UserFav5Model;
import com.adroidtech.turnstr2.Models.VideoStoryModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.Utils;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.WebServices.OkHttpRequestSender;
import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.google.gson.Gson;
import com.sendbird.android.shadow.com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ViewVideoActivity extends Activity implements AsyncCallback, View.OnClickListener {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private ArrayList<Bitmap> mBbitmap1 = new ArrayList<>();
    private FrameLayout layout_frame_main;
    private SharedPreference sharedPreference;
    private Cubesurfaceview view1;
    private TextView btnChat;
    private ImageView my_story;
    private FrameLayout layoutFrame;
    private ImageView like;
    private VideoStoryModel videoStoryModel;
    private ImageView share;
    private ImageView comments;
    private TextView user_name;
    private EasyVideoPlayer player;
//    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);
        videoStoryModel = (VideoStoryModel) getIntent().getSerializableExtra("VIDEO_DATA");
        sharedPreference = new SharedPreference(this);
        viewIntail();
        getStorieDetails();
    }

    private void getStorieDetails() {
        JSONObject mJson = new JSONObject();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.GET_VIDEO_STORIES_DETAIL
                .replace("_STORY_ID_", videoStoryModel.getId() + ""), mJson, headers).execute();
    }

    private void viewIntail() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        player = (EasyVideoPlayer) findViewById(R.id.video_player);
        videoPlayerUpdate(player, videoStoryModel.getUrl());
        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
        like = (ImageView) findViewById(R.id.like);
        user_name = (TextView) findViewById(R.id.user_name);
        like.setOnClickListener(this);
        comments = (ImageView) findViewById(R.id.comments);
        comments.setOnClickListener(this);
        share = (ImageView) findViewById(R.id.share);
        share.setOnClickListener(this);
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
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, videoStoryModel.getStatus());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, videoStoryModel.getUrl());
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            case R.id.layout_frame:
                startActivity(new Intent(ViewVideoActivity.this, MyStoryActivity.class));
                break;
            case R.id.edit_profile:
                startActivity(new Intent(ViewVideoActivity.this, EditProfileActivity.class));
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
                            new OkHttpRequestSender(ViewVideoActivity.this, new AsyncCallback() {
                                @Override
                                public void getAsyncResult(String jsonObject, String txt) {
                                    try {
                                        JSONObject jsonObject1 = new JSONObject(jsonObject);
                                        if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                                            getAllComments(recyclerView);
                                            Utils.hideKeyboard(ViewVideoActivity.this, comment);
                                            comment.setText("");
                                            comment.clearFocus();
                                        } else {
                                            Toast.makeText(ViewVideoActivity.this, "Sorry, Comment not Added", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, GeneralValues.BASE_URL + GeneralValues.POST_STORIE_COMMENTS
                                    .replace("_STORY_ID_", videoStoryModel.getId() + ""), formField,
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
                                Toast.makeText(ViewVideoActivity.this, allComments.size() + " Comments", Toast.LENGTH_SHORT).show();
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
                    .replace("_STORY_ID_", videoStoryModel.getId() + ""), mJson, headers).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postLikeComment() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        JSONObject mJson = new JSONObject();
        new CommonAsync(this, mJson, this, GeneralValues.STORIES_LIKES
                .replace("_STORY_ID_", videoStoryModel.getId() + ""), headers).execute();
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data", jsonObject.toString());
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                if (txt.contains("/likes")) {
                    Toast.makeText(this, jsonObject1.getString("message"), Toast.LENGTH_SHORT).show();
//                    videoStoryModel.setHasLiked(!videoStoryModel.getHasLiked());
                    updateLike();
                } else if (txt.contains("/v1/stories")) {
//                    videoStoryModel = new Gson().fromJson(jsonObject1.getJSONObject("data").getString("story"), MyStoryModel.class);
                    updateLike();
                }
            } else {
                Toast.makeText(ViewVideoActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (
                JSONException e)

        {
            e.printStackTrace();
        }

    }

    private void videoPlayerUpdate(EasyVideoPlayer player, String mediaUrl) {
        player.setCallback(new EasyVideoCallback() {
            @Override
            public void onStarted(EasyVideoPlayer player) {

            }

            @Override
            public void onPaused(EasyVideoPlayer player) {

            }

            @Override
            public void onPreparing(EasyVideoPlayer player) {

            }

            @Override
            public void onPrepared(EasyVideoPlayer player) {

            }

            @Override
            public void onBuffering(int percent) {

            }

            @Override
            public void onError(EasyVideoPlayer player, Exception e) {

            }

            @Override
            public void onCompletion(EasyVideoPlayer player) {

            }

            @Override
            public void onRetry(EasyVideoPlayer player, Uri source) {

            }

            @Override
            public void onSubmit(EasyVideoPlayer player, Uri source) {

            }
        });
        player.setSource(Uri.parse(mediaUrl));
        player.setAutoPlay(true);
    }

    private void updateLike() {
//        if (videoStoryModel.getHasLiked()) {
//            like.setImageResource(R.drawable.ic_liked);
//        } else {
//            like.setImageResource(R.drawable.ic_like);
//        }
    }

}
