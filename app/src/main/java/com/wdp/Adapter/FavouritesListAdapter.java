package com.wdp.Adapter;


import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.FavouriteslistDataModal;
import com.wdp.Modal.FollowingDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import java.util.List;

public class FavouritesListAdapter extends RecyclerView.Adapter<FavouritesListAdapter.MyViewHolder>{
    List<FavouriteslistDataModal.DataBean.UsersBean> dataList;
    Context context;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    LoginResDataModal loginResDataModal;
    GetGlViews getGlViews;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CubeGLSurfaceView glView;
        LinearLayout layout;
        DragControl dragControl;
        TextView txtuser;

        public MyViewHolder(View view) {
            super(view);
            layout =  view.findViewById(R.id.layout);
            txtuser =  view.findViewById(R.id.txtuser);
        }
    }

    public interface GetGlViews {
        public void getglviews(CubeGLSurfaceView cubeGLSurfaceView);
    }

    public FavouritesListAdapter(Context context, List<FavouriteslistDataModal.DataBean.UsersBean> dataList, GetGlViews getGlViews) {
        this.context=context;
        this.dataList = dataList;
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(context);
        this.getGlViews = getGlViews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_fav, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FavouriteslistDataModal.DataBean.UsersBean current = dataList.get(position);
        holder.txtuser.setText(current.getName());
        String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="", id="";
        if (current.getAvatar()!= null){
            avatar1 = current.getAvatar().getThumb();
        }
        if (current.getAvatar2()!= null){
            avatar2 = current.getAvatar2().getThumb();
        }
        if (current.getAvatar3()!= null){
            avatar3 =current.getAvatar3().getThumb();
        }
        if (current.getAvatar4()!= null){
            avatar4 = current.getAvatar4().getThumb();
        }
        if (current.getAvatar5()!= null){
            avatar5 = current.getAvatar5().getThumb();
        }
        if (current.getAvatar6()!= null){
            avatar6 = current.getAvatar6().getThumb();
        }if (current.getId()!=null) {
            id = current.getId();
        }


        holder.glView = new CubeGLSurfaceView(context,avatar1,avatar2,avatar3,avatar4,avatar5,avatar6);
        holder.layout.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.layout.addView(holder.glView );
        holder.dragControl = new DragControl(context,context,avatar1,avatar2,avatar3,avatar4,avatar5,avatar6, id);
        holder.glView.setOnTouchListener(holder.dragControl);
        holder.glView.setDragControl(holder.dragControl);
        holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder.glView.setZOrderOnTop(true);
        holder.glView.setZOrderMediaOverlay(true);
        getGlViews.getglviews(holder.glView);

    }

    @Override
   public int getItemCount() {
        return dataList.size();
    }
}

