package com.adroidtech.turnstr2.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagePickerUtils {
    /**
     * Add these Permission in menifest
     * <uses-permission android:name="android.permission.CAMERA" />
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
     * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
     * <uses-feature android:name="android.hardware.camera" />
     * <uses-feature android:name="android.hardware.camera.autofocus" />
     * <p>
     * Creates a ACTION_IMAGE_CAPTURE photo & ACTION_GET_CONTENT intent. This intent will be
     * aggregation of intents required to take picture from Gallery and Camera at the minimum. The
     * intent will also be directed towards the apps that are capable of sourcing the image data.
     * For e.g. Dropbox, Astro file manager.
     *
     * @param activity
     * @param request_code
     **/

    /**
     * <b> Add onActivityResult in your activity </b>
     *
     * @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     * super.onActivityResult(requestCode, resultCode, data);
     * if (resultCode == Activity.RESULT_OK) {
     * Uri fileUri = ImagePickerUtils.getFileUriOfImage(this, data, mCameraUri);
     * try { Bitmap bitmap = null;
     * if (CAMERA_REQUEST_CODE == requestCode) {
     * bitmap = new BitmapUtils().getDownsampledBitmap(this, fileUri, userImage.getWidth(), userImage.getHeight()); }
     * if (bitmap != null) userImage.setImageBitmap(bitmap);
     * } catch (Exception e) {  e.printStackTrace();} } }
     **/
    public static Uri createTakePictureIntent(Activity activity, int request_code) {
        if (checkAndRequestPermissions(activity)) {
            Uri savingUri = MediaUtils.createFileForCamera();
            if (savingUri == null) {
                throw new NullPointerException("Uri cannot be null");
            }
            final List<Intent> cameraIntents = new ArrayList<Intent>();
            final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            final PackageManager packageManager = activity.getPackageManager();
            final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
            for (ResolveInfo res : listCam) {
                final String packageName = res.activityInfo.packageName;
                final Intent intent = new Intent(captureIntent);
                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(packageName);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
                cameraIntents.add(intent);
            }
            // Filesystem.
            final Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            // Chooser of filesystem options.
            final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
            // Add the camera options.
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
            activity.startActivityForResult(chooserIntent, request_code);
            return savingUri;
        } else {
            return null;
        }
    }

    public static Uri createFileIntent(Activity activity, int request_code) {
        if (checkAndRequestPermissions(activity)) {
            Uri savingUri = MediaUtils.createFileForCamera();
            if (savingUri == null) {
                throw new NullPointerException("Uri cannot be null");
            }
            final List<Intent> cameraIntents = new ArrayList<Intent>();
            final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            final PackageManager packageManager = activity.getPackageManager();
            final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
            for (ResolveInfo res : listCam) {
                final String packageName = res.activityInfo.packageName;
                final Intent intent = new Intent(captureIntent);
                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(packageName);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
                cameraIntents.add(intent);
            }
            // Filesystem.
            final Intent galleryIntent = new Intent();
            galleryIntent.setType("*/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            // Chooser of filesystem options.
            final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
            // Add the camera options.
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
            activity.startActivityForResult(chooserIntent, request_code);
            return savingUri;
        } else {
            return null;
        }
    }

    /**
     * Check camera permission when we lounch camera.
     *
     * @param activity
     * @return
     */
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

    /**
     * FileUriOfImage is used to convert the base document or camera uri to file uri ,
     * so that we can use this to get image data.
     *
     * @param context
     * @param data
     * @param mainUri
     * @return
     */
    public static Uri getFileUriOfImage(Activity context, Intent data, Uri mainUri) {
        Uri fileUri = null;
        try {
            if (data != null) fileUri = data.getData();
            if (fileUri == null) fileUri = mainUri;
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                    fileUri = convertUriPathFromActivityResult(context, data);
                else
                    fileUri = convertMediaUriToPath(context, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return fileUri;
        }

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

    public static int getImageOrientation(Context context, String imagePath, Uri path, Boolean value) {
        int rotate = 0;
        int orientation;
        try {

            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            if (value)
                orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL);
            else
                orientation = getOrientation(context, path);

            Log.e("rotation angle", "=" + orientation);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;

                case 270:
                    rotate = 270;
                    break;
                case 180:
                    rotate = 180;
                    break;
                case 90:
                    rotate = 90;
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
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

}
