package com.wdp.Adapter;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.turnstr.R;

import java.util.ArrayList;

public class MyStoryGridAdapter extends RecyclerView.Adapter<MyStoryGridAdapter.MyViewHolder> {
    ArrayList<MeStoryDataModal.DataBean.StoriesBean> dataList;
    private Context context;
    GetTab1GlViews getTab1GlViews;

    public MyStoryGridAdapter(Context context, ArrayList<MeStoryDataModal.DataBean.StoriesBean> dataList, GetTab1GlViews getTab1GlViews) {
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
        MeStoryDataModal.DataBean.StoriesBean dataBean = dataList.get(position);
        holder.glView = new CubeGLSurfaceView(context,"","","","",dataBean.getMedia_profile());
        holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.linearLayout.addView(holder.glView );
        holder.dragControl = new DragControl(context,"","","","",dataBean.getMedia_profile());
        holder.glView.setOnTouchListener(holder.dragControl);
        holder.glView.setDragControl(holder.dragControl);
        holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder.glView.setZOrderOnTop(true);
        holder.glView.setZOrderMediaOverlay(true);



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
