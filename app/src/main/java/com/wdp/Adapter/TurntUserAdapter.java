package com.wdp.Adapter;


import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.FavouriteslistDataModal;
import com.wdp.Modal.FollowingDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import java.util.List;

public class TurntUserAdapter extends RecyclerView.Adapter<TurntUserAdapter.MyViewHolder>{
    List<StoryDataModal.DataBean.UsersBean> dataList;
    Context context;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    LoginResDataModal loginResDataModal;
    GetGlViews getGlViews;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        TextView txtuser;
        ImageView imgUserImage;

        public MyViewHolder(View view) {
            super(view);
            layout =  view.findViewById(R.id.layout);
            txtuser =  view.findViewById(R.id.txtuserName);
            imgUserImage = view.findViewById(R.id.imgUserimage);
        }
    }

    public interface GetGlViews {
        public void getglviews(CubeGLSurfaceView cubeGLSurfaceView);
    }

    public TurntUserAdapter(Context context, List<StoryDataModal.DataBean.UsersBean> dataList) {
        this.context=context;
        this.dataList = dataList;
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(context);
        this.getGlViews = getGlViews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trunt_users, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StoryDataModal.DataBean.UsersBean current = dataList.get(position);
        holder.txtuser.setText(current.getName());
        Glide.with(context).load(current.getAvatar().getMedium()).into(holder.imgUserImage);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}


