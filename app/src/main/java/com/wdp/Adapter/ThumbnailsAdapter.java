package com.wdp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;


import com.wdp.Utility.galleryImage.photofilters.imageprocessors.utils.ThumbnailItem;
import com.wdp.turnstr.R;


import  com.wdp.Utility.galleryImage.photofilters.imageprocessors.Filter;

import java.util.List;


/**
 * Created by narinder on 12/12/17.
 */

public class ThumbnailsAdapter extends RecyclerView.Adapter<ThumbnailsAdapter.MyViewHolder> {
    private List<ThumbnailItem> thumbnailItemList;
    ThumbnailsAdapterListener listener;
    private Context mContext;
    private int selectedIndex = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView thumbnail;
        TextView filterName;

        public MyViewHolder(View view) {
            super(view);
            thumbnail= view.findViewById(R.id.thumbnail);
            filterName= view.findViewById(R.id.filter_name);

        }
    }


    public ThumbnailsAdapter(Context context, List<ThumbnailItem> thumbnailItemList, ThumbnailsAdapterListener listener) {
        mContext = context;
        this.thumbnailItemList = thumbnailItemList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thumbnail_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ThumbnailItem thumbnailItem = thumbnailItemList.get(position);
        holder.thumbnail.setImageBitmap(thumbnailItem.image);
        holder.filterName.setText(thumbnailItem.filterName);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFilterSelected(thumbnailItem.filter);
                selectedIndex = position;
                notifyDataSetChanged();
            }
        });
        if (selectedIndex == position) {
            holder.filterName.setTextColor(ContextCompat.getColor(mContext, R.color.filter_label_selected));
        } else {
            holder.filterName.setTextColor(ContextCompat.getColor(mContext, R.color.filter_label_normal));
        }
    }

    @Override
    public int getItemCount() {
        return thumbnailItemList.size();
    }

    public interface ThumbnailsAdapterListener {
        void onFilterSelected(Filter filter);
    }
}
