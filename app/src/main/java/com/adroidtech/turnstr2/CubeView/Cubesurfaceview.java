package com.adroidtech.turnstr2.CubeView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.adroidtech.turnstr2.R;

import java.util.ArrayList;
//import es2.learning.ViewPortRenderer;

public class Cubesurfaceview extends GLSurfaceView {
    final Handler handler = new Handler();
    FrameLayout layoutFrame;
    OnClickListener onClickListener;
    Context context;
    float touchedX = 0;
    float touchedY = 0;
    Cuberenderer renderer;
    private boolean mRotateEnable = true;
    private boolean LastActionActionDown = false;

    public Cubesurfaceview(Context context, ArrayList<Bitmap> allBitmaps, boolean mRotateEnable) {
        super(context);
        this.context = context;
        this.mRotateEnable = mRotateEnable;
        setEGLContextClientVersion(2);
        if (allBitmaps == null || allBitmaps.size() == 0) {
            allBitmaps = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                allBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_image_cube));
            }
        }
        setRenderer(renderer = new Cuberenderer(this, allBitmaps));
    }

    public Cubesurfaceview(Context context, ArrayList<Bitmap> allBitmaps, boolean mRotateEnable, FrameLayout layoutFrame) {
        this(context, allBitmaps, mRotateEnable);
        this.layoutFrame = layoutFrame;
    }

//    @Override
//    public void setZOrderOnTop(boolean onTop) {
//        super.setZOrderOnTop(true);
//    }

    private final float TOUCH_SCALE_FACTOR = 220.0f / 280;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
//        if (mRotateEnable) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPreviousX = x;
                mPreviousY = y;
                handler.postDelayed(mLongPressed, 500);
                break;
            case MotionEvent.ACTION_MOVE:
                if ((mPreviousX - 2 >= x || mPreviousX + 2 <= x) && (mPreviousY - 2 >= y || mPreviousY + 2 <= y)) {
                    handler.removeCallbacks(mLongPressed);
                }
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;
                // reverse direction of rotation above the mid-line
//                if (y > getHeight() / 2) {
//                    Log.e("Y is big ", "true");
//                    dx = dx * -1;
//                }
                // reverse direction of rotation to left of the mid-line
//                if (x < getWidth() / 2) {
//                    Log.e("X is big ", "true");
//                    dy = dy * -1;
//                }
                renderer.xAngle = (renderer.xAngle + (-(dx) * TOUCH_SCALE_FACTOR / 5f)) % 360;
                float xAngle = 0;
                if (renderer.xAngle < 0) {
                    xAngle = 360 + renderer.xAngle;
                } else {
                    xAngle = renderer.xAngle;
                }
                if ((xAngle < 90 || xAngle > 260)) {
                    renderer.yAngle = renderer.yAngle + (((dy) * TOUCH_SCALE_FACTOR / 5f)) % 360;
                } else {
                    renderer.yAngle = renderer.yAngle + (-((dy) * TOUCH_SCALE_FACTOR / 5f)) % 360;
                }
//                renderer.yAngle = renderer.yAngle + (-((dy) * TOUCH_SCALE_FACTOR / 5f)) % 360;
                requestRender();
                break;
            default:
                if ((mPreviousX - 1 >= x || mPreviousX + 1 <= x) && (mPreviousY - 1 >= y || mPreviousY + 1 <= y)) {
                    handler.removeCallbacks(mLongPressed);
                }
                break;
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;

//        } else {
//            return false;
//        }

    }

    Runnable mLongPressed = new Runnable() {
        public void run() {
            try {
                if (layoutFrame != null) layoutFrame.performClick();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}