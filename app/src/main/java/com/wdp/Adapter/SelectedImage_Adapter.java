package com.wdp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.RequestManager;
import com.wdp.turnstr.R;
import java.util.List;


public class SelectedImage_Adapter extends RecyclerView.Adapter<SelectImageAdapterViewHolder> {
    private final List<String> imageUrl;
    private LayoutInflater inflater;
    private Context context;
    private RequestManager requestManager;
    private int position;

    public SelectedImage_Adapter(Context context, List<String> imageUrl,RequestManager requestManager) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.imageUrl=imageUrl;
        this.requestManager = requestManager;
    }

    public void delete(int position) {
        imageUrl.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SelectImageAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_selected_image, parent, false);
        SelectImageAdapterViewHolder holder = new SelectImageAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SelectImageAdapterViewHolder holder, int position) {
        ((SelectImageAdapterViewHolder)holder).onBind(imageUrl,position,context,requestManager);



    }

    @Override
    public int getItemCount() {
        return imageUrl.size();
    }


}

