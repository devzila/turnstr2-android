package com.adroidtech.turnstr2.CubeView;

import android.app.Activity;
import android.content.Context;
import android.opengl.EGLConfig;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.TextView;

import javax.microedition.khronos.opengles.GL10;

public class MyGlSurfaceView extends GLSurfaceView
{
    TextView tv;
    Activity maContext;
    public MyGlSurfaceView(Context context)
    {
          super(context);
            
          setEGLContextClientVersion(2);
      
        setRenderer(new Renderer() {
            
              public void onSurfaceCreated(GL10 unused, EGLConfig config) {
                    // Set the background frame color
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);// r g b alpha
                }

                public void onDrawFrame(GL10 unused) {
                    // Redraw background color
                    GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
                }

            @Override
            public void onSurfaceCreated(GL10 gl, javax.microedition.khronos.egl.EGLConfig config) {

            }

            public void onSurfaceChanged(GL10 unused, int width, int height) {
                    GLES20.glViewport(0, 0, width, height);
                }
        });
          setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
    
     @Override
     public boolean onTouchEvent(MotionEvent e)
     {
         final float x=e.getX();
         final float y=e.getY();

        return false;
         
     }
    

}