package com.adroidtech.turnstr2.CubeView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.adroidtech.turnstr2.R;

import java.util.ArrayList;
//import es2.learning.ViewPortRenderer;

public class Cubesurfaceview extends GLSurfaceView {

    float touchedX = 0;
    float touchedY = 0;
    Cuberenderer renderer;

    public Cubesurfaceview(Context context, ArrayList<Bitmap> allBitmaps) {
        super(context);
        setEGLContextClientVersion(2);
        if(allBitmaps==null ||allBitmaps.size()==0){
            allBitmaps=new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                allBitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.image_icon));
            }
        }
        setRenderer(renderer = new Cuberenderer(this, allBitmaps));
    }

    @Override
    public void setZOrderOnTop(boolean onTop) {
        super.setZOrderOnTop(onTop);
    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            touchedX = event.getX();
//            touchedY = event.getY();
//        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            renderer.xAngle += (touchedX - event.getX()) / 5f;
//            renderer.yAngle += (touchedY - event.getY()) / 5f;
//            touchedX = event.getX();
//            touchedY = event.getY();
//        }
//        return true;
//
//    }
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1;
                }
                renderer.xAngle = renderer.xAngle + (dx) * TOUCH_SCALE_FACTOR / 5f;
                renderer.yAngle = renderer.yAngle + (dy) * TOUCH_SCALE_FACTOR / 5f;
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}