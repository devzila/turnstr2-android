package com.adroidtech.turnstr2.CubeView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

public class URLImageParser extends AsyncTask<Object, Void, ArrayList<Bitmap>> {

    private static final String TAG = "Image Down";
    private final AsyncCallback asyncCallback;
    private LevelListDrawable mDrawable;
    TextView textView;
    Stack<String> allURL;
    ArrayList<Bitmap> allBitMaps = new ArrayList<>();


    public URLImageParser(Stack<String> allURL, AsyncCallback asyncCallback) {
        this.allURL = allURL;
        this.asyncCallback = asyncCallback;
    }

    @Override
    protected ArrayList<Bitmap> doInBackground(Object... params) {
        try {
            while (allURL.size() > 0) {
                String currentUrl = allURL.pop();
                try {
                    URL url = new URL(currentUrl);
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    allBitMaps.add(bitmap);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            return allBitMaps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allBitMaps;
    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> allBitmap) {
        if (allBitmap != null) {
            asyncCallback.getAsyncResult(allBitmap, "Image");
//            BitmapDrawable d = new BitmapDrawable(bitmap);
        }
    }

    public static interface AsyncCallback {

        public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt);
    }
}