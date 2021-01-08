package com.wdp.ActivityScreen;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wdp.Adapter.StoryCubeViewAdapter;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.Modal.StoryDataModal;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

public class StoryCubeViewActivity extends Activity implements StoryCubeViewAdapter.GetStoryViewGlViews {
    RecyclerView mRecyclerView;
    private boolean firstTime = true;
    StoryCubeViewAdapter storyCubeViewAdapter;
    List<StoryDataModal.DataBean.UsersBean.StoriesBean> dataList = new ArrayList<>();
    ImageView img_back;
    boolean checker =false;
    List<CubeGLSurfaceView> cubeStoryViewList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_full_viewt);
        initView();
        prepair();
    }

    private void initView(){
        mRecyclerView = findViewById(R.id.mRecyclerView);
        img_back = findViewById(R.id.img_back);
    }

    private void prepair(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        storyCubeViewAdapter = new StoryCubeViewAdapter(this, dataList, this);
        mRecyclerView.setAdapter(storyCubeViewAdapter);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loadStories();
    }

    /*private void loadStories(){
        new Runnable() {
            public void run() {
                Gson gson = new Gson();
                if (getIntent().getStringExtra("cubelist") != null) {
                    String imageUrl = getIntent().getStringExtra("cubelist");
                    TypeToken<List<StoryDataModal.DataBean.UsersBean.StoriesBean>> token = new TypeToken<List<StoryDataModal.DataBean.UsersBean.StoriesBean>>() {
                    };
                    dataList.addAll(gson.fromJson(imageUrl, token.getType()));
                    storyCubeViewAdapter.notifyDataSetChanged();
                }
            }
        }.run();
    }*/

    private void loadStories(){
        StoryDataModal.DataBean.UsersBean model;
        /*if (getIntent().getBundleExtra("story_data") != null) {
            model = (StoryDataModal.DataBean.UsersBean) getIntent().getBundleExtra("story_data").getSerializable("cubelist");
            dataList.addAll(model.getStories());
            storyCubeViewAdapter.notifyDataSetChanged();
        }*/

        if (getIntent().getSerializableExtra("story_data")!=null){
            model = (StoryDataModal.DataBean.UsersBean)getIntent().getSerializableExtra("story_data");
            dataList.addAll(model.getStories());
            storyCubeViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (cubeStoryViewList.size()>0){
            for (int i=0; i<cubeStoryViewList.size(); i++){
                cubeStoryViewList.get(i).onResume();
            }
        }*/
    }

    @Override
    public void onPause(){
        super.onPause();
        /*if (cubeStoryViewList.size()>0){
            for (int i=0; i<cubeStoryViewList.size(); i++){
                cubeStoryViewList.get(i).onPause();
            }
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void getstoryviewglviews(CubeGLSurfaceView cubeGLSurfaceView) {
        cubeStoryViewList.add(cubeGLSurfaceView);
    }
}
