package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.adroidtech.turnstr2.Adapter.CustomPagerAdapter;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CustomeViews.CubeOutTransformer;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.SharedPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ViewFullScrrenStoryActivity extends Activity {
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
    private CustomPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_story);
        try {
            sharedPreference = new SharedPreference(this);
            myStoryModel = (MyStoryModel) getIntent().getSerializableExtra("DATA_STORY");
            userDetail = myStoryModel.getUser();
            cubeMedia = myStoryModel.getMedia();
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewIntail(cubeMedia);
    }


    private void viewIntail(List<MyStoryModel.Medium> allMedia) {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
        try {
            ArrayList<MyStoryModel.Medium> allURL = new ArrayList<>();
            for (int i = 0; i < allMedia.size(); i++)
                if (allMedia.get(i).getMediaUrl() != null && allMedia.get(i).getMediaUrl().length() > 1)
                    allURL.add(allMedia.get(i));
            int loc = 0;
            while (allURL.size() > 0 && allURL.size() < 6)
                allURL.add(allURL.get(loc++));

            this.mPagerAdapter = new CustomPagerAdapter(this, allURL);
            pager.setAdapter(mPagerAdapter);
            pager.setPageTransformer(true, new CubeOutTransformer());
        } catch (Exception e) {
        }
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


}
