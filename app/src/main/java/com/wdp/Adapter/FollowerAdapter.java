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
import com.wdp.Modal.FollowerDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.MyViewHolder>{
    private List<FollowerDataModal.DataBean.FollowerBean> dataList;
    private Context context;
    onClickFollower onclickFollower;
    LoginResDataModal loginResDataModal;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    public interface onClickFollower {
        public void onclickFollower(String pos,String id);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name;
        public Button btn_approve;
        public ImageView img_user;
        CubeGLSurfaceView glView;
        DragControl dragControl;
        LinearLayout ll_cube;
        public MyViewHolder(View view) {
            super(view);
            txt_name=(TextView) view.findViewById(R.id.txt_name);
            btn_approve=(Button) view.findViewById(R.id.btn_approve);
            img_user=(ImageView) view.findViewById(R.id.img_user);
            ll_cube=(LinearLayout) view.findViewById(R.id.ll_cube);

        }
    }

    public FollowerAdapter(Context context,List<FollowerDataModal.DataBean.FollowerBean> dataList,onClickFollower onClickFollowing) {
        this.dataList = dataList;
        this.context=context;
        this.onclickFollower=onClickFollowing;
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_following, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FollowerDataModal.DataBean.FollowerBean current = dataList.get(position);
         holder.txt_name.setText(current.getName());
        if (current.getId().equalsIgnoreCase(loginResDataModal.getData().getUser().getId())){
            holder.btn_approve.setVisibility(View.GONE);
        }
        else {
            holder.btn_approve.setVisibility(View.VISIBLE);
            if (current.isFollowed_by_current_user().equalsIgnoreCase("true")){
                holder.btn_approve.setText(context.getResources().getString(R.string.unTurnt));

            }
            else {
                holder.btn_approve.setText(context.getResources().getString(R.string.Turnt));
            }
        }


        holder.btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclickFollower != null){
                    onclickFollower.onclickFollower(holder.btn_approve.getText().toString(),current.getId());
                }

            }
        });

        String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";
        if (current.getAvatar()!= null){
            avatar1 = current.getAvatar().getThumb();
        }
        if (current.getAvatar2()!= null){
            avatar2 = current.getAvatar2().getThumb();
        }
        if (current.getAvatar3()!= null){
            avatar3 = current.getAvatar3().getThumb();
        }
        if (current.getAvatar4()!= null){
            avatar4 = current.getAvatar4().getThumb();
        }
        if (current.getAvatar5()!= null){
            avatar5 = current.getAvatar5().getThumb();
        }
        if (current.getAvatar6()!= null){
            avatar6 = current.getAvatar6().getThumb();
        }

        holder.glView = new CubeGLSurfaceView(context,avatar1,avatar2,
                avatar3,avatar4,avatar5,avatar6);
        holder.ll_cube.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.ll_cube.addView(holder.glView );
        holder.dragControl = new DragControl(context,context,avatar1,avatar2,
                avatar3,avatar4,avatar5,avatar6, current.getId());
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
}

