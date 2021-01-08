/*
 * Copyright (c) 2014-2019, Digi International Inc. <support@digi.com>
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.wdp.CubeScreen;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Modal.StoryDataModal;

import java.util.ArrayList;
import java.util.List;

/**
 * OpenGL Custom renderer used with GLSurfaceView .
 */
class CubeGLRenderer implements GLSurfaceView.Renderer {

    // Touch screen event handler.
    private DragControl dragControl;

    // OpenGL object in form of a rendered cube.
    private  PhotoCube photocube;
    private float angleCube = 30;

    private float speedCube = 0.5f;
    public float mAngleX, mAngleY;

    /**
     * Class constructor.
     *
     * @param context Application context.
     */
    CubeGLRenderer(Context context, List<PostDataModal.DataBean.PostsBean.MediaProfileBean> dataList) {
        photocube = new PhotoCube(context,dataList);
    }

    CubeGLRenderer(Context context,Context mcontext, List<MyPostDataModal.DataBean.PostsBean.MediaProfileBean> dataList) {
        photocube = new PhotoCube(context,mcontext,dataList);
    }



    CubeGLRenderer(Context context, String blank, List<StoryDataModal.DataBean.UsersBean.StoriesBean.MediaProfileBean> dataList) {
        photocube = new PhotoCube(context, blank, dataList);
    }

    CubeGLRenderer(Context context, String blank,String blank2,String blank3,String blank4, List<MeStoryDataModal.DataBean.StoriesBean.MediaProfileBean> dataList) {
        photocube = new PhotoCube(context, blank,blank2,blank3,blank4, dataList);
    }

    public CubeGLRenderer(Context context, String avatar, String avatar2, String avatar3, String avatar4, String avatar5, String avatar6) {
        photocube = new PhotoCube(context, avatar, avatar2, avatar3, avatar4, avatar5, avatar6);

    }


	CubeGLRenderer(Context context, ArrayList<CubeModal> profileLis) {
		photocube = new PhotoCube(context,"","",profileLis);
	}
    /**
     * Sets the drag control with the given one.
     *
     * @param dragControl New drag control.
     */
    void setDragControl(DragControl dragControl) {
        this.dragControl = dragControl;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set color's clear-value to black.
        //gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        // Set depth's clear-value to farthest.
        gl.glClearDepthf(1.0f);
        // Enables depth-buffer for hidden surface removal.
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_BLEND);
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // Nice perspective view
        //gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        // Enable smooth shading of color.
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Selects blending method.
        //gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        // Allows alpha channels or transperancy.
        gl.glEnable(GL10.GL_ALPHA_TEST);
        // Sets aplha function.
        gl.glAlphaFunc(GL10.GL_GREATER, 0.1f);
        // Enable texture.
        gl.glEnable(GL10.GL_TEXTURE_2D);
        // Load cube textures.
        photocube.loadTexture(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        // To prevent divide by zero
        float aspect = (float) width / height;
        // Set the viewport (display area) to cover the entire window.
        gl.glViewport(0, 0, width, height);
        // Setup perspective projection, with aspect ratio matches viewport
        // Select projection matrix.
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset projection matrix.
        gl.glLoadIdentity();
        // Use perspective projection.
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);
        // Select model-view matrix.
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset.
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers using clear-value set earlier.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // Reset the model-view matrix.
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        // Translate into the screen depending on scale.
        gl.glTranslatef(0.0f, 0.0f, dragControl.getCurrentScale());
        // Rotate depending on touch rotation.
        gl.glMultMatrixf(dragControl.currentRotation().toMatrix(), 0);
        // Draw the cube.
        //gl.glScalef(0.8f, 0.8f, 0.8f);      // Scale down (NEW)
        //gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // rotate about the axis (1,1,1) (NEW)
        gl.glRotatef(angleCube, 0.0f, 1.0f, 0.0f);
        photocube.draw(gl);

    }
}