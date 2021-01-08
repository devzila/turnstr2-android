package com.wdp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wdp.ActivityScreen.StoryCubeViewActivity;
import com.wdp.Agora.live.LiveStartActivity;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.CubeModal;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.CubeDataModal;
import com.wdp.Modal.CubeFullViewDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {
    ArrayList<StoryDataModal.DataBean.UsersBean> dataList;
    private Context context;
    ArrayList<CubeModal> storylist = new ArrayList<>();
    List<CubeFullViewDataModal> cubedatalist = new ArrayList<>();
    GetStoryGlViews getStoryGlViews;
    private RecyclerViewReadyCallback recyclerViewReadyCallback;

    public interface GetStoryGlViews {
        public void getstoryglviews(CubeGLSurfaceView cubeGLSurfaceView, StoryDataModal.DataBean.UsersBean dataBean);
    }

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }

    public StoryAdapter(Context context, ArrayList<StoryDataModal.DataBean.UsersBean> datalist, GetStoryGlViews getStoryGlViews) {
        this.context = context;
        this.dataList = datalist;
        this.getStoryGlViews = getStoryGlViews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_story, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StoryDataModal.DataBean.UsersBean dataBean = dataList.get(position);
        String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";
        if (dataBean.getAvatar()!= null){
            avatar1 = dataBean.getAvatar().getThumb();
        }
        if (dataBean.getAvatar2()!= null){
            avatar2 = dataBean.getAvatar2().getThumb();
        }
        if (dataBean.getAvatar3()!= null){
            avatar3 = dataBean.getAvatar3().getThumb();
        }
        if (dataBean.getAvatar4()!= null){
            avatar4 = dataBean.getAvatar4().getThumb();
        }
        if (dataBean.getAvatar5()!= null){
            avatar5 = dataBean.getAvatar5().getThumb();
        }
        if (dataBean.getAvatar6()!= null){
            avatar6 = dataBean.getAvatar6().getThumb();
        }

        storyview(holder,context,context,avatar1,avatar2,avatar3,
                avatar4,avatar5,avatar6,dataBean.getStories());
         holder.txtuser.setText(dataBean.getName());
        if (dataBean.isIs_broadcasting().equalsIgnoreCase("true")){
            holder.txt_live.setVisibility(View.VISIBLE);
        }
        else {
            holder.txt_live.setVisibility(View.GONE);
        }

        holder.txt_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataBean.isIs_broadcasting().equalsIgnoreCase("true")){
                    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
                    commonSharedPreference.setLiveType(context, "audience");
                    commonSharedPreference.setTitleName(context, dataBean.getUsername());
                    Intent intent = new Intent(context, LiveStartActivity.class);
                    context.startActivity(intent);
                }

            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataBean.getStories().size() > 0){
                    /*Intent i = new Intent(context, StoryCubeViewActivity.class);
                    Gson gson = new Gson();
                    String arrayData = gson.toJson(dataBean.getStories());
                    i.putExtra("cubelist",arrayData);
                    context.startActivity(i);*/

                    /*Intent i = new Intent(context, StoryCubeViewActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("cubelist",(Serializable)dataBean.getStories());
                    i.putExtra("story_data",args);
                    context.startActivity(i);*/
                    getStoryGlViews.getstoryglviews(holder.glView, dataBean);
                }

            }
        });
    }

    private void storyview(MyViewHolder holder,Context context, Context context1, String avatar,
                           String avatar2, String avatar3, String avatar4, String avatar5, String avatar6,
                           List<StoryDataModal.DataBean.UsersBean.StoriesBean> stories) {
        holder.glView = new CubeGLSurfaceView(context,avatar,avatar2,avatar3,avatar4,avatar5,avatar6);
        holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.linearLayout.addView(holder.glView);
        holder.dragControl = new DragControl(context,context,avatar,avatar2,avatar3,avatar4,avatar5,avatar6,stories);
        //holder.glView.setOnTouchListener(holder.dragControl);
        holder.glView.setDragControl(holder.dragControl);
        holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder.glView.setZOrderOnTop(true);
        holder.glView.setZOrderMediaOverlay(true);
        getStoryGlViews.getstoryglviews(holder.glView, null);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        CubeGLSurfaceView glView;
        DragControl dragControl;
        TextView txt_live,txtuser;
        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout);
            txt_live = itemView.findViewById(R.id.txt_live);
            txtuser = itemView.findViewById(R.id.txtuser);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
