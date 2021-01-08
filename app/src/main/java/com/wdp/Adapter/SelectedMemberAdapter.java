package com.wdp.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.wdp.Modal.FollowingBean;
import com.wdp.Modal.FollowingDataModal;
import com.wdp.Utility.ItemClickListener;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class SelectedMemberAdapter extends RecyclerView.Adapter<SelectedMemberAdapter.MyViewHolder>{

    private List<FollowingBean> dataList;
    Context context;
    ItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageView imgUser;

        public MyViewHolder(View view) {
            super(view);
            txtName=(TextView) view.findViewById(R.id.txtMemberName);
            imgUser=(ImageView) view.findViewById(R.id.imgMemberPhoto);
        }
    }

    public SelectedMemberAdapter(Context context, List<FollowingBean> dataList, ItemClickListener listener) {
        this.context=context;
        this.dataList = dataList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_memberview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FollowingBean current = dataList.get(position);
        holder.txtName.setText(current.getName());
        if (current.getAvatar() != null){
            Glide.with(context).load(current.getAvatar()).into(holder.imgUser);
        }
        else {
            Glide.with(context).load("").placeholder(R.drawable.profile).into(holder.imgUser);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}



