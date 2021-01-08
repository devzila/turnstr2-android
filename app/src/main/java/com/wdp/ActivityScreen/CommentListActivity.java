package com.wdp.ActivityScreen;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.wdp.Adapter.CommentListAdapter;
import com.wdp.ApiServices.PostsCommentApiService;
import com.wdp.ApiServices.PostsCommentsListApiService;
import com.wdp.ApiServices.PostsLIkesApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommetListDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.PostCommentModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;

public class CommentListActivity extends AppCompatActivity implements ApiResponseListner, View.OnClickListener {
    RecyclerView recycleview;
    ArrayList<CommetListDataModal.DataBean.CommentsBean> dataList = new ArrayList<>();
    CommentListAdapter commentListAdapter;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    private String postid="";
    TextView txt_title;
    ImageView img_back;
    EditText edt_message;
    ImageView sendButton;
    RadioGroup radioTabgroup;
    RadioButton radioHeader1,radioHeader2;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        intiView();
        clickListner();
        prepair();


    }

    private void intiView() {
        recycleview = findViewById(R.id.recycleview);
        txt_title = findViewById(R.id.txt_title);
        img_back = findViewById(R.id.img_back);
        edt_message = findViewById(R.id.edt_message);
        sendButton = findViewById(R.id.sendButton);
        radioTabgroup = findViewById(R.id.radioTabgroup);
        radioHeader1 = findViewById(R.id.radioHeader1);
        radioHeader2 = findViewById(R.id.radioHeader2);
    }

    private void clickListner(){
        sendButton.setOnClickListener(this);
    }

    private void prepair() {
        postid = getIntent().getStringExtra("postid");
        txt_title.setText(getResources().getString(R.string.comments));
        txt_title.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);
        commentListAdapter = new CommentListAdapter(this, dataList);
        recycleview.setAdapter(commentListAdapter);
        recycleview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    if (recycleview.getAdapter().getItemCount() > 0){
                        recycleview.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                recycleview.smoothScrollToPosition(
                                        recycleview.getAdapter().getItemCount() - 1);
                            }
                        }, 100);
                    }

                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        connectCommentList(postid,true);

    }

    private void connectCommentList(String postid,boolean flag) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            PostsCommentsListApiService postsCommentsListApiService = new PostsCommentsListApiService(this);
            postsCommentsListApiService.Connect(loginResDataModal.getData().getToken(), postid,flag, this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectComment(String postid,String message) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            PostsCommentApiService postsCommentApiService = new PostsCommentApiService(this);
            postsCommentApiService.Connect(loginResDataModal.getData().getToken(), postid, message,this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.posts_commentlist_tag)){
            CommetListDataModal commetListDataModal = (CommetListDataModal) superCastClass;
            dataList.addAll(commetListDataModal.getData().getComments());
            if (dataList.size() > 2){
                recycleview.smoothScrollToPosition(dataList.size()-1);
            }
            commentListAdapter.notifyDataSetChanged();

        }
        else if (Tag.equalsIgnoreCase(ApiConstants.posts_comment_tag)){
            PostCommentModal commentModal = (PostCommentModal) superCastClass;
            dataList.clear();
            connectCommentList(postid,false);
        }

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (sendButton == view){
            connectComment(postid,edt_message.getText().toString().trim());
            edt_message.setText("");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (dataList.size() > 0){
            Intent intent = new Intent(getString(R.string.COMMENT_POSITION_RECEIVER));
            intent.putExtra("count",dataList.size());
            sendBroadcast(intent);
        }

    }
}
