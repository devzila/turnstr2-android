package com.wdp.ActivityScreen;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.wdp.Adapter.FavouritesListAdapter;
import com.wdp.Adapter.MyGridPostAdapter;
import com.wdp.Adapter.MyPostAdapter;
import com.wdp.ApiServices.FavouriteFiveApiService;
import com.wdp.ApiServices.FavouritesListApiService;
import com.wdp.ApiServices.MyPostApiService;
import com.wdp.ApiServices.OtherProfileApiService;
import com.wdp.ApiServices.PostsLIkesApiService;
import com.wdp.ApiServices.ProfileApiService;
import com.wdp.ApiServices.TurnUnTurentApiService;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommanDataModal;
import com.wdp.Modal.FavouriteslistDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.OtherProfileDataModal;
import com.wdp.Modal.PostLikesDataModal;
import com.wdp.Modal.ProfileDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

public class OtherProfileActivity extends AppCompatActivity implements View.OnClickListener, ApiResponseListner, MyPostAdapter.OnclickIteam, MyGridPostAdapter.GetTab1GlViews,
        MyPostAdapter.GetTab2GlViews, FavouritesListAdapter.GetGlViews {
    LinearLayout ll_tab1, ll_tab2, ll_tab1_view, ll_tab2_view;
    RelativeLayout rl_txt_tab1, rl_txt_tab2;
    TextView txt_turnt, txt_fave5,txt_about,txt_email;
    ImageView img_tab1, img_tab2, img_profile_bg;
    RecyclerView recyclerView_tab1, recyclerView_tab2;
    View view_tab1, view_tab2;
    ImageView img_back;
    LinearLayout ll_followrs, ll_following;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    TextView txt_post_count, txt_followr_count, txt_following_count, txt_username, txt_name;
    MyPostDataModal myPostDataModal;
    ArrayList<MyPostDataModal.DataBean.PostsBean> postsdatalist = new ArrayList<>();
    MyPostAdapter myPostAdapter;
    MyGridPostAdapter myGridPostAdapter;
    FavouritesListAdapter favouritesListAdapter;
    List<CubeGLSurfaceView> favCubeList = new ArrayList<>();
    List<CubeGLSurfaceView> cubeTab1List = new ArrayList<>();
    List<CubeGLSurfaceView> cubeTab2List = new ArrayList<>();
    int page = 1;
    ArrayList<FavouriteslistDataModal.DataBean.UsersBean> favList = new ArrayList<>();
    RecyclerView recyclerViewFav;
    String user_id;
    CubeGLSurfaceView glView;
    DragControl dragControl;
    RelativeLayout rl_cube;
    boolean checker =false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        initView();
        clickLister();
        prepair();

    }

    private void prepair() {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        user_id = getIntent().getStringExtra("user_id");
        if (user_id.equalsIgnoreCase(loginResDataModal.getData().getUser().getId())){
            txt_fave5.setVisibility(View.GONE);
            txt_turnt.setVisibility(View.GONE);
        }
        else {
            txt_fave5.setVisibility(View.VISIBLE);
            txt_turnt.setVisibility(View.VISIBLE);
        }
        recyclerView_tab1.setLayoutManager(new GridLayoutManager(this, 3));
        myGridPostAdapter = new MyGridPostAdapter(this, postsdatalist, this);
        recyclerView_tab1.setAdapter(myGridPostAdapter);
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

        LinearLayoutManager lineargrid = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_tab2.setLayoutManager(lineargrid);
        myPostAdapter = new MyPostAdapter(this, postsdatalist, "otherprofile",this, this);
        recyclerView_tab2.setAdapter(myPostAdapter);

        LinearLayoutManager linearfav = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFav.setLayoutManager(linearfav);
        favouritesListAdapter = new FavouritesListAdapter(this, favList, this);
        recyclerViewFav.setAdapter(favouritesListAdapter);




        connectProfile(user_id);
        connectFavourites(user_id);
        connectMyPost(page, user_id);
    }

    private void loadMore() {
        page++;
        connectMyPost(page, user_id);
    }

    private void connectProfile(String user_id) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            OtherProfileApiService otherProfileApiService = new OtherProfileApiService(this);
            otherProfileApiService.Connect(loginResDataModal.getData().getToken(), user_id, this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectUnTurn(String url, boolean flag) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            TurnUnTurentApiService turnUnTurentApiService = new TurnUnTurentApiService(this);
            if (flag) {
                turnUnTurentApiService.Connect1(loginResDataModal.getData().getToken(), url, this);
            } else {
                turnUnTurentApiService.Connect(loginResDataModal.getData().getToken(), url, this);
            }


        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectFavourites(String user_id) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            FavouritesListApiService favouritesListApiService = new FavouritesListApiService(this);
            favouritesListApiService.Connect1(loginResDataModal.getData().getToken(), user_id, this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }
   private void connectFavouritesFive(String url,boolean flag) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            FavouriteFiveApiService favouriteFiveApiService = new FavouriteFiveApiService(this);
            if (flag){
                favouriteFiveApiService.Connect(loginResDataModal.getData().getToken(), url, this);
            }
            else {
                favouriteFiveApiService.Connect1(loginResDataModal.getData().getToken(), url, this);
            }


        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }



    private void connectMyPost(int page, String user_id) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            MyPostApiService myPostApiService = new MyPostApiService(this);
            myPostApiService.Connect1(loginResDataModal.getData().getToken(), user_id, String.valueOf(page), this);
        } else {
            Toast.makeText(this, loginResDataModal.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void connectPostsLikes(String postid) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            PostsLIkesApiService postsLIkesApiService = new PostsLIkesApiService(this);
            postsLIkesApiService.Connect(loginResDataModal.getData().getToken(), postid, this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectPostsDisLikes(String postid) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        if (NetworkCheck.isConnected(this)) {
            PostsLIkesApiService postsLIkesApiService = new PostsLIkesApiService(this);
            postsLIkesApiService.Connect1(loginResDataModal.getData().getToken(), postid, this);

        } else {
            Toast.makeText(this, getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        ll_tab1 = findViewById(R.id.ll_tab1);
        ll_tab2 = findViewById(R.id.ll_tab2);
        rl_txt_tab1 = findViewById(R.id.rl_txt_tab1);
        rl_txt_tab2 = findViewById(R.id.rl_txt_tab2);
        view_tab1 = findViewById(R.id.view_tab1);
        view_tab2 = findViewById(R.id.view_tab2);
        img_tab1 = findViewById(R.id.img_tab1);
        img_tab2 = findViewById(R.id.img_tab2);
        img_profile_bg = findViewById(R.id.img_profile_bg);
        recyclerView_tab1 = findViewById(R.id.recyclerView_tab1);
        recyclerView_tab2 = findViewById(R.id.recyclerView_tab2);
        ll_tab1_view = findViewById(R.id.ll_tab1_view);
        ll_tab2_view = findViewById(R.id.ll_tab2_view);
        ll_followrs = findViewById(R.id.ll_followrs);
        ll_following = findViewById(R.id.ll_following);
        txt_post_count = findViewById(R.id.txt_post_count);
        txt_followr_count = findViewById(R.id.txt_followr_count);
        txt_following_count = findViewById(R.id.txt_following_count);
        txt_username = findViewById(R.id.txt_username);
        txt_name = findViewById(R.id.txt_name);
        txt_fave5 = findViewById(R.id.txt_fave5);
        txt_turnt = findViewById(R.id.txt_turnt);
        recyclerViewFav = findViewById(R.id.recyclerViewFav);
        img_back = findViewById(R.id.img_back);
        rl_cube = findViewById(R.id.rl_cube);
        txt_about = findViewById(R.id.txt_about);
        txt_email = findViewById(R.id.txt_email);
    }

    private void clickLister() {
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_followrs.setOnClickListener(this);
        ll_following.setOnClickListener(this);
        txt_turnt.setOnClickListener(this);
        txt_fave5.setOnClickListener(this);
        recyclerViewFav.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (ll_tab1 == view) {
            tab1Selected(postsdatalist.size());
        } else if (ll_tab2 == view) {
            tab2Selected(postsdatalist.size());
        } else if (ll_following == view) {
            Intent i = new Intent(this, FollowFollowingActivity.class);
            i.putExtra("type", "following");
            i.putExtra("whichScreen", "otherprofile");
            i.putExtra("userid", user_id);
            startActivity(i);
        } else if (ll_followrs == view) {
            Intent i = new Intent(this, FollowFollowingActivity.class);
            i.putExtra("type", "follower");
            i.putExtra("whichScreen", "otherprofile");
            i.putExtra("userid",user_id);
            startActivity(i);
        } else if (txt_turnt == view) {
            String url = ApiConstants.Me_Favourites.replace("id", user_id);
            if (txt_turnt.getText().toString().equalsIgnoreCase(getResources().getString(R.string.Turnt))) {
                connectUnTurn(url, true);
            } else {
                connectUnTurn(url, false);
            }
        } else if (img_back == view) {
            finish();
        } else if (txt_fave5 == view) {
            String url = ApiConstants.Favourites_Five + user_id;
            connectFavouritesFive(url,true);
        }


    }

    public void logout() {

    }

    public void facebookLogOut() {
        LoginManager.getInstance().logOut();
    }

    private void tab1Selected(int size) {
        img_tab1.setColorFilter(ContextCompat.getColor(this, R.color.black));
        img_tab2.setColorFilter(ContextCompat.getColor(this, R.color.ColorGray808080));
        view_tab1.setBackgroundColor(getResources().getColor(R.color.black));
        view_tab2.setBackgroundColor(getResources().getColor(R.color.ColorGray808080));
        view_tab1.setVisibility(View.VISIBLE);
        view_tab2.setVisibility(View.GONE);
        if (size > 0) {
            recyclerView_tab1.setVisibility(View.VISIBLE);
            rl_txt_tab1.setVisibility(View.GONE);
        } else {
            recyclerView_tab1.setVisibility(View.GONE);
            rl_txt_tab1.setVisibility(View.VISIBLE);
        }
        rl_txt_tab2.setVisibility(View.GONE);
        recyclerView_tab2.setVisibility(View.GONE);
        ll_tab1_view.setVisibility(View.VISIBLE);
        ll_tab2_view.setVisibility(View.GONE);
        myGridPostAdapter.notifyDataSetChanged();
        myPostAdapter.notifyDataSetChanged();

    }

    private void tab2Selected(int size) {
        img_tab1.setColorFilter(ContextCompat.getColor(this, R.color.ColorGray808080));
        img_tab2.setColorFilter(ContextCompat.getColor(this, R.color.black));
        view_tab1.setBackgroundColor(getResources().getColor(R.color.ColorGray808080));
        view_tab2.setBackgroundColor(getResources().getColor(R.color.black));
        view_tab1.setVisibility(View.GONE);
        view_tab2.setVisibility(View.VISIBLE);
        if (size > 0) {
            recyclerView_tab2.setVisibility(View.VISIBLE);
            rl_txt_tab2.setVisibility(View.GONE);
        } else {
            recyclerView_tab2.setVisibility(View.GONE);
            rl_txt_tab2.setVisibility(View.VISIBLE);
        }
        rl_txt_tab1.setVisibility(View.GONE);
        recyclerView_tab1.setVisibility(View.GONE);
        ll_tab1_view.setVisibility(View.GONE);
        ll_tab2_view.setVisibility(View.VISIBLE);
        myPostAdapter.notifyDataSetChanged();
        myGridPostAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.other_profile_TAG)) {
            OtherProfileDataModal otherProfileDataModal = (OtherProfileDataModal) superCastClass;
            txt_post_count.setText(otherProfileDataModal.getData().getMember().getPost_count());
            txt_following_count.setText(otherProfileDataModal.getData().getMember().getFollowing_count());
            txt_followr_count.setText(otherProfileDataModal.getData().getMember().getFollower_count());
            txt_username.setText(otherProfileDataModal.getData().getMember().getUsername());
            txt_name.setText(otherProfileDataModal.getData().getMember().getName());
            txt_email.setText(otherProfileDataModal.getData().getMember().getWebsite());
            txt_about.setText(otherProfileDataModal.getData().getMember().getBio());

            String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";
            if (otherProfileDataModal.getData().getMember().getAvatar()!= null){
                avatar1 =  otherProfileDataModal.getData().getMember().getAvatar().getThumb();
            }
            if (otherProfileDataModal.getData().getMember().getAvatar2()!= null){
                avatar2 =  otherProfileDataModal.getData().getMember().getAvatar2().getThumb();
            }
            if (otherProfileDataModal.getData().getMember().getAvatar3()!= null){
                avatar3 =  otherProfileDataModal.getData().getMember().getAvatar3().getThumb();
            }
            if (otherProfileDataModal.getData().getMember().getAvatar4()!= null){
                avatar4 =  otherProfileDataModal.getData().getMember().getAvatar4().getThumb();
            }
            if (otherProfileDataModal.getData().getMember().getAvatar5()!= null){
                avatar5 =  otherProfileDataModal.getData().getMember().getAvatar5().getThumb();
            }
            if (otherProfileDataModal.getData().getMember().getAvatar6()!= null){
                avatar6 =  otherProfileDataModal.getData().getMember().getAvatar6().getThumb();
            }

            if (otherProfileDataModal.getData().getMember().getBackground()!=null){
                Glide.with(this).load(otherProfileDataModal.getData().getMember().getBackground().getThumb()).into(img_profile_bg);
                /*final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
                img_profile_bg.startAnimation(zoomAnimation);*/
            }

            glView = new CubeGLSurfaceView(this,avatar1,avatar2,avatar3,avatar4,avatar5,avatar6);
            rl_cube.setBackgroundColor(getResources().getColor(R.color.colorOrangelite));
            rl_cube.addView(glView);
            dragControl = new DragControl(this,this,avatar1,
                    avatar2,avatar3,
                    avatar4,
                    avatar5,avatar6);
            glView.setOnTouchListener(dragControl);
            glView.setDragControl(dragControl);
            checker = true;

            if (otherProfileDataModal.getData().getMember().isFollowed_by_current_user().equalsIgnoreCase("true")) {
                if (otherProfileDataModal.getData().getMember().isFamily().equalsIgnoreCase("true")) {
                    txt_turnt.setText(getResources().getString(R.string.family));
                } else {
                    txt_turnt.setText(getResources().getString(R.string.unTurnt));
                }

            } else {
                txt_turnt.setText(getResources().getString(R.string.Turnt));
            }
        } else if (Tag.equalsIgnoreCase(ApiConstants.my_post_TAG)) {
            myPostDataModal = (MyPostDataModal) superCastClass;
            postsdatalist.addAll(myPostDataModal.getData().getPosts());
            tab1Selected(postsdatalist.size());
        } else if (Tag.equalsIgnoreCase(ApiConstants.posts_likes_tag)) {
            PostLikesDataModal postLikesDataModal = (PostLikesDataModal) superCastClass;
        } else if (Tag.equalsIgnoreCase(ApiConstants.favourites_TAG)) {
            FavouriteslistDataModal favouritesDataModal = (FavouriteslistDataModal) superCastClass;
            favList.addAll(favouritesDataModal.getData().getUsers());
            favouritesListAdapter.notifyDataSetChanged();
        } else if (Tag.equalsIgnoreCase(ApiConstants.turn_unturn_TAG)) {
            CommanDataModal commanDataModal = (CommanDataModal) superCastClass;
            connectProfile(user_id);
        }
      else if (Tag.equalsIgnoreCase(ApiConstants.favfive_TAG)) {
            CommanDataModal commanDataModal = (CommanDataModal) superCastClass;
            if (commanDataModal.getMessage().equalsIgnoreCase("Already favourite")){
                String url = ApiConstants.Favourites_Five + user_id;
                connectFavouritesFive(url,false);
            }

        }
    }


    @Override
    public void onFailure(String message) {
       // Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onclickiteam(String type, String postid,int postion) {
        if (type.equalsIgnoreCase("like")) {
            connectPostsLikes(postid);
        } else if (type.equalsIgnoreCase("dislike")) {
            connectPostsDisLikes(postid);
        }
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("count", 0);
            if (myPostAdapter != null) {
                if (myPostAdapter.getPosition() >= 0) {
                    postsdatalist.get(myPostAdapter.getPosition()).setComment_count(String.valueOf(count));
                    myPostAdapter.notifyDataSetChanged();
                }

            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(getString(R.string.COMMENT_POSITION_RECEIVER)));
        if (checker){
            if (glView instanceof GLSurfaceView) {
                ((CubeGLSurfaceView) glView).onResume();
            }
            if (favCubeList.size()>0){
                for (int i=0; i<favCubeList.size(); i++){
                    favCubeList.get(i).onResume();
                }
            }
            if (cubeTab1List.size()>0){
                for (int i=0; i<cubeTab1List.size(); i++){
                    cubeTab1List.get(i).onResume();
                }
            }
            if (cubeTab2List.size()>0){
                for (int i=0; i<cubeTab2List.size(); i++){
                    cubeTab2List.get(i).onResume();
                }
            }
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if (checker){
            if (glView instanceof GLSurfaceView) {
                ((CubeGLSurfaceView) glView).onResume();
            }
            if (favCubeList.size()>0){
                for (int i=0; i<favCubeList.size(); i++){
                    favCubeList.get(i).onPause();
                }
            }
            if (cubeTab1List.size()>0){
                for (int i=0; i<cubeTab1List.size(); i++){
                    cubeTab1List.get(i).onPause();
                }
            }
            if (cubeTab2List.size()>0){
                for (int i=0; i<cubeTab2List.size(); i++){
                    cubeTab2List.get(i).onPause();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


    @Override
    public void gettab1glviews(CubeGLSurfaceView cubeGLSurfaceView) {
        cubeTab1List.add(cubeGLSurfaceView);
    }

    @Override
    public void gettab2glviews(CubeGLSurfaceView cubeGLSurfaceView) {
        cubeTab2List.add(cubeGLSurfaceView);
    }

    @Override
    public void getglviews(CubeGLSurfaceView cubeGLSurfaceView) {
        favCubeList.add(cubeGLSurfaceView);
    }
}