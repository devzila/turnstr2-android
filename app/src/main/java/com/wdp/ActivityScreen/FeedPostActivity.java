package com.wdp.ActivityScreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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
import com.wdp.Adapter.FinalMultiSelectAdapter;
import com.wdp.ApiServices.PostApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.GoogleProgressDialog;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CreatePosts_model;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Utility.CommanUtility;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.OnStartDragListener;
import com.wdp.Utility.SimpleItemTouchHelperCallback;
import com.wdp.turnstr.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedPostActivity extends Activity implements
        FinalMultiSelectAdapter.onClickApprove, OnStartDragListener, ApiResponseListner {
    ArrayList<String> imageUrl = new ArrayList<String>();
    RecyclerView recyclerViewMultiImage;
    FinalMultiSelectAdapter finalMultiSelectAdapter;
    ItemTouchHelper mItemTouchHelper;
    TextView txt_next;
    ImageView img_back;
    RoundedImageView imageView;
    EditText edt_caption;
    List<FileLink> resultOut;
    private String fileType;
    private GoogleProgressDialog progressDialog;
    File file;
    LoginResDataModal loginResDataModal;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();


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
            TypeToken<List<String>> token = new TypeToken<List<String>>() {};
            imageUrl = gson.fromJson(img, token.getType());

        }
        if (imageUrl.size() > 0){
            finalMultiSelectAdapter = new FinalMultiSelectAdapter(this,imageUrl,this,this);
            recyclerViewMultiImage.setHasFixedSize(true);
            recyclerViewMultiImage.setAdapter(finalMultiSelectAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
            recyclerViewMultiImage.setLayoutManager(layoutManager);
            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(finalMultiSelectAdapter);
            mItemTouchHelper = new ItemTouchHelper(callback);
            mItemTouchHelper.attachToRecyclerView(recyclerViewMultiImage);
        }
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
        new AsyncTaskRunner().execute(imageUrl);
        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUrl.size() > 0) {
                    progressDialog = new GoogleProgressDialog(FeedPostActivity.this);
                    progressDialog.showDialog();
                    txt_next.setTag("1");
                    createRequest();
                }
                else {
                    Toast.makeText(FeedPostActivity.this,getResources().getString(R.string.please_select_at_leaset_one_post),Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void onclickApprove(int pos) {
        txt_next.setTag("0");
    }

    private void createRequest() {
        CreatePosts_model model = new CreatePosts_model();
        List<CreatePosts_model.Medias_attributes> mediaList = new ArrayList<>();
        int i=0;
        if (resultOut != null){
            for (FileLink fileLink : resultOut) {
                int value = i++;
                if (imageUrl.get(value).contains(".jpg") || imageUrl.get(value).contains(".PNG") || imageUrl.get(value).contains(".GIF") ||
                        imageUrl.get(value).contains(".WEBP") ||imageUrl.get(value).contains(".TIFF") ||imageUrl.get(value).contains(".PSD")||imageUrl.get(value).contains(".JPEG")){
                    fileType = "photo";
                }
                else {
                    fileType = "video";
                }
                CreatePosts_model.Medias_attributes medias_attributes = new CreatePosts_model.Medias_attributes();
                medias_attributes.setGuid(fileLink.getHandle());
                medias_attributes.setMedia_content_type(fileType);
                medias_attributes.setJob_id(file.getName());
                //medias_attributes.setMedia_url(file.getAbsolutePath());
                medias_attributes.setMedia_url(CommanUtility.getAdaptiveUrl(fileLink, 400,fileType));
                mediaList.add(medias_attributes);
            }
            CreatePosts_model.Post post = new CreatePosts_model.Post();
            if (txt_next.getTag().equals("1")){
                post.setMedias_attributes(mediaList);
            }
            post.setCaption("" + edt_caption.getText().toString());
            model.setPost(post);
            Log.d("mediaList","--->" + mediaList.size());
            progressDialog.dismiss();
            PostApiService postApiService = new PostApiService(this);
            postApiService.Connect(model,loginResDataModal.getData().getToken(),this);
        }

    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equals(ApiConstants.ME_POSTS_Tag)){
            PostDataModal postDataModal = (PostDataModal)superCastClass;
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


    public class AsyncTaskRunner extends AsyncTask<List<String>, String, List<FileLink>> {
        @Override
        protected void onPostExecute(List<FileLink> result) {
            resultOut = result;
            if (null != txt_next.getTag() && txt_next.getTag().toString().equals("1")) {
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
                    File myDir = new File(root + "/Gizmoh");
                    myDir.mkdirs();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String fname = "gizmoh_" + timeStamp + ".jpg";

                    file = new File(uu);
                    //if (file.exists()) file.delete ();
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    int value = i++;
                    if (imageUrl.get(value).contains(".jpg") || imageUrl.get(value).contains(".PNG") || imageUrl.get(value).contains(".GIF") ||
                            imageUrl.get(value).contains(".WEBP") ||imageUrl.get(value).contains(".TIFF") ||imageUrl.get(value).contains(".PSD")||imageUrl.get(value).contains(".JPEG")){
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
                    FileLink fileLink = CommanUtility.Upload(file.getPath(), fileType);
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

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
