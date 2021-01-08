package com.wdp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.wdp.Modal.CubeDataModal;
import com.wdp.Modal.CubeFullViewDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.turnstr.R;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeSliderAdapter extends PagerAdapter {
    private ArrayList<CubeFullViewDataModal> dataList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;
    RequestOptions requestOptions = new RequestOptions();
    private PlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;
    private ConcatenatingMediaSource concatenatingMediaSource;
    private int position=-1;

    public HomeSliderAdapter(Context context, ArrayList<CubeFullViewDataModal> imageBeanList ) {
        this.context = context;
        this.dataList = imageBeanList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        requestOptions.placeholder(R.drawable.profile);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.row_profile_slider, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_view);
        ImageView img_play = (ImageView) itemView.findViewById(R.id.img_play);
        PlayerView playerView = (PlayerView) itemView.findViewById(R.id.video_view);
        resumePlayer();

        if (dataList.get(position).getType().equalsIgnoreCase("photo")){
            img_play.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            playerView.setVisibility(View.GONE);
            Glide.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(dataList.get(position).getUrl()).into(imageView);
        }
        else {
            img_play.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            playerView.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(dataList.get(position).getUrl()).into(imageView);
            prepair(dataList.get(position).getUrl(),playerView,false);
        }


        img_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepair(dataList.get(position).getUrl(),playerView,true);
            }
        });

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }


    private void prepair(String url,PlayerView mPlayerView,boolean flag){
        ArrayList<String> contentUrl = new ArrayList<>();
        contentUrl.clear();
        contentUrl.add(url);
        DefaultRenderersFactory defaultRenderersFactory = new DefaultRenderersFactory(context);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        LoadControl loadControl = new DefaultLoadControl();
        mPlayer = ExoPlayerFactory.newSimpleInstance(context, defaultRenderersFactory, trackSelector, loadControl);
        mPlayerView.setPlayer(mPlayer);
        mPlayerView.setUseArtwork(false);
        mPlayerView.setControllerShowTimeoutMs(0);
        mPlayerView.requestFocus();
        mPlayerView.setOnClickListener(null);
        final AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(C.USAGE_MEDIA)
                .setContentType(C.CONTENT_TYPE_MOVIE)
                .build();
        mPlayer.setAudioAttributes(audioAttributes, true);
        MediaSource[] mediaSources = new MediaSource[contentUrl.size()];
        for (int i = 0; i < contentUrl.size(); i++) {
            mediaSources[i] = buildMediaSource(Uri.parse(contentUrl.get(i)));
        }
        concatenatingMediaSource = new ConcatenatingMediaSource(mediaSources);
        mPlayer.prepare(concatenatingMediaSource, true, true);
        mPlayer.setPlayWhenReady(flag);
    }
    private MediaSource buildMediaSource(Uri uri) {
        String userAgent = Util.getUserAgent(context, "My ExoPlayer");
        return new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
               context, userAgent), new DefaultExtractorsFactory(), null, null);
    }
    public void releasePlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;

        }
    }
    public void resumePlayer() {
        if (mPlayer != null) {
            mPlayer.setPlayWhenReady(false);
            mPlayer.seekTo(0);
            mPlayer.getPlaybackState();
        }
    }
}
