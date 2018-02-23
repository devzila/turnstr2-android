package com.adroidtech.turnstr2.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adroidtech.turnstr2.Activity.MyStoryActivity;
import com.adroidtech.turnstr2.Activity.ProfileActivity;
import com.adroidtech.turnstr2.Activity.ViewStoryActivity;
import com.adroidtech.turnstr2.CubeView.CubeImageColored;
import com.adroidtech.turnstr2.CubeView.CubeSurfaceColored;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.ViewHolders.MyStoryListingView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<MyStoryModel> allDataList;
    Activity context;
    private OnItemClickListener listener;
    private ArrayList<Bitmap> mBbitmap1;

    public MyStoryAdapter(Activity context, List<MyStoryModel> allDataList, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.allDataList = allDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_story, parent, false);
        return new MyStoryListingView(itemView, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        try {
            final MyStoryListingView view = (MyStoryListingView) holder;
            view.bind(listener, position);
            try {
                Stack<String> strings1 = new Stack<>();
                for (int i = 0; i < allDataList.get(position).getMedia().size(); i++) {
                    strings1.push(allDataList.get(position).getMedia().get(i).getThumbUrl());
                }
                new URLImageParser(strings1, new URLImageParser.AsyncCallback() {
                    @Override
                    public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                        mBbitmap1 = bitmap;
                        view.ll_main.removeAllViews();
                        CubeImageColored view1 = new CubeImageColored(context, mBbitmap1, view.imageView, view.ll_main);
                        view1.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
//                        CubeSurfaceColored view1 = new CubeSurfaceColored(context, mBbitmap1, false, view.ll_main, "1:1:1");
//                        view1.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
//                        view1.setZOrderOnTop(false);
                        view.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, ViewStoryActivity.class);
                                intent.putExtra("DATA", allDataList.get(position));
                                context.startActivity(intent);
                            }
                        });
                        view.ll_main.addView(view1);
                    }
                }).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
//        return 5;
        return allDataList == null ? 0 : allDataList.size();
    }

    public interface OnItemClickListener {
        void ClickAction(int position);
    }
}

