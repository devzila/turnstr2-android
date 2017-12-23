package com.adroidtech.turnstr2.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;


public class URLImageParser extends AsyncTask<Object, Void, Bitmap> {

    private LevelListDrawable mDrawable;
    TextView textView;

    @Override
    protected Bitmap doInBackground(Object... params) {
        Context context = (Context) params[0];
        String source = (String) params[1];
        mDrawable = (LevelListDrawable) params[2];
        textView = (TextView) params[3];
        Log.d("TAG", "Image in Background " + source);
        int screenWidth = GeneralValues.getScreenWidth(context);
        try {
            InputStream is = new URL(source).openStream();
            if (is.markSupported()) is.reset();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            if (bitmap != null) {
                if (bitmap.getWidth() > screenWidth / 1.2)
                    return Utils.getSmallBitmap(bitmap, screenWidth);
                else {
                    return bitmap;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Log.d("TAG", "onPostExecute drawable " + mDrawable);
        Log.d("TAG", "onPostExecute bitmap " + bitmap);
        if (bitmap != null) {
            BitmapDrawable d = new BitmapDrawable(bitmap);
            mDrawable.addLevel(1, 1, d);
            mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            mDrawable.setLevel(1);
            CharSequence t = textView.getText();
            textView.setText(t);
        }
    }
}