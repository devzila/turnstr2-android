package com.wdp.Adapter;


import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.CommetListDataModal;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder>{
    private List<CommetListDataModal.DataBean.CommentsBean> dataList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_username,txt_comment;
        LinearLayout ll_user_profile;
        CubeGLSurfaceView glView;
        DragControl dragControl;

        public MyViewHolder(View view) {
            super(view);
            txt_username=(TextView) view.findViewById(R.id.txt_username);
            txt_comment=(TextView) view.findViewById(R.id.txt_comment);
            ll_user_profile = view.findViewById(R.id.ll_user_profile);

        }
    }

    public CommentListAdapter(Context context, List<CommetListDataModal.DataBean.CommentsBean> dataList) {
        this.context=context;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_comment, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        List<MeStoryDataModal.DataBean.StoriesBean> mediaprofile = new ArrayList<>();
        String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";
        CommetListDataModal.DataBean.CommentsBean current = dataList.get(position);
        holder.txt_username.setText(dataList.get(position).getUser().getName());
        holder.txt_comment.setText(dataList.get(position).getComment());

        if (dataList.get(position).getUser().getAvatar()!= null){
            avatar1 =  dataList.get(position).getUser().getAvatar().getThumb();
        }
        if (dataList.get(position).getUser().getAvatar2()!= null){
            avatar2 = dataList.get(position).getUser().getAvatar2().getThumb();
        }
        if (dataList.get(position).getUser().getAvatar3()!= null){
            avatar3 =  dataList.get(position).getUser().getAvatar3().getThumb();
        }
        if (dataList.get(position).getUser().getAvatar4()!= null){
            avatar4 =  dataList.get(position).getUser().getAvatar4().getThumb();
        }
        if (dataList.get(position).getUser().getAvatar5()!= null){
            avatar5 =  dataList.get(position).getUser().getAvatar5().getThumb();
        }
        if (dataList.get(position).getUser().getAvatar6()!= null){
            avatar6 =  dataList.get(position).getUser().getAvatar6().getThumb();
        }

        holder.glView = new CubeGLSurfaceView(context, avatar1, avatar2, avatar3, avatar4, avatar5, avatar6);
        holder.ll_user_profile.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.ll_user_profile.addView(holder.glView);
        holder.dragControl = new DragControl(context, "", "", mediaprofile);
        //glView.setOnTouchListener(dragControl);
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

