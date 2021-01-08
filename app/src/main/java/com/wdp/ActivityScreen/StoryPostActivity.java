package com.wdp.ActivityScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.filestack.FileLink;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.wdp.Adapter.SelectedImage_Adapter;
import com.wdp.Adapter.ThumbnailsAdapter;
import com.wdp.ApiServices.FavouriteFiveApiService;
import com.wdp.ApiServices.PostApiService;
import com.wdp.ApiServices.StoryPostApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommanDataModal;
import com.wdp.Modal.CreatePosts_model;
import com.wdp.Modal.CreateStroyRequest_model;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommanUtility;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.ExoPlayerRecyclerViewSelectMedia;
import com.wdp.Utility.NetworkCheck;
import com.wdp.Utility.SnapHelperOneByOne;
import com.wdp.Utility.SpacesItemDecoration;
import com.wdp.Utility.VerticalSpacingItemDecorator;
import com.wdp.Utility.galleryImage.BitmapUtils;
import com.wdp.Utility.galleryImage.photofilters.imageprocessors.Filter;
import com.wdp.Utility.galleryImage.photofilters.imageprocessors.utils.FilterPack;
import com.wdp.Utility.galleryImage.photofilters.imageprocessors.utils.ThumbnailItem;
import com.wdp.Utility.galleryImage.photofilters.imageprocessors.utils.ThumbnailsManager;
import com.wdp.turnstr.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoryPostActivity extends Activity implements View.OnClickListener,
        ExoPlayerRecyclerViewSelectMedia.OnSelectPositon, ApiResponseListner {
    ArrayList<String> imageUrl = new ArrayList<>();;
    ExoPlayerRecyclerViewSelectMedia exoPlayerRecyclerViewSelectMedia;
    SelectedImage_Adapter selectedImage_adapter;
    boolean firstTime= false;
    TextView txt_next,txt_share_story;
    List<FileLink> resultOut;
    File file;
    ImageView img_back;
    String fileType;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_post);
        initView();
        prepair();
    }

    private void initView(){
        exoPlayerRecyclerViewSelectMedia = findViewById(R.id.picker);
        txt_next = findViewById(R.id.txt_next);
        txt_share_story = findViewById(R.id.txt_share_story);
        img_back = findViewById(R.id.img_back);
    }

    private void prepair(){
        txt_next.setVisibility(View.GONE);

        Gson gson = new Gson();
        if (getIntent().getStringExtra("selectlist") != null){
            String  img = getIntent().getStringExtra("selectlist");
            TypeToken<List<String>> token = new TypeToken<List<String>>() {};
            imageUrl = gson.fromJson(img, token.getType());
        }



        new AsyncTaskRunner().execute(imageUrl);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        exoPlayerRecyclerViewSelectMedia.setListener(this);
        exoPlayerRecyclerViewSelectMedia.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        exoPlayerRecyclerViewSelectMedia.addItemDecoration(itemDecorator);
        exoPlayerRecyclerViewSelectMedia.setMediaObjects(imageUrl,this);
        selectedImage_adapter = new SelectedImage_Adapter(this, imageUrl,initGlide());
        exoPlayerRecyclerViewSelectMedia.setAdapter(selectedImage_adapter);
        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        linearSnapHelper.attachToRecyclerView(exoPlayerRecyclerViewSelectMedia);
        if (firstTime) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    exoPlayerRecyclerViewSelectMedia.playVideo(false);
                }
            });
            firstTime = false;
        }

        txt_share_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUrl.size() > 0) {
                    if (resultOut != null){
                        txt_share_story.setTag("1");
                        createRequest();
                    }
                    else {
                        txt_share_story.setTag("1");
                        new AsyncTaskRunner().execute(imageUrl);
                    }

                }
                else {
                    Toast.makeText(StoryPostActivity.this,getResources().getString(R.string.please_select_at_leaset_one_post),Toast.LENGTH_LONG).show();
                }
            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void connectStoryPost(CreateStroyRequest_model createStroyRequest_model) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            StoryPostApiService storyPostApiService = new StoryPostApiService(this);
            storyPostApiService.Connect(createStroyRequest_model,loginResDataModal.getData().getToken(), this);


        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_grey_border_bottom)
                .error(R.drawable.white_grey_border_bottom);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }
    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.StoryPost_TAG)){
            CommanDataModal commanDataModal = (CommanDataModal) superCastClass;
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onException(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    public class AsyncTaskRunner extends AsyncTask<List<String>, String, List<FileLink>> {
        @Override
        protected void onPostExecute(List<FileLink> result) {
            resultOut = result;
            if (null != txt_share_story.getTag() && txt_share_story.getTag().toString().equals("1")) {
                createRequest();
            }
        }
        @Override
        protected List<FileLink> doInBackground(List<String>... lists) {
            List<FileLink> result = new ArrayList<>();
            int i =0;
            for (String uu : imageUrl) {
                try {

                    Bitmap finalBitmap = BitmapFactory.decodeFile(uu);
                    String root = Environment.getExternalStorageDirectory().toString();
                    File myDir = new File(root + "/Turnstrs");
                    if (!myDir.exists()){
                        myDir.mkdirs();
                    }
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String fname = "turnstrs" + timeStamp + ".jpg";
                    file = new File(uu);
                    if (imageUrl.get(i++).contains(".jpg") || imageUrl.get(i++).contains(".PNG") || imageUrl.get(i++).contains(".GIF") ||
                            imageUrl.get(i++).contains(".WEBP") ||imageUrl.get(i++).contains(".TIFF") ||imageUrl.get(i++).contains(".PSD")||imageUrl.get(i++).contains(".JPEG")){
                        fileType = "photo";
                    }
                    else {
                        fileType = "video";
                    }
                    if (!fileType.equals("video")) {
                        FileOutputStream out = new FileOutputStream(file);
                        finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.flush();
                        out.close();
                    }
                    FileLink fileLink = CommanUtility.Upload(file.getAbsolutePath(), fileType);
                    result.add(fileLink);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(String... text) {


        }
    }

    private void createRequest() {
        CreateStroyRequest_model model = new CreateStroyRequest_model();
        List<CreateStroyRequest_model.Medias_attributes> mediaList = new ArrayList<>();
        int i=0;
        if (resultOut != null){
            for (FileLink fileLink : resultOut) {
                if (imageUrl.get(i++).contains(".jpg") || imageUrl.get(i++).contains(".PNG") || imageUrl.get(i++).contains(".GIF") ||
                        imageUrl.get(i++).contains(".WEBP") ||imageUrl.get(i++).contains(".TIFF") ||imageUrl.get(i++).contains(".PSD")||imageUrl.get(i++).contains(".JPEG")){
                    fileType = "photo";
                }
                else {
                    fileType = "video";
                }
                CreateStroyRequest_model.Medias_attributes medias_attributes = new CreateStroyRequest_model.Medias_attributes();
                medias_attributes.setGuid(fileLink.getHandle());
                medias_attributes.setMedia_content_type(fileType);
                medias_attributes.setMedia_url(CommanUtility.getAdaptiveUrl(fileLink, 400,fileType));
                mediaList.add(medias_attributes);
            }
            CreateStroyRequest_model.Story story = new CreateStroyRequest_model.Story();
            story.setMedias_attributes(mediaList);
            model.setStory(story);
            connectStoryPost(model);
        }

    }




    @Override
    public void onClick(View view) {

    }


    @Override
    public void onselectPositon(int position) {

    }
}
