package com.wdp.ActivityScreen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wdp.Adapter.MeStoryCubeViewAdapter;
import com.wdp.Adapter.StoryCubeViewAdapter;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

public class MeStoryCubeViewActivity extends Activity {
    RecyclerView mRecyclerView;
    private boolean firstTime = true;
    MeStoryCubeViewAdapter meStoryCubeViewAdapter;
    List<MeStoryDataModal.DataBean.StoriesBean> dataList = new ArrayList<>();
    ImageView img_back;
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
        Gson gson = new Gson();
        if (getIntent().getStringExtra("mestorylist") != null){
            String  imageUrl = getIntent().getStringExtra("mestorylist");
            TypeToken<List<MeStoryDataModal.DataBean.StoriesBean>> token = new TypeToken<List<MeStoryDataModal.DataBean.StoriesBean>>() {};
            dataList = gson.fromJson(imageUrl, token.getType());
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        meStoryCubeViewAdapter = new MeStoryCubeViewAdapter(this, dataList);
        mRecyclerView.setAdapter(meStoryCubeViewAdapter);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
