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


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Modal.StoryDataModal;

import java.util.ArrayList;
import java.util.List;

/*
 * Custom GL view by extending GLSurfaceView so as
 * to override event handlers such as onKeyUp(), onTouchEvent().
 */
public class CubeGLSurfaceView extends GLSurfaceView {

    // Custom GL Renderer.
    private final CubeGLRenderer renderer;

    /**
     * Class constructor. Allocate and set the renderer.
     *
     * @param context Application context.
     */
    public CubeGLSurfaceView(Context context, List<PostDataModal.DataBean.PostsBean.MediaProfileBean> dataList) {
        super(context);
        renderer = new CubeGLRenderer(context, dataList);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer(renderer);
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }



    public CubeGLSurfaceView(Context context,Context mcontext, List<MyPostDataModal.DataBean.PostsBean.MediaProfileBean> dataList) {
        super(context);
        renderer = new CubeGLRenderer(context,mcontext, dataList);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer(renderer);
        // Request focus, otherwise key/button won't react
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }


    public CubeGLSurfaceView(Context context,String blank,String blank2,String blank3,String blank4, List<MeStoryDataModal.DataBean.StoriesBean.MediaProfileBean> dataList) {
        super(context);
        renderer = new CubeGLRenderer(context,"","","","", dataList);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer(renderer);
        // Request focus, otherwise key/button won't react
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }
    public CubeGLSurfaceView(Context context,String blank1,String blank2, ArrayList<CubeModal> dataList) {
        super(context);
        renderer = new CubeGLRenderer(context, dataList);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer(renderer);
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }



    public CubeGLSurfaceView(Context context, String blank, List<StoryDataModal.DataBean.UsersBean.StoriesBean.MediaProfileBean> dataList) {
        super(context);
        renderer = new CubeGLRenderer(context, blank, dataList);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer(renderer);
        // Request focus, otherwise key/button won't react
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }

    public CubeGLSurfaceView(Context context, AttributeSet attributes, ArrayList<PostDataModal.DataBean.PostsBean.MediaProfileBean> dataList) {
        super(context, attributes);
        renderer = new CubeGLRenderer(context, dataList);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer(renderer);
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }

    public CubeGLSurfaceView(Context context, String avatar, String avatar2,
                             String avatar3, String avatar4, String avatar5,
                             String avatar6) {
        super(context);

        renderer = new CubeGLRenderer(context, avatar, avatar2, avatar3, avatar4, avatar5, avatar6);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.setRenderer(renderer);
        this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        // Request focus, otherwise key/button won't react
        this.requestFocus();
        this.setFocusableInTouchMode(true);
    }


    /**
     * Sets the drag control with the given one.
     *
     * @param dragControl New drag control.
     */
    public void setDragControl(DragControl dragControl) {
        this.renderer.setDragControl(dragControl);
    }
}