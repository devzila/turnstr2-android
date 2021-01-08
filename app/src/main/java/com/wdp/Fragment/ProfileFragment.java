package com.wdp.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.wdp.ActivityScreen.FollowFollowingActivity;
import com.wdp.ActivityScreen.LoginActivity;
import com.wdp.ActivityScreen.MainActivity;
import com.wdp.ActivityScreen.MyStoriesActivity;
import com.wdp.ActivityScreen.ProfileEditActivity;
import com.wdp.Adapter.FavouritesListAdapter;
import com.wdp.Adapter.MyGridPostAdapter;
import com.wdp.Adapter.MyPostAdapter;
import com.wdp.Adapter.StoryAdapter;
import com.wdp.ApiServices.DeletePostApiService;
import com.wdp.ApiServices.FavouritesListApiService;
import com.wdp.ApiServices.MeStoryApiService;
import com.wdp.ApiServices.MyPostApiService;
import com.wdp.ApiServices.PostsLIkesApiService;
import com.wdp.ApiServices.ProfileApiService;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommanDataModal;
import com.wdp.Modal.FavouriteslistDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.MyPostDataModal;
import com.wdp.Modal.PostLikesDataModal;
import com.wdp.Modal.ProfileDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements View.OnClickListener, ApiResponseListner, MyPostAdapter.OnclickIteam, FavouritesListAdapter.GetGlViews,
        MyGridPostAdapter.GetTab1GlViews, MyPostAdapter.GetTab2GlViews {

    LinearLayout ll_tab1, ll_tab2, ll_tab1_view, ll_tab2_view;
    RelativeLayout rl_txt_tab1, rl_txt_tab2;
    TextView txt_logout, txt_edit_profile;
    ImageView img_tab1, img_tab2, img_profile_bg, img_cube;
    RecyclerView recyclerView_tab1, recyclerView_tab2;
    View view_tab1, view_tab2;
    View root;
    RadioGroup radioTabgroup;
    LinearLayout ll_followrs, ll_following;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    TextView txt_post_count, txt_followr_count, txt_following_count, txt_username, txt_name;
    MyPostDataModal myPostDataModal;
    ArrayList<MyPostDataModal.DataBean.PostsBean> postsdatalist = new ArrayList<>();
    MyPostAdapter myPostAdapter;
    MyGridPostAdapter myGridPostAdapter;
    FavouritesListAdapter favouritesListAdapter;
    int page = 1;
    ArrayList<FavouriteslistDataModal.DataBean.UsersBean> favList = new ArrayList<>();
    RecyclerView recyclerViewFav;
    List<CubeGLSurfaceView> favCubeList = new ArrayList<>();
    List<CubeGLSurfaceView> cubeTab1List = new ArrayList<>();
    List<CubeGLSurfaceView> cubeTab2List = new ArrayList<>();
    CubeGLSurfaceView glView;
    DragControl dragControl;
    RelativeLayout rl_cube;
    LoginResDataModal loginResDataModal;
    TextView txt_about,txt_email;;
    int Position;
    boolean isFollowerFollowingOpen = false;
    boolean checker =false;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(getString(R.string.COMMENT_POSITION_RECEIVER)));
        initView();
        clickLister();
        prepair();
        connectProfile();
        connectFavourites();
        connectMyPost(page);
        return root;
    }

    private void prepair() {
        recyclerView_tab1.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        myGridPostAdapter = new MyGridPostAdapter(getActivity(), postsdatalist, this);
        recyclerView_tab1.setAdapter(myGridPostAdapter);
        recyclerView_tab1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (myPostDataModal != null) {
                            if (Integer.parseInt(myPostDataModal .getData().getPagination().getCurrent_page()) < Integer.parseInt(myPostDataModal.getData().getPagination().getTotal_pages())) {
                                loadMore();
                            }
                        }
                    }
                }
            }
        });

        LinearLayoutManager lineargrid = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView_tab2.setLayoutManager(lineargrid);
        myPostAdapter = new MyPostAdapter(getActivity(), postsdatalist,"profile", this, this);
        recyclerView_tab2.setAdapter(myPostAdapter);
        LinearLayoutManager linearfav = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFav.setLayoutManager(linearfav);
        favouritesListAdapter = new FavouritesListAdapter(getActivity(), favList, this);
        recyclerViewFav.setAdapter(favouritesListAdapter);
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());

        if (loginResDataModal.getData().getUser().getBackground()!=null){
            Glide.with(this).load(loginResDataModal.getData().getUser().getBackground().getThumb()).into(img_profile_bg);
            /*final Animation zoomAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom);
            img_profile_bg.startAnimation(zoomAnimation);*/
        }

    }

    private void loadMore() {
        page++;
        connectMyPost(page);
    }

    private void connectProfile() {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            ProfileApiService profileApiService = new ProfileApiService(getActivity());
            profileApiService.Connect(loginResDataModal.getData().getToken(), this);

        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectFavourites() {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            FavouritesListApiService favouritesApiService = new FavouritesListApiService(getActivity());
            favouritesApiService.Connect(loginResDataModal.getData().getToken(), this);

        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectMyPost(int page) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            MyPostApiService myPostApiService = new MyPostApiService(getActivity());
            myPostApiService.Connect(loginResDataModal.getData().getToken(), String.valueOf(page), this);
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectPostsLikes(String postid) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            PostsLIkesApiService postsLIkesApiService = new PostsLIkesApiService(getActivity());
            postsLIkesApiService.Connect(loginResDataModal.getData().getToken(), postid, this);

        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void connectPostsDisLikes(String postid) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            PostsLIkesApiService postsLIkesApiService = new PostsLIkesApiService(getActivity());
            postsLIkesApiService.Connect1(loginResDataModal.getData().getToken(), postid, this);

        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }
    public void connectMeDeletePost(String url, int position) {
        Position = position;
        if (NetworkCheck.isConnected(getActivity())) {
            Position = position;
            DeletePostApiService deletePostApiService = new DeletePostApiService(getActivity());
            deletePostApiService.Connect(url,loginResDataModal.getData().getToken(), this);
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        ll_tab1 = root.findViewById(R.id.ll_tab1);
        ll_tab2 = root.findViewById(R.id.ll_tab2);
        rl_txt_tab1 = root.findViewById(R.id.rl_txt_tab1);
        rl_txt_tab2 = root.findViewById(R.id.rl_txt_tab2);
        view_tab1 = root.findViewById(R.id.view_tab1);
        view_tab2 = root.findViewById(R.id.view_tab2);
        img_tab1 = root.findViewById(R.id.img_tab1);
        img_tab2 = root.findViewById(R.id.img_tab2);
        img_profile_bg = root.findViewById(R.id.img_profile_bg);
        img_cube = root.findViewById(R.id.img_cube);
        recyclerView_tab1 = root.findViewById(R.id.recyclerView_tab1);
        recyclerView_tab2 = root.findViewById(R.id.recyclerView_tab2);
        ll_tab1_view = root.findViewById(R.id.ll_tab1_view);
        ll_tab2_view = root.findViewById(R.id.ll_tab2_view);
        ll_followrs = root.findViewById(R.id.ll_followrs);
        ll_following = root.findViewById(R.id.ll_following);
        txt_logout = root.findViewById(R.id.txt_logout);
        txt_post_count = root.findViewById(R.id.txt_post_count);
        txt_followr_count = root.findViewById(R.id.txt_followr_count);
        txt_following_count = root.findViewById(R.id.txt_following_count);
        txt_username = root.findViewById(R.id.txt_username);
        txt_name = root.findViewById(R.id.txt_name);
        txt_edit_profile = root.findViewById(R.id.txt_edit_profile);

        recyclerViewFav = root.findViewById(R.id.recyclerViewFav);
        rl_cube = root.findViewById(R.id.rl_cube);
        txt_about = root.findViewById(R.id.txt_about);
        txt_email = root.findViewById(R.id.txt_email);


    }

    private void clickLister() {
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_followrs.setOnClickListener(this);
        ll_following.setOnClickListener(this);
        txt_logout.setOnClickListener(this);
        txt_edit_profile.setOnClickListener(this);
        recyclerViewFav.setOnClickListener(this);
        img_cube.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (ll_tab1 == view) {
            tab1Selected(postsdatalist.size());
        } else if (ll_tab2 == view) {
            tab2Selected(postsdatalist.size());
        } else if (ll_following == view) {
            isFollowerFollowingOpen = true;
            Intent i = new Intent(getActivity(), FollowFollowingActivity.class);
            i.putExtra("type", "following");
            i.putExtra("whichScreen", "myprofile");
            i.putExtra("userid", loginResDataModal.getData().getUser().getId());
            startActivity(i);
        } else if (ll_followrs == view) {
            isFollowerFollowingOpen = true;
            Intent i = new Intent(getActivity(), FollowFollowingActivity.class);
            i.putExtra("type", "follower");
            i.putExtra("whichScreen", "myprofile");
            i.putExtra("userid", loginResDataModal.getData().getUser().getId());
            startActivity(i);
        } else if (txt_logout == view) {
            logout();
        } else if (txt_edit_profile == view) {
            isFollowerFollowingOpen = true;
            Intent i = new Intent(getActivity(), ProfileEditActivity.class);
            startActivity(i);
        } else if (img_cube ==view){
            Intent i = new Intent(getActivity(), MyStoriesActivity.class);
            startActivity(i);
        }
    }

    public void logout() {
        final BottomSheetDialog dialog = new BottomSheetDialog(getActivity(), R.style.SheetDialog);
        dialog.setContentView(R.layout.customalertdailog);
        Button no = (Button) dialog.findViewById(R.id.bt_no);
        Button yes = (Button) dialog.findViewById(R.id.bt_yes);
        TextView title = (TextView) dialog.findViewById(R.id.tv_title);
        title.setText(R.string.are_you_sure_want_to_logout);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonSharedPreference.setisLoginSharedPref(getActivity(), false);
                commonSharedPreference.Logout_User(getActivity());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void tab1Selected(int size) {
        img_tab1.setColorFilter(ContextCompat.getColor(getActivity(), R.color.black));
        img_tab2.setColorFilter(ContextCompat.getColor(getActivity(), R.color.ColorGray808080));
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
        img_tab1.setColorFilter(ContextCompat.getColor(getActivity(), R.color.ColorGray808080));
        img_tab2.setColorFilter(ContextCompat.getColor(getActivity(), R.color.black));
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
        myGridPostAdapter.notifyDataSetChanged();
        myPostAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.profile_TAG)) {
            ProfileDataModal profileDataModal = (ProfileDataModal) superCastClass;
            txt_post_count.setText(profileDataModal.getData().getCurrent_user().getPost_count());
            txt_following_count.setText(profileDataModal.getData().getCurrent_user().getFollowing_count());
            txt_followr_count.setText(profileDataModal.getData().getCurrent_user().getFollower_count());
            txt_username.setText(profileDataModal.getData().getCurrent_user().getUsername());
            txt_name.setText(profileDataModal.getData().getCurrent_user().getName());
            txt_about.setText(profileDataModal.getData().getCurrent_user().getBio());
            txt_email.setText(profileDataModal.getData().getCurrent_user().getWebsite());
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
            glView = new CubeGLSurfaceView(getActivity(),avatrar1,
                    avatrar2,avatrar3,
                    avatrar4,
                    avatrar5,avatrar6);
            rl_cube.addView(glView);
            rl_cube.setBackgroundColor(getResources().getColor(R.color.colorTransparent));
            dragControl = new DragControl(getActivity(),getActivity(),avatrar1,
                    avatrar2,avatrar3,
                    avatrar4,
                    avatrar5,avatrar6);
            glView.setOnTouchListener(dragControl);
            glView.setDragControl(dragControl);
            checker = true;

        } else if (Tag.equalsIgnoreCase(ApiConstants.my_post_TAG)) {
            myPostDataModal = (MyPostDataModal) superCastClass;
            postsdatalist.addAll(myPostDataModal.getData().getPosts());
            tab1Selected(postsdatalist.size());
        } else if (Tag.equalsIgnoreCase(ApiConstants.posts_likes_tag)) {
            PostLikesDataModal postLikesDataModal = (PostLikesDataModal) superCastClass;
        }
        else if (Tag.equalsIgnoreCase(ApiConstants.favourites_TAG)){
            FavouriteslistDataModal favouritesDataModal = (FavouriteslistDataModal) superCastClass;
            favList.addAll(favouritesDataModal.getData().getUsers());
            favouritesListAdapter.notifyDataSetChanged();
        }
        else if (Tag.equalsIgnoreCase(ApiConstants.MePostdelete_TAG)) {
            CommanDataModal commanDataModal = (CommanDataModal) superCastClass;
            myPostAdapter.delete();
            myPostAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onclickiteam(String type, String postid,int pos) {
        if (type.equalsIgnoreCase("like")) {
            connectPostsLikes(postid);
        } else if (type.equalsIgnoreCase("dislike")) {
            connectPostsDisLikes(postid);
        }
        else if (type.equalsIgnoreCase("delete")){
            connectMeDeletePost(postid,pos);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (checker && !isFollowerFollowingOpen){
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
        }*/
    }

    @Override
    public void onPause(){
        super.onPause();
        /*if (checker && !isFollowerFollowingOpen){
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
        }*/
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(broadcastReceiver);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void getglviews(CubeGLSurfaceView cubeGLSurfaceView) {
        favCubeList.add(cubeGLSurfaceView);
    }

    @Override
    public void gettab1glviews(CubeGLSurfaceView cubeGLSurfaceView) {
        cubeTab1List.add(cubeGLSurfaceView);
    }

    @Override
    public void gettab2glviews(CubeGLSurfaceView cubeGLSurfaceView) {
        cubeTab2List.add(cubeGLSurfaceView);
    }
}