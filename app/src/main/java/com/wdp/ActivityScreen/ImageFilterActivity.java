package com.wdp.ActivityScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wdp.Adapter.SelectedImage_Adapter;
import com.wdp.Adapter.ThumbnailsAdapter;

import com.wdp.Utility.ExoPlayerRecyclerViewSelectMedia;
import com.wdp.Utility.FileUtils;
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
import java.util.ArrayList;
import java.util.List;

public class ImageFilterActivity extends Activity implements View.OnClickListener,
        ExoPlayerRecyclerViewSelectMedia.OnSelectPositon, ThumbnailsAdapter.ThumbnailsAdapterListener {
    ArrayList<String> imageUrl = new ArrayList<>();
    ;
    ArrayList<String> tempimageUrl = new ArrayList<>();
    ExoPlayerRecyclerViewSelectMedia exoPlayerRecyclerViewSelectMedia;
    SelectedImage_Adapter selectedImage_adapter;
    RecyclerView recycler_view;
    ThumbnailsAdapter thumbnailsAdapter;
    boolean firstTime = false;
    List<ThumbnailItem> thumbnailItemList = new ArrayList<>();
    private int intPosition = 0;
    TextView txt_next;
    Bitmap filterBitmap;
    Bitmap finalImage;
    ImageView img_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fillter);
        initView();
        prepair();

    }

    private void initView() {
        exoPlayerRecyclerViewSelectMedia = findViewById(R.id.picker);
        recycler_view = findViewById(R.id.recycler_view);
        txt_next = findViewById(R.id.txt_next);
        img_back = findViewById(R.id.img_back);
    }

    private void prepair() {
        txt_next.setVisibility(View.VISIBLE);
        Gson gson = new Gson();
        if (getIntent().getStringExtra("selectlist") != null) {
            String img = getIntent().getStringExtra("selectlist");
            TypeToken<List<String>> token = new TypeToken<List<String>>() {
            };
            imageUrl = gson.fromJson(img, token.getType());
            tempimageUrl = imageUrl;
        }

        if (tempimageUrl.size() > 0) {
            if (tempimageUrl.get(intPosition).contains(".jpg")) {
                File image = new File(tempimageUrl.get(intPosition));
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap filterBitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
                prepareThumbnail(filterBitmap);
                recycler_view.setVisibility(View.VISIBLE);
            } else {
                recycler_view.setVisibility(View.GONE);

            }

        }

        //....filter list
        thumbnailsAdapter = new ThumbnailsAdapter(this, thumbnailItemList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        recycler_view.addItemDecoration(new SpacesItemDecoration(space));
        recycler_view.setAdapter(thumbnailsAdapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        exoPlayerRecyclerViewSelectMedia.setListener(this);
        exoPlayerRecyclerViewSelectMedia.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        exoPlayerRecyclerViewSelectMedia.addItemDecoration(itemDecorator);
        exoPlayerRecyclerViewSelectMedia.setMediaObjects(imageUrl, this);
        selectedImage_adapter = new SelectedImage_Adapter(this, imageUrl, initGlide());
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


        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUrl.size() > 0) {
                    Intent i = new Intent(ImageFilterActivity.this, FeedPostActivity.class);
                    Gson gson = new Gson();
                    String arrayData = gson.toJson(imageUrl);
                    i.putExtra("selectlist", arrayData);
                    startActivity(i);
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_grey_border_bottom)
                .error(R.drawable.white_grey_border_bottom);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onselectPositon(int position) {
        intPosition = position;
        if (tempimageUrl.size() > 0) {
            if (tempimageUrl.get(intPosition).contains(".jpg") || tempimageUrl.get(intPosition).contains(".PNG") || tempimageUrl.get(intPosition).contains(".GIF") ||
                    tempimageUrl.get(intPosition).contains(".WEBP") || tempimageUrl.get(intPosition).contains(".TIFF") || tempimageUrl.get(intPosition).contains(".PSD") || tempimageUrl.get(intPosition).contains(".JPEG")) {
                File image = new File(tempimageUrl.get(intPosition));
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap filterBitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
                prepareThumbnail(filterBitmap);
                recycler_view.setVisibility(View.VISIBLE);
            } else {
                recycler_view.setVisibility(View.VISIBLE);
            }
        }
    }


    private void updateSelectedImageList(int pos, String url) {
        Log.d("url", "---->" + url);
        imageUrl.set(pos, url);
        selectedImage_adapter.notifyDataSetChanged();
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void prepareThumbnail(Bitmap bitmap) {
        Bitmap thumbImage;
        if (bitmap == null) {
            thumbImage = BitmapUtils.getBitmapFromAssets(this, "dog.jpg", 100, 100);
        } else {
            thumbImage = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        }
        //thumbImage = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        if (thumbImage == null)
            return;

        ThumbnailsManager.clearThumbs();
        thumbnailItemList.clear();

        // add normal bitmap first
        ThumbnailItem thumbnailItem = new ThumbnailItem();
        thumbnailItem.image = thumbImage;
        thumbnailItem.filterName = getResources().getString(R.string.filter_normal);
        ThumbnailsManager.addThumb(thumbnailItem);

        List<Filter> filters = FilterPack.getFilterPack(this);

        for (Filter filter : filters) {
            ThumbnailItem tI = new ThumbnailItem();
            tI.image = thumbImage;
            tI.filterName = filter.getName();
            ThumbnailsManager.addThumb(tI);
        }

        thumbnailItemList.addAll(ThumbnailsManager.processThumbs(this));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (thumbnailsAdapter != null) {
                    thumbnailsAdapter.notifyDataSetChanged();
                }

            }
        });
    }


    @Override
    public void onFilterSelected(Filter filter) {
        Log.d("url", "---->" + filter.getName());
        File image = new File(imageUrl.get(intPosition));
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
        finalImage = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        filterBitmap = filter.processFilter(finalImage);
        Uri uri = getImageUri(this, filterBitmap);
        String selectedPath = FileUtils.getPath(this, uri);
        updateSelectedImageList(intPosition, selectedPath);
    }
}
