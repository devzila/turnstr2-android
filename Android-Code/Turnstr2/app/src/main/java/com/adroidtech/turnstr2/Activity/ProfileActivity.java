package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.R;
import com.bumptech.glide.Glide;

import com.bumptech.glide.request.target.SimpleTarget;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Stack;

public class ProfileActivity extends Activity {
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private FrameLayout layout_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        layout_frame = (FrameLayout) findViewById(R.id.layout_frame1);
        loadAllImagesToCube();
    }

    private void loadAllImagesToCube() {
        final Stack<String> strings = new Stack<>();
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
//        SimpleTarget target = new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
//                mBbitmap.add(bitmap);
//            }
//        };
//        while (strings.size() > 0) {
//            String currentUrl = strings.pop();
//            Glide.with(this).load(currentUrl).asBitmap().into(target);
//        }


        new URLImageParser(strings, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap = bitmap;
                view = new Cubesurfaceview(ProfileActivity.this, bitmap);
                layout_frame.addView(view);
            }
        }).execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (view != null) view.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (view != null) view.onPause();
    }
}
