package com.wdp.Adapter;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;


import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.CubeFullViewDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.turnstr.R;


import java.util.ArrayList;
import java.util.List;


public class StoryCubeViewAdapter extends RecyclerView.Adapter<StoryCubeViewAdapter.MediaFullViewHolder> {
    public int Position;
    public Context context;
    public List<StoryDataModal.DataBean.UsersBean.StoriesBean> dataList;
    ArrayList<CubeFullViewDataModal> cubedatalist = new ArrayList<>();
    GetStoryViewGlViews getStoryViewGlViews;

    public interface GetStoryViewGlViews {
        public void getstoryviewglviews(CubeGLSurfaceView cubeGLSurfaceView);
    }

    public StoryCubeViewAdapter(Context context, List<StoryDataModal.DataBean.UsersBean.StoriesBean> posts, GetStoryViewGlViews getStoryViewGlViews) {
        this.context = context;
        this.dataList = posts;
        this.getStoryViewGlViews = getStoryViewGlViews;
    }

    @Override
    public MediaFullViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_iteam_full_view_media_adapter_, parent, false);
        return  new MediaFullViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MediaFullViewHolder holder, int position) {
        StoryDataModal.DataBean.UsersBean.StoriesBean current = dataList.get(position);
        /*new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {*/
                holder.glView = new CubeGLSurfaceView(context,"",current.getMedia_profile());
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
                holder.linearLayout.addView(holder.glView );
                holder.dragControl = new DragControl(context,"",current.getMedia_profile());
                holder.glView.setOnTouchListener(holder.dragControl);
                holder.glView.setDragControl(holder.dragControl);
                holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
                holder.glView.setZOrderOnTop(true);
                holder.glView.setZOrderMediaOverlay(true);
                getStoryViewGlViews.getstoryviewglviews(holder.glView);
            /*}
        }, 2000);*/


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MediaFullViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        CubeGLSurfaceView glView;
        DragControl dragControl;
        public MediaFullViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.mediaContainer);

        }
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

}

