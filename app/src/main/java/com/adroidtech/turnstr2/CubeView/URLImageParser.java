package com.adroidtech.turnstr2.CubeView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.widget.TextView;

import com.adroidtech.turnstr2.R;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Stack;

public class URLImageParser extends AsyncTask<Object, Void, ArrayList<Bitmap>> {

    private static final String TAG = "Image Down";
    public AsyncCallback asyncCallback;
    private LevelListDrawable mDrawable;
    TextView textView;
    Stack<String> allURL = new Stack<>();
    ArrayList<Bitmap> allBitMaps = new ArrayList<>();

    public URLImageParser(Stack<String> strings, AsyncCallback asyncCallback) {
        try {
            for (int i = 0; i < strings.size(); i++)
                if (strings.get(i) != null && strings.get(i).length() > 1)
                    allURL.push(strings.get(i));
            int loc = 0;
            while (allURL.size() > 0 && allURL.size() < 6)
                allURL.push(allURL.get(loc++));
        } catch (Exception e) {
            this.allURL = strings;
        }
        this.asyncCallback = asyncCallback;
    }

    @Override
    protected ArrayList<Bitmap> doInBackground(Object... params) {
        try {
            while (allURL.size() > 0) {
                String currentUrl = allURL.pop();
                try {
                    URL url = new URL(currentUrl);
                    URLConnection Con = url.openConnection();
                    Con.setUseCaches(true);
                    Bitmap bitmap = BitmapFactory.decodeStream(Con.getInputStream());
                    bitmap = addBorderColor(bitmap, 1, Color.BLACK);
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

    private Bitmap addBorderColor(Bitmap bmp, int borderSize, int white) {
        Bitmap bmpWithBorder = Bitmap.createBitmap(bmp.getWidth() + borderSize * 2, bmp.getHeight() + borderSize * 2, bmp.getConfig());
        Canvas canvas = new Canvas(bmpWithBorder);
        canvas.drawColor(white);
        canvas.drawBitmap(bmp, borderSize, borderSize, null);
        return bmpWithBorder;
    }

    public static interface AsyncCallback {

        public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt);
    }
}