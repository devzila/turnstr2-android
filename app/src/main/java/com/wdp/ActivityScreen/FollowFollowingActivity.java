package com.wdp.ActivityScreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wdp.Adapter.FollowerAdapter;
import com.wdp.Adapter.FollowingAdapter;
import com.wdp.ApiServices.FollowUnfollowiService;
import com.wdp.ApiServices.FollowerApiService;
import com.wdp.ApiServices.FollowingApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.CommanDataModal;
import com.wdp.Modal.FollowerDataModal;
import com.wdp.Modal.FollowingBean;
import com.wdp.Modal.FollowingDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;

public class FollowFollowingActivity extends Activity implements ApiResponseListner,FollowerAdapter.onClickFollower,FollowingAdapter.onClickFollowing {
    RadioGroup radioTabgroup;
    RadioButton radioHeader1,radioHeader2;
    RecyclerView recycleview_follower,recyclerview_following;
    ArrayList<FollowingBean> followingList = new ArrayList<>();
    ArrayList<FollowerDataModal.DataBean.FollowerBean> followerList = new ArrayList<>();
    FollowingAdapter followingAdapter;
    FollowerAdapter followerAdapter;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    LoginResDataModal loginResDataModal;
    FollowingDataModal followingDataModal;
    FollowerDataModal followerDataModal;
    private int pageFollowing = 1;
    private int pageFollower = 1;
    private String whichScreen;
    ImageView img_back;
    String userid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_follow_following);
        intiView();
        prepair();

    }

    private void intiView(){
        radioTabgroup = findViewById(R.id.radioTabgroup);
        recycleview_follower = findViewById(R.id.recycleview_follower);
        recyclerview_following = findViewById(R.id.recyclerview_following);
        radioHeader1 = findViewById(R.id.radioHeader1);
        radioHeader2 = findViewById(R.id.radioHeader2);
        img_back = findViewById(R.id.img_back);



    }

    private void prepair(){
        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        LinearLayoutManager linearfollowing = new LinearLayoutManager(this);
        recyclerview_following.setLayoutManager(linearfollowing);
        followingAdapter = new FollowingAdapter(this, followingList,this);
        recyclerview_following.setAdapter(followingAdapter);

        LinearLayoutManager linearfollower = new LinearLayoutManager(this);
        recycleview_follower.setLayoutManager(linearfollower);
        followerAdapter = new FollowerAdapter(this, followerList,this);
        recycleview_follower.setAdapter(followerAdapter);
        recycleview_follower.setVisibility(View.GONE);

        radioTabgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioHeader1:
                        recyclerview_following.setVisibility(View.VISIBLE);
                        recycleview_follower.setVisibility(View.GONE);
                        radioHeader1.setTextColor(getResources().getColor(R.color.colorOrangelite));
                        radioHeader2.setTextColor(getResources().getColor(R.color.ColorGray808080));
                        break;
                    case R.id.radioHeader2:
                        recyclerview_following.setVisibility(View.GONE);
                        recycleview_follower.setVisibility(View.VISIBLE);
                        radioHeader2.setTextColor(getResources().getColor(R.color.colorOrangelite));
                        radioHeader1.setTextColor(getResources().getColor(R.color.ColorGray808080));
                        break;


                }

            }
        });

          if (getIntent().getStringExtra("whichScreen").equalsIgnoreCase("myprofile")){
            whichScreen = getIntent().getStringExtra("whichScreen");
            userid = getIntent().getStringExtra("userid");
            whichUserApiCall();
        }
        else {
              whichScreen = getIntent().getStringExtra("whichScreen");
              userid = getIntent().getStringExtra("userid");
              whichUserApiCall();
        }

        if (getIntent().getStringExtra("type").equalsIgnoreCase("following")){
            radioHeader1.setChecked(true);
            radioHeader1.setTextColor(getResources().getColor(R.color.colorOrangelite));
            radioHeader2.setTextColor(getResources().getColor(R.color.ColorGray808080));
        }
        else {
            radioHeader2.setChecked(true);
            radioHeader2.setTextColor(getResources().getColor(R.color.colorOrangelite));
            radioHeader1.setTextColor(getResources().getColor(R.color.ColorGray808080));
        }







        recyclerview_following.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (followingDataModal != null) {
                            if (Integer.parseInt(followingDataModal.getData().getPagination().getCurrent_page()) < Integer.parseInt(followingDataModal.getData().getPagination().getTotal_pages())) {
                                loadMoreFollowing();
                            }

                        }
                    }

                }
            }
        });

        recycleview_follower.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (followerDataModal != null) {
                            if (Integer.parseInt(followerDataModal.getData().getPagination().getCurrent_page()) < Integer.parseInt(followerDataModal.getData().getPagination().getTotal_pages())) {
                                loadMoreFollower();
                            }

                        }
                    }

                }
            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void whichUserApiCall(){
        if (whichScreen.equalsIgnoreCase("myprofile")){
            meApiCall();
        }
        else {
            otherUserApiCall();
        }
    }

    private void meApiCall() {
        connectFollowingList(pageFollowing);
        connectFollowerList(pageFollower);
    }

    private void otherUserApiCall() {
        String url = ApiConstants.MEMBEFOLLOWER.replace("(id)", String.valueOf(userid));
        String url2 = ApiConstants.MEMBEFOLLWING.replace("(id)", String.valueOf(userid));
        connectFollowerMemberList(url,pageFollower);
        connectFollowingMemberList(url2,pageFollowing);
    }

    private void loadMoreFollowing() {
        pageFollowing++;

    }

    private void loadMoreFollower() {
        pageFollower++;
    }


    private void connectFollowingList(int page) {
        if (NetworkCheck.isConnected(this)) {
            FollowingApiService followingApiService = new FollowingApiService(this);
            followingApiService.Connect(loginResDataModal.getData().getToken(), String.valueOf(page), this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
    }
    private void connectFollowerList(int page) {
        if (NetworkCheck.isConnected(this)) {
            FollowerApiService followerApiService = new FollowerApiService(this);
            followerApiService.Connect(loginResDataModal.getData().getToken(), String.valueOf(page), this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void connectFollowingMemberList(String url,int page) {
        if (NetworkCheck.isConnected(this)) {
            FollowingApiService approveApiService = new FollowingApiService(this);
            approveApiService.Connect1(url,loginResDataModal.getData().getToken(), String.valueOf(page), this);
        }
        else {
            Toast.makeText(this, loginResDataModal.getData().getToken(), Toast.LENGTH_LONG).show();
        }
    }

    private void connectFollowerMemberList(String url,int page) {
        if (NetworkCheck.isConnected(this)) {
            FollowerApiService followerApiService = new FollowerApiService(this);
            followerApiService.Connect1(url, loginResDataModal.getData().getToken(), String.valueOf(page), this);
        } else {
            Toast.makeText(this,getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
    }




    private void connectFollowing(String url) {
        if (NetworkCheck.isConnected(this)) {
            FollowUnfollowiService followUnfollowiService = new FollowUnfollowiService(this);
            followUnfollowiService.Connect1(loginResDataModal.getData().getToken(), url, this);
        } else {
            Toast.makeText(this,getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }


    private void connectFollow(String url) {
        if (NetworkCheck.isConnected(this)) {
            FollowUnfollowiService followUnfollowiService = new FollowUnfollowiService(this);
            followUnfollowiService.Connect(loginResDataModal.getData().getToken(), url, this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.me_following)) {
            followingDataModal = (FollowingDataModal) superCastClass;
            followingList.addAll(followingDataModal.getData().getFollowing());
            followingAdapter.notifyDataSetChanged();
        } else if (Tag.equalsIgnoreCase(ApiConstants.me_follower)) {
            followerDataModal = (FollowerDataModal) superCastClass;
            followerList.addAll(followerDataModal.getData().getFollower());
            followerAdapter.notifyDataSetChanged();
        }
        else if (Tag.equalsIgnoreCase(ApiConstants.FollowUnFollow_TAG)){
            CommanDataModal commanDataModal = (CommanDataModal) superCastClass;
            whichUserApiCall();

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

    @Override
    public void onclickFollowing(String flag, String id) {
        followerList.clear();
        followingList.clear();
        pageFollower = 1;
        pageFollowing = 1;
        if (flag.equalsIgnoreCase(getResources().getString(R.string.Turnt))) {
            String url = ApiConstants.Member_Follow;
            connectFollow(url.replace("id", String.valueOf(id)));
        } else {
            String url = ApiConstants.Member_Follow;
            connectFollowing(url.replace("id", String.valueOf(id)));

        }

    }

    @Override
    public void onclickFollower(String flag, String id) {
        followerList.clear();
        followingList.clear();
        pageFollowing = 1;
        pageFollower = 1;
        if (flag.equalsIgnoreCase(getResources().getString(R.string.Turnt))) {
            String url =  ApiConstants.Member_Follow;
            connectFollow(url.replace("id", String.valueOf(id)));

        } else {

            String url =  ApiConstants.Member_Follow;
            connectFollowing(url.replace("id", String.valueOf(id)));
        }
    }
}
