package com.wdp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wdp.ActivityScreen.MediaViewActivity;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.CubeFullViewDataModal;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;


public class MeStoryCubeViewAdapter extends RecyclerView.Adapter<MeStoryCubeViewAdapter.MediaFullViewHolder> {
    public int Position;
    public Context context;
    public List<MeStoryDataModal.DataBean.StoriesBean> dataList;
    ArrayList<CubeFullViewDataModal> cubedatalist = new ArrayList<>();
    public MeStoryCubeViewAdapter(Context context, List<MeStoryDataModal.DataBean.StoriesBean> posts) {
        this.context = context;
        this.dataList = posts;
    }

    @Override
    public MediaFullViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_iteam_full_view_media_adapter_, parent, false);
        return  new MediaFullViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MediaFullViewHolder holder, int position) {
        MeStoryDataModal.DataBean.StoriesBean current = dataList.get(position);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                holder.glView = new CubeGLSurfaceView(context,"","","","",current.getMedia_profile());
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
                holder.linearLayout.addView(holder.glView );
                holder.dragControl = new DragControl(context,"","","","",current.getMedia_profile());
                holder.glView.setOnTouchListener(holder.dragControl);
                holder.glView.setDragControl(holder.dragControl);
                holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
                holder.glView.setZOrderOnTop(true);
                holder.glView.setZOrderMediaOverlay(true);
            }
        });

    /*    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cubedatalist.clear();
                for (int i=0; i < current.getMedia_profile().size();i++){
                    cubedatalist.add(new CubeFullViewDataModal(current.getMedia_profile().get(i).getUrl(),current.getMedia_profile().get(i).getContent_type()));
                }
                Intent i = new Intent(context, MediaViewActivity.class);
                Gson gson = new Gson();
                String arrayData = gson.toJson(cubedatalist);
                i.putExtra("medialist",arrayData);
                context.startActivity(i);
            }
        });*/
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


}

