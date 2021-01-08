package com.wdp.ActivityScreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.filestack.FileLink;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wdp.Adapter.EditMultiSelectAdapter;
import com.wdp.Adapter.FinalMultiSelectAdapter;
import com.wdp.ApiServices.PostApiService;
import com.wdp.ApiServices.SuffleMediaApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CreatePosts_model;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Modal.SufflePosts_model;
import com.wdp.Utility.CommanUtility;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.EditSimpleItemTouchHelperCallback;
import com.wdp.Utility.NetworkCheck;
import com.wdp.Utility.OnStartDragListener;
import com.wdp.Utility.SimpleItemTouchHelperCallback;
import com.wdp.turnstr.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.wdp.Interface.ApiConstants.ME_POSTS;

public class FeedEditPostActivity extends Activity implements
        EditMultiSelectAdapter.onClickApprove, OnStartDragListener, ApiResponseListner {
    ArrayList<MyPostDataModal.DataBean.PostsBean.MediaProfileBean> imageUrl = new ArrayList<>();
    RecyclerView recyclerViewMultiImage;
    EditMultiSelectAdapter editMultiSelectAdapter;
    ItemTouchHelper mItemTouchHelper;
    TextView txt_next;
    ImageView img_back;
    RoundedImageView imageView;
    EditText edt_caption;
    List<FileLink> resultOut;
    private String fileType;
    File file;
    LoginResDataModal loginResDataModal;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    String postid;
    String caption="";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedpost);
        initView();
        prepair();

    }

    private void initView(){
        recyclerViewMultiImage = (RecyclerView) findViewById(R.id.recyclerViewMultiImage);
        txt_next = (TextView) findViewById(R.id.txt_next);
        img_back = (ImageView) findViewById(R.id.img_back);
        imageView = (RoundedImageView) findViewById(R.id.imageView);
        edt_caption = (EditText) findViewById(R.id.edt_caption);
    }

    private void prepair(){
        txt_next.setVisibility(View.VISIBLE);
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        Gson gson = new Gson();
        if (getIntent().getStringExtra("selectlist") != null){
            String  img = getIntent().getStringExtra("selectlist");
            postid = getIntent().getStringExtra("postid");
            caption = getIntent().getStringExtra("caption");
            TypeToken<List<MyPostDataModal.DataBean.PostsBean.MediaProfileBean>> token = new TypeToken<List<MyPostDataModal.DataBean.PostsBean.MediaProfileBean>>() {};
            imageUrl = gson.fromJson(img, token.getType());

        }
        if (imageUrl.size() > 0){
            editMultiSelectAdapter = new EditMultiSelectAdapter(this,imageUrl,this,this);
            recyclerViewMultiImage.setHasFixedSize(true);
            recyclerViewMultiImage.setAdapter(editMultiSelectAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
            recyclerViewMultiImage.setLayoutManager(layoutManager);
            ItemTouchHelper.Callback callback = new EditSimpleItemTouchHelperCallback(editMultiSelectAdapter);
            mItemTouchHelper = new ItemTouchHelper(callback);
            mItemTouchHelper.attachToRecyclerView(recyclerViewMultiImage);
        }
        edt_caption.setText(caption);
        Glide.with(this)
                .load(loginResDataModal.getData().getUser().getAvatar())
                .placeholder(R.drawable.profile)
                .into(imageView);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ME_POSTS + postid;
                CreatePosts_model model = new CreatePosts_model();
                CreatePosts_model.Post post = new CreatePosts_model.Post();
                post.setCaption("" + edt_caption.getText().toString());
                model.setPost(post);
                connectEditPost(url,model);

            }
        });

    }

    private void connectEditPost(String url,CreatePosts_model model){
        if (NetworkCheck.isConnected(this)) {
            PostApiService postApiService = new PostApiService(this);
            postApiService.Connect(url,model,loginResDataModal.getData().getToken(),this);
        }
       else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectSuffleMedia(SufflePosts_model.Post post) {
        String url = ApiConstants.ME_SHUFFLEMEDIAS.replace("(postId)", postid);
        if (NetworkCheck.isConnected(this)) {
            SuffleMediaApiService suffleMediaApiService = new SuffleMediaApiService(this);
            suffleMediaApiService.Connect(post, loginResDataModal.getData().getToken(), url, this);
        } else {
            Toast.makeText(this,getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onclickApprove(int pos) {
        txt_next.setTag("0");
    }



    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equals(ApiConstants.ME_POSTS_Tag)) {
            PostDataModal postDataModal = (PostDataModal) superCastClass;
            List<SufflePosts_model.Sequence_attributes> mediaList = new ArrayList<>();
            if (imageUrl.size() > 0) {
                for (int i = 0; i < imageUrl.size(); i++) {
                    SufflePosts_model.Sequence_attributes sequence_attributes = new SufflePosts_model.Sequence_attributes();
                    SufflePosts_model.Post post1 = new SufflePosts_model.Post();
                    sequence_attributes.setId(imageUrl.get(i).getId());
                    sequence_attributes.setSequence(String.valueOf(i));
                    mediaList.add(sequence_attributes);
                }
                SufflePosts_model.Post post1 = new SufflePosts_model.Post();
                post1.setMedias_attributes(mediaList);
                connectSuffleMedia(post1);

            }
        }
        else if (Tag.equalsIgnoreCase(ApiConstants.SuffleMedia_TAG)){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
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
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
