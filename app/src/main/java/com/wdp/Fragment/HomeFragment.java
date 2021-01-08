package com.wdp.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wdp.ActivityScreen.FilterActivity;
import com.wdp.ActivityScreen.MeStoryCubeViewActivity;
import com.wdp.ActivityScreen.StoryCubeViewActivity;
import com.wdp.Adapter.HomeAdapter;
import com.wdp.Adapter.StoryAdapter;
import com.wdp.ApiServices.FacebookApiService;
import com.wdp.ApiServices.MeStoryApiService;
import com.wdp.ApiServices.PostsApiService;
import com.wdp.ApiServices.PostsLIkesApiService;
import com.wdp.ApiServices.StoryApiService;
import com.wdp.CubeScreen.CubeGLSurfaceView;
import com.wdp.CubeScreen.CubeModal;
import com.wdp.CubeScreen.DragControl;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.MeStoryDataModal;
import com.wdp.Modal.PostDataModal;
import com.wdp.Modal.PostLikesDataModal;
import com.wdp.Modal.StoryDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.internal.operators.parallel.ParallelRunOn;


public class HomeFragment extends Fragment implements ApiResponseListner,HomeAdapter.OnclickIteam, HomeAdapter.GetGlViews, StoryAdapter.GetStoryGlViews {
    RecyclerView recyclerView;
    RecyclerView recyclerViewStories;
    NestedScrollView nestedScrollView;
    HomeAdapter homeAdapter;
    StoryAdapter storyAdapter;
    ArrayList<PostDataModal.DataBean.PostsBean> postDataList  = new ArrayList<>();
    PostDataModal postDataModal;
    View root;
    HomeFragment homeFragment;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    ArrayList<StoryDataModal.DataBean.UsersBean> storyDatList = new ArrayList<>();
    LinearLayout ll_me_story;
    ArrayList<CubeModal> profileList = new ArrayList<>();
    CubeGLSurfaceView glView;
    DragControl dragControl;
    CircleImageView img_plus;
    List<CubeGLSurfaceView> cubeList = new ArrayList<>();
    List<CubeGLSurfaceView> cubeStoryList = new ArrayList<>();
    int page = 1;
    boolean isStoryOpen = false;
    boolean isMyStoryOpen = false;
    boolean checker =false;
    String avatar1="",avatar2="",avatar3="",avatar4="",avatar5="",avatar6="";
    private StoryAdapter.RecyclerViewReadyCallback recyclerViewReadyCallback;
    private HomeAdapter.RecyclerViewReadyCallback recyclerViewReadyCallback1;

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("count",0);
            Log.d("count","---->" + "count");
            if (homeAdapter != null){
                if (homeAdapter.getPosition() >= 0){
                    postDataList.get(homeAdapter.getPosition()).setComment_count(String.valueOf(count));
                    homeAdapter.notifyDataSetChanged();
                }
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        homeFragment = this;
        initView();

        init();
        connectMeStory();
        connectPosts(String.valueOf(page));
        return root;
    }

    private void loadMore(){
        page++;
        connectPosts(String.valueOf(page));
    }

    private void init(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        homeAdapter = new HomeAdapter(getActivity(), postDataList,this, this);
        recyclerView.setAdapter(homeAdapter);

        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (postDataModal != null) {
                            if (Integer.parseInt(postDataModal.getData().getPagination().getCurrent_page()) < Integer.parseInt(postDataModal.getData().getPagination().getTotal_pages())) {
                                loadMore();
                            }

                        }
                    }

                }
            }
        });*/

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerViewReadyCallback1 != null) {
                    recyclerViewReadyCallback1.onLayoutReady();
                }
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        recyclerView.setNestedScrollingEnabled(false);

        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged()
            {
                View view = (View)nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);

                int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView
                        .getScrollY()));

                if (diff == 0) {
                    loadMore();
                }
            }
        });

        recyclerViewReadyCallback1 = new HomeAdapter.RecyclerViewReadyCallback() {
            @Override
            public void onLayoutReady() {
                //
                //here comes your code that will be executed after all items are laid down
                //
                connectStory();
            }
        };

        LinearLayoutManager linear = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewStories.setLayoutManager(linear);
        storyAdapter = new StoryAdapter(getActivity(), storyDatList, this);
        recyclerViewStories.setAdapter(storyAdapter);

        recyclerViewStories.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerViewReadyCallback != null) {
                    recyclerViewReadyCallback.onLayoutReady();
                }
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        recyclerViewReadyCallback = new StoryAdapter.RecyclerViewReadyCallback() {
            @Override
            public void onLayoutReady() {


            }
        };


        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FilterActivity.class);
                i.putExtra("whichScreen","storypost");
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(broadcastReceiver, new IntentFilter(getString(R.string.COMMENT_POSITION_RECEIVER)));
        /*if (checker && !isStoryOpen && !isMyStoryOpen){
            if (glView instanceof GLSurfaceView) {
                ((CubeGLSurfaceView) glView).onResume();
            }
            if (cubeList.size()>0){
                for (int i=0; i<cubeList.size(); i++){
                    cubeList.get(i).onResume();
                }
            }
            if (cubeStoryList.size()>0){
                for (int i=0; i<cubeStoryList.size(); i++){
                    cubeStoryList.get(i).onResume();
                }
            }
        }*/
    }

    @Override
    public void onPause(){
        super.onPause();
        /*if (checker && !isStoryOpen && !isMyStoryOpen){
            if (glView instanceof GLSurfaceView) {
                ((CubeGLSurfaceView) glView).onPause();
            }
            if (cubeList.size()>0){
                for (int i=0; i<cubeList.size(); i++){
                    cubeList.get(i).onPause();
                }
            }
            if (cubeStoryList.size()>0){
                for (int i=0; i<cubeStoryList.size(); i++){
                    cubeStoryList.get(i).onPause();
                }
            }
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    private void connectPosts(String page) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            PostsApiService postsApiService = new PostsApiService(getActivity());
            postsApiService.Connect(loginResDataModal.getData().getToken(), page, this);

        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }


    private void connectMeStory() {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            MeStoryApiService meStoryApiService = new MeStoryApiService(getActivity());
            meStoryApiService.Connect(loginResDataModal.getData().getToken(), this);

        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.server_data_not_found), Toast.LENGTH_SHORT).show();
        }
    }


    private void connectStory() {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (NetworkCheck.isConnected(getActivity())) {
            StoryApiService storyApiService = new StoryApiService(getActivity());
            storyApiService.Connect(loginResDataModal.getData().getToken(), this);

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

    private void initView(){
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerViewStories = root.findViewById(R.id.recyclerViewStories);
        ll_me_story = root.findViewById(R.id.ll_me_story);
        img_plus = root.findViewById(R.id.img_plus);
        nestedScrollView = root.findViewById(R.id.nestedScrollView);
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.posts_tag)){
            postDataModal =  (PostDataModal) superCastClass;
            postDataList.addAll(postDataModal.getData().getPosts());
            homeAdapter.notifyDataSetChanged();
        }
        else if (Tag.equalsIgnoreCase(ApiConstants.posts_likes_tag)){
            PostLikesDataModal postLikesDataModal = (PostLikesDataModal) superCastClass;
        }
        else if (Tag.equalsIgnoreCase(ApiConstants.mestoryview_tag)){
            MeStoryDataModal meStoryDataModal = (MeStoryDataModal) superCastClass;
            MeStoryData(meStoryDataModal);
        }
        else if (Tag.equalsIgnoreCase(ApiConstants.storty_TAG)){
            StoryDataModal storyDataModal = (StoryDataModal) superCastClass;
            StoryData(storyDataModal);
        }
    }

    private void StoryData(StoryDataModal storyDataModal) {
        storyDatList.addAll(storyDataModal.getData().getUsers());
        storyAdapter.notifyDataSetChanged();
    }

    private void MeStoryData(MeStoryDataModal meStoryDataModal) {
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(getActivity());
        if (loginResDataModal.getData().getUser().getAvatar()!= null){
            if (!TextUtils.isEmpty(loginResDataModal.getData().getUser().getAvatar().getThumb()) && !loginResDataModal.getData().getUser().getAvatar().getThumb().equalsIgnoreCase("null")) {
                profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar().getThumb(), R.drawable.thumnail));
            }
        }
        else {
            profileList.add(new CubeModal("", R.drawable.thumnail));
        }
        if (loginResDataModal.getData().getUser().getAvatar2()!= null){
            if (!TextUtils.isEmpty(loginResDataModal.getData().getUser().getAvatar2().getThumb()) && !loginResDataModal.getData().getUser().getAvatar2().getThumb().equalsIgnoreCase("null")) {
                profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar2().getThumb(), R.drawable.thumnail));
            }
        }
        else {
            profileList.add(new CubeModal("", R.drawable.thumnail));
        }

        if (loginResDataModal.getData().getUser().getAvatar3()!= null){
            if (!TextUtils.isEmpty(loginResDataModal.getData().getUser().getAvatar3().getThumb()) && !loginResDataModal.getData().getUser().getAvatar3().getThumb().equalsIgnoreCase("null")) {
                profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar3().getThumb(), R.drawable.thumnail));
            }
        }
        else {
            profileList.add(new CubeModal("", R.drawable.thumnail));
        }

        if (loginResDataModal.getData().getUser().getAvatar4()!= null){
            if (!TextUtils.isEmpty(loginResDataModal.getData().getUser().getAvatar4().getThumb()) && !loginResDataModal.getData().getUser().getAvatar4().getThumb().equalsIgnoreCase("null")) {
                profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar4().getThumb(), R.drawable.thumnail));
            }
        }
        else {
            profileList.add(new CubeModal("", R.drawable.thumnail));
        }
        if (loginResDataModal.getData().getUser().getAvatar5()!= null){
            if (!TextUtils.isEmpty(loginResDataModal.getData().getUser().getAvatar5().getThumb()) && !loginResDataModal.getData().getUser().getAvatar5().getThumb().equalsIgnoreCase("null")) {
                profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar5().getThumb(), R.drawable.thumnail));
            }
        }
        else {
            profileList.add(new CubeModal("", R.drawable.thumnail));
        }

        if (loginResDataModal.getData().getUser().getAvatar6()!= null){
            if (!TextUtils.isEmpty(loginResDataModal.getData().getUser().getAvatar6().getThumb()) && !loginResDataModal.getData().getUser().getAvatar6().getThumb().equalsIgnoreCase("null")) {
                profileList.add(new CubeModal(loginResDataModal.getData().getUser().getAvatar6().getThumb(), R.drawable.thumnail));
            }
        }
        else {
            profileList.add(new CubeModal("", R.drawable.thumnail));
        }

        if (loginResDataModal.getData().getUser().getAvatar()!= null){
            avatar1 =  loginResDataModal.getData().getUser().getAvatar().getOriginal();
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


        Log.d("avatar1","----->" + avatar1 + "," + avatar2 +"," + avatar3 + "," +avatar4 + "," + avatar5+ ", " + avatar6);

        glView = new CubeGLSurfaceView(getActivity(), avatar1, avatar2, avatar3, avatar4, avatar5, avatar6);
        ll_me_story.setBackgroundColor(getResources().getColor(R.color.white));
        ll_me_story.addView(glView);
        dragControl = new DragControl(getActivity(), "", "", meStoryDataModal.getData().getStories());
        //glView.setOnTouchListener(dragControl);
        glView.setDragControl(dragControl);
        glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        glView.setZOrderOnTop(true);
        glView.setZOrderMediaOverlay(true);
        checker = true;

        ll_me_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (meStoryDataModal.getData().getStories() != null){
                    if (meStoryDataModal.getData().getStories().size() > 0){
                        isMyStoryOpen = true;
                        Intent i = new Intent(getContext(), MeStoryCubeViewActivity.class);
                        Gson gson = new Gson();
                        String arrayData = gson.toJson(meStoryDataModal.getData().getStories());
                        i.putExtra("mestorylist",arrayData);
                        getContext().startActivity(i);
                    }

                }
            }
        });
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onException(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onclickiteam(String type, String postid) {
        if (type.equalsIgnoreCase("like")){
            connectPostsLikes(postid);
        }
        else if (type.equalsIgnoreCase("dislike")){
            connectPostsDisLikes(postid);
        }
    }


    @Override
    public void getglviews(CubeGLSurfaceView cubeGLSurfaceView) {
        cubeList.add(cubeGLSurfaceView);
    }

    @Override
    public void getstoryglviews(CubeGLSurfaceView cubeGLSurfaceView , StoryDataModal.DataBean.UsersBean dataBean) {

        if (dataBean!=null){
            isStoryOpen = true;
            Intent i = new Intent(getContext(), StoryCubeViewActivity.class);
            i.putExtra("story_data",dataBean);
            getContext().startActivity(i);
        }else {
            cubeStoryList.add(cubeGLSurfaceView);
        }
    }
}