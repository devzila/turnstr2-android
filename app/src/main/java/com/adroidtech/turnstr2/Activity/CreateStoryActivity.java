package com.adroidtech.turnstr2.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.OkHttpRequestSender;
import com.adroidtech.turnstr2.imageFillters.ThumbnailCallback;
import com.adroidtech.turnstr2.imageFillters.ThumbnailItem;
import com.adroidtech.turnstr2.imageFillters.ThumbnailsAdapter;
import com.adroidtech.turnstr2.imageFillters.ThumbnailsManager;
import com.google.gson.Gson;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraLogger;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.SessionType;
import com.zomato.photofilters.imageprocessors.Filter;

import net.alhazmy13.imagefilter.ImageFilter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static com.zomato.photofilters.SampleFilters.getAweStruckVibeFilter;
import static com.zomato.photofilters.SampleFilters.getBlueMessFilter;
import static com.zomato.photofilters.SampleFilters.getLimeStutterFilter;
import static com.zomato.photofilters.SampleFilters.getNightWhisperFilter;
import static com.zomato.photofilters.SampleFilters.getStarLitFilter;

public class CreateStoryActivity extends AppCompatActivity implements View.OnClickListener, AsyncCallback, ThumbnailCallback {
    static {
        System.loadLibrary("NativeImageProcessor");
    }

    private boolean mCapturingPicture;
    private boolean mCapturingVideo;
    private LinearLayout allImages;
    private ImageView avatarFace1;
    private ImageView avatarFace2;
    private ImageView avatarFace3;
    private ImageView avatarFace4;
    private ImageView avatarFace5;
    private ImageView avatarFace6;
    private ImageView icDelete1;
    private ImageView icDelete2;
    private ImageView icDelete3;
    private ImageView icDelete4;
    private ImageView icDelete5;
    private ImageView icDelete6;
    private Button bnt_library;
    private Button bnt_photos;
    private Button bnt_video;
    private CameraView camera;
    private ImageButton captureVideo;
    private ImageButton capturePhoto;
    private ImageButton toggleCamera;
    private ImageView selectedView, mNextView;
    HashMap<Integer, Uri> uriHashMap = new HashMap<>();
    HashMap<Integer, Uri> uriHashMapThumb = new HashMap<>();
    private SharedPreference sharedPreference;
    private TextView next;
    private RecyclerView thumbListView;
    private ImageView placeHolderImageView;
    //Image gallery
    //define source of MediaStore.Images.Media, internal or external storage
    final Uri sourceUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    final Uri thumbUri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
    final String thumb_DATA = MediaStore.Images.Thumbnails.DATA;
    final String thumb_IMAGE_ID = MediaStore.Images.Thumbnails.IMAGE_ID;
    MyAdapter mySimpleCursorAdapter;
    private GridView myGridView;
    private FrameLayout camera_views;
    private Bitmap selectedBitmap;
    private Uri selectedFileUri;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private boolean isRecording;
    private int lastPerview = 0;
    private String StoryCaption = "";
    private ImageView icDeleteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(R.layout.activity_create_story);
        CameraLogger.setLogLevel(CameraLogger.LEVEL_VERBOSE);
        uiInatial();
        camera.addCameraListener(new CameraListener() {

            @Override
            public void onPictureTaken(byte[] picture) {
                // CameraUtils will read EXIF orientation for you, in a worker thread.
                CameraUtils.decodeBitmap(picture, new CameraUtils.BitmapCallback() {
                    @Override
                    public void onBitmapReady(Bitmap bitmap) {
                        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);
                        selectedBitmap = bitmap;
                        selectedFileUri = RewriteBitmapToFile(bitmap, createFileForCamera());
                        uriHashMap.put(selectedView.getId(), selectedFileUri);
                        uriHashMapThumb.put(selectedView.getId(), selectedFileUri);
                        createFiltersDialog();
                    }
                });
            }

            @Override
            public void onVideoTaken(File video) {
                selectedFileUri = Uri.fromFile(video);
                selectedBitmap = ThumbnailUtils.createVideoThumbnail(video.getPath(), MediaStore.Video.Thumbnails.MINI_KIND);
                selectedView.setImageBitmap(selectedBitmap);
                uriHashMap.put(selectedView.getId(), selectedFileUri);
                uriHashMapThumb.put(selectedView.getId(), saveBitmapTOFile(selectedBitmap));
                progressBar.setVisibility(View.GONE);
                progressBar.setProgress(0);
                icDeleteSelected.setVisibility(View.VISIBLE);
                isRecording = false;
                captureVideo.setImageResource(R.mipmap.nav_video);
                mNextView.performClick();
            }
        });
    }

    private Uri saveBitmapTOFile(Bitmap selectedBitmap) {
        FileOutputStream out = null;
        File filename = null;
        try {
            filename = new File(getFilesDir(), "Image_" + Calendar.getInstance().getTimeInMillis() + ".png");
            out = new FileOutputStream(filename);
            selectedBitmap.compress(Bitmap.CompressFormat.PNG, 50, out); // bmp is your Bitmap instance
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Uri.fromFile(filename);
    }


    private void uiInatial() {
        camera = (CameraView) findViewById(R.id.camera);
//        camera.setSessionType(SessionType.VIDEO);
        myGridView = (GridView) findViewById(R.id.gridview);
        sharedPreference = new SharedPreference(this);
        camera_views = (FrameLayout) findViewById(R.id.camera_views);
        captureVideo = (ImageButton) findViewById(R.id.captureVideo);
        capturePhoto = (ImageButton) findViewById(R.id.capturePhoto);
        toggleCamera = (ImageButton) findViewById(R.id.toggleCamera);
        allImages = (LinearLayout) findViewById(R.id.all_images);
        avatarFace1 = (ImageView) findViewById(R.id.avatar_face1);
        avatarFace2 = (ImageView) findViewById(R.id.avatar_face2);
        avatarFace3 = (ImageView) findViewById(R.id.avatar_face3);
        avatarFace4 = (ImageView) findViewById(R.id.avatar_face4);
        avatarFace5 = (ImageView) findViewById(R.id.avatar_face5);
        avatarFace6 = (ImageView) findViewById(R.id.avatar_face6);
        icDelete1 = (ImageView) findViewById(R.id.ic_delete1);
        icDelete2 = (ImageView) findViewById(R.id.ic_delete2);
        icDelete3 = (ImageView) findViewById(R.id.ic_delete3);
        icDelete4 = (ImageView) findViewById(R.id.ic_delete4);
        icDelete5 = (ImageView) findViewById(R.id.ic_delete5);
        icDelete6 = (ImageView) findViewById(R.id.ic_delete6);
        icDelete1.setOnClickListener(this);
        icDelete2.setOnClickListener(this);
        icDelete3.setOnClickListener(this);
        icDelete4.setOnClickListener(this);
        icDelete5.setOnClickListener(this);
        icDelete6.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next = (TextView) findViewById(R.id.next);
        selectedView = avatarFace1;
        mNextView = avatarFace2;
        icDeleteSelected = icDelete1;
        next.setOnClickListener(this);
        avatarFace1.setOnClickListener(this);
        avatarFace2.setOnClickListener(this);
        avatarFace3.setOnClickListener(this);
        avatarFace4.setOnClickListener(this);
        avatarFace5.setOnClickListener(this);
        avatarFace6.setOnClickListener(this);
        bnt_library = (Button) findViewById(R.id.library);
        bnt_photos = (Button) findViewById(R.id.photos);
        bnt_video = (Button) findViewById(R.id.video);
        bnt_video.setOnClickListener(this);
        bnt_photos.setOnClickListener(this);
        bnt_library.setOnClickListener(this);
        captureVideo.setOnClickListener(this);
        capturePhoto.setOnClickListener(this);
        toggleCamera.setOnClickListener(this);
    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                createPerviewDialog();
//                uploadImageToServer();
                break;
            case R.id.avatar_face1:
                selectedView = (ImageView) view;
                mNextView = avatarFace2;
                icDeleteSelected = icDelete1;
                break;
            case R.id.avatar_face2:
                selectedView = (ImageView) view;
                mNextView = avatarFace3;
                icDeleteSelected = icDelete2;
                break;
            case R.id.avatar_face3:
                icDeleteSelected = icDelete3;
                mNextView = avatarFace4;
                selectedView = (ImageView) view;
                break;
            case R.id.avatar_face4:
                icDeleteSelected = icDelete4;
                mNextView = avatarFace5;
                selectedView = (ImageView) view;
                break;
            case R.id.avatar_face5:
                icDeleteSelected = icDelete5;
                mNextView = avatarFace6;
                selectedView = (ImageView) view;
                break;
            case R.id.avatar_face6:
                icDeleteSelected = icDelete6;
                selectedView = (ImageView) view;

                break;
            case R.id.ic_delete1:
                try {
                    avatarFace1.setImageResource(R.color.gray_light);
                    uriHashMap.remove(R.id.avatar_face1);
                    uriHashMapThumb.remove(R.id.avatar_face1);
                    icDelete1.setVisibility(View.GONE);
                } catch (Exception e) {
                }
                break;
            case R.id.ic_delete2:
                try {
                    avatarFace2.setImageResource(R.color.gray_light);
                    uriHashMap.remove(R.id.avatar_face2);
                    uriHashMapThumb.remove(R.id.avatar_face2);
                    icDelete2.setVisibility(View.GONE);
                } catch (Exception e) {
                }
                break;
            case R.id.ic_delete3:
                try {
                    avatarFace3.setImageResource(R.color.gray_light);
                    uriHashMap.remove(R.id.avatar_face3);
                    uriHashMapThumb.remove(R.id.avatar_face3);
                    icDelete3.setVisibility(View.GONE);
                } catch (Exception e) {
                }
                break;
            case R.id.ic_delete4:
                try {
                    avatarFace4.setImageResource(R.color.gray_light);
                    uriHashMap.remove(R.id.avatar_face4);
                    uriHashMapThumb.remove(R.id.avatar_face4);
                    icDelete4.setVisibility(View.GONE);
                } catch (Exception e) {
                }
                break;
            case R.id.ic_delete5:
                try {
                    avatarFace5.setImageResource(R.color.gray_light);
                    uriHashMap.remove(R.id.avatar_face5);
                    uriHashMapThumb.remove(R.id.avatar_face5);
                    icDelete5.setVisibility(View.GONE);
                } catch (Exception e) {
                }
                break;
            case R.id.ic_delete6:
                try {
                    avatarFace6.setImageResource(R.color.gray_light);
                    uriHashMap.remove(R.id.avatar_face6);
                    uriHashMapThumb.remove(R.id.avatar_face6);
                    icDelete6.setVisibility(View.GONE);
                } catch (Exception e) {
                }
                break;

            case R.id.library:
                bnt_library.setBackgroundResource(R.color.background_gradient);
                bnt_photos.setBackgroundResource(R.color.black);
                bnt_video.setBackgroundResource(R.color.black);
                myGridView.setVisibility(View.VISIBLE);
                camera_views.setVisibility(View.GONE);
                galleryViewImages();
                //TODO implement
                break;
            case R.id.photos:
                camera.setSessionType(SessionType.PICTURE);
                myGridView.setVisibility(View.GONE);
                camera_views.setVisibility(View.VISIBLE);
                bnt_photos.setBackgroundResource(R.color.background_gradient);
                bnt_library.setBackgroundResource(R.color.black);
                bnt_video.setBackgroundResource(R.color.black);
                capturePhoto.setVisibility(View.VISIBLE);
                captureVideo.setVisibility(View.GONE);
                //TODO implement
                break;
            case R.id.video:
                try {
                    camera.setSessionType(SessionType.VIDEO);
                    myGridView.setVisibility(View.GONE);
                    camera_views.setVisibility(View.VISIBLE);
                    bnt_video.setBackgroundResource(R.color.background_gradient);
                    bnt_library.setBackgroundResource(R.color.black);
                    bnt_photos.setBackgroundResource(R.color.black);
                    capturePhoto.setVisibility(View.GONE);
                    captureVideo.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.captureVideo:
                // Full version
                try {
                    if (!isRecording) {
                        isRecording = true;
                        captureVideo.setImageResource(R.drawable.ic_stop_video);
                        captureVideo();
                    }
//                    else {
//                        isRecording = false;
//                        camera.stopCapturingVideo();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.capturePhoto:
//                app:cameraPictureSizeAspectRatio="1:1"
//                app:cameraPictureSizeMaxWidth="100"
                camera.capturePicture();
                break;
            case R.id.toggleCamera:
                camera.toggleFacing();
                break;
        }
    }

    private void captureVideo() {
        if (camera.getSessionType() != SessionType.VIDEO) {
            Toast.makeText(this, "Sorry, Can't record video.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(progressStatus);
        progressStatus = 0;
        Toast.makeText(this, "Recording for 10 seconds...", Toast.LENGTH_SHORT).show();
        try {
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus <= 100) {
                        try {
                            progressStatus += 5;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressStatus);
                                }
                            });
                            Thread.sleep(500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = null;
        try {
            file = new File(getFilesDir(), "video_" + Calendar.getInstance().getTimeInMillis() + ".mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        camera.startCapturingVideo(file, 10000);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void uploadImageToServer() {
        try {
            ArrayMap<String, String> formField = new ArrayMap<>();
            ArrayMap<String, File> filePart = new ArrayMap<>();
            formField.put("story[caption]", StoryCaption);
            formField.put("story[likes_count]", "0");
            Integer[] allImagesName = uriHashMap.keySet().toArray(new Integer[uriHashMap.size()]);

            for (int i = 0; i < allImagesName.length; i++) {
                File selectedView = new File(uriHashMap.get(allImagesName[i]).getPath());
                File thumbFile = new File(uriHashMapThumb.get(allImagesName[i]).getPath());
                switch (allImagesName[i]) {
                    case R.id.avatar_face1:
                        filePart.put("story[face1_thumb]", thumbFile);
                        filePart.put("story[story_face1]", selectedView);
                        filePart.put("story[face1_media]", selectedView);
                        break;
                    case R.id.avatar_face2:
                        filePart.put("story[face2_thumb]", thumbFile);
                        filePart.put("story[story_face2]", selectedView);
                        filePart.put("story[face2_media]", selectedView);
                        break;
                    case R.id.avatar_face3:
                        filePart.put("story[face3_thumb]", thumbFile);
                        filePart.put("story[story_face3]", selectedView);
                        filePart.put("story[face3_media]", selectedView);
                        break;
                    case R.id.avatar_face4:
                        filePart.put("story[face4_thumb]", thumbFile);
                        filePart.put("story[story_face4]", selectedView);
                        filePart.put("story[face4_media]", selectedView);
                        break;
                    case R.id.avatar_face5:
                        filePart.put("story[face5_thumb]", thumbFile);
                        filePart.put("story[story_face5]", selectedView);
                        filePart.put("story[face5_media]", selectedView);
                        break;
                    case R.id.avatar_face6:
                        filePart.put("story[face6_thumb]", thumbFile);
                        filePart.put("story[story_face6]", selectedView);
                        filePart.put("story[face6_media]", selectedView);
                        break;
                }
            }
            new OkHttpRequestSender(this, this, GeneralValues.BASE_URL + GeneralValues.CREATE_STORIES, formField, filePart,
                    sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN), "POST", true).execute();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.destroy();
    }

    public static Uri createFileForCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), (Calendar.getInstance().getTimeInMillis() + ".png"));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return Uri.fromFile(file);
    }

    public static Uri RewriteBitmapToFile(Bitmap bitmap, Uri uri) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(uri.getPath());
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uri;
    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {
        Log.e("Data", jsonObject.toString());
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (jsonObject1.has("success") && jsonObject1.getBoolean("success")) {
                Toast.makeText(CreateStoryActivity.this, jsonObject1.getString("message"), Toast.LENGTH_LONG).show();
                MyStoryActivity.isStoryCreated = true;
                finish();
            } else {
                Toast.makeText(CreateStoryActivity.this, jsonObject1.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class MyAdapter extends SimpleCursorAdapter {
        Cursor myCursor;
        Context myContext;

        public MyAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            myCursor = c;
            myContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
            }
            ImageView thumbV = (ImageView) row.findViewById(R.id.thumb);
            myCursor.moveToPosition(position);
            int myID = myCursor.getInt(myCursor.getColumnIndex(MediaStore.Images.Media._ID));
            String[] thumbColumns = {thumb_DATA, thumb_IMAGE_ID};
            CursorLoader thumbCursorLoader = new CursorLoader(myContext, thumbUri, thumbColumns, thumb_IMAGE_ID + "=" + myID, null, null);
            Cursor thumbCursor = thumbCursorLoader.loadInBackground();
            Bitmap myBitmap = null;
            if (thumbCursor.moveToFirst()) {
                try {
                    int dataIndex = thumbCursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//                    for (int i = 0; i < thumbCursor.getCount(); i++) {
//                        thumbCursor.moveToPosition(i);
//                        Log.d("MyTag", "Location : " + i + " : " + thumbCursor.getString(dataIndex));
//                    }
                    String thumbPath = thumbCursor.getString(dataIndex);
//                    int thCulumnIndex = thumbCursor.getColumnIndex(thumb_DATA);
//                    String thumbPath = thumbCursor.getString(thCulumnIndex);
//                    myBitmap=uriToBitmap(Uri.parse(thumbPath));
                    myBitmap = BitmapFactory.decodeFile(thumbPath);
                    thumbV.setImageBitmap(myBitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return row;
        }
    }

    private void galleryViewImages() {
        try {
            String[] from = {MediaStore.MediaColumns.TITLE};
            int[] to = {android.R.id.text1};
            CursorLoader cursorLoader = new CursorLoader(this, sourceUri,
                    null, null, null, MediaStore.Audio.Media.TITLE);
            Cursor cursor = cursorLoader.loadInBackground();
            mySimpleCursorAdapter = new MyAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            myGridView.setAdapter(mySimpleCursorAdapter);
            myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cursor = mySimpleCursorAdapter.getCursor();
                    cursor.moveToPosition(position);
                    int int_ID = 0;
                    try {
                        int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                        String path = cursor.getString(dataIndex);
                        selectedFileUri = Uri.fromFile(new File(path));
                        selectedBitmap = uriToBitmap(selectedFileUri);
                        uriHashMap.put(selectedView.getId(), selectedFileUri);
                        uriHashMapThumb.put(selectedView.getId(), selectedFileUri);
                        createFiltersDialog();
                        Uri urpath = ContentUris.withAppendedId(sourceUri, cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //                getThumbnail(int_ID);
                }
            });
        } catch (Exception e) {

        }
    }

    private Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap getThumbnail(int id) {
        String[] thumbColumns = {thumb_DATA, thumb_IMAGE_ID};
        CursorLoader thumbCursorLoader = new CursorLoader(this, thumbUri, thumbColumns, thumb_IMAGE_ID + "=" + id, null, null);
        Cursor thumbCursor = thumbCursorLoader.loadInBackground();
        Bitmap thumbBitmap = null;
        if (thumbCursor.moveToFirst()) {
            int thCulumnIndex = thumbCursor.getColumnIndex(thumb_DATA);
            String thumbPath = thumbCursor.getString(thCulumnIndex);
            Toast.makeText(getApplicationContext(), thumbPath, Toast.LENGTH_LONG).show();
            thumbBitmap = BitmapFactory.decodeFile(thumbPath);
            //Create a Dialog to display the thumbnail
            AlertDialog.Builder thumbDialog = new AlertDialog.Builder(CreateStoryActivity.this);
            ImageView thumbView = new ImageView(CreateStoryActivity.this);
            thumbView.setImageBitmap(thumbBitmap);
            LinearLayout layout = new LinearLayout(CreateStoryActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(thumbView);
            thumbDialog.setView(layout);
            thumbDialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "NO Thumbnail!", Toast.LENGTH_LONG).show();
        }
        return thumbBitmap;
    }

    private void createFiltersDialog() {
        final Dialog dialog = new Dialog(this, R.style.MaterialDialogSheet);
        dialog.setContentView(R.layout.image_fillters_activity);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View root_view = (View) dialog.findViewById(R.id.root_view);
        thumbListView = (RecyclerView) dialog.findViewById(R.id.thumbnails);
        placeHolderImageView = (ImageView) dialog.findViewById(R.id.place_holder_imageview);
        ImageView cancle = (ImageView) dialog.findViewById(R.id.cancle);
        ImageView okBtn = (ImageView) dialog.findViewById(R.id.ok);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectedFileUri = RewriteBitmapToFile(selectedBitmap, selectedFileUri);
                    selectedView.setImageBitmap(selectedBitmap);
                    icDeleteSelected.setVisibility(View.VISIBLE);
                    mNextView.performClick();
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        placeHolderImageView.setImageBitmap(selectedBitmap);
        initHorizontalList();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        params.gravity = Gravity.TOP;
        dialog.getWindow().setAttributes(params);
        dialog.show();
    }

    private void initHorizontalList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        thumbListView.setLayoutManager(layoutManager);
        thumbListView.setHasFixedSize(true);
        Bitmap bitmap = Bitmap.createScaledBitmap(selectedBitmap, 100, 100, false);
        bindDataToAdapter(bitmap);
    }

    private void bindDataToAdapter(final Bitmap thumbImage) {
        final Context context = this.getApplication();
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                ThumbnailItem t1 = new ThumbnailItem();
                ThumbnailItem t2 = new ThumbnailItem();
                ThumbnailItem t3 = new ThumbnailItem();
                ThumbnailItem t4 = new ThumbnailItem();
                ThumbnailItem t5 = new ThumbnailItem();
                ThumbnailItem t6 = new ThumbnailItem();

                t1.image = thumbImage;
                t2.image = thumbImage;
                t3.image = thumbImage;
                t4.image = thumbImage;
                t5.image = thumbImage;
                t6.image = thumbImage;
                ThumbnailsManager.clearThumbs();
                ThumbnailsManager.addThumb(t1); // Original Image

                t2.filter = getStarLitFilter();
                ThumbnailsManager.addThumb(t2);

                t3.filter = getBlueMessFilter();
                ThumbnailsManager.addThumb(t3);

                t4.filter = getAweStruckVibeFilter();
                ThumbnailsManager.addThumb(t4);

                t5.filter = getLimeStutterFilter();
                ThumbnailsManager.addThumb(t5);

                t6.filter = getNightWhisperFilter();
                ThumbnailsManager.addThumb(t6);

                List<ThumbnailItem> thumbs = ThumbnailsManager.processThumbs(context);

                ThumbnailsAdapter adapter = new ThumbnailsAdapter(thumbs, (ThumbnailCallback) CreateStoryActivity.this);
                thumbListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }

    @Override
    public void onThumbnailClick(Filter filter) {
//        selectedBitmap = uriToBitmap(selectedFileUri, selectedBitmap);
//        selectedBitmap = ImageFilter.applyFilter(selectedBitmap, ImageFilter.Filter.GRAY);
//        placeHolderImageView.setImageBitmap(filter.processFilter(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.photo), 640, 640, false)));
        try {
            selectedBitmap = selectedBitmap.copy(Bitmap.Config.ARGB_8888, true);
            selectedBitmap = filter.processFilter(selectedBitmap);
            placeHolderImageView.setImageBitmap(selectedBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createPerviewDialog() {
        final Uri[] allPaths = uriHashMapThumb.values().toArray(new Uri[uriHashMap.size()]);
        if (allPaths.length <= 0) return;
        final Dialog dialog = new Dialog(this, R.style.MaterialDialogSheet);
        dialog.setContentView(R.layout.dialog_create_story_perview);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        TextView nextq = (TextView) dialog.findViewById(R.id.next);
        EditText et_captions = (EditText) dialog.findViewById(R.id.et_captions);
        StoryCaption = et_captions.getText().toString();
        final ImageView avatarFace = (ImageView) dialog.findViewById(R.id.avatar_face);
        nextq.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
//                if (lastPerview == allPaths.length) {
                uploadImageToServer();
//                } else {
//                    Uri pathUri = allPaths[lastPerview++];
//                    avatarFace.setImageBitmap(uriToBitmap(pathUri));
//                }
            }
        });
        Uri pathUri = allPaths[lastPerview++];
        avatarFace.setImageBitmap(uriToBitmap(pathUri));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.show();
    }

}
