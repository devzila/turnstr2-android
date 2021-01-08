package com.wdp.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.wdp.Modal.FollowerDataModal;
import com.wdp.Modal.FollowingBean;
import com.wdp.Modal.FollowingDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.ItemClickListener;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class FollowingListAdapter extends RecyclerView.Adapter<FollowingListAdapter.MyViewHolder>{

    private List<FollowingBean> dataList;
    private List<FollowingBean> temDataList;
    Context context;
    private String from;
    ItemClickListener listener;
    List<Boolean> selected = new ArrayList<>();
    int count=0;
    private RadioButton lastCheckedRB = null;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private RadioButton rdBtn;
        private ImageView imgUser;
        private RelativeLayout rlTop;

        public MyViewHolder(View view) {
            super(view);
            txtName=(TextView) view.findViewById(R.id.txtName);
            rdBtn=(RadioButton) view.findViewById(R.id.rdBtn);
            imgUser=(ImageView) view.findViewById(R.id.imgUser);
            rlTop=view.findViewById(R.id.rlTop);
        }
    }

    public FollowingListAdapter(Context context, List<FollowingBean> dataList, String from, ItemClickListener listener) {
        this.context=context;
        this.dataList = dataList;
        this.temDataList = dataList;
        this.listener = listener;
        this.from = from;
        for (int i=0; i<dataList.size(); i++){
            selected.add(false);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_following_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FollowingBean current = dataList.get(position);
        holder.txtName.setText(dataList.get(position).getName());
        selected.add(false);
        holder.txtName.setText(current.getName());
        if (current.getAvatar() != null){
            Glide.with(context).load(current.getAvatar()).into(holder.imgUser);
        }
        else {
            Glide.with(context).load("").placeholder(R.drawable.profile).into(holder.imgUser);
        }

        if (from.equalsIgnoreCase("VideoCallActivity")) {
            holder.rlTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.rdBtn.isChecked()) {
                        holder.rdBtn.setChecked(false);
                        selected.set(position, false);
                        count -= 1;
                        listener.onClickListener(position, count);
                    } else {
                        if (count<4) {
                            holder.rdBtn.setChecked(true);
                            selected.set(position, true);
                            count += 1;
                            listener.onClickListener(position, count);
                        }else {
                            Toast.makeText(context, context.getResources().getString(R.string.max4), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

            holder.rdBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("CheckedChange : ", "" + holder.rdBtn.isChecked());
                    if (selected.get(position)) {
                        selected.set(position, false);
                        holder.rdBtn.setChecked(false);
                        count -= 1;
                        listener.onClickListener(position, count);
                    } else {
                        if (count<4) {
                            selected.set(position, true);
                            holder.rdBtn.setChecked(true);
                            count += 1;
                            listener.onClickListener(position, count);
                        }else {
                            Toast.makeText(context, context.getResources().getString(R.string.max4), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }else if (from.equalsIgnoreCase("ChatDashboardActivity")){
            View.OnClickListener rbClick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton checked_rb = (RadioButton) v;
                    if(lastCheckedRB != null){
                        lastCheckedRB.setChecked(false);
                    }
                    if (checked_rb.isChecked()) {
                        lastCheckedRB = checked_rb;
                        listener.onClickListener(position, 1);
                    }
                }
            };
            //holder.rlTop.setOnClickListener(rbClick);
            holder.rdBtn.setOnClickListener(rbClick);
        }

        /*holder.rdBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    count+=1;
                    listener.onClickListener(position, count);
                }else {
                    holder.rdBtn.setChecked(true);
                    count-=1;
                    listener.onClickListener(position, count);
                }
            }
        });*/
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataList = new ArrayList<>();
        if (charText.length() == 0) {
            dataList.addAll(temDataList);
        } else {
            for (int i=0; i<temDataList.size(); i++){
                if (temDataList.get(i).getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    dataList.add(temDataList.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}


