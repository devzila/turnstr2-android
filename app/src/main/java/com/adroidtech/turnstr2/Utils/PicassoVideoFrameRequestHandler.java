package com.adroidtech.turnstr2.Utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;

import java.io.IOException;
import java.util.HashMap;


/**
 * Loads frames from a video at the specified microsecond.  Usage:
 * Picasso picasso = new Picasso.Builder(this).addRequestHandler(new VideoFrameRequestHandler()).build();
 * picasso.load("videoframe://path/to/video#microseconds
 * The offset is in microseconds, so #1000000 = 1 second in.
 */
public class PicassoVideoFrameRequestHandler extends RequestHandler {
    public static final String SCHEME = "videoframe";
    private String videoPath;

    public PicassoVideoFrameRequestHandler(String path){
        this.videoPath=path;
    }
    @Override
    public boolean canHandleRequest(Request data) {
        return SCHEME.equals(data.uri.getScheme());
    }

    @Override
    public Result load(Request data) throws IOException {
//        Bitmap bitmap = null;
//        try {
//            MediaMetadataRetriever mediaMetadataRetriever = null;
//            mediaMetadataRetriever = new MediaMetadataRetriever();
//            if (Build.VERSION.SDK_INT >= 14)
//                mediaMetadataRetriever.setDataSource(data.uri.getPath(), new HashMap<String, String>());
//            else
//                mediaMetadataRetriever.setDataSource(data.uri.getPath());
//            bitmap = mediaMetadataRetriever.getFrameAtTime();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }

        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        if (Build.VERSION.SDK_INT >= 14)
            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
        else
            mediaMetadataRetriever.setDataSource(videoPath);
//        mediaMetadataRetriever.setDataSource(data.uri.toString());
        String offsetString = data.uri.getFragment();
        long offset = Long.parseLong(offsetString);
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime(offset);
        return new Result(bitmap, Picasso.LoadedFrom.DISK);
    }
}