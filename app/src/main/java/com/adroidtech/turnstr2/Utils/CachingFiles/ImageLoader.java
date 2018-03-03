package com.adroidtech.turnstr2.Utils.CachingFiles;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    Context context;
    ExecutorService executorService;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());

    public ImageLoader(Context context) {
        this.context = context;
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);
    }

    public Bitmap getCachedBitmap(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null) {
            return bitmap;
        } else {
            bitmap = getBitmap(url);
            if (bitmap != null) {
                memoryCache.put(url, bitmap);
            } else {
                bitmap = getBitmap(url);
            }
            return bitmap;
        }
    }

    public Bitmap getCachedVideoThumbnail(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null) {
            return bitmap;
        } else {
            bitmap = getBitmap(url);
            if (bitmap != null) {
                memoryCache.put(url, bitmap);
            }
//            else {
//                bitmap = getVideoBitmap(url);
//            }
            return bitmap;
        }
    }

    public Bitmap getVideoBitmap(String videoPath) {
        File f = fileCache.getFile(videoPath);
        // from SD cache
        Bitmap b = decodeFile(f);
        if (b != null)
            return b;
        // from web
        try {
            Bitmap bitmap = null;
            MediaMetadataRetriever mediaMetadataRetriever = null;
            try {
                mediaMetadataRetriever = new MediaMetadataRetriever();
                if (Build.VERSION.SDK_INT >= 14)
                    mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
                else
                    mediaMetadataRetriever.setDataSource(videoPath);
                bitmap = mediaMetadataRetriever.getFrameAtTime();
                try {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 0, out);
                    bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mediaMetadataRetriever != null) {
                    mediaMetadataRetriever.release();
                }
            }

        } catch (Exception e) {
        }
        return null;
    }

    public Bitmap getBitmap(String url) {
        File f = fileCache.getFile(url);
        // from SD cache
        Bitmap b = decodeFile(f);
        if (b != null)
            return b;
        // from web
        try {

            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl
                    .openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            try {
                byte[] buffer = new byte[4 * 1024]; // or other buffer size
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            } finally {
                os.close();
            }
            bitmap = decodeFile(f);
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 0, out);
                bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        } catch (Throwable ex) {
            ex.printStackTrace();
            if (ex instanceof OutOfMemoryError)
                memoryCache.clear();
            return null;
        }
    }

    // decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f) {
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 2;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), options);
        return bitmap;

    }

}
