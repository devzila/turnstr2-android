package com.wdp.ActivityScreen;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.wdp.ApiServices.ForgotPasswordApiService;
import com.wdp.ApiServices.ProfileEditApiService;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.CubeModal;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.ForgotpasswordDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.ProfileEditDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.FileUtils;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.BuildConfig;
import com.wdp.turnstr.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileEditActivity extends Activity implements View.OnClickListener, ApiResponseListner {
    private static final int REQUEST_IMAGE = 12;
    int PERMISSION_ALL = 999;
    int RESULT_LOAD_IMAGE = 3;
    int CAMERA_REQUEST = 2;
    MultipartBody.Part part1 = null;
    MultipartBody.Part part2 = null;
    MultipartBody.Part part3 = null;
    MultipartBody.Part part4 = null;
    MultipartBody.Part part5 = null;
    MultipartBody.Part part6 = null;
    MultipartBody.Part background = null;
    String imageFilePath;
    ImageView img1, img2, img3, img4, img5, img6;
    ImageView imgview1,imgview2,imgview3,imgview4,imgview5,imgview6,img_bacground,img_back, img_profile_bg;
    Uri cameraImagePath;
    Uri imageFilePathUri;
    String isSelect="";
    EditText edt_name,edt_username,edt_web,edt_email,edt_phone_number,edt_address,edt_about;
    TextView txt_update;
    RelativeLayout rl_background;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    LoginResDataModal loginResDataModal;
    ArrayList<CubeModal> profileList = new ArrayList<>();
    CubeGLSurfaceView glView;
    DragControl dragControl;
    LinearLayout ll_cube;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();

        clickListner();

    }

    private void initView() {
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        imgview1 = findViewById(R.id.imgview1);
        imgview2 = findViewById(R.id.imgview2);
        imgview3 = findViewById(R.id.imgview3);
        imgview4 = findViewById(R.id.imgview4);
        imgview5 = findViewById(R.id.imgview5);
        imgview6 = findViewById(R.id.imgview6);
        edt_name = findViewById(R.id.edt_name);
        edt_username = findViewById(R.id.edt_username);
        edt_web = findViewById(R.id.edt_web);
        edt_email = findViewById(R.id.edt_email);
        edt_phone_number = findViewById(R.id.edt_phone_number);
        edt_address = findViewById(R.id.edt_address);
        txt_update = findViewById(R.id.txt_update);
        rl_background = findViewById(R.id.rl_background);
        img_bacground = findViewById(R.id.img_bacground);
        img_back = findViewById(R.id.img_back);
        img_profile_bg= findViewById(R.id.img_profile_bg);
        edt_about = findViewById(R.id.edt_about);
        ll_cube = findViewById(R.id.ll_cube);
    }

    private void prepair(){
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        edt_name.setText(loginResDataModal.getData().getUser().getName());
        edt_username.setText(loginResDataModal.getData().getUser().getUsername());
        edt_web.setText(loginResDataModal.getData().getUser().getWebsite());
        edt_phone_number.setText(loginResDataModal.getData().getUser().getPhone());
        edt_about.setText(loginResDataModal.getData().getUser().getBio());



        MyProfileCube();


    }

    private void setData(){
        if (loginResDataModal.getData().getUser().getAvatar()!= null){
            Glide.with(this).load(loginResDataModal.getData().getUser().getAvatar().getThumb()).into(imgview1);
        }
        if (loginResDataModal.getData().getUser().getAvatar2()!= null){
            Glide.with(this).load(loginResDataModal.getData().getUser().getAvatar2().getThumb()).into(imgview2);
        }
        if (loginResDataModal.getData().getUser().getAvatar3()!= null){
            Glide.with(this).load(loginResDataModal.getData().getUser().getAvatar3().getThumb()).into(imgview3);
        }
        if (loginResDataModal.getData().getUser().getAvatar4()!= null){
            Glide.with(this).load(loginResDataModal.getData().getUser().getAvatar4().getThumb()).into(imgview4);
        }
        if (loginResDataModal.getData().getUser().getAvatar5()!= null){
            Glide.with(this).load(loginResDataModal.getData().getUser().getAvatar5().getThumb()).into(imgview5);
        }
        if (loginResDataModal.getData().getUser().getAvatar6()!= null){
            Glide.with(this).load(loginResDataModal.getData().getUser().getAvatar6().getThumb()).into(imgview6);
        }
        if (loginResDataModal.getData().getUser().getBackground()!=null){
            Glide.with(this).load(loginResDataModal.getData().getUser().getBackground().getThumb()).into(img_profile_bg);
        }
    }


    private void clickListner() {
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        txt_update.setOnClickListener(this);
        rl_background.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    private void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        } else {
            chooseGalleryDialog();
        }
    }

    private void requestPermission() {
        String[] PERMISSIONS = {android.Manifest.permission.INTERNET, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_NETWORK_STATE, android.Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.CAMERA};
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == RESULT_LOAD_IMAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseGalleryDialog();

            }
        } else if (requestCode == CAMERA_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseGalleryDialog();
            }
        }
    }



    private void takePhotoFromCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ContentValues values = new ContentValues(3);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
        cameraImagePath = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImagePath);
        startActivityForResult(takePictureIntent, CAMERA_REQUEST);

    }
    private void connectEditProfile() {
        if (NetworkCheck.isConnected(this)) {
            String web, about, number;
            if (edt_web.getText().length()==0) {
                web = "";
            }else {
                web = edt_web.getText().toString();
            }

            if (edt_about.getText().length()==0) {
                about = "";
            }else {
                about = edt_about.getText().toString();
            }

            if (edt_phone_number.getText().length()==0){
                number = "";
            }else {
                number = edt_phone_number.getText().toString();
            }
            ProfileEditApiService profileEditApiService = new ProfileEditApiService(this);
            RequestBody reques_name = RequestBody.create(MediaType.parse("multipart/form-data"), edt_name.getText().toString());
            RequestBody reques_username = RequestBody.create(MediaType.parse("multipart/form-data"), edt_username.getText().toString());
            RequestBody reques_bio = RequestBody.create(MediaType.parse("multipart/form-data"), about);
            RequestBody reques_phone = RequestBody.create(MediaType.parse("multipart/form-data"), number);
            RequestBody reques_website = RequestBody.create(MediaType.parse("multipart/form-data"), web);
            profileEditApiService.Connect1(reques_name,reques_username, reques_bio,reques_phone,reques_website,loginResDataModal.getData().getToken(),this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }


    private void connectEditProfile1() {
        if (NetworkCheck.isConnected(this)) {
            String web, about, number;
            if (edt_web.getText().length()==0) {
                web = "";
            }else {
                web = edt_web.getText().toString();
            }

            if (edt_about.getText().length()==0) {
                about = "";
            }else {
                about = edt_about.getText().toString();
            }

            if (edt_phone_number.getText().length()==0){
                number = "";
            }else {
                number = edt_phone_number.getText().toString();
            }


            ProfileEditApiService profileEditApiService = new ProfileEditApiService(this);
            RequestBody reques_name = RequestBody.create(MediaType.parse("multipart/form-data"), edt_name.getText().toString());
            RequestBody reques_username = RequestBody.create(MediaType.parse("multipart/form-data"), edt_username.getText().toString());
            RequestBody reques_bio = RequestBody.create(MediaType.parse("multipart/form-data"), about);
            RequestBody reques_phone = RequestBody.create(MediaType.parse("multipart/form-data"), number);
            RequestBody reques_website = RequestBody.create(MediaType.parse("multipart/form-data"), web);
            profileEditApiService.Connect(reques_name,reques_username, reques_bio,reques_phone,reques_website,part1,part2,part3,part4,part5,part6,background,loginResDataModal.getData().getToken(),this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectEditProfile2() {
        if (NetworkCheck.isConnected(this)) {
            String web, about, number;
            if (edt_web.getText().length()==0) {
                web = "";
            }else {
                web = edt_web.getText().toString();
            }

            if (edt_about.getText().length()==0) {
                about = "";
            }else {
                about = edt_about.getText().toString();
            }

            if (edt_phone_number.getText().length()==0){
                number = "";
            }else {
                number = edt_phone_number.getText().toString();
            }


            ProfileEditApiService profileEditApiService = new ProfileEditApiService(this);
            RequestBody reques_name = RequestBody.create(MediaType.parse("multipart/form-data"), edt_name.getText().toString());
            RequestBody reques_username = RequestBody.create(MediaType.parse("multipart/form-data"), edt_username.getText().toString());
            RequestBody reques_bio = RequestBody.create(MediaType.parse("multipart/form-data"), about);
            RequestBody reques_phone = RequestBody.create(MediaType.parse("multipart/form-data"), number);
            RequestBody reques_website = RequestBody.create(MediaType.parse("multipart/form-data"), web);
            profileEditApiService.Connect2(reques_name,reques_username, reques_bio,reques_phone,reques_website,background,loginResDataModal.getData().getToken(),this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    if (data != null) {
                        final Uri uri = data.getData();
                        imageFilePathUri =  uri;
                        getMimeType(this, uri);
                        String selectedPath = FileUtils.getPath(this, imageFilePathUri);
                        if (isSelect.equalsIgnoreCase("1")){
                            imgview1.setImageURI(imageFilePathUri);
                            part1 = prepareFilePart("user[avatar]",  selectedPath);
                        }
                        else  if (isSelect.equalsIgnoreCase("2")){
                            imgview2.setImageURI(imageFilePathUri);
                            part2 = prepareFilePart("user[avatar2]",  selectedPath);
                        }
                        else  if (isSelect.equalsIgnoreCase("3")){
                            imgview3.setImageURI(imageFilePathUri);
                            part3 = prepareFilePart("user[avatar3]",  selectedPath);
                        }
                        else  if (isSelect.equalsIgnoreCase("4")){
                            imgview4.setImageURI(imageFilePathUri);
                            part4 = prepareFilePart("user[avatar4]",  selectedPath);
                        }
                        else  if (isSelect.equalsIgnoreCase("5")){
                            imgview5.setImageURI(imageFilePathUri);
                            part5 = prepareFilePart("user[avatar5]",  selectedPath);
                        }
                        else  if (isSelect.equalsIgnoreCase("6")){
                            imgview6.setImageURI(imageFilePathUri);
                            part6 = prepareFilePart("user[avatar6]",  selectedPath);
                        } else  if (isSelect.equalsIgnoreCase("background")){
                            img_bacground.setImageURI(imageFilePathUri);
                            background = prepareFilePartBackground("user[background]",  selectedPath);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                try {
                    if (cameraImagePath != null) {
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor imageCursor = getContentResolver().query(cameraImagePath, filePathColumn, null, null, null);

                        if (imageCursor != null && imageCursor.moveToFirst()) {
                            int columnIndex = imageCursor.getColumnIndex(filePathColumn[0]);
                            String filePath = imageCursor.getString(columnIndex);
                            imageCursor.close();
                            imageFilePathUri = filePath != null ? Uri.parse(filePath) : null;
                            String selectedPath = FileUtils.getPath(this, imageFilePathUri);
                            if (isSelect.equalsIgnoreCase("1")){
                                imgview1.setImageURI(imageFilePathUri);
                                part1 = prepareFilePart(ApiConstants.FILE,  selectedPath);
                            }
                            else if (isSelect.equalsIgnoreCase("2")){
                                imgview2.setImageURI(imageFilePathUri);
                                part2 = prepareFilePart(ApiConstants.FILE,  selectedPath);
                            }
                            else if (isSelect.equalsIgnoreCase("3")){
                                imgview3.setImageURI(imageFilePathUri);
                                part3 = prepareFilePart(ApiConstants.FILE,  selectedPath);
                            }
                            else if (isSelect.equalsIgnoreCase("4")){
                                imgview4.setImageURI(imageFilePathUri);
                                part4 = prepareFilePart(ApiConstants.FILE,  selectedPath);
                            }
                            else if (isSelect.equalsIgnoreCase("5")){
                                imgview5.setImageURI(imageFilePathUri);
                                part5 = prepareFilePart(ApiConstants.FILE,  selectedPath);
                            }
                            else if (isSelect.equalsIgnoreCase("6")){
                                imgview6.setImageURI(imageFilePathUri);
                                part6 = prepareFilePart(ApiConstants.FILE,  selectedPath);
                            }
                            else  if (isSelect.equalsIgnoreCase("background")){
                                img_bacground.setImageURI(imageFilePathUri);
                                background = prepareFilePartBackground("user[background]",  selectedPath);
                            }

                        }
                    }




                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private void MyProfileCube() {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (loginResDataModal.getData().getUser().getAvatar() != null){
            profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar().getThumb(), R.drawable.thumnail));

        }
        else {
            profileList.add(new CubeModal("", R.drawable.a));
        }

        if (loginResDataModal.getData().getUser().getAvatar2() != null){
            profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar2().getThumb(), R.drawable.thumnail));
        }
        else {
            profileList.add(new CubeModal("", R.drawable.a));
        }

        if (loginResDataModal.getData().getUser().getAvatar3() != null){
            profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar3().getThumb(), R.drawable.thumnail));

        }
        else {
            profileList.add(new CubeModal("", R.drawable.a));
        }

        if (loginResDataModal.getData().getUser().getAvatar4() != null){
            profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar4().getThumb(), R.drawable.thumnail));

        }
        else {
            profileList.add(new CubeModal("", R.drawable.a));
        }

        if (loginResDataModal.getData().getUser().getAvatar5() != null){
            profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar5().getThumb(), R.drawable.thumnail));

        }
        else {
            profileList.add(new CubeModal("", R.drawable.a));
        }

        if (loginResDataModal.getData().getUser().getAvatar6() != null){
            profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar6().getThumb(), R.drawable.thumnail));
        }
        else {
            profileList.add(new CubeModal("", R.drawable.a));
        }

        glView = new CubeGLSurfaceView(this,"","",profileList);
        ll_cube.setBackgroundColor(this.getResources().getColor(R.color.white));
        ll_cube.addView(glView );
        dragControl = new DragControl(this,profileList,this);
        glView.setOnTouchListener(dragControl);
        glView.setDragControl(dragControl);
        glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        glView.setZOrderOnTop(true);
        glView.setZOrderMediaOverlay(true);


    }
    public static String getMimeType(Context context, Uri uri) {

        String extension = "";
        try {
            if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
                final MimeTypeMap mime = MimeTypeMap.getSingleton();
                extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
            } else {
                extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());
            }
        } catch (Exception e) {
            extension = "";
            e.printStackTrace();
        }

        return extension;
    }
    private MultipartBody.Part prepareFilePart(String partName, String fileUri) {
        File file = new File(fileUri);
        Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }  private MultipartBody.Part prepareFilePartBackground(String partName, String fileUri) {
        File file = new File(fileUri);
        Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public void chooseGalleryDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_attachment);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCancelable(true);
        LinearLayout ll_photo = dialog.findViewById(R.id.ll_photo);
        LinearLayout ll_camera = dialog.findViewById(R.id.ll_camera);


        ll_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
                dialog.dismiss();

            }
        });

        ll_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoFromCamera();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View view) {
        if (img1 == view) {
            isSelect ="1";
            getPermission();
        } else if (img2 == view) {
            isSelect ="2";
            getPermission();
        } else if (img3 == view) {
            isSelect ="3";
            getPermission();
        } else if (img4 == view) {
            isSelect ="4";
            getPermission();
        } else if (img5 == view) {
            isSelect ="5";
            getPermission();
        } else if (img6 == view) {
            isSelect ="6";
            getPermission();
        }
        else if (rl_background == view){
            isSelect="background";
            getPermission();
        } else if (img_back == view){
            finish();
        }
        else if (txt_update ==view){
            if (TextUtils.isEmpty(edt_name.getText().toString())){
                Toast.makeText(this,getResources().getString(R.string.please_enter_name),Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(edt_username.getText().toString())){
                Toast.makeText(this,getResources().getString(R.string.please_enter_user_name),Toast.LENGTH_LONG).show();
            }
            /*else if (TextUtils.isEmpty(edt_web.getText().toString())){
                Toast.makeText(this,getResources().getString(R.string.please_enter_website),Toast.LENGTH_LONG).show();
            }else if (TextUtils.isEmpty(edt_about.getText().toString())){
                Toast.makeText(this,getResources().getString(R.string.please_enter_about_us),Toast.LENGTH_LONG).show();
            }else if (TextUtils.isEmpty(edt_phone_number.getText().toString())){
                Toast.makeText(this,getResources().getString(R.string.please_enter_number),Toast.LENGTH_LONG).show();
            }*/
            else {
                if (background!=null && part1 ==null && part2 == null && part3 == null && part4 == null && part5 == null && part6 == null){
                    connectEditProfile2();
                }else if (part1 ==null && part2 == null && part3 == null && part4 == null && part5 == null && part6 == null && background == null){
                    connectEditProfile();
                }else {
                    connectEditProfile1();
                }

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepair();
        setData();
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.profileedit_TAG)){
            ProfileEditDataModal profileEditDataModal= (ProfileEditDataModal) superCastClass;

            loginResDataModal.getData().getUser().setUsername(profileEditDataModal.getData().getProfile().getUsername());
            loginResDataModal.getData().getUser().setName(profileEditDataModal.getData().getProfile().getName());
            loginResDataModal.getData().getUser().setWebsite(profileEditDataModal.getData().getProfile().getWebsite());
            loginResDataModal.getData().getUser().setBio(profileEditDataModal.getData().getProfile().getBio());
            loginResDataModal.getData().getUser().setPhone(profileEditDataModal.getData().getProfile().getBio());



            if (profileEditDataModal.getData().getProfile().getAvatar() != null){
                loginResDataModal.getData().getUser().setAvatar(new LoginResDataModal.DataBean.UserBean.AvatarBean("null","null","null"));
                loginResDataModal.getData().getUser().getAvatar().setThumb(profileEditDataModal.getData().getProfile().getAvatar().getThumb());
            }
            if (profileEditDataModal.getData().getProfile().getAvatar2() != null){
                loginResDataModal.getData().getUser().setAvatar2(new LoginResDataModal.DataBean.UserBean.Avatar2Bean("null","null","null"));
                loginResDataModal.getData().getUser().getAvatar2().setThumb(profileEditDataModal.getData().getProfile().getAvatar2().getThumb());
            }
            if (profileEditDataModal.getData().getProfile().getAvatar3() != null){
                loginResDataModal.getData().getUser().setAvatar3(new LoginResDataModal.DataBean.UserBean.Avatar3Bean("null","null","null"));
                loginResDataModal.getData().getUser().getAvatar3().setThumb(profileEditDataModal.getData().getProfile().getAvatar3().getThumb());
            }
            if (profileEditDataModal.getData().getProfile().getAvatar4() != null){
                loginResDataModal.getData().getUser().setAvatar4(new LoginResDataModal.DataBean.UserBean.Avatar4Bean("null","null","null"));
                loginResDataModal.getData().getUser().getAvatar4().setThumb(profileEditDataModal.getData().getProfile().getAvatar4().getThumb());
            }
            if (profileEditDataModal.getData().getProfile().getAvatar5() != null){

                loginResDataModal.getData().getUser().setAvatar5(new LoginResDataModal.DataBean.UserBean.Avatar5Bean("null","null","null"));

                loginResDataModal.getData().getUser().getAvatar5().setThumb(profileEditDataModal.getData().getProfile().getAvatar5().getThumb());
            }
            if (profileEditDataModal.getData().getProfile().getAvatar6() != null){
                loginResDataModal.getData().getUser().setAvatar6(new LoginResDataModal.DataBean.UserBean.Avatar6Bean("null","null","null"));

                loginResDataModal.getData().getUser().getAvatar6().setThumb(profileEditDataModal.getData().getProfile().getAvatar6().getThumb());
            }
            if (profileEditDataModal.getData().getProfile().getBackground() != null){
                loginResDataModal.getData().getUser().setBackground(new LoginResDataModal.DataBean.UserBean.BackgroundBean("null","null","null"));
                loginResDataModal.getData().getUser().getBackground().setThumb(profileEditDataModal.getData().getProfile().getBackground().getThumb());
            }


            commonSharedPreference.setSubscriberLoginSharedPref(ProfileEditActivity.this, loginResDataModal);
            onBackPressed();
            MainActivity.h.sendEmptyMessage(0);
        }

    }



    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
