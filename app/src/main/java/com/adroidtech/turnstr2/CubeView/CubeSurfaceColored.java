package com.adroidtech.turnstr2.CubeView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.DisplayMetrics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.adroidtech.turnstr2.Activity.CreateStoryActivity;
import com.adroidtech.turnstr2.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.adroidtech.turnstr2.Activity.CreateStoryActivity.createFileForCamera;
//import es2.learning.ViewPortRenderer;

public class CubeSurfaceColored extends GLSurfaceView {
    DisplayMetrics displaydata;
    boolean rendererHasBeenSet;
    ArrayList<Integer> allViews = new ArrayList<>();
    final Handler handler = new Handler();
    FrameLayout layoutFrame;
    OnClickListener onClickListener;
    Context context;
    float touchedX = 0;
    float touchedY = 0;
    Cuberenderer renderer;
    private boolean mRotateEnable = true;
    private boolean LastActionActionDown = false;
    public ArrayList<Bitmap> allBitmaps;
    private int isSet;
    private long endTime;
    private long startTime;

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (rendererHasBeenSet) {

        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.e("Scroll", "Checnges");
    }

    @Override
    public long getDrawingTime() {
        return getDrawingTime();
    }

    public CubeSurfaceColored(Context context, ArrayList<Bitmap> allBitmaps, boolean mRotateEnable, String colorCode) {
        super(context);
        try {
            this.context = context;
            this.mRotateEnable = mRotateEnable;
            setEGLContextClientVersion(2);
            if (allBitmaps == null || allBitmaps.size() == 0) {
                allBitmaps = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    allBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_image_cube));
                }
            }
            try {
                String[] colors = colorCode.split(":");
                red = Float.parseFloat(colors[0]);
                green = Float.parseFloat(colors[1]);
                blue = Float.parseFloat(colors[2]);
            } catch (Exception e) {
            }
            setRenderer(renderer = new Cuberenderer(this, allBitmaps));
            rendererHasBeenSet = true;
        } catch (Exception e) {

        }
    }

    public CubeSurfaceColored(Context context, ArrayList<Bitmap> allBitmaps, boolean mRotateEnable, FrameLayout layoutFrame, String colorCode) {
        this(context, allBitmaps, mRotateEnable, colorCode);
        this.layoutFrame = layoutFrame;
        try {
            String[] colors = colorCode.split(":");
            red = Float.parseFloat(colors[0]);
            green = Float.parseFloat(colors[1]);
            blue = Float.parseFloat(colors[2]);
        } catch (Exception e) {
        }
    }

    //    @Override
//    public void setZOrderOnTop(boolean onTop) {
//        super.setZOrderOnTop(true);
//    }
    private float red = 0, green = 0, blue = 0;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 280;
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
                try {
                    if ((mPreviousX - 1 >= x || mPreviousX + 1 <= x) && (mPreviousY - 1 >= y || mPreviousY + 1 <= y)) {
                        handler.removeCallbacks(mLongPressed);
                    }
                    float dx = x - mPreviousX;
                    float dy = y - mPreviousY;
                    // reverse direction of rotation above the mid-line
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
                    renderer.yAngle = renderer.yAngle + (-((dy) * TOUCH_SCALE_FACTOR / 5f)) % 360;
//                    requestRender();
                } catch (Exception e1) {
                }
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

//import com.example.my3dtrianleone.R;

    public class Cuberenderer implements GLSurfaceView.Renderer {

        /**
         * Store the model matrix. This matrix is used to move models from object space (where each model can be thought
         * of being located at the center of the universe) to world space.
         */
//	float touchedX = 0;
//	float touchedY = 0;
        public float xAngle = 135;
        public float yAngle = 150;
        //        public float xAngle = 135;
//        public float yAngle = 150;
        private float[] mModelMatrix = new float[16];

        short[] indeces = {
                0, 1, 2, 2, 3, 0,
                4, 5, 7, 5, 6, 7,
                8, 9, 11, 9, 10, 11,
                12, 13, 15, 13, 14, 15,
                16, 17, 19, 17, 18, 19,
                20, 21, 23, 21, 22, 23,

        };
        /**
         * Store the view matrix. This can be thought of as our camera. This matrix transforms world space to eye space;
         * it positions things relative to our eye.
         */
        private float[] mViewMatrix = new float[16];

        /**
         * Store the projection matrix. This is used to project the scene onto a 2D viewport.
         */
        private float[] mProjectionMatrix = new float[16];


        /**
         * Allocate storage for the final combined matrix. This will be passed into the shader program.
         */
        private float[] mMVPMatrix = new float[16];
        private final FloatBuffer mCubeColors;
        /**
         * Store our model data in a float buffer.
         */
        private final FloatBuffer mcubeVertices;

        ShortBuffer indexBuffer = null;
        /**
         * Store our model data in a float buffer.
         */
        private final FloatBuffer mCubeTextureCoordinates;

        /**
         * This will be used to pass in the texture.
         */
        private int mTextureUniformHandle;

        /**
         * This will be used to pass in model texture coordinate information.
         */
        private int mTextureCoordinateHandle;

        /**
         * Size of the texture coordinate data in elements.
         */
        private final int mTextureCoordinateDataSize = 2;

        /**
         * This is a handle to our texture data.
         */

        private int mTextureDataHandle0;
        private int mTextureDataHandle1;
        private int mTextureDataHandle2;
        private int mTextureDataHandle3;
        private int mTextureDataHandle4;
        private int mTextureDataHandle5;

        /**
         * This will be used to pass in the transformation matrix.
         */
        private int mMVPMatrixHandle;

        /**
         * This will be used to pass in model position information.
         */
        private int mPositionHandle;
        /**
         * This is a handle to our cube shading program.
         */
        private int mProgramHandle;
        /**
         * This will be used to pass in model color information.
         */
        private int mColorHandle;

        /**
         * How many bytes per float.
         */
        private final int mBytesPerFloat = 4;


        GLSurfaceView glSurfaceView;
        private long StartTime = 0;


        /**
         * Initialize the model data.
         */
        public Cuberenderer(CubeSurfaceColored cubesurfaceview, ArrayList<Bitmap> allBitmap) {
            glSurfaceView = cubesurfaceview;
            glSurfaceView.setZOrderOnTop(true);
            glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
//            glSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            allBitmaps = allBitmap;
            try {
                if (allBitmaps.size() < 6) {
                    while (allBitmaps.size() < 6) {
                        allBitmaps.add(allBitmaps.get(0));
                    }
                }
            } catch (Exception e) {

            }
            startTime = System.currentTimeMillis();
            displaydata = glSurfaceView.getResources().getDisplayMetrics();
            float ratio = (float) displaydata.widthPixels / (float) displaydata.heightPixels;
            // Define points for equilateral triangles.
            float height = 0.4f;
            float width = height / ratio;
            // This triangle is red, green, and blue.
            final float[] triangle1VerticesData = {// X, Y, Z,
                    //front
                    -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
                    // Right
                    0.5f, -0.5f, 0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f,
                    // Back
                    0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
                    // Left
                    -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, -0.5f,
                    // Top
                    -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
                    // Bottom
                    -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f,
            };
            // R, G, B, A
            final float[] cubeColorData = {       // Front face (red)
                    1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
                    // Right face (green)
                    1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
                    // Back face (blue)
                    1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
                    // Left face (yellow)
                    1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
                    // Top face (cyan)
                    1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
                    // Bottom face (magenta)
                    1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
            };
            // This triangle is yellow, cyan, and magenta.

            // S, T (or X, Y)
            // Texture coordinate data.
            // Because images have a Y axis pointing downward (values increase as you move down the image) while
            // OpenGL has a Y axis pointing upward, we adjust for that here by flipping the Y axis.
            // What's more is that the texture coordinates are the same for every face.
            final float[] cubeTextureCoordinateData = {
                    //front face
                    0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
                    // Right face
                    0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
                    // Back face
                    0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
                    // Left face
                    0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
                    // Top face
                    0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
                    // Bottom face
                    0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
            };

            // Initialize the buffers.
            mcubeVertices = ByteBuffer.allocateDirect(triangle1VerticesData.length * mBytesPerFloat)
                    .order(ByteOrder.nativeOrder()).asFloatBuffer();
            mcubeVertices.put(triangle1VerticesData).position(0);

            mCubeTextureCoordinates = ByteBuffer.allocateDirect(cubeTextureCoordinateData.length * mBytesPerFloat)
                    .order(ByteOrder.nativeOrder()).asFloatBuffer();
            mCubeTextureCoordinates.put(cubeTextureCoordinateData).position(0);

            mCubeColors = ByteBuffer.allocateDirect(cubeColorData.length * mBytesPerFloat
            ).order(ByteOrder.nativeOrder()).asFloatBuffer();
            mCubeColors.put(cubeColorData).position(0);

            indexBuffer = ByteBuffer.allocateDirect(indeces.length * 2)
                    .order(ByteOrder.nativeOrder()).asShortBuffer();
            indexBuffer.put(indeces).position(0);
        }

        @Override
        public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {

            glUnused.glDisable(GL10.GL_DITHER);
            glUnused.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
//        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glUnused.glClearColor(red, green, blue, 1);
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
//		// Position the eye behind the origin.
            final float eyeX = 0.0f;
            final float eyeY = 0.0f;
            final float eyeZ = 1.5f;
            // We are looking toward the distance
            final float lookX = 0.0f;
            final float lookY = 0.0f;
            final float lookZ = -5.0f;
            // Set our up vector. This is where our head would be pointing were we holding the camera.
            final float upX = 0.0f;
            final float upY = 1.0f;
            final float upZ = 0.0f;
            // Set the view matrix. This matrix can be said to represent the camera position.
            // NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination of a model and
            // view matrix. In OpenGL 2, we can keep track of these matrices separately if we choose.
            Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
            //please note i am making view matrix as identity matrix intentionally here to avoid the
            //effects of view matrix. if you want explore the effect of view matrix you can uncomment this line
            Matrix.setIdentityM(mViewMatrix, 0);
            final String vertexShader =
                    "uniform mat4 u_MVPMatrix;      \n"        // A constant representing the combined model/view/projection matrix.
                            + "attribute vec4 a_Position;     \n"        // Per-vertex position information we will pass in.
                            + "attribute vec4 a_Color;        \n"        // Per-vertex color information we will pass in.
                            + "attribute vec2 a_TexCoordinate;\n"
                            + "varying vec2 v_TexCoordinate;  \n"
                            + "varying vec4 v_Color;          \n"        // This will be passed into the fragment shader.

                            + "void main()                    \n"        // The entry point for our vertex shader.
                            + "{                              \n"
                            + "   v_Color = a_Color;          \n"        // Pass the color through to the fragment shader.
                            + "v_TexCoordinate = a_TexCoordinate;\n"    // It will be interpolated across the triangle.
                            + "   gl_Position = u_MVPMatrix   \n"    // gl_Position is a special variable used to store the final position.
                            + "               * a_Position;   \n"     // Multiply the vertex by the matrix to get the final point in
                            + "}                              \n";    // normalized screen coordinates.

            final String fragmentShader =
                    "precision mediump float;       \n"        // Set the default precision to medium. We don't need as high of a
                            // precision in the fragment shader.
                            + "varying vec4 v_Color;          \n"        // This is the color from the vertex shader interpolated across the
                            + "uniform sampler2D u_Texture;   \n"
                            + "varying vec2 v_TexCoordinate;   \n"// triangle per fragment.
                            + "void main()                    \n"        // The entry point for our fragment shader.
                            + "{                              \n"
                            + " gl_FragColor = (v_Color * texture2D(u_Texture, v_TexCoordinate));     \n"        // Pass the color directly through the pipeline.
                            + "}                              \n";

            // Load in the vertex shader.
            int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
            if (vertexShaderHandle != 0) {
                // Pass in the shader source.
                GLES20.glShaderSource(vertexShaderHandle, vertexShader);
                // Compile the shader.
                GLES20.glCompileShader(vertexShaderHandle);
                // Get the compilation status.
                final int[] compileStatus = new int[1];
                GLES20.glGetShaderiv(vertexShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
                // If the compilation failed, delete the shader.
                if (compileStatus[0] == 0) {
                    GLES20.glDeleteShader(vertexShaderHandle);
                    vertexShaderHandle = 0;
                }
            }

            if (vertexShaderHandle == 0) {
                throw new RuntimeException("Error creating vertex shader.");
            }

            // Load in the fragment shader shader.
            int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
            if (fragmentShaderHandle != 0) {
                // Pass in the shader source.
                GLES20.glShaderSource(fragmentShaderHandle, fragmentShader);
                // Compile the shader.
                GLES20.glCompileShader(fragmentShaderHandle);
                // Get the compilation status.
                final int[] compileStatus = new int[1];
                GLES20.glGetShaderiv(fragmentShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
                // If the compilation failed, delete the shader.
                if (compileStatus[0] == 0) {
                    GLES20.glDeleteShader(fragmentShaderHandle);
                    fragmentShaderHandle = 0;
                }
            }

            if (fragmentShaderHandle == 0) {
                throw new RuntimeException("Error creating fragment shader.");
            }

            // Create a program object and store the handle to it.
            mProgramHandle = GLES20.glCreateProgram();

            if (mProgramHandle != 0) {
                // Bind the vertex shader to the program.
                try {
                    GLES20.glAttachShader(mProgramHandle, vertexShaderHandle);
                    GLES20.glAttachShader(mProgramHandle, fragmentShaderHandle);
                    GLES20.glBindAttribLocation(mProgramHandle, 0, "a_Position");
                    GLES20.glBindAttribLocation(mProgramHandle, 1, "a_Color");
                    GLES20.glBindAttribLocation(mProgramHandle, 2, "a_TexCoordinate");
                    GLES20.glLinkProgram(mProgramHandle);
                    mTextureDataHandle0 = loadTexture(glSurfaceView, allBitmaps.get(0));
                    mTextureDataHandle1 = loadTexture(glSurfaceView, allBitmaps.get(1));
                    mTextureDataHandle2 = loadTexture(glSurfaceView, allBitmaps.get(2));
                    mTextureDataHandle3 = loadTexture(glSurfaceView, allBitmaps.get(3));
                    mTextureDataHandle4 = loadTexture(glSurfaceView, allBitmaps.get(4));
                    mTextureDataHandle5 = loadTexture(glSurfaceView, allBitmaps.get(5));
                    final int[] linkStatus = new int[1];
                    GLES20.glGetProgramiv(mProgramHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);
                    if (linkStatus[0] == 0) {
                        GLES20.glDeleteProgram(mProgramHandle);
                        mProgramHandle = 0;
                    }
                    allBitmaps.clear();
                } catch (Exception e) {
                }
            }
            StartTime = SystemClock.uptimeMillis() / 1000;
            if (mProgramHandle == 0) {
                throw new RuntimeException("Error creating program.");
            }


        }

        @Override
        public void onSurfaceChanged(GL10 glUnused, int width, int height) {
            // Set the OpenGL viewport to the same size as the surface.
            GLES20.glViewport(0, 0, width, height);
            // Create a new perspective projection matrix. The height will stay the same
            // while the width will vary as per aspect ratio.
            final float ratio = (float) width / height;
            final float left = -ratio;
            final float right = ratio;
            final float bottom = -1.0f;
            final float top = 1.0f;
            final float near = 1.0f;
            final float far = 10.0f;
            Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
//please note i am making projection matrix as identity matrix intentionally here to avoid the
//effects of projection matrix. if you want you can uncomment this line
            Matrix.setIdentityM(mProjectionMatrix, 0);
        }

        @Override
        public void onDrawFrame(GL10 glUnused) {
            endTime = System.currentTimeMillis();
            long dt = endTime - startTime;
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            GLES20.glUseProgram(mProgramHandle);
            try {
                // Set program handles for cube drawing.023
                mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVPMatrix");
                mTextureUniformHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_Texture");
                mPositionHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Position");
                mColorHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Color");
                mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_TexCoordinate");

                // Draw the triangle facing straight on.
                Matrix.setIdentityM(mModelMatrix, 0);
                Matrix.rotateM(mModelMatrix, 0, xAngle, 0.0f, 1.0f, 0.0f);
                Matrix.rotateM(mModelMatrix, 0, -yAngle, 1.0f, 0.0f, 0.0f);
//        Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 1.0f, 1.0f, 1.0f);
                // Set the active texture unit to texture unit 0.
                GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle0);
                GLES20.glUniform1i(mTextureUniformHandle, 0);
                draw(mcubeVertices, 0);

                // Set the active texture unit to texture unit 0.
                GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle1);
                GLES20.glUniform1i(mTextureUniformHandle, 1);
                draw(mcubeVertices, 1);

                // Set the active texture unit to texture unit 0.
                GLES20.glActiveTexture(GLES20.GL_TEXTURE2);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle2);
                GLES20.glUniform1i(mTextureUniformHandle, 2);
                draw(mcubeVertices, 2);

                // Set the active texture unit to texture unit 0.
                GLES20.glActiveTexture(GLES20.GL_TEXTURE3);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle3);
                GLES20.glUniform1i(mTextureUniformHandle, 3);
                draw(mcubeVertices, 3);

                //Set the active texture unit to texture unit 0.
                GLES20.glActiveTexture(GLES20.GL_TEXTURE4);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle4);
                GLES20.glUniform1i(mTextureUniformHandle, 4);
                draw(mcubeVertices, 4);

                //Set the active texture unit to texture unit 0.
                GLES20.glActiveTexture(GLES20.GL_TEXTURE5);
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle5);
                GLES20.glUniform1i(mTextureUniformHandle, 5);
                draw(mcubeVertices, 5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        private void draw(final FloatBuffer acubeBuffer, final int i) {
            // Pass in the position information. each vertex needs 3 values and each face of the
            //cube needs 4 vertices. so total 3*4 = 12
            try {
                acubeBuffer.position(12 * i);
                GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 0, acubeBuffer);
                GLES20.glEnableVertexAttribArray(mPositionHandle);
                // Pass in the color information. every vertex colr is defined by 4 values and each cube
                //face has 4 vertices so 4*4 = 16
                mCubeColors.position(16 * i);
                GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false, 0, mCubeColors);
                GLES20.glEnableVertexAttribArray(mColorHandle);
                // Pass in the texture coordinate information. every vertex needs 2 values to define texture
//        for each face of the cube we need 4 textures . so 4*2=8
                mCubeTextureCoordinates.position(8 * i);
                GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false, 0, mCubeTextureCoordinates);
                GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);
                // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
                // (which currently contains model * view).
                Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
                // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
                // (which now contains model * view * projection).
                Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);
                GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
//*each face of the cube is drawn using 2 triangles. so 2*3=6 lines
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, indexBuffer);
            } catch (Exception e) {

            }

        }


    }

    public static int loadTexture(GLSurfaceView mActivityContext2, final Bitmap bitmap) {
        final int[] textureHandle = new int[1];
        try {
            GLES20.glGenTextures(1, textureHandle, 0);
            if (textureHandle[0] != 0) {
                GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
                // Set filtering
                GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
                GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
                // Load the bitmap into the bound texture.
                GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
                // Recycle the bitmap, since its data has been loaded into OpenGL.
                bitmap.recycle();
            }
            if (textureHandle[0] == 0) {
                throw new RuntimeException("Error loading texture.");
            }
        } catch (RuntimeException e) {

        }
        return textureHandle[0];
    }

}