package com.wdp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wdp.ActivityScreen.CommentListActivity;
import com.wdp.ActivityScreen.FeedEditPostActivity;
import com.wdp.ActivityScreen.FeedPostActivity;
import com.wdp.ActivityScreen.ImageFilterActivity;
import com.wdp.ActivityScreen.PostReportListActivity;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Modal.CubeDataModal;
import com.wdp.Modal.CubeFullViewDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

import static com.wdp.Interface.ApiConstants.ME_POST_DELETE;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.MyViewHolder> {
    ArrayList<MyPostDataModal.DataBean.PostsBean> dataList;
    private Context context;


    private Bitmap bitmap;
    OnclickIteam onclickIteam;
    public int Position=-1;
    GestureDetector gestureDetector;
    GetTab2GlViews getTab2GlViews;

    public interface OnclickIteam {
        public void onclickiteam(String type, String postid,int pos);
    }

    String whichScreen;
    ArrayList<CubeDataModal> cubeList = new ArrayList<>();


    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public MyPostAdapter(Context context, ArrayList<MyPostDataModal.DataBean.PostsBean> dataList,String whichscreen, OnclickIteam onclickIteam, GetTab2GlViews getTab2GlViews) {
        this.context = context;
        this.dataList = dataList;
        this.whichScreen = whichscreen;
        this.onclickIteam = onclickIteam;
        this.getTab2GlViews = getTab2GlViews;
    }

    public interface GetTab2GlViews {
        public void gettab2glviews(CubeGLSurfaceView cubeGLSurfaceView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_home, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyPostDataModal.DataBean.PostsBean dataBean = dataList.get(position);
        holder.txt_name.setText(dataBean.getUser().getName());
        holder.txt_title.setText(dataBean.getCaption());
        holder.txt_comment_count.setText(dataBean.getComment_count());
        final int[] likeCount = {Integer.valueOf(dataBean.getLikes_count())};
        if (likeCount[0] > 1) {
            holder.txt_like_count.setText(likeCount[0] + " " + context.getResources().getString(R.string.likes));
        } else {
            holder.txt_like_count.setText(likeCount[0] + " " + context.getResources().getString(R.string.like));
        }

        if (dataBean.isLiked_by_current_user().equalsIgnoreCase("true")) {
            holder.img_heart.setImageResource(R.drawable.heart);
            holder.img_heart.setTag(1);
        } else {
            holder.img_heart.setImageResource(R.drawable.heart_gray);
            holder.img_heart.setTag(0);
        }

        holder.img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.img_heart.getTag().toString().equals("1")) {
                    holder.img_heart.setTag(0);
                    holder.img_heart.setImageResource(R.drawable.heart_gray);
                    onclickIteam.onclickiteam("dislike", dataBean.getId(),position);
                    if (likeCount[0] != 0) {
                        likeCount[0] = likeCount[0] - 1;
                    }

                } else if (holder.img_heart.getTag().toString().equals("0")) {
                    holder.img_heart.setImageResource(R.drawable.heart);
                    holder.img_heart.setTag(1);
                    onclickIteam.onclickiteam("like", dataBean.getId(),position);
                    likeCount[0] = likeCount[0] + 1;
                }
                if (likeCount[0] > 1) {
                    holder.txt_like_count.setText(likeCount[0] + " " + context.getResources().getString(R.string.likes));
                } else {
                    holder.txt_like_count.setText(likeCount[0] + " " + context.getResources().getString(R.string.like));
                }
            }
        });
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                holder.glView = new CubeGLSurfaceView(context,context,dataBean.getMedia_profile());
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
                holder.linearLayout.addView(holder.glView );
                holder.dragControl = new DragControl(context,context,dataBean.getMedia_profile());
                holder.glView.setOnTouchListener(holder.dragControl);
                holder.glView.setDragControl(holder.dragControl);
                holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
                holder.glView.setZOrderOnTop(true);
                holder.glView.setZOrderMediaOverlay(true);
                getTab2GlViews.gettab2glviews(holder.glView);
            }
        });

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";
                if (dataBean.getUser().getAvatar()!= null){
                    avatar1 = dataBean.getUser().getAvatar().getThumb();
                }
                if (dataBean.getUser().getAvatar2()!= null){
                    avatar2 = dataBean.getUser().getAvatar2().getThumb();
                }
                if (dataBean.getUser().getAvatar3()!= null){
                    avatar3 = dataBean.getUser().getAvatar3().getThumb();
                }
                if (dataBean.getUser().getAvatar4()!= null){
                    avatar4 = dataBean.getUser().getAvatar4().getThumb();
                }
                if (dataBean.getUser().getAvatar5()!= null){
                    avatar5 = dataBean.getUser().getAvatar5().getThumb();
                }
                if (dataBean.getUser().getAvatar6()!= null){
                    avatar6 = dataBean.getUser().getAvatar6().getThumb();
                }

                profilecube(holder,avatar1,avatar2,avatar3,
                        avatar4,avatar5,
                        avatar6,dataBean.getMedia_profile());

            }
        });

        holder.img_three_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View dialogView = inflater.inflate(R.layout.dialog_invite, null);
                dialogBuilder.setView(dialogView);
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(false);
                alertDialog.getWindow().setGravity(Gravity.BOTTOM);
                alertDialog.show();
                TextView txt_report = alertDialog.findViewById(R.id.txt_report);
                TextView txt_cancel = alertDialog.findViewById(R.id.txt_cancel);
                TextView txt_delete = alertDialog.findViewById(R.id.txt_delete);
                View view1 = alertDialog.findViewById(R.id.view1);
                RelativeLayout rl_two = alertDialog.findViewById(R.id.rl_two);

                if (whichScreen.equalsIgnoreCase("profile")){
                    rl_two.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.VISIBLE);
                    txt_report.setText(context.getResources().getString(R.string.edit_post));
                }
                else {
                    rl_two.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    txt_report.setText(context.getResources().getString(R.string.report_post));

                }

                txt_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Position = position;
                        String url = ME_POST_DELETE + dataBean.getId();
                        onclickIteam.onclickiteam("delete",url,position);
                        alertDialog.dismiss();
                    }
                });

                txt_report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (whichScreen.equalsIgnoreCase("profile")){
                            if (dataBean.getMedia_profile().size() > 0){
                                Intent i = new Intent(context, FeedEditPostActivity.class);
                                Gson gson = new Gson();
                                String arrayData = gson.toJson(dataBean.getMedia_profile());
                                i.putExtra("selectlist",arrayData);
                                i.putExtra("postid",dataBean.getId());
                                i.putExtra("caption",dataBean.getCaption());
                                context.startActivity(i);
                            }
                            alertDialog.dismiss();

                        }
                        else {
                            Intent i = new Intent(context, PostReportListActivity.class);
                            i.putExtra("postid",dataBean.getId());
                            context.startActivity(i);
                            alertDialog.dismiss();
                        }


                    }
                });

                txt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

        holder.img_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Position = position;
                Intent i = new Intent(context, CommentListActivity.class);
                i.putExtra("postid",dataBean.getId());
                context.startActivity(i);
            }
        });





    }
    public void delete() {
        dataList.remove(Position);
        notifyDataSetChanged();
    }
    private void profilecube(MyViewHolder holder, String avatar, String avatar2, String avatar3, String avatar4, String avatar5,
                             String avatar6, List<MyPostDataModal.DataBean.PostsBean.MediaProfileBean> media_profile) {
        holder.glView = new CubeGLSurfaceView(context,avatar,avatar2,avatar3,avatar4,avatar5,avatar6);
        holder.ll_user_profile.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.ll_user_profile.addView(holder.glView );
        holder.dragControl = new DragControl(context,context,avatar,avatar2,avatar3,avatar4,avatar5,avatar6);
        holder.glView.setOnTouchListener(holder.dragControl);
        holder.glView.setDragControl(holder.dragControl);
        holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        holder.glView.setZOrderOnTop(true);
        holder.glView.setZOrderMediaOverlay(true);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_like_count, txt_comment_count,txt_title;
        ImageView img_three_dot, img_user, img_heart,img_comment;
        LinearLayout linearLayout,ll_user_profile;
        CubeGLSurfaceView glView;
        DragControl dragControl;
        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout);
            txt_name = itemView.findViewById(R.id.txt_name);
            img_three_dot = itemView.findViewById(R.id.img_three_dot);
            img_user = itemView.findViewById(R.id.img_user);
            txt_like_count = itemView.findViewById(R.id.txt_like_count);
            txt_comment_count = itemView.findViewById(R.id.txt_comment_count);
            img_heart = itemView.findViewById(R.id.img_heart);
            img_comment = itemView.findViewById(R.id.img_comment);
            ll_user_profile = itemView.findViewById(R.id.ll_user_profile);
            txt_title = itemView.findViewById(R.id.txt_title);
        }
    }


}
