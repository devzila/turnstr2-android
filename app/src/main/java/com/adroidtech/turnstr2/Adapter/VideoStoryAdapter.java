package com.adroidtech.turnstr2.Adapter;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adroidtech.turnstr2.Activity.HomePageActivity;
import com.adroidtech.turnstr2.Activity.MyVideoStoryActivity;
import com.adroidtech.turnstr2.Activity.ViewStoryActivity;
import com.adroidtech.turnstr2.Activity.ViewVideoActivity;
import com.adroidtech.turnstr2.CubeView.CubeImageColored;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.MyStoryModel;
import com.adroidtech.turnstr2.Models.VideoStoryModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.BitmapUtils;
import com.adroidtech.turnstr2.Utils.CachingFiles.TaskLoader;
import com.adroidtech.turnstr2.Utils.PicassoVideoFrameRequestHandler;
import com.adroidtech.turnstr2.Utils.Utils;
import com.adroidtech.turnstr2.ViewHolders.MyStoryListingView;
import com.adroidtech.turnstr2.ViewHolders.VideoStoryListingView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class VideoStoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<VideoStoryModel> allDataList;
    Activity context;
    private OnItemClickListener listener;
    private ArrayList<Bitmap> mBbitmap1;

    public VideoStoryAdapter(Activity context, ArrayList<VideoStoryModel> allDataList) {
        this.context = context;
        this.allDataList = allDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_view, parent, false);
        return new VideoStoryListingView(itemView, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        try {
            final VideoStoryListingView view = (VideoStoryListingView) holder;
//            Picasso picasso = new Picasso.Builder(context).addRequestHandler(new PicassoVideoFrameRequestHandler("https://s3-us-west-2.amazonaws.com/turnstr2-production/45910392/2f0eab85-fb1b-4c82-abaa-0e654d807270/archive.mp4")).build();
//            picasso.load("videoframe://" + allDataList.get(position).getUrl()).into(view.thumb_image);
            TaskLoader task = new TaskLoader(context, allDataList.get(position).getUrl(), view.thumb_image);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                task.execute();
            }
//        "https://s3-us-west-2.amazonaws.com/turnstr2-production/45910392/2f0eab85-fb1b-4c82-abaa-0e654d807270/archive.mp4"
//
            view.thumb_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        VideoStoryModel userData = allDataList.get(position);
                        Intent intent = new Intent(context, ViewVideoActivity.class);
                        intent.putExtra("VIDEO_DATA", userData);
                        context.startActivity(intent);
                    } catch (Exception e) {

                    }
                }
            });
            String dateString = Utils.splitDateToFormte(allDataList.get(position).getCreatedAt());
            view.date.setText(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
//        return 5;
        return allDataList == null ? 0 : allDataList.size();
    }

    public interface OnItemClickListener {
        void ClickAction(int position);
    }
}

