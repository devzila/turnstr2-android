package com.wdp.CubeScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.opengl.GLUtils;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wdp.Modal.CubeDataModal;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.turnstr.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class PhotoCube {
    // Vertex Buffer.
    private FloatBuffer vertexBuffer;

    private final int numFaces = 6;
    // Image file IDs.
    private final int[] imageFileIDs = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f
    };
    GL10 gl;
    int face;

    private int faceCount = 0;
    private int updatedFaceCount = 0;
    private FloatBuffer mTextureBuffer;

    private boolean mShouldLoadTexture = false;

    private int[] textureIDs = new int[numFaces];
    ;
    private Bitmap[] bitmap = new Bitmap[numFaces];
    ;
    private Context context;


    /*   private final String[] imageslist = {
               "https://cdn.filestackcontent.com/nPhl8ZEGS7aXMESnzbYu",
               "https://cdn.filestackcontent.com/Fcju2tCSQoGv495A03Ll",
               "https://cdn.filestackcontent.com/Ir0SCcqTRbieXjz2NLsn",
               "https://cdn.filestackcontent.com/W5xwzvEqRrWruGUqqriw",
               "https://cdn.filestackcontent.com/HbhpPXEgRPWM23MzkuJr",
               "https://cdn.filestackcontent.com/HbhpPXEgRPWM23MzkuJr"
       };*/
 /*   private final String[] imageslist = {
            "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
            "https://homepages.cae.wisc.edu/~ece533/images/baboon.png",
            "https://homepages.cae.wisc.edu/~ece533/images/barbara.png",
            "https://homepages.cae.wisc.edu/~ece533/images/boat.png",
            "https://homepages.cae.wisc.edu/~ece533/images/fruits.png",
            "https://homepages.cae.wisc.edu/~ece533/images/peppers.png"
    };
*/
    List<PostDataModal.DataBean.PostsBean.MediaProfileBean> imagesFileUrl = new ArrayList<>();
    List<MeStoryDataModal.DataBean.StoriesBean.MediaProfileBean> dataList = new ArrayList<>();
    List<StoryDataModal.DataBean.UsersBean.StoriesBean.MediaProfileBean> storyList = new ArrayList<>();
    List<MyPostDataModal.DataBean.PostsBean.MediaProfileBean> mypostlist = new ArrayList<>();
    List<CubeModal> temp = new ArrayList<>();
    List<CubeModal> mestorylist = new ArrayList<>();

    PhotoCube(Context context, List<PostDataModal.DataBean.PostsBean.MediaProfileBean> imagesFileUrl) {
        this.context = context;
        this.imagesFileUrl = imagesFileUrl;
        float[] vertices = {
                // Vertices of the 6 faces
                // FRONT
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                // BACK
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                // LEFT
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                // RIGHT
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // TOP
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // BOTTOM
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f   // 1. right-bottom-front
        };

        String tempurl = "";
        for (int i = 0; i < numFaces; i++) {
            if (imagesFileUrl != null) {
                if (i < imagesFileUrl.size()) {
                    if (imagesFileUrl.get(i) != null) {
                        if (!TextUtils.isEmpty(imagesFileUrl.get(i).getThumb_url())){
                            temp.add(new CubeModal(imagesFileUrl.get(i).getThumb_url(), R.drawable.thumnail));
                            tempurl = imagesFileUrl.get(i).getThumb_url();
                        }
                         else {
                            if (!TextUtils.isEmpty(tempurl)) {
                                temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                            } else {
                                temp.add(new CubeModal("", R.drawable.thumnail));

                            }
                        }
                    }
                } else {
                    if (!TextUtils.isEmpty(tempurl)) {
                        temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                    } else {
                        temp.add(new CubeModal("", R.drawable.thumnail));
                    }

                }

            } else {
                temp.add(new CubeModal("", R.drawable.thumnail));
            }


        }
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // Use native byte order.
        vbb.order(ByteOrder.nativeOrder());
        // Convert from byte to float.
        vertexBuffer = vbb.asFloatBuffer();
        // Copy data into buffer.
        vertexBuffer.put(vertices);
        // Rewind.
        vertexBuffer.position(0);

        float[] textureCoordinates = {0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f};
        setTextureCoordinates(textureCoordinates);
    }

    PhotoCube(Context context, String blank, String blank2, String blank3, String blank4, List<MeStoryDataModal.DataBean.StoriesBean.MediaProfileBean> dataList) {
        this.context = context;
        this.dataList = dataList;
        float[] vertices = {
                // Vertices of the 6 faces
                // FRONT
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                // BACK
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                // LEFT
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                // RIGHT
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // TOP
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // BOTTOM
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f   // 1. right-bottom-front
        };
        int pos = 0;
        String tempurl = "";
        for (int i = 0; i < numFaces; i++) {
            if (dataList != null) {
                if (i < dataList.size()) {
                    if (dataList.get(i) != null) {
                        if (!TextUtils.isEmpty(dataList.get(i).getThumb_url())) {
                            temp.add(new CubeModal(dataList.get(i).getThumb_url(), R.drawable.thumnail));
                            tempurl = dataList.get(i).getThumb_url();
                        } else {
                            if (!TextUtils.isEmpty(tempurl)) {
                                temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                            } else {
                                temp.add(new CubeModal("", R.drawable.thumnail));

                            }
                        }
                    }
                } else {
                    if (!TextUtils.isEmpty(tempurl)) {
                        temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                    } else {
                        temp.add(new CubeModal("", R.drawable.thumnail));
                    }

                }
            } else {
                temp.add(new CubeModal("", R.drawable.thumnail));
            }


        }




        Log.d("temp", "----->" + temp.size());
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // Use native byte order.
        vbb.order(ByteOrder.nativeOrder());
        // Convert from byte to float.
        vertexBuffer = vbb.asFloatBuffer();
        // Copy data into buffer.
        vertexBuffer.put(vertices);
        // Rewind.
        vertexBuffer.position(0);

        float[] textureCoordinates = {0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f};
        setTextureCoordinates(textureCoordinates);
    }

    PhotoCube(Context context, String blank, String blank1, List<CubeModal> storylist) {
        this.context = context;
        this.mestorylist = storylist;
        float[] vertices = {  // Vertices of the 6 faces
                // FRONT
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                // BACK
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                // LEFT
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                // RIGHT
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // TOP
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // BOTTOM
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f   // 1. right-bottom-front
        };
        int pos = 0;
        String tempurl = "";
        for (int i = 0; i < numFaces; i++) {
            if (storyList != null) {
                if (i < storyList.size()) {
                    if (storyList.get(i) != null) {
                        if (!TextUtils.isEmpty(storyList.get(i).getThumb_url())) {
                            temp.add(new CubeModal(storyList.get(i).getThumb_url(), R.drawable.thumnail));
                            tempurl = storyList.get(i).getThumb_url();
                        } else {
                            if (!TextUtils.isEmpty(tempurl)) {
                                temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                            } else {
                                temp.add(new CubeModal("", R.drawable.thumnail));

                            }
                        }
                    }
                } else {
                    if (!TextUtils.isEmpty(tempurl)) {
                        temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                    } else {
                        temp.add(new CubeModal("", R.drawable.thumnail));
                    }

                }
            } else {
                temp.add(new CubeModal("", R.drawable.thumnail));
            }


        }

        Log.d("temp.", "" + temp.size());
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // Use native byte order.
        vbb.order(ByteOrder.nativeOrder());
        // Convert from byte to float.
        vertexBuffer = vbb.asFloatBuffer();
        // Copy data into buffer.
        vertexBuffer.put(vertices);
        // Rewind.
        vertexBuffer.position(0);

        float[] textureCoordinates = {0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f};
        setTextureCoordinates(textureCoordinates);
    }

    PhotoCube(Context context, String blank, List<StoryDataModal.DataBean.UsersBean.StoriesBean.MediaProfileBean> storylist) {
        this.context = context;
        this.storyList = storylist;
        float[] vertices = {  // Vertices of the 6 faces
                // FRONT
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                // BACK
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                // LEFT
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                // RIGHT
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // TOP
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // BOTTOM
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f   // 1. right-bottom-front
        };
        String tempurl = "";
        for (int i = 0; i < numFaces; i++) {
            if (storyList != null) {
                if (i < storyList.size()) {
                    if (storyList.get(i) != null) {
                        if (!TextUtils.isEmpty(storyList.get(i).getThumb_url())) {
                            temp.add(new CubeModal(storyList.get(i).getThumb_url(), R.drawable.thumnail));
                            tempurl = storyList.get(i).getThumb_url();
                        } else {
                            if (!TextUtils.isEmpty(tempurl)) {
                                temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                            } else {
                                temp.add(new CubeModal("", R.drawable.thumnail));

                            }
                        }
                    }
                } else {
                    if (!TextUtils.isEmpty(tempurl)) {
                        temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                    } else {
                        temp.add(new CubeModal("", R.drawable.thumnail));
                    }

                }
            } else {
                temp.add(new CubeModal("", R.drawable.thumnail));
            }
        }
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // Use native byte order.
        vbb.order(ByteOrder.nativeOrder());
        // Convert from byte to float.
        vertexBuffer = vbb.asFloatBuffer();
        // Copy data into buffer.
        vertexBuffer.put(vertices);
        // Rewind.
        vertexBuffer.position(0);

        float[] textureCoordinates = {0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f};
        setTextureCoordinates(textureCoordinates);
    }

    PhotoCube(Context context, Context mcontext, List<MyPostDataModal.DataBean.PostsBean.MediaProfileBean> dataList) {
        this.context = context;
        this.mypostlist = dataList;
        float[] vertices = {
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                // BACK
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                // LEFT
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                // RIGHT
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // TOP
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // BOTTOM
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f   // 1. right-bottom-front
        };
        int pos = 0;
        String tempurl = "";
        for (int i = 0; i < numFaces; i++) {
            if (mypostlist != null) {
                if (i < mypostlist.size()) {
                    if (mypostlist.get(i) != null) {
                        if (!TextUtils.isEmpty(mypostlist.get(i).getThumb_url())) {
                            temp.add(new CubeModal(mypostlist.get(i).getThumb_url(), R.drawable.thumnail));
                            tempurl = mypostlist.get(i).getThumb_url();
                        } else {
                            if (!TextUtils.isEmpty(tempurl)) {
                                temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                            } else {
                                temp.add(new CubeModal("", R.drawable.thumnail));

                            }

                        }
                    }
                } else {
                    if (!TextUtils.isEmpty(tempurl)) {
                        temp.add(new CubeModal(tempurl, R.drawable.thumnail));
                    } else {
                        temp.add(new CubeModal("", R.drawable.thumnail));
                    }

                }
            } else {
                temp.add(new CubeModal("", R.drawable.thumnail));
            }


        }
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // Use native byte order.
        vbb.order(ByteOrder.nativeOrder());
        // Convert from byte to float.
        vertexBuffer = vbb.asFloatBuffer();
        // Copy data into buffer.
        vertexBuffer.put(vertices);
        // Rewind.
        vertexBuffer.position(0);

        float[] textureCoordinates = {0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f};
        setTextureCoordinates(textureCoordinates);
    }

    public PhotoCube(Context context, String avatar, String avatar2, String avatar3, String avatar4, String avatar5, String avatar6) {
        this.context = context;
        float[] vertices = {
                // Vertices of the 6 faces
                // FRONT
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                // BACK
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                // LEFT
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                // RIGHT
                1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // TOP
                -1.0f, 1.0f, 1.0f,  // 2. left-top-front
                1.0f, 1.0f, 1.0f,  // 3. right-top-front
                -1.0f, 1.0f, -1.0f,  // 5. left-top-back
                1.0f, 1.0f, -1.0f,  // 7. right-top-back
                // BOTTOM
                -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
                1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
                -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
                1.0f, -1.0f, 1.0f   // 1. right-bottom-front
        };
        if (!TextUtils.isEmpty(avatar) && !avatar.equalsIgnoreCase("null")) {
            Log.d("avatar","----->" + avatar);
            temp.add(new CubeModal(avatar, R.drawable.thumnail));
        } else {
            temp.add(new CubeModal("", R.drawable.thumnail));
        }
        if (!TextUtils.isEmpty(avatar2) && !avatar2.equalsIgnoreCase("null")) {
            temp.add(new CubeModal(avatar2, R.drawable.thumnail));
        } else {
            temp.add(new CubeModal("", R.drawable.thumnail));
        }
        if (!TextUtils.isEmpty(avatar3) && !avatar3.equalsIgnoreCase("null")) {
            temp.add(new CubeModal(avatar3, R.drawable.thumnail));
        } else {
            temp.add(new CubeModal("", R.drawable.thumnail));
        }
        if (!TextUtils.isEmpty(avatar4) && !avatar4.equalsIgnoreCase("null")) {
            temp.add(new CubeModal(avatar4, R.drawable.thumnail));
        } else {
            temp.add(new CubeModal("", R.drawable.thumnail));
        }
        if (!TextUtils.isEmpty(avatar5) && !avatar5.equalsIgnoreCase("null")) {
            temp.add(new CubeModal(avatar5, R.drawable.thumnail));
        } else {
            temp.add(new CubeModal("", R.drawable.thumnail));
        }
        if (!TextUtils.isEmpty(avatar6) && !avatar6.equalsIgnoreCase("null")) {
            temp.add(new CubeModal(avatar6, R.drawable.thumnail));
        } else {
            temp.add(new CubeModal("", R.drawable.thumnail));
        }


        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        // Use native byte order.
        vbb.order(ByteOrder.nativeOrder());
        // Convert from byte to float.
        vertexBuffer = vbb.asFloatBuffer();
        // Copy data into buffer.
        vertexBuffer.put(vertices);
        // Rewind.
        vertexBuffer.position(0);

        float[] textureCoordinates = {0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f};
        setTextureCoordinates(textureCoordinates);
    }

    // Render the shape.
    void draw(GL10 gl) {
        if (mShouldLoadTexture) {
            loadTexture(gl);
            mShouldLoadTexture = false;
        }

        gl.glFrontFace(GL10.GL_CCW);    // Front face in counter-clockwise orientation
        gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face
        gl.glCullFace(GL10.GL_BACK);    // Cull the back face (don't display)


        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);

        // Front.
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[0]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


        // Left.

        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[1]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        // Back.
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[2]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);

        // Right.
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[3]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        // Top.
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[4]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);

        // Bottom.
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[5]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NICEST);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    /**
     * Set the texture coordinates.
     */
    private void setTextureCoordinates(float[] textureCoords) {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoords.length * 4 * 6);
        byteBuf.order(ByteOrder.nativeOrder());
        mTextureBuffer = byteBuf.asFloatBuffer();
        for (int face = 0; face < numFaces; face++)
            mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);
    }

    // Load images into 6 GL textures.

    void loadTexture(GL10 gl) {
        // Generate texture-ID array for 6 IDs.
        gl.glGenTextures(6, textureIDs, 0);

        for (int face = 0; face < temp.size(); face++) {
            try {
                if (!TextUtils.isEmpty(temp.get(face).getUrl())) {
                    URL url = new URL(temp.get(face).getUrl());
                    bitmap[face] = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } else {
                    bitmap[face] = BitmapFactory.decodeStream(context.getResources().openRawResource(temp.get(face).getIcon()));
                }

            } catch (IOException e) {
                System.out.println(e);
            }


            gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs[face]);

            // Create Nearest Filtered Texture.
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                    GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                    GL10.GL_NEAREST);

            // Build Texture from loaded bitmap for the currently-bind texture ID.

            if (bitmap[face] != null){
                int TILE_WIDTH = bitmap[face].getWidth();
                int TILE_HEIGHT = bitmap[face].getHeight();
                int TILE_SIZE = TILE_WIDTH * TILE_HEIGHT;
                int[] pixels = new int[TILE_WIDTH];
                short[] rgb_565 = new short[TILE_SIZE];
                int i = 0;
                for (int y = 0; y < TILE_HEIGHT; y++) {
                    bitmap[face].getPixels(pixels, 0, TILE_WIDTH, 0, y, TILE_WIDTH, 1);
                    for (int x = 0; x < TILE_WIDTH; x++) {
                        int argb = pixels[x];
                        // Take 5 bits from 23..19.
                        int r = 0x1f & (argb >> 19);
                        // Take 6 bits from 15..10.
                        int g = 0x3f & (argb >> 10);
                        // Take 5 bits from 7.. 3.
                        int b = 0x1f & (argb >> 3);
                        int rgb = (r << 11) | (g << 5) | b;
                        rgb_565[i] = (short) rgb;
                        ++i;
                    }
                }
                ShortBuffer textureBuffer = ShortBuffer.wrap(rgb_565, 0, TILE_SIZE);
                gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGB, bitmap[face].getWidth(),
                        bitmap[face].getHeight(), 0, GL10.GL_RGB, GL10.GL_UNSIGNED_SHORT_5_6_5, textureBuffer);
                gl.glFlush();
                gl.glFinish();
                bitmap[face].recycle();
            }

        }
    }

    public Bitmap mergeBitmaps(Bitmap overlay, Bitmap bitmap) {

        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        Bitmap combined = Bitmap.createBitmap(width, height, bitmap.getConfig());
        Canvas canvas = new Canvas(combined);
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        canvas.drawBitmap(bitmap, new Matrix(), null);
        int centreX = (canvasWidth - overlay.getWidth()) / 2;
        int centreY = (canvasHeight - overlay.getHeight()) / 2;
        canvas.drawBitmap(overlay, centreX, centreY, null);

        return combined;
    }
}
