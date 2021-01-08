package com.wdp.Deepar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import com.wdp.turnstr.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MaskFilterationListAdapter extends RecyclerView.Adapter<MaskFilterationListAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<AREffect> data = new ArrayList<>();
    private static final int VIEW_TYPE_PADDING = 1;
    private static final int VIEW_TYPE_ITEM = 2;
    private int paddingWidthDate = 0;


    private int selectedItem = -1;
    public OnclickPosition onclickPosition;
    private RecyclerView recyclerView;

   public interface OnclickPosition {
       public void onClickPosiition(int position);
   }

    public MaskFilterationListAdapter(Context mContext, ArrayList<AREffect> music_models, OnclickPosition onclickPosition){
       this.mContext=mContext;
       this.recyclerView=recyclerView;
       this.data=music_models;
       this.onclickPosition = onclickPosition;


   }
   @Override
   public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_mask,
               parent, false);
       return new MyViewHolder(view);

   }

   @Override
   public void onBindViewHolder(final MyViewHolder holder, final int position) {
       final AREffect current = data.get(position);
       holder.txt_avtar.setText(current.getEffectName());

       Glide.with(mContext).load(current.getIcon()).into(holder.circleImageView);

       holder.circleImageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (recyclerView != null) {
                   recyclerView.smoothScrollToPosition(position);
               }
           }
       });


       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (onclickPosition != null){
                   onclickPosition.onClickPosiition(position);
               }
           }
       });



    }

   public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       CircleImageView circleImageView;
       TextView txt_avtar;
       MyViewHolder(View view) {
           super(view);
           circleImageView = view.findViewById(R.id.imageView);
           txt_avtar = view.findViewById(R.id.txt_avtar);
       }

       @Override
       public void onClick(View v) {
       }
   }
   @Override
   public int getItemCount() {
       return data.size();
   }
    public void setSelecteditem(int selecteditem) {
        this.selectedItem = selecteditem;
        notifyDataSetChanged();
    }

}