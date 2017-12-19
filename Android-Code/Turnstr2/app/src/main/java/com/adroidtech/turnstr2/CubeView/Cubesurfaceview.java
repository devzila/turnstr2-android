package com.adroidtech.turnstr2.CubeView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import java.util.ArrayList;
//import es2.learning.ViewPortRenderer;

public class Cubesurfaceview extends GLSurfaceView {

    float touchedX = 0;
    float touchedY = 0;
    Cuberenderer renderer;

    public Cubesurfaceview(Context context, ArrayList<Bitmap> allBitmaps) {
        super(context);
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setRenderer(renderer = new Cuberenderer(this, allBitmaps));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchedX = event.getX();
            touchedY = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            renderer.xAngle += (touchedX - event.getX()) / 5f;
            renderer.yAngle += (touchedY - event.getY()) / 5f;
            touchedX = event.getX();
            touchedY = event.getY();
        }
        return true;

    }
}