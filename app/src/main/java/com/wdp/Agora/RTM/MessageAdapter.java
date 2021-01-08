package com.wdp.Agora.RTM;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.wdp.turnstr.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private List<MessageBean> messageBeanList;
    private LayoutInflater inflater;
    private Context context;

    public MessageAdapter(Context context, List<MessageBean> messageBeanList) {
        this.context = context;
        inflater = ((Activity) context).getLayoutInflater();
        this.messageBeanList = messageBeanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.msg_item_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        setupView(holder, position);
    }

    @Override
    public int getItemCount() {
        return messageBeanList.size();
    }


    private void setupView(MyViewHolder holder, int position) {
        MessageBean bean = messageBeanList.get(position);
        if (bean.isBeSelf()) {
            Glide.with(context)
                    .load(bean.getUserImage()).placeholder(R.drawable.ic_account)
                    .into(holder.imageuserright);
            holder.textViewSelfMsg.setText(bean.getMessage());
        } else {

            Glide.with(context)
                    .load(bean.getUserImage()).placeholder(R.drawable.ic_account)
                    .into(holder.imageuserright);
            holder.textViewOtherMsg.setText(bean.getMessage());

        }

        holder.layoutRight.setVisibility(bean.isBeSelf() ? View.VISIBLE : View.GONE);
        holder.layoutLeft.setVisibility(bean.isBeSelf() ? View.GONE : View.VISIBLE);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageuserleft;
        private TextView textViewOtherMsg;
        private CircleImageView imageuserright;
        private TextView textViewSelfMsg;
        private RelativeLayout layoutLeft;
        private RelativeLayout layoutRight;

        MyViewHolder(View itemView) {
            super(itemView);
            imageuserleft = itemView.findViewById(R.id.item_name_l);
            textViewOtherMsg = itemView.findViewById(R.id.item_msg_l);
            imageuserright = itemView.findViewById(R.id.item_name_r);
            textViewSelfMsg = itemView.findViewById(R.id.item_msg_r);
            layoutLeft = itemView.findViewById(R.id.item_layout_l);
            layoutRight = itemView.findViewById(R.id.item_layout_r);
        }
    }

    private String showdata(String message){
        String[] bits = message.split(">>>>");
        return  bits[bits.length];
    }


}
