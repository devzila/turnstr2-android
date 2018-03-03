package com.adroidtech.turnstr2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroidtech.turnstr2.CubeView.Cubesurfaceview;
import com.adroidtech.turnstr2.CubeView.URLImageParser;
import com.adroidtech.turnstr2.Models.LoginDetailModel;
import com.adroidtech.turnstr2.Models.ViewUserDetailModel;
import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.BitmapUtils;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.ImagePickerUtils;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.Utils.Utils;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.OkHttpRequestSender;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class EditProfileActivity extends Activity implements View.OnClickListener, AsyncCallback {
    private static final int PIC_REQUEST = 1231;
    private static final int PIC_CROP = 1101;
    Cubesurfaceview view;
    private ArrayList<Bitmap> mBbitmap = new ArrayList<>();
    private FrameLayout layout_frame_main;
    private SharedPreference sharedPreference;
    private LoginDetailModel userDetail;
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
    private HashMap<String, Uri> updatedImage;
    private Uri fileUri;
    private String[] allImagesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        updatedImage = new HashMap<String, Uri>();
        sharedPreference = new SharedPreference(this);
        userDetail = sharedPreference.getSerializableObject(PreferenceKeys.USER_DETAIL, LoginDetailModel.class);
        layout_frame_main = (FrameLayout) findViewById(R.id.layout_frame1);
        txtChangePic = (TextView) findViewById(R.id.txt_change_pic);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        findViewById(R.id.txt_done).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                uploadImageToServer();
                Utils.hideKeyboard(EditProfileActivity.this, txtUserPhone);
            }
        });
        view = new Cubesurfaceview(com.adroidtech.turnstr2.Activity.EditProfileActivity.this, mBbitmap, false);
        layout_frame_main.addView(view);
//        viewIntail();
        uiDataUpdate(userDetail.getUser());
        loadAllImages();
        loadAllImagesToCube();
//        addAllImagesFromServer();
    }

    private void uiDataUpdate(ViewUserDetailModel userDetail) {
        txtName.setText(userDetail.getFirstName());
        txtUsername.setText(userDetail.getUsername());
        txtWebsite.setText(userDetail.getWebsite());
        txtInfo.setText(userDetail.getBio());
        txtUserEmail.setText(userDetail.getEmail());
        txtUserPhone.setText(userDetail.getPhone());
        txtUsername.setText(userDetail.getUsername());

    }

    private void loadAllImages() {
        try {
//            ImageLoader imageLoader = new ImageLoader(this);
//            imageLoader.DisplayImage(userDetail.getUser().getAvatarFace1(), avatarFace1);
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
        strings.push(userDetail.getUser().getAvatarFace1());
        strings.push(userDetail.getUser().getAvatarFace2());
        strings.push(userDetail.getUser().getAvatarFace3());
        strings.push(userDetail.getUser().getAvatarFace4());
        strings.push(userDetail.getUser().getAvatarFace5());
        strings.push(userDetail.getUser().getAvatarFace6());
        new URLImageParser(strings, new URLImageParser.AsyncCallback() {
            @Override
            public void getAsyncResult(ArrayList<Bitmap> bitmap, String txt) {
                mBbitmap = bitmap;
                layout_frame_main.removeAllViews();
                view = new Cubesurfaceview(com.adroidtech.turnstr2.Activity.EditProfileActivity.this, mBbitmap, false);
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
//            if (view != null) view.onPause();
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

        if (resultCode == Activity.RESULT_OK && requestCode == PIC_REQUEST) {
            fileUri = ImagePickerUtils.getFileUriOfImage(this, data, mCameraUri);
            ImagePickerUtils.performCrop(this, fileUri, PIC_CROP);
//            try {
//                Bitmap bitmap = null;
//                if (PIC_REQUEST == requestCode) {
//                    bitmap = new BitmapUtils().getDo`wnsampledBitmap(this, fileUri, GeneralValues.getScreenWidth(this), GeneralValues.getScreenWidth(this));
//                    includeView((ImageView) currentViewSelected, fileUri, bitmap);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == PIC_CROP) {
            try {
                if (data != null) {
                    // get the returned data
                    Bundle extras = data.getExtras();
                    // get the cropped bitmap
                    Bitmap selectedBitmap = extras.getParcelable("data");
                    BitmapUtils.RewriteBitmapToFile(selectedBitmap, fileUri);
//                    includeView((ImageView) currentViewSelected, fileUri, selectedBitmap);
                }
                Bitmap bitmap = new BitmapUtils().getDownsampledBitmap(this, fileUri, GeneralValues.getScreenWidth(this), 0);
                includeView((ImageView) currentViewSelected, fileUri, bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void includeView(ImageView currentViewSelected, Uri fileUri, Bitmap bitmap) {
        if (bitmap != null) {
            currentViewSelected.setImageBitmap(bitmap);
            currentViewSelected.setTag(fileUri);
            switch (currentViewSelected.getId()) {
                case R.id.avatar_face1:
                    updatedImage.put("avatar_face1", fileUri);
                    break;
                case R.id.avatar_face2:
                    updatedImage.put("avatar_face2", fileUri);
                    break;
                case R.id.avatar_face3:
                    updatedImage.put("avatar_face3", fileUri);
                    break;
                case R.id.avatar_face4:
                    updatedImage.put("avatar_face4", fileUri);
                    break;
                case R.id.avatar_face5:
                    updatedImage.put("avatar_face5", fileUri);
                    break;
                case R.id.avatar_face6:
                    updatedImage.put("avatar_face6", fileUri);
                    break;

            }
        }

//        currentViewSelected.addView(tagView);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void uploadImageToServer() {
        try {
            ArrayMap<String, String> formField = new ArrayMap<>();
            ArrayMap<String, File> filePart = new ArrayMap<>();
            formField.put("user[first_name]", (txtName.getText().toString()));
            formField.put("user[last_name]", (txtUsername.getText().toString()));
            formField.put("user[gender]", ("Female"));
            formField.put("user[website]", (txtWebsite.getText().toString()));
            formField.put("user[bio]", (txtInfo.getText().toString()));
            formField.put("user[address]", (txtName.getText().toString()));
            formField.put("user[city]", (txtName.getText().toString()));
            formField.put("user[state]", (txtName.getText().toString()));
            formField.put("user[info]", (txtInfo.getText().toString()));
            formField.put("user[phone]", (txtUserPhone.getText().toString()));
            formField.put("user[username]", (txtUsername.getText().toString()));
            allImagesName = updatedImage.keySet().toArray(new String[updatedImage.size()]);
            for (int i = 0; i < allImagesName.length; i++) {
                File selectedimageFile = new File(updatedImage.get(allImagesName[i]).getPath());
                filePart.put("user[" + allImagesName[i] + "]", selectedimageFile);
            }
            new OkHttpRequestSender(this, this, GeneralValues.BASE_URL + GeneralValues.EDIT_PROFILE, formField, filePart,
                    sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN), "PUT", true).execute();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void getAsyncResult(String json, String txt) {
        Log.e("Data", json.toString());
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("success") && jsonObject.getBoolean("success")) {
                LoginDetailModel userData = new Gson().fromJson(jsonObject.getString("data"), LoginDetailModel.class);
                sharedPreference.putSerializableObject(PreferenceKeys.USER_DETAIL, userData);
                finish();

            } else {
                Toast.makeText(com.adroidtech.turnstr2.Activity.EditProfileActivity.this, jsonObject.getString("error"), Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
