package com.adroidtech.turnstr2.Utils.CachingFiles;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class TaskLoader extends AsyncTask<Void, Void, Bitmap> {
    private final Activity activity;
    private final String url;
    private final ImageView thumb_image;

    public TaskLoader(Activity activity, String url, ImageView thumb_image) {
        this.activity = activity;
        this.url = url;
        this.thumb_image = thumb_image;
    }

    @Override
    protected Bitmap doInBackground(Void... arg0) {
        return new ImageLoader(activity).getCachedVideoThumbnail(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmapCach) {
        super.onPostExecute(bitmapCach);
        thumb_image.setImageBitmap(bitmapCach);
    }
}
