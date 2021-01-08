package com.wdp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.wdp.ActivityScreen.CommentListActivity;
import com.wdp.ActivityScreen.PostReportListActivity;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Modal.CubeDataModal;
import com.wdp.Modal.CubeFullViewDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    ArrayList<PostDataModal.DataBean.PostsBean> dataList;
    private Context context;
    private Bitmap bitmap;
    OnclickIteam onclickIteam;
    GetGlViews getGlViews;
    public int Position=-1;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    LoginResDataModal loginResDataModal;
    private GoogleProgressDialog progressDialog;
    MyViewHolder holder;
    private StoryAdapter.RecyclerViewReadyCallback recyclerViewReadyCallback;

    public interface OnclickIteam {
        public void onclickiteam(String type, String postid);
    }

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }

    public interface GetGlViews {
        public void getglviews(CubeGLSurfaceView cubeGLSurfaceView);
    }
    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
    List<CubeFullViewDataModal> cubedatalist = new ArrayList<>();
    public HomeAdapter(Context context, ArrayList<PostDataModal.DataBean.PostsBean> dataList, OnclickIteam onclickIteam, GetGlViews getGlViews) {
        this.context = context;
        this.dataList = dataList;
        this.onclickIteam = onclickIteam;
        this.getGlViews = getGlViews;
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(context);
        progressDialog = new GoogleProgressDialog(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_iteam_home, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        vh.setIsRecyclable(false);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        progressDialog.showDialog();
        PostDataModal.DataBean.PostsBean dataBean = dataList.get(position);
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
                    onclickIteam.onclickiteam("dislike", dataBean.getId());
                    if (likeCount[0] != 0) {
                        likeCount[0] = likeCount[0] - 1;
                    }

                } else if (holder.img_heart.getTag().toString().equals("0")) {
                    holder.img_heart.setImageResource(R.drawable.heart);
                    holder.img_heart.setTag(1);
                    onclickIteam.onclickiteam("like", dataBean.getId());
                    likeCount[0] = likeCount[0] + 1;
                }
                if (likeCount[0] > 1) {
                    holder.txt_like_count.setText(likeCount[0] + " " + context.getResources().getString(R.string.likes));
                } else {
                    holder.txt_like_count.setText(likeCount[0] + " " + context.getResources().getString(R.string.like));
                }
            }
        });
        /*new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {*/
                holder.glView = new CubeGLSurfaceView(context,dataBean.getMedia_profile());
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
                holder.linearLayout.addView(holder.glView );
                holder.dragControl = new DragControl(context,dataBean.getMedia_profile());
                holder.glView.setOnTouchListener(holder.dragControl);
                holder.glView.setDragControl(holder.dragControl);
                holder.glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
                holder.glView.setZOrderOnTop(true);
                holder.glView.setZOrderMediaOverlay(true);
                getGlViews.getglviews(holder.glView);
            /*}
        });*/

        /*new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {*/
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
                        avatar4,
                        avatar5,avatar6,dataBean.getMedia_profile(),dataBean.getUser().getId());

           /* }
        });*/

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
                txt_report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(context, PostReportListActivity.class);
                        i.putExtra("postid",dataBean.getId());
                        context.startActivity(i);
                        alertDialog.dismiss();
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

        holder.img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, context.getResources().getString(R.string.share_url) + dataBean.getId());
                sendIntent.setType("text/plain");
                context.startActivity(Intent.createChooser(sendIntent, context.getResources().getText(R.string.app_name)));
            }
        });

        progressDialog.dismiss();

    }

    private void profilecube(MyViewHolder holder, String avatar, String avatar2, String avatar3, String avatar4,
                             String avatar5, String avatar6, List<PostDataModal.DataBean.PostsBean.MediaProfileBean> datalist,String id) {

        holder.glView = new CubeGLSurfaceView(context,avatar,avatar2,avatar3,avatar4,avatar5,avatar6);
        holder.ll_user_profile.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.ll_user_profile.addView(holder.glView );
        holder.dragControl = new DragControl(context,context,avatar,avatar2,avatar3,avatar4,avatar5,avatar6,id);
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
        ImageView img_three_dot, img_user, img_heart,img_comment,img_share;
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
            img_share = itemView.findViewById(R.id.img_share);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
