package com.adroidtech.turnstr2.GoLive;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.adroidtech.turnstr2.R;

/**
 * Created by narinder on 13/02/18.
 */

public class StartGoLive_Activity extends AppCompatActivity implements View.OnClickListener {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Button btnGoLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_golive_activity);


        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        btnGoLive=(Button)findViewById(R.id.btnGoLive);
        btnGoLive.setOnClickListener(this);

    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    public void onClick(View view) {

        if(view==btnGoLive)
        {

            startActivity(new Intent(getApplicationContext(), MainVideoActivity.class));
        }
    }
}
