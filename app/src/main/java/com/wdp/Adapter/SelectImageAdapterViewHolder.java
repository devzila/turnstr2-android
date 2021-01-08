package com.wdp.Adapter;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Log;
import com.wdp.turnstr.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;


public class SelectImageAdapterViewHolder extends RecyclerView.ViewHolder {
    public FrameLayout mediaContainer;
    public ImageView ivMediaCoverImage;
    public ImageView image;
    public ImageView ivVolumeControl;
    public ProgressBar progressBar;
    public RequestManager requestManager;

    View parent;

    public SelectImageAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        mediaContainer = (FrameLayout) itemView.findViewById(R.id.mediaContainer);
        ivMediaCoverImage = (ImageView) itemView.findViewById(R.id.ivMediaCoverImage);
        image = (ImageView) itemView.findViewById(R.id.image);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        ivVolumeControl = (ImageView) itemView.findViewById(R.id.ivVolumeControl);


    }
  

    public void onBind(List<String> imageUrl,int position,Context context,RequestManager requestManager) {
        this.requestManager = requestManager;
        parent.setTag(this);
        if (imageUrl.get(position).contains(".jpg")){
             Glide.with(context)
                    .asBitmap()
                    .load(imageUrl.get(position))
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Bitmap bitmap = resource;
                            ivMediaCoverImage.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
        }
        else {
            Glide.with(context)
                    .asBitmap()
                    .load(imageUrl.get(position))
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Bitmap bitmap = resource;
                            ivMediaCoverImage.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
        }
    }




}
