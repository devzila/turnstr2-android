package com.adroidtech.turnstr2.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.R;
import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {

    private List<MyStoryModel.Medium> allMedia;
    private Context mContext;
    private MyStoryModel.Medium modelObject;
    private ImageView imageView;
    private EasyVideoPlayer player;

    public CustomPagerAdapter(Context context, ArrayList<MyStoryModel.Medium> allMedia) {
        mContext = context;
        this.allMedia = allMedia;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        modelObject = allMedia.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = null;
        try {
            layout = (ViewGroup) inflater.inflate(R.layout.pager_view, collection, false);
            imageView = (ImageView) layout.findViewById(R.id.img_view);
            player = (EasyVideoPlayer) layout.findViewById(R.id.video_player);
            try {
                if (modelObject.getMediaType().contains("image")) {
                    imageView.setVisibility(View.VISIBLE);
                    player.setVisibility(View.GONE);
                    Picasso.with(mContext).load(modelObject.getMediaUrl()).into(imageView);
                } else {
                    imageView.setVisibility(View.GONE);
                    player.setVisibility(View.VISIBLE);
                    videoPlayerUpdate(player, modelObject.getMediaUrl());
                }
                if (player.isPlaying()) player.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            collection.addView(layout);
        } catch (Exception e) {

        }
        return layout;
    }

    private void videoPlayerUpdate(EasyVideoPlayer player, String mediaUrl) {
        player.setCallback(new EasyVideoCallback() {
            @Override
            public void onStarted(EasyVideoPlayer player) {

            }

            @Override
            public void onPaused(EasyVideoPlayer player) {

            }

            @Override
            public void onPreparing(EasyVideoPlayer player) {

            }

            @Override
            public void onPrepared(EasyVideoPlayer player) {

            }

            @Override
            public void onBuffering(int percent) {

            }

            @Override
            public void onError(EasyVideoPlayer player, Exception e) {

            }

            @Override
            public void onCompletion(EasyVideoPlayer player) {

            }

            @Override
            public void onRetry(EasyVideoPlayer player, Uri source) {

            }

            @Override
            public void onSubmit(EasyVideoPlayer player, Uri source) {

            }
        });
        player.setSource(Uri.parse(mediaUrl));
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return allMedia.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        ModelObject customPagerEnum = ModelObject.values()[position];
//        return mContext.getString(customPagerEnum.getTitleResId());
//    }

}