package com.wdp.ActivityScreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wdp.Adapter.FavouritesListAdapter;
import com.wdp.Adapter.MyGridPostAdapter;
import com.wdp.Adapter.MyStoryGridAdapter;
import com.wdp.Adapter.TurntUserAdapter;
import com.wdp.ApiServices.FavouritesListApiService;
import com.wdp.ApiServices.MeStoryApiService;
import com.wdp.ApiServices.MyPostApiService;
import com.wdp.ApiServices.ProfileApiService;
import com.wdp.ApiServices.StoryApiService;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.CubeModal;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.FavouriteslistDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.ProfileDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;

public class MyStoriesActivity extends AppCompatActivity implements View.OnClickListener ,
        ApiResponseListner, FavouritesListAdapter.GetGlViews, MyStoryGridAdapter.GetTab1GlViews {

    boolean isFollowerFollowingOpen = false;
    LoginResDataModal loginResDataModal;
    MyPostDataModal myPostDataModal;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    ArrayList<FavouriteslistDataModal.DataBean.UsersBean> favList = new ArrayList<>();
    ArrayList<StoryDataModal.DataBean.UsersBean> turntList = new ArrayList<>();
    ArrayList<MeStoryDataModal.DataBean.StoriesBean> postsdatalist = new ArrayList<>();
    int page = 1;

    ImageView imgBack;
    TextView txt_edit_profile, txt_post_count, txt_following_count, txt_followr_count, txtTurntStories, usernamestories;
    RelativeLayout rl_cube, rl_txt_tab1;
    CubeGLSurfaceView glView;
    DragControl dragControl;
    RecyclerView recyclerViewFav, recylerTurntUsers, recyclerView_tab1;
    FavouritesListAdapter favouritesListAdapter;
    TurntUserAdapter turntUserAdapter;
    MyStoryGridAdapter myStoryGridAdapter;
    ImageView img_view;
    String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";


    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_mystories);

        init();
        Listeners();
        Prepare();
        connectProfile();
        connectFavourites();
        ConnectTurntstories();
        connectMeStory();
        //connectMyPost(page);
    }

    private void init(){
        imgBack = findViewById(R.id.imgBack);
        txt_edit_profile = findViewById(R.id.txt_edit_profile);
        rl_cube = findViewById(R.id.rl_cube);
        txt_post_count = findViewById(R.id.txt_post_count);
        txt_following_count = findViewById(R.id.txt_following_count);
        txt_followr_count = findViewById(R.id.txt_followr_count);
        recyclerViewFav = findViewById(R.id.recyclerViewFav);
        txtTurntStories = findViewById(R.id.txtTurntStories);
        recylerTurntUsers = findViewById(R.id.recylerTurntUsers);
        usernamestories = findViewById(R.id.usernamestories);
        recyclerView_tab1 = findViewById(R.id.recyclerView_tab1);
        rl_txt_tab1 = findViewById(R.id.rl_txt_tab1);
        img_view = findViewById(R.id.img_view);
    }

    private void Listeners(){
        imgBack.setOnClickListener(this);
        txt_edit_profile.setOnClickListener(this);
    }

    private void  Prepare(){

        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(MyStoriesActivity.this);
        usernamestories.setText(loginResDataModal.getData().getUser().getName()+"'S STORIES");

        LinearLayoutManager linearfav = new LinearLayoutManager(MyStoriesActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFav.setLayoutManager(linearfav);
        favouritesListAdapter = new FavouritesListAdapter(MyStoriesActivity.this, favList, this);
        recyclerViewFav.setAdapter(favouritesListAdapter);

        LinearLayoutManager linearfav1 = new LinearLayoutManager(MyStoriesActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recylerTurntUsers.setLayoutManager(linearfav1);
        turntUserAdapter = new TurntUserAdapter(MyStoriesActivity.this, turntList);
        recylerTurntUsers.setAdapter(turntUserAdapter);

        recyclerView_tab1.setLayoutManager(new GridLayoutManager(MyStoriesActivity.this, 3));
        myStoryGridAdapter = new MyStoryGridAdapter(MyStoriesActivity.this, postsdatalist, this);
        recyclerView_tab1.setAdapter(myStoryGridAdapter);
        recyclerView_tab1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (myPostDataModal != null) {
                            if (Integer.parseInt(myPostDataModal.getData().getPagination().getCurrent_page()) < Integer.parseInt(myPostDataModal.getData().getPagination().getTotal_pages())) {
                                loadMore();
                            }
                        }
                    }
                }
            }
        });
        MeStoryData();
    }
    private void MeStoryData() {
        if (loginResDataModal.getData().getUser().getAvatar()!= null){
            avatar1 =  loginResDataModal.getData().getUser().getAvatar().getOriginal();
            Glide.with(this)
                    .load(avatar1)
                    .placeholder(R.drawable.profile)
                    .into(img_view);
        }
        if (loginResDataModal.getData().getUser().getAvatar2()!= null){
            avatar2 = loginResDataModal.getData().getUser().getAvatar2().getOriginal();
        }
        if (loginResDataModal.getData().getUser().getAvatar3()!= null){
            avatar3 =  loginResDataModal.getData().getUser().getAvatar3().getOriginal();
        }
        if (loginResDataModal.getData().getUser().getAvatar4()!= null){
            avatar4 =  loginResDataModal.getData().getUser().getAvatar4().getOriginal();
        }
        if (loginResDataModal.getData().getUser().getAvatar5()!= null){
            avatar5 =  loginResDataModal.getData().getUser().getAvatar5().getOriginal();
        }
        if (loginResDataModal.getData().getUser().getAvatar6()!= null){
            avatar6 =  loginResDataModal.getData().getUser().getAvatar6().getOriginal();
        }



    }
    private void connectProfile() {
        if (NetworkCheck.isConnected(MyStoriesActivity.this)) {
            ProfileApiService profileApiService = new ProfileApiService(MyStoriesActivity.this);
            profileApiService.Connect(loginResDataModal.getData().getToken(), this);

        } else {
            Toast.makeText(MyStoriesActivity.this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectMeStory() {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            MeStoryApiService meStoryApiService = new MeStoryApiService(this);
            meStoryApiService.Connect(loginResDataModal.getData().getToken(), this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }
    private void connectFavourites() {
        if (NetworkCheck.isConnected(MyStoriesActivity.this)) {
            FavouritesListApiService favouritesApiService = new FavouritesListApiService(MyStoriesActivity.this);
            favouritesApiService.Connect(loginResDataModal.getData().getToken(), this);

        } else {
            Toast.makeText(MyStoriesActivity.this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void ConnectTurntstories() {
        if (NetworkCheck.isConnected(MyStoriesActivity.this)) {
            StoryApiService storyApiService = new StoryApiService(MyStoriesActivity.this);
            storyApiService.Connect(loginResDataModal.getData().getToken(), this);

        } else {
            Toast.makeText(MyStoriesActivity.this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectMyPost(int page) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            MyPostApiService myPostApiService = new MyPostApiService(this);
            myPostApiService.Connect(loginResDataModal.getData().getToken(), String.valueOf(page), this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadMore() {
        page++;
        connectMyPost(page);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.imgBack){
            finish();
        }else if (id==R.id.txt_edit_profile){
            isFollowerFollowingOpen = true;
            Intent i = new Intent(MyStoriesActivity.this, ProfileEditActivity.class);
            startActivity(i);
        }
    }


    private void MeStoryData(MeStoryDataModal meStoryDataModal) {
        postsdatalist.addAll(meStoryDataModal.getData().getStories());
        if (postsdatalist.size()<=0){
            rl_txt_tab1.setVisibility(View.VISIBLE);
            recyclerView_tab1.setVisibility(View.GONE);
        }
        myStoryGridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.profile_TAG)) {
            ProfileDataModal profileDataModal = (ProfileDataModal) superCastClass;
            txt_post_count.setText(profileDataModal.getData().getCurrent_user().getPost_count());
            txt_following_count.setText(profileDataModal.getData().getCurrent_user().getFollowing_count());
            txt_followr_count.setText(profileDataModal.getData().getCurrent_user().getFollower_count());
            String avatrar1="", avatrar2="", avatrar3="", avatrar4="", avatrar5="", avatrar6="";
            if (profileDataModal.getData().getCurrent_user().getAvatar()!= null){
                avatrar1 = profileDataModal.getData().getCurrent_user().getAvatar().getThumb();
            }
            if (profileDataModal.getData().getCurrent_user().getAvatar2()!= null){
                avatrar2 = profileDataModal.getData().getCurrent_user().getAvatar2().getThumb();
            }
            if (profileDataModal.getData().getCurrent_user().getAvatar3()!= null){
                avatrar3 = profileDataModal.getData().getCurrent_user().getAvatar3().getThumb();
            }
            if (profileDataModal.getData().getCurrent_user().getAvatar4()!= null){
                avatrar4 = profileDataModal.getData().getCurrent_user().getAvatar4().getThumb();
            }
            if (profileDataModal.getData().getCurrent_user().getAvatar5()!= null){
                avatrar5 = profileDataModal.getData().getCurrent_user().getAvatar5().getThumb();
            }
            if (profileDataModal.getData().getCurrent_user().getAvatar6()!= null){
                avatrar6 = profileDataModal.getData().getCurrent_user().getAvatar6().getThumb();
            }
            glView = new CubeGLSurfaceView(MyStoriesActivity.this,avatrar1, avatrar2,avatrar3, avatrar4, avatrar5,avatrar6);
            rl_cube.setBackgroundColor(getResources().getColor(R.color.colorOrangelite));
            rl_cube.addView(glView);
            dragControl = new DragControl(MyStoriesActivity.this,MyStoriesActivity.this,avatrar1, avatrar2,avatrar3, avatrar4, avatrar5,avatrar6);
            glView.setOnTouchListener(dragControl);
            glView.setDragControl(dragControl);

        }else if (Tag.equalsIgnoreCase(ApiConstants.favourites_TAG)){
            FavouriteslistDataModal favouritesDataModal = (FavouriteslistDataModal) superCastClass;
            favList.addAll(favouritesDataModal.getData().getUsers());
            favouritesListAdapter.notifyDataSetChanged();
        }else if (Tag.equalsIgnoreCase(ApiConstants.storty_TAG)){
            StoryDataModal storyDataModal = (StoryDataModal) superCastClass;
            turntList.addAll(storyDataModal.getData().getUsers());
            turntUserAdapter.notifyDataSetChanged();

        }
        else if (Tag.equalsIgnoreCase(ApiConstants.mestoryview_tag)){
            MeStoryDataModal meStoryDataModal = (MeStoryDataModal) superCastClass;
            MeStoryData(meStoryDataModal);
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onException(String message) {

    }

    @Override
    public void getglviews(CubeGLSurfaceView cubeGLSurfaceView) {

    }

    @Override
    public void gettab1glviews(CubeGLSurfaceView cubeGLSurfaceView) {

    }
}
