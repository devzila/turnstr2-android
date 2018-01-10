package com.adroidtech.turnstr2.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adroidtech.turnstr2.Activity.ProfileActivity;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.ViewHolders.MyStoryListingView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List allDataList;
    Context context;
    private OnItemClickListener listener;
    private ArrayList<Bitmap> mBbitmap1;

    public MyStoryAdapter(Context context, List allDataList, OnItemClickListener listener) {
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
                Cubesurfaceview view1 = new Cubesurfaceview(context, mBbitmap1,false);
                view.ll_main.addView(view1);
                Stack<String> strings1 = new Stack<>();
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
                        view.ll_main.removeAllViews();
                        Cubesurfaceview view1 = new Cubesurfaceview(context, mBbitmap1,false);
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

