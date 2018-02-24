package com.adroidtech.turnstr2.Adapter;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adroidtech.turnstr2.Activity.HomePageActivity;
import com.adroidtech.turnstr2.Activity.UserProfileViewActivity;
import com.adroidtech.turnstr2.Activity.ViewStoryActivity;
import com.adroidtech.turnstr2.CubeView.CubeImageColored;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.Models.UserFav5Model;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.ViewHolders.MyStoryListingView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MembersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<UserFav5Model> allDataList;
    Activity context;
    private ArrayList<Bitmap> mBbitmap1;

    public MembersAdapter(Activity context, List<UserFav5Model> allDataList, OnItemClickListener listener) {
        this.context = context;
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
            try {
                Stack<String> allUrls = new Stack<>();
                allUrls.push(allDataList.get(position).getAvatarFace1());
                allUrls.push(allDataList.get(position).getAvatarFace2());
                allUrls.push(allDataList.get(position).getAvatarFace3());
                allUrls.push(allDataList.get(position).getAvatarFace4());
                allUrls.push(allDataList.get(position).getAvatarFace5());
                allUrls.push(allDataList.get(position).getAvatarFace6());
                new URLImageParser(allUrls, new URLImageParser.AsyncCallback() {
                    @Override
                    public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                        try {
                            mBbitmap1 = bitmap;
                            view.ll_main.removeAllViews();
                            CubeImageColored view1 = new CubeImageColored(context, mBbitmap1, view.imageView, view.ll_main);
                            view1.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                            view.imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    UserFav5Model userData = allDataList.get(position);
                                    Intent intent = new Intent(context, UserProfileViewActivity.class);
                                    intent.putExtra("USER_DATA", userData);
                                    context.startActivity(intent);
                                }
                            });
                            view.ll_main.addView(view1);
                        } catch (Exception e) {

                        }
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

