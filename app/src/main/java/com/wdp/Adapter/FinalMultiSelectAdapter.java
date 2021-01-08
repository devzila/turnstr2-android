package com.wdp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wdp.Utility.ItemTouchHelperAdapter;
import com.wdp.Utility.ItemTouchHelperViewHolder;
import com.wdp.Utility.OnStartDragListener;
import com.wdp.turnstr.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FinalMultiSelectAdapter extends RecyclerView.Adapter<FinalMultiSelectAdapter.MyViewHolder>
         implements ItemTouchHelperAdapter {

    private List<String> posts;
    private LayoutInflater inflater;
    private Context context;
    private String fileType;
    onClickApprove onClickApprove;
    private final OnStartDragListener mDragStartListener;
    public interface onClickApprove {
        public void onclickApprove(int pos);
    }

    public FinalMultiSelectAdapter(Activity context, ArrayList<String> datalist, onClickApprove clickApprove, OnStartDragListener dragStartListener) {
        this.context = context;
        this.posts = datalist;
        inflater = LayoutInflater.from(context);
        this.onClickApprove=clickApprove;
        this.mDragStartListener=dragStartListener;
    }



    public void delete(int position) {
        posts.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_iteam_multiimage, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      /*  if (posts.get(position).contains(".jpg")){
           // holder.imgview.setImageURI(Uri.parse(posts.get(position)));


        }
        else {
            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(posts.get(position), MediaStore.Video.Thumbnails.MINI_KIND);
            holder.imgview.setImageURI(getImageUri(context,bitmap));
        }*/
        Glide.with(context)
                .asBitmap()
                .load(posts.get(position))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap bitmap = resource;

                        holder.imgview.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

        holder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(position);
                onClickApprove.onclickApprove(position);

            }
        });


        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });


       }
    @Override
    public void onItemDismiss(int position) {
        posts.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(posts, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        ImageView imgview,img_close;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgview=(ImageView)itemView.findViewById(R.id.imgview);
            img_close=(ImageView)itemView.findViewById(R.id.img_close);
        }


        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}

