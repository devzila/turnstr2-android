package com.adroidtech.turnstr2.Utils.CachingFiles;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    ViewPager mViewPager;
    Context context;
    //	ProgressBar progrss;
    ExecutorService executorService;
    private Map<ImageView, String> imageViews = Collections
            .synchronizedMap(new WeakHashMap<ImageView, String>());

    public ImageLoader(Context context) {
        this.context = context;
//		this.progrss=progrss;
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);
    }

    // final int stub_id=R.drawable.ic_launcher;
    public Bitmap DisplayImage(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null) {
            return bitmap;
        } else {
            queuePhoto(url);
            // imageView.setImageResource(stub_id);
        }
        return bitmap;
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

    public File saveImageToSD(String filename, Bitmap bmp) {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Temp");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(folder.getAbsolutePath() + "/" + filename + ".png");
        ;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream out = null;
        try {

            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 40, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    private void queuePhoto(final String url) {
        PhotoToLoad p = new PhotoToLoad(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = getBitmap(url);
                memoryCache.put(url, bmp);
                long time = Calendar.getInstance().getTimeInMillis();
                File file = saveImageToSD("IMg_" + time, bmp);
            }
        });

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
        // try {
        // //decode image size
        // BitmapFactory.Options o = new BitmapFactory.Options();
        // o.inJustDecodeBounds = true;
        // BitmapFactory.decodeStream(new FileInputStream(f),null,o);
        //
        // //Find the correct scale value. It should be the power of 2.
        // final int REQUIRED_SIZE=70;
        // int width_tmp=o.outWidth, height_tmp=o.outHeight;
        // int scale=1;
        // while(true){
        // if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
        // break;
        // width_tmp/=2;
        // height_tmp/=2;
        // scale*=2;
        // }
        //
        // //decode with inSampleSize
        // BitmapFactory.Options o2 = new BitmapFactory.Options();
        // o2.inSampleSize=scale;
        // return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        // } catch (FileNotFoundException e) {}
        // return null;

        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 2;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), options);
        return bitmap;

    }

    // Task for the queue
    private class PhotoToLoad {
        public String url;

        public ArrayList<Uri> uris;

        public PhotoToLoad(String u) {
            url = u;
        }

    }


}
