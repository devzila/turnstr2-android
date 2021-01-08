package com.wdp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wdp.ActivityScreen.CommentListActivity;
import com.wdp.ActivityScreen.PostReportListActivity;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.CubeDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.turnstr.R;

import java.util.ArrayList;

public class MyGridPostAdapter extends RecyclerView.Adapter<MyGridPostAdapter.MyViewHolder> {
    ArrayList<MyPostDataModal.DataBean.PostsBean> dataList;
    private Context context;
    GetTab1GlViews getTab1GlViews;

    public MyGridPostAdapter(Context context, ArrayList<MyPostDataModal.DataBean.PostsBean> dataList, GetTab1GlViews getTab1GlViews) {
        this.context = context;
        this.dataList = dataList;
        this.getTab1GlViews = getTab1GlViews;
        Log.d("dataList","----->" + dataList.size());
    }

    public interface GetTab1GlViews {
        public void gettab1glviews(CubeGLSurfaceView cubeGLSurfaceView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_cube_full_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyPostDataModal.DataBean.PostsBean dataBean = dataList.get(position);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                holder.glView = new CubeGLSurfaceView(context,context,dataBean.getMedia_profile());
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
                holder.linearLayout.addView(holder.glView);
                holder.dragControl = new DragControl(context,context,dataBean.getMedia_profile());
                holder.glView.setOnTouchListener(holder.dragControl);
                holder.glView.setDragControl(holder.dragControl);
                holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
                holder.glView.setZOrderOnTop(true);
                holder.glView.setZOrderMediaOverlay(true);
                getTab1GlViews.gettab1glviews(holder.glView);
            }
        });



    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        CubeGLSurfaceView glView;
        DragControl dragControl;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout);
        }
    }

}
