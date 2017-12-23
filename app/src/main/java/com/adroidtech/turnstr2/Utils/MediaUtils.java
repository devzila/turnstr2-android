package com.adroidtech.turnstr2.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.MediaStore.Video;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MediaUtils {

    /**
     * Add these Permission in menifest
     * <uses-permission android:name="android.permission.CAMERA" />
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
     * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
     * <uses-feature android:name="android.hardware.camera" />
     * <uses-feature android:name="android.hardware.camera.autofocus" />
     *
     * @param activity
     * @param requestCode
     */
    public static void openPictureSelectDialog(final Activity activity, final int requestCode) {
        CharSequence options[] = new CharSequence[]{"Take Photo", "Choose Photo"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);
        builder.setTitle("Select your option:");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on options[which]
                switch (which) {
                    case 0:
                        createFileForCamera();
//                        createTakePictureIntent(activity, 111);
//                        activity.startActivityForResult(cameraIntent, requestCode);
                        break;
                    case 1:
                        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        activity.startActivityForResult(i, requestCode);
                        break;
                }
            }
        });
        builder.show();
    }

    /**
     * Create a File in memory for Camera option ,
     * please use this URI in onActivityResult for image data fetching.
     *
     * @return
     */
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
//        try {
//            File attachDir = new File(Environment.getExternalStorageDirectory() + "/Attachments/");
//            if (!attachDir.exists()) {
//                attachDir.mkdirs();
//            }
//            File file = File.createTempFile(Calendar.getInstance().getTimeInMillis() + "", ".jpg", attachDir);
//            // Save a file: path for use with ACTION_VIEW intents
//            return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Create a File in memory for Camera option ,
     * please use this URI in onActivityResult for image data fetching.
     *
     * @return
     */
    public static Uri createFileForCameraVideo() {
        File file = new File(Environment.getExternalStorageDirectory(), (Calendar.getInstance().getTimeInMillis() + ".mp4"));
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

    private static final String TAG = MediaUtils.class.getSimpleName();

    /**
     * Get runtime duration of media such as audio or video in milliseconds
     ****/
    public static long getDuration(Context ctx, Uri mediaUri) {
        Cursor cur = ctx.getContentResolver().query(mediaUri, new String[]{Video.Media.DURATION}, null, null, null);
        long duration = -1;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    duration = cur.getLong(cur.getColumnIndex(Video.Media.DURATION));

                    if (duration == 0)
                        Log.w(TAG, "#getMediaDuration The image size was found to be 0. Reason: UNKNOWN");

                }    // end while
            } else if (cur.getCount() == 0) {
                Log.e(TAG, "#getMediaDuration cur size is 0. File may not exist");
            } else {
                Log.e(TAG, "#getMediaDuration cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return duration;
    }

    /**
     * Checks if the parameter {@link Uri} is a Media content uri.
     ****/
    public static boolean isMediaContentUri(Uri uri) {
        if (!uri.toString().contains("content://media/")) {
            Log.w(TAG, "#isContentUri The uri is not a media content uri");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Creates an intent to take a video from camera or gallery or any other application that can
     * handle the intent.
     *
     * @param ctx
     * @param savingUri
     * @param durationInSeconds
     * @return
     */
    public static Intent createTakeVideoIntent(Activity ctx, Uri savingUri, int durationInSeconds) {

        if (savingUri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        final PackageManager packageManager = ctx.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, durationInSeconds);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("video/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));

        return chooserIntent;
    }

    /**
     * Creates a ACTION_IMAGE_CAPTURE photo & ACTION_GET_CONTENT intent. This intent will be
     * aggregation of intents required to take picture from Gallery and Camera at the minimum. The
     * intent will also be directed towards the apps that are capable of sourcing the image data.
     * For e.g. Dropbox, Astro file manager.
     **/
//    public static Uri createTakePictureIntent(Activity activity, int request_code) {
//        if (checkAndRequestPermissions(activity)) {
//            Uri savingUri = MediaUtils.createFileForCamera();
//            if (savingUri == null) {
//                throw new NullPointerException("Uri cannot be null");
//            }
//            final List<Intent> cameraIntents = new ArrayList<Intent>();
//            final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            final PackageManager packageManager = activity.getPackageManager();
//            final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
//            for (ResolveInfo res : listCam) {
//                final String packageName = res.activityInfo.packageName;
//                final Intent intent = new Intent(captureIntent);
//                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
//                intent.setPackage(packageName);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
//                cameraIntents.add(intent);
//            }
//            // Filesystem.
//            final Intent galleryIntent = new Intent();
//            galleryIntent.setType("image/*");
//            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//            // Chooser of filesystem options.
//            final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
//            // Add the camera options.
//            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
//            activity.startActivityForResult(chooserIntent, request_code);
//            return savingUri;
//        } else {
//            return null;
//        }
//    }
    @Nullable
    /**
     * Creates external content:// scheme uri to save the images at. The image saved at this
     * {@link Uri} will be visible via the gallery application on the device.
     **/
    public static Uri createImageUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri = null;

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, "");
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }

    @Nullable
    /**
     * Creates external content:// scheme uri to save the videos at.
     **/
    public static Uri createVideoUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri = null;

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, "");
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(Video.Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }

    @Nullable
    /**
     * Gets media type from the Uri.
     */
    public static String getMediaType(Uri uri) {
        if (uri == null) {
            return null;
        }

        String uriStr = uri.toString();

        if (uriStr.contains("video")) {
            return "video";
        } else if (uriStr.contains("audio")) {
            return "audio";
        } else if (uriStr.contains("image")) {
            return "image";
        } else {
            return null;
        }
    }

    /**
     * Gets media file name.
     **/
    public static String getMediaFileName(Context ctx, Uri mediaUri) {
        String colName = MediaStore.MediaColumns.DISPLAY_NAME;
        Cursor cur = ctx.getContentResolver().query(mediaUri, new String[]{colName}, null, null, null);
        String dispName = null;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    dispName = cur.getString(cur.getColumnIndex(colName));

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getMediaFileName byte.size: " + size );

                    if (TextUtils.isEmpty(colName)) {
                        Log.w(TAG, "#getMediaFileName The file name is blank or null. Reason: UNKNOWN");
                    }

                } // end while
            } else if (cur != null && cur.getCount() == 0) {
                Log.e(TAG, "#getMediaFileName File may not exist");
            } else {
                Log.e(TAG, "#getMediaFileName cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return dispName;
    }

    /**
     * Gets the size of the media resource pointed to by the parameter mediaUri.
     * <p/>
     * Known bug: for unknown reason, the image size for some images was found to be 0
     *
     * @param mediaUri uri to the media resource. For e.g. content://media/external/images/media/45490 or
     *                 content://media/external/video/media/45490
     * @return Size in bytes
     **/
    public static long getMediaSize(Context context, Uri mediaUri) {
        Cursor cur = context.getContentResolver().query(mediaUri, new String[]{MediaStore.Images.Media.SIZE}, null, null, null);
        long size = -1;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    size = cur.getLong(cur.getColumnIndex(MediaStore.Images.Media.SIZE));

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getSize byte.size: " + size );

                    if (size == 0)
                        Log.w(TAG, "#getSize The media size was found to be 0. Reason: UNKNOWN");

                } // end while
            } else if (cur.getCount() == 0) {
                Log.e(TAG, "#getMediaSize cur size is 0. File may not exist");
            } else {
                Log.e(TAG, "#getMediaSize cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return size;
    }

    @Nullable
    /**
     * Gets the media data from the one of the following media {@link android.content.ContentProvider} This method
     * should not be called from the main thread of the application. Calling this method may have
     * performance issues as this may allocate a huge memory array.
     * <ul>
     * <li>{@link MediaStore.Images.Media}</li>
     * <li>{@link MediaStore.Audio.Media}</li>
     * <li>{@link Video.Media}</li>
     * </ul>
     *
     * @param ctx Context object
     * @param uri Media content uri of the image, audio or video resource
     */
    public static byte[] getMediaData(Context ctx, Uri uri) {
        if (uri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        Cursor cur = ctx.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        byte[] data = null;

        try {
            if (cur != null && cur.getCount() > 0) {
                if (cur.moveToNext()) {
                    String path = cur.getString(cur.getColumnIndex(MediaStore.Images.Media.DATA));

                    try {
                        File f = new File(path);
                        FileInputStream fis = new FileInputStream(f);
                        data = Utils.readStreamToBytes(fis);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Log.v( TAG, "#getVideoData byte.size: " + data.length );
                } // end while
            } else
                Log.e(TAG, "#getMediaData cur is null or blank");
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return data;
    }

    /**
     * Returns true if the mime type is a standard image mime type
     */
    public static boolean isImage(String mimeType) {
        // TODO: apply regex patter for checking the MIME type
        if (mimeType != null) {
            if (mimeType.startsWith("image/"))
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * Returns true if the mime type is a standard audio mime type
     */
    public static boolean isAudio(String mimeType) {
        // TODO: apply regex patter for checking the MIME type
        if (mimeType != null) {
            if (mimeType.startsWith("audio/"))
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * Returns true if the mime type is a standard video mime type
     */
    public static boolean isVideo(String mimeType) {
        // TODO: apply regex patter for checking the MIME type
        if (mimeType != null) {
            if (mimeType.startsWith("video/"))
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    /**
     * Identifies if the content represented by the parameter mimeType is media. Image, Audio and
     * Video is treated as media by this method. You can refer to standard MIME type here. <a
     * href="http://www.iana.org/assignments/media-types/media-types.xhtml" >Standard MIME
     * types.</a>
     *
     * @param mimeType standard MIME type of the data.
     */
    public static boolean isMedia(String mimeType) {
        boolean isMedia = false;

        if (mimeType != null) {
            if (mimeType.startsWith("image/") || mimeType.startsWith("video/") || mimeType.startsWith("audio/")) {
                isMedia = true;
            }
        } else {
            isMedia = false;
        }

        return isMedia;
    }

    /**
     * Get the type of the media. Audio, Video or Image.
     *
     * @return Lower case string for one of above listed media type
     */
    public static String getMediaType(String contentType) {
        if (isMedia(contentType)) {
            if (isVideo(contentType))
                return "video";
            else if (isAudio(contentType))
                return "audio";
            else if (isImage(contentType))
                return "image";
            else
                return null;
        } else {
            return null;
        }
    }

    /**
     * Writes the given image to the external storage of the device. If external storage is not
     * available, the image is written to the application private directory
     *
     * @return Path of the image file that has been written.
     **/
    public static String writeImage(Context ctx, byte[] imageData) {

        final String FILE_NAME = "photograph.jpeg";
        File dir = null;
        String filePath = null;
        OutputStream imageFileOS;

        dir = Utils.getStorageDirectory(ctx, null);

        // dir.mkdirs();
        File f = new File(dir, FILE_NAME);

        // File f = getFile( FILE_NAME );

        try {
            imageFileOS = new FileOutputStream(f);
            imageFileOS.write(imageData);
            imageFileOS.flush();
            imageFileOS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        filePath = f.getAbsolutePath();

        return filePath;
    }


    public static Bitmap getBitmapImageOrientation(Context context, Bitmap bitmap, Uri path, Boolean value) {
        int rotate = 0;
        int orientation = 0;
        try {
            String imagePath = path.getPath();
            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            if (value)
                orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL);
            else
                orientation = getOrientation(context, path);
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    public static boolean checkAndRequestPermissions(Activity activity) {
//        int permissionCamera = ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.CAMERA);
//        int permissionWriteStorage = ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        int permissionReadStorage = ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.READ_EXTERNAL_STORAGE);
//        List<String> listPermissionsNeeded = new ArrayList<>();
//
//        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.CAMERA);
//        }
//        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//        if (permissionReadStorage != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        }
//
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 124);
//            return false;
//        }
        return true;
    }

    public static int getOrientation(Context context, Uri photoUri) {
        /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    /**
     * Select a video from gallery or any other location
     *
     * @param activity
     * @param RESULT_CHOSSE_VIDEO
     */
    public static void openSelectVideoDialog(Activity activity, int RESULT_CHOSSE_VIDEO) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Video"), RESULT_CHOSSE_VIDEO);
    }

    /**
     * Convert a File or Document Uri to file path uri.
     *
     * @param activity
     * @param data
     * @return
     */
    public static Uri convertUriPathFromActivityResult(Activity activity, Intent data) {
        try {
            return Uri.fromFile(new File(ConvertUriToFilePath.getPathFromURI(activity, data.getData())));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param activity
     * @return
     */
    public static Uri convertMediaUriToPath(Activity activity, Intent data) {
        Uri uri = data.getData();
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return Uri.fromFile(new File(path));
    }
}
