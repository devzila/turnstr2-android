package com.adroidtech.turnstr2.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adroidtech.turnstr2.R;

public class CreateStoryActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout allImages;
    private ImageView avatarFace1;
    private ImageView avatarFace2;
    private ImageView avatarFace3;
    private ImageView avatarFace4;
    private ImageView avatarFace5;
    private ImageView avatarFace6;
    private Button bnt_library;
    private Button bnt_photos;
    private Button bnt_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);
        allImages = (LinearLayout) findViewById(R.id.all_images);
        avatarFace1 = (ImageView) findViewById(R.id.avatar_face1);
        avatarFace2 = (ImageView) findViewById(R.id.avatar_face2);
        avatarFace3 = (ImageView) findViewById(R.id.avatar_face3);
        avatarFace4 = (ImageView) findViewById(R.id.avatar_face4);
        avatarFace5 = (ImageView) findViewById(R.id.avatar_face5);
        avatarFace6 = (ImageView) findViewById(R.id.avatar_face6);
        bnt_library = (Button) findViewById(R.id.library);
        bnt_photos = (Button) findViewById(R.id.photos);
        bnt_video = (Button) findViewById(R.id.video);
        bnt_video.setOnClickListener(this);
        bnt_photos.setOnClickListener(this);
        bnt_library.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.library:
                bnt_library.setBackgroundResource(R.color.background_gradient);
                bnt_photos.setBackgroundResource(R.color.black);
                bnt_video.setBackgroundResource(R.color.black);
                //TODO implement
                break;
            case R.id.photos:
                bnt_photos.setBackgroundResource(R.color.background_gradient);
                bnt_library.setBackgroundResource(R.color.black);
                bnt_video.setBackgroundResource(R.color.black);

                //TODO implement
                break;
            case R.id.video:
                bnt_video.setBackgroundResource(R.color.background_gradient);
                bnt_library.setBackgroundResource(R.color.black);
                bnt_photos.setBackgroundResource(R.color.black);
                //TODO implement
                break;
        }
    }
}
