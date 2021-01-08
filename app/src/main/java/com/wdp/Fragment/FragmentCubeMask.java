package com.wdp.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.iammert.library.cameravideobuttonlib.CameraVideoButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wdp.ActivityScreen.ImageFilterActivity;
import com.wdp.ActivityScreen.MainActivity;
import com.wdp.Deepar.AREffect;
import com.wdp.Deepar.CameraGrabber;
import com.wdp.Deepar.CameraGrabberListener;

import com.wdp.Deepar.MaskFilterationListAdapter;
import com.wdp.Deepar.PermissionsActivity;
import com.wdp.turnstr.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ai.deepar.ar.AREventListener;
import ai.deepar.ar.CameraResolutionPreset;
import ai.deepar.ar.DeepAR;


public class FragmentCubeMask extends PermissionsActivity implements AREventListener, SurfaceHolder.Callback,
        MaskFilterationListAdapter.OnclickPosition,DiscreteScrollView.ScrollListener<MaskFilterationListAdapter.MyViewHolder>,
        DiscreteScrollView.OnItemChangedListener<MaskFilterationListAdapter.MyViewHolder>,
        View.OnClickListener  {

    private  String TAG = MainActivity.class.getSimpleName();
    private SurfaceView arView;
    private CameraGrabber cameraGrabber;
    private ImageButton switchCamera;
    String whichScreen;

    public FragmentCubeMask(String whichscreen){
        whichScreen = whichscreen;
        Log.d("whichScreen","--->" + whichScreen);
    }

    public FragmentCubeMask(){

    }

    private RadioButton radioMasks;
    private RadioButton radioEffects;
    private RadioButton radioFilters;



    private final static String SLOT_MASKS = "masks";
    private final static String SLOT_EFFECTS = "effects";
    private final static String SLOT_FILTER = "filters";
    private String currentSlot = SLOT_MASKS;

    private int currentMask = 0;
    private int currentEffect = 0;
    private int currentFilter = 0;
    private int screenOrientation;
    ArrayList<AREffect> masks = new ArrayList<>();;
    ArrayList<AREffect> effects;
    ArrayList<AREffect> filters;
    private DeepAR deepAR;
    boolean recording = false;
    private int defaultCameraDevice = Camera.CameraInfo.CAMERA_FACING_FRONT;
    private int cameraDevice = defaultCameraDevice;
    private MaskFilterationListAdapter maskFilterationListAdapter;
    private View view;
    RelativeLayout relativeLayout,rl_fillter;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private DiscreteScrollView discreteScrollView;
    private Spinner spinner;
    CameraVideoButton cameraVideoButton;
    private CharSequence now;
    TextView txt_live, txt_cancel, txt_next;



   private int getScreenOrientation() {
        int rotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;

        if ((rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) && height > width || (rotation == Surface.ROTATION_90
                        || rotation == Surface.ROTATION_270) && width > height) {
            switch (rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    Log.e(TAG, "Unknown screen orientation. Defaulting to " + "portrait.");
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        }
        else {
            switch (rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_mask_cube, container, false);
        screenOrientation = getScreenOrientation();
        if (savedInstanceState != null) {
            cameraDevice = savedInstanceState.getInt("camera", defaultCameraDevice);
        }
        deepAR = new DeepAR(getActivity());
        deepAR.setAntialiasingLevel(4);
        deepAR.setLicenseKey("de8ad2e7f117e4237314e2ac3296117127f6ac09d5b469a283268eab9e5b698822fe2fac7e9ae146");
        deepAR.initialize(getActivity(), this);
        rl_fillter =  view.findViewById(R.id.rl_fillter);
        relativeLayout =  view.findViewById(R.id.relativeLayout);
        spinner =  view.findViewById(R.id.spinner);
        txt_cancel = view.findViewById(R.id.txt_cancel);
        txt_next = view.findViewById(R.id.txt_next);
        spinner.setVisibility(View.GONE);
        cameraVideoButton =  view.findViewById(R.id.cameraVideoButton);
        cameraVideoButton.setVideoDuration(30000);
        txt_live = view.findViewById(R.id.txt_live);
        txt_live.setVisibility(View.GONE);
        if (whichScreen.equalsIgnoreCase("image")){
            cameraVideoButton.enablePhotoTaking(true);
            cameraVideoButton.enableVideoRecording(false);
        }
        else {
            cameraVideoButton.enablePhotoTaking(false);
            cameraVideoButton.enableVideoRecording(true);
        }
        cameraVideoButton.setActionListener(new CameraVideoButton.ActionListener() {
            @Override
            public void onStartRecord() {
                now = DateFormat.format("yyyy_MM_dd_hh_mm_ss", new Date());
                deepAR.startVideoRecording(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/DeepAR_" + now + "video.mp4", 300, 0);
            }

            @Override
            public void onEndRecord() {
                deepAR.stopVideoRecording();
            }

            @Override
            public void onDurationTooShortError() {

            }

            @Override
            public void onSingleTap() {
                deepAR.takeScreenshot();
            }
        });

        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.h.sendEmptyMessage(1);
            }
        });

        txt_next.setVisibility(View.GONE);

        return view;
    }


    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }



    @Override
    public void onStart() {
        super.onStart();
        String cameraPermission = getResources().getString(R.string.camera_permission);
        String externalStoragePermission = getResources().getString(R.string.external_permission);

        checkPermissionSelf();
        checkMultiplePermissions(Arrays.asList(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO),
                cameraPermission + " " + externalStoragePermission,
                100,
                new MultiplePermissionsCallback() {
                    @Override
                    public void onAllPermissionsGranted() {
                    }
                    @Override
                    public void onPermissionsDenied(List<String> deniedPermissions) {
                        Log.d("permission","--1-->" + "permission");
                    }
                });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        deepAR.setAREventListener(null);
        deepAR.release();
        deepAR = null;

    }

    @Override
    public void onResume() {
        super.onResume();
       // setupDeepAR();


    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("destroy","--->" + "3");
        cameraGrabber.setFrameReceiver(null);
        cameraGrabber.stopPreview();
        cameraGrabber.releaseCamera();
        cameraGrabber = null;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (cameraGrabber != null) {
            outState.putInt("camera", cameraGrabber.getCurrCameraDevice());
        }
    }


    public void checkPermissionSelf() {
        Dexter.withContext(getActivity())
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            setupDeepAR();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            getPermission();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {
            setupDeepAR();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_ASK_PERMISSIONS);
    }


    private void setupEffects() {
        masks.clear();
        masks.add(new AREffect("none",AREffect.EffectTypeMask,R.drawable.ic_launcher_background));
        masks.add(new AREffect("oversized_sunglasses",AREffect.EffectTypeMask,R.drawable.oversized_sunglasses_));
        masks.add(new AREffect("bunny_ears",AREffect.EffectTypeMask,R.drawable.bunny_ears_));
        masks.add(new AREffect("dwayne",AREffect.EffectTypeMask,R.drawable.dwayne_));
        masks.add(new AREffect("fluffy_ears",AREffect.EffectTypeMask,R.drawable.fluffy_ears_));
        masks.add(new AREffect("neon_heart_overlay",AREffect.EffectTypeMask,R.drawable.neon_heart_overlay_));
        masks.add(new AREffect("pig_face",AREffect.EffectTypeMask,R.drawable.pig_face_));
        masks.add(new AREffect("side_flower",AREffect.EffectTypeMask,R.drawable.side_flower_));
        masks.add(new AREffect("look1",AREffect.EffectTypeMask,R.drawable.look1_));
        masks.add(new AREffect("look2",AREffect.EffectTypeMask,R.drawable.look2_));
        masks.add(new AREffect("look3",AREffect.EffectTypeMask,R.drawable.look3_));

   }




    private void radioButtonClicked() {
        if (radioMasks.isChecked()) {
            currentSlot = SLOT_MASKS;
        } else if (radioEffects.isChecked()) {
            currentSlot = SLOT_EFFECTS;
        } else if (radioFilters.isChecked()) {
            currentSlot = SLOT_FILTER;
        }
    }

    private ArrayList<AREffect> getActiveList() {
        if (currentSlot.equals(SLOT_MASKS)) {
            return masks;
        } else if (currentSlot.equals(SLOT_EFFECTS)) {
            return masks;
        } else {
            return masks;
        }
    }

    private int getActiveIndex() {
        if (currentSlot.equals(SLOT_MASKS)) {
            return currentMask;
        } else if (currentSlot.equals(SLOT_EFFECTS)) {
            return currentEffect;
        } else {
            return currentFilter;
        }
    }

    private void setActiveIndex(int index) {
        if (currentSlot.equals(SLOT_MASKS)) {
            currentMask = index;
        } else if (currentSlot.equals(SLOT_EFFECTS)) {
            currentEffect = index;
        } else {
            currentFilter = index;
        }
    }





    private void gotoMaskPrevious(int position) {
       ArrayList<AREffect> activeList = getActiveList();
        if (position == activeList.size() && currentSlot.equals(SLOT_MASKS)) {
            setTwoFaceMask();
            return;
        }
        if (position >= activeList.size()) {
            position = 0;
        }
        setActiveIndex(position);
        deepAR.switchEffect(currentSlot, activeList.get(position).getPath());
        deepAR.switchEffect("face2", null, 1);
    }

    private void setTwoFaceMask() {
        ArrayList<AREffect> activeList = getActiveList();
        deepAR.switchEffect(currentSlot, activeList.get(1).getPath(), 0);
        deepAR.switchEffect("face2", activeList.get(3).getPath(), 1);
        currentMask = activeList.size();
   }

   private void setupDeepAR() {
        cameraGrabber = new CameraGrabber(cameraDevice);
        switch (screenOrientation) {
            case ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
                cameraGrabber.setScreenOrientation(90);
                break;
            case ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE:
                cameraGrabber.setScreenOrientation(270);
                break;
            default:
                cameraGrabber.setScreenOrientation(0);
                break;
        }
        cameraGrabber.setResolutionPreset(CameraResolutionPreset.P640x480);
        Activity context = getActivity();
        cameraGrabber.initCamera(new CameraGrabberListener() {
            @Override
            public void onCameraInitialized() {
                cameraGrabber.setFrameReceiver(deepAR);
                cameraGrabber.startPreview();
                if (recording) {
                    deepAR.resumeVideoRecording();
                }

            }

            @Override
            public void onCameraError(String errorMsg) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Camera error");
                builder.setMessage(errorMsg);
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        setupViews();
    }

    private void setupViews() {
        arView = (SurfaceView)view.findViewById(R.id.surface);
        discreteScrollView = (DiscreteScrollView) view. findViewById(R.id.picker);

        arView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deepAR.onClick();
            }
        });

        arView.getHolder().addCallback(this);
        arView.setVisibility(View.GONE);
        arView.setVisibility(View.VISIBLE);
        setupEffects();




        switchCamera = (ImageButton)view.findViewById(R.id.switchCamera);
        switchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraDevice = cameraGrabber.getCurrCameraDevice() == Camera.CameraInfo.CAMERA_FACING_FRONT ? Camera.CameraInfo.CAMERA_FACING_BACK : Camera.CameraInfo.CAMERA_FACING_FRONT;
                cameraGrabber.changeCameraDevice(cameraDevice);
            }
        });

        ArrayList<AREffect> filterMaskList = getActiveList();
        maskFilterationListAdapter = new MaskFilterationListAdapter(getActivity(),filterMaskList, this);
        discreteScrollView.setAdapter(maskFilterationListAdapter);
        discreteScrollView.addScrollListener(this);
        discreteScrollView.addOnItemChangedListener(this);
        discreteScrollView.scrollToPosition(0);

    }


    @Override
    public void screenshotTaken(Bitmap screenshot) {
        CharSequence now = DateFormat.format("yyyy_MM_dd_hh_mm_ss", new Date());
        try {
            File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/DeepAR_" + now + ".jpg");
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            screenshot.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            MediaScannerConnection.scanFile(getActivity(), new String[]{imageFile.toString()}, null, null);
            Uri uri = Uri.fromFile(new File(imageFile.toString()));
            loadProfile(imageFile.getAbsolutePath());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void loadProfile(String url) {
        ArrayList<String> selectedImageList=new ArrayList<>();
        selectedImageList.add(url);
        Intent i = new Intent(getActivity(), ImageFilterActivity.class);
        Gson gson = new Gson();
        String arrayData = gson.toJson(selectedImageList);
        i.putExtra("selectlist",arrayData);
        startActivity(i);
    }

    @Override
    public void videoRecordingStarted() {

    }

    @Override
    public void videoRecordingFinished() {
        try {
            File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/DeepAR_" + now + "video.mp4");
            Uri uri = Uri.fromFile(new File(imageFile.toString()));
            loadProfile(imageFile.getPath());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void videoRecordingFailed() {
    }

    @Override
    public void initialized() {

    }

    @Override
    public void faceVisibilityChanged(boolean faceVisible) {

    }

    @Override
    public void imageVisibilityChanged(String gameObjectName, boolean imageVisible) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        deepAR.setRenderSurface(surfaceHolder.getSurface(), width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (deepAR != null) {
            deepAR.setRenderSurface(null, 0, 0);
        }
    }

    @Override
    public void videoRecordingPrepared() {
    }

    @Override
    public void shutdownFinished() {
    }

    @Override
    public void error(String error) {
        if (error.equals(DeepAR.ERROR_EFFECT_FILE_LOAD_FAILED)) {
        }
        else if (error.equals(DeepAR.ERROR_MODEL_FILE_NOT_FOUND)) {
        }
    }

    @Override
    public void effectSwitched(String slot) {

    }

    @Override
    public void onClickPosiition(int position) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCurrentItemChanged(@Nullable MaskFilterationListAdapter.MyViewHolder myViewHolder, int i) {
        gotoMaskPrevious(i);
    }

    @Override
    public void onScroll(float v, int i, int i1, @Nullable MaskFilterationListAdapter.MyViewHolder myViewHolder, @Nullable MaskFilterationListAdapter.MyViewHolder t1) {

    }
}
