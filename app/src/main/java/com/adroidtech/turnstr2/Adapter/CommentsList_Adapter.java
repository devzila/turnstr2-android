package com.adroidtech.turnstr2.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adroidtech.turnstr2.Activity.ViewStoryActivity;
import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.CommentsModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by sarbjot on 18/12/17.
 */

public class CommentsList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<CommentsModel> commentsModels;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
        private TextView name;
        private TextView comments;
        private TextView time;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.time);
            comments = (TextView) view.findViewById(R.id.comments);
            frameLayout = (FrameLayout) view.findViewById(R.id.layout_frame);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }


    public CommentsList_Adapter(Context context, List<CommentsModel> commentsModels) {
        this.context = context;
        this.commentsModels = commentsModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comments_list_adapter, parent, false);
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            final MyViewHolder userViewHolder = (MyViewHolder) holder;
            userViewHolder.name.setText(commentsModels.get(position).getUser().getFirstName());
            userViewHolder.comments.setText(commentsModels.get(position).getBody());
            userViewHolder.time.setText(Utils.convertDateTimeToTImeline(commentsModels.get(position).getCreatedAt()));
            ArrayList<Bitmap> mBbitmap = new ArrayList<>();
            Cubesurfaceview view = new Cubesurfaceview(context, mBbitmap, false);
            userViewHolder.frameLayout.addView(view);
            final Stack<String> strings = new Stack<>();
            strings.push(commentsModels.get(position).getUser().getAvatarFace1());
            strings.push(commentsModels.get(position).getUser().getAvatarFace2());
            strings.push(commentsModels.get(position).getUser().getAvatarFace3());
            strings.push(commentsModels.get(position).getUser().getAvatarFace4());
            strings.push(commentsModels.get(position).getUser().getAvatarFace5());
            strings.push(commentsModels.get(position).getUser().getAvatarFace6());
            new URLImageParser(strings, new URLImageParser.AsyncCallback() {
                @Override
                public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                    userViewHolder.frameLayout.removeAllViews();
                    Cubesurfaceview viewData = new Cubesurfaceview(context, bitmap, true);
                    userViewHolder.frameLayout.addView(viewData);
                }
            }).execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
//        return 5;
        return commentsModels == null ? 0 : commentsModels.size();
    }

}
