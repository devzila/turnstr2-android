package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.BitmapUtils;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.ImagePickerUtils;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Stack;

public class EditProfileActivity extends Activity implements View.OnClickListener {
    private static final int PIC_REQUEST = 1231;
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private FrameLayout layout_frame_main;
    private SharedPreference sharedPreference;
    private LoginDetailModel userDetail;
    private TextView editProfile;
    private LinearLayout search;
    private TextView txtPosts;
    private TextView txtFollowers;
    private TextView txtFamily;
    private FrameLayout layoutFrame;
    private TextView txtAbout;
    private TextView txtAddress;
    private TextView txtEmail;
    private Cubesurfaceview view1;
    private FrameLayout layoutFrame1;
    private TextView txtChangePic;
    private LinearLayout allImages;
    private EditText txtName;
    private EditText txtUsername;
    private EditText txtWebsite;
    private EditText txtInfo;
    private CheckBox cbOnline;
    private TextView privacy;
    private EditText txtUserEmail;
    private EditText txtUserPhone;
    private Uri mCameraUri;
    private ImageView avatarFace1;
    private ImageView avatarFace2;
    private ImageView avatarFace3;
    private ImageView avatarFace4;
    private ImageView avatarFace5;
    private ImageView avatarFace6;
    private View currentViewSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
        txtChangePic = (TextView) findViewById(R.id.txt_change_pic);
        txtChangePic.setOnClickListener(this);
        allImages = (LinearLayout) findViewById(R.id.all_images);
        txtName = (EditText) findViewById(R.id.txt_name);
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtWebsite = (EditText) findViewById(R.id.txt_website);
        txtInfo = (EditText) findViewById(R.id.txt_info);
        cbOnline = (CheckBox) findViewById(R.id.cb_online);
        privacy = (TextView) findViewById(R.id.privacy);
        txtUserEmail = (EditText) findViewById(R.id.txt_user_email);
        txtUserPhone = (EditText) findViewById(R.id.txt_user_phone);

        avatarFace1 = (ImageView) findViewById(R.id.avatar_face1);
        avatarFace2 = (ImageView) findViewById(R.id.avatar_face2);
        avatarFace3 = (ImageView) findViewById(R.id.avatar_face3);
        avatarFace4 = (ImageView) findViewById(R.id.avatar_face4);
        avatarFace5 = (ImageView) findViewById(R.id.avatar_face5);
        avatarFace6 = (ImageView) findViewById(R.id.avatar_face6);

        avatarFace1.setOnClickListener(this);
        avatarFace2.setOnClickListener(this);
        avatarFace3.setOnClickListener(this);
        avatarFace4.setOnClickListener(this);
        avatarFace5.setOnClickListener(this);
        avatarFace6.setOnClickListener(this);

        view = new Cubesurfaceview(EditProfileActivity.this, mBbitmap);
        layout_frame_main.addView(view);
//        viewIntail();
//        uiDataUpdate(userDetail);
        loadAllImages();
        loadAllImagesToCube();
    }

    private void loadAllImages() {
        try {
            Picasso.with(this).load(userDetail.getUser().getAvatarFace1()).into(avatarFace1);
            Picasso.with(this).load(userDetail.getUser().getAvatarFace2()).into(avatarFace2);
            Picasso.with(this).load(userDetail.getUser().getAvatarFace3()).into(avatarFace3);
            Picasso.with(this).load(userDetail.getUser().getAvatarFace4()).into(avatarFace4);
            Picasso.with(this).load(userDetail.getUser().getAvatarFace5()).into(avatarFace5);
            Picasso.with(this).load(userDetail.getUser().getAvatarFace6()).into(avatarFace6);
        } catch (Exception e) {

        }

    }

    private void loadAllImagesToCube() {
        final Stack<String> strings = new Stack<>();
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        strings.push("https://lorempixel.com/100/100/");
        new URLImageParser(strings, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap = bitmap;
                layout_frame_main.removeAllViews();
                view = new Cubesurfaceview(EditProfileActivity.this, mBbitmap);
                layout_frame_main.addView(view);
            }
        }).execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
//            if (view != null) view.onResume();
        } catch (Exception e) {

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (view != null) view.onPause();
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        currentViewSelected = v;
        switch (v.getId()) {
            case R.id.edit_profile:

                break;
            case R.id.txt_change_pic:
//                mCameraUri = ImagePickerUtils.createTakePictureIntent(this, PIC_REQUEST);
                break;
            case R.id.avatar_face1:
                currentViewSelected = v;
                mCameraUri = ImagePickerUtils.createTakePictureIntent(this, PIC_REQUEST);
                break;
            case R.id.avatar_face2:
                mCameraUri = ImagePickerUtils.createTakePictureIntent(this, PIC_REQUEST);
                break;
            case R.id.avatar_face3:
                mCameraUri = ImagePickerUtils.createTakePictureIntent(this, PIC_REQUEST);
                break;
            case R.id.avatar_face4:
                mCameraUri = ImagePickerUtils.createTakePictureIntent(this, PIC_REQUEST);
                break;
            case R.id.avatar_face5:
                mCameraUri = ImagePickerUtils.createTakePictureIntent(this, PIC_REQUEST);
                break;
            case R.id.avatar_face6:
                mCameraUri = ImagePickerUtils.createTakePictureIntent(this, PIC_REQUEST);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri fileUri = ImagePickerUtils.getFileUriOfImage(this, data, mCameraUri);
            try {
                Bitmap bitmap = null;
                if (PIC_REQUEST == requestCode) {
                    bitmap = new BitmapUtils().getDownsampledBitmap(this, fileUri, GeneralValues.getScreenWidth(this), GeneralValues.getScreenWidth(this));
                    includeView((ImageView) currentViewSelected, fileUri, bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void includeView(ImageView currentViewSelected, Uri fileUri, Bitmap bitmap) {
//        final View tagView = getLayoutInflater().inflate(R.layout.img_view, null);
//        ImageView img = (ImageView) tagView.findViewById(R.id.img_view);
//        img.setTag(fileUri);
        if (bitmap != null) {
            currentViewSelected.setImageBitmap(bitmap);
            currentViewSelected.setTag(fileUri);
        }
//        currentViewSelected.addView(tagView);
    }

}
