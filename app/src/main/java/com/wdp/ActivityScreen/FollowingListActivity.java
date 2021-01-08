package com.wdp.ActivityScreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sendbird.android.GroupChannel;
import com.sendbird.android.SendBirdException;
import com.wdp.Adapter.FollowingListAdapter;
import com.wdp.Adapter.SelectedMemberAdapter;
import com.wdp.ApiServices.FollowingApiService;
import com.wdp.ApiServices.SharingChannelNameApiService;
import com.wdp.Interface.ApiConstants;
import com.wdp.Interface.ApiResponseListner;

import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.FollowingBean;
import com.wdp.Modal.FollowingDataModal;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Modal.SharingChannelModel;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.ItemClickListener;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FollowingListActivity extends AppCompatActivity implements ItemClickListener, ApiResponseListner, View.OnClickListener {

    RecyclerView rv_following, rvDynamicView;
    ImageView imgBack;
    EditText edtSearch;
    private int count=0, callingCount=0;
    TextView txtSelectedCount;
    LinearLayout layDynamicView;
    FollowingListAdapter followingAdapter;
    SelectedMemberAdapter selectedMemberAdapter;
    private int pageFollowing = 1;
    FollowingDataModal followingDataModal;
    public static final String EXTRA_NEW_CHANNEL_URL = "EXTRA_NEW_CHANNEL_URL";
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    LoginResDataModal loginResDataModal;
    String from="";
    ArrayList<FollowingBean> followingList = new ArrayList<>();
    ArrayList<FollowingBean> selectedList = new ArrayList<>();
    int sendCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followinglist);

        rv_following = findViewById(R.id.rv_following);
        rvDynamicView = findViewById(R.id.rvDynamicView);
        imgBack = findViewById(R.id.imgBack);
        edtSearch = findViewById(R.id.edtSearch);
        txtSelectedCount = findViewById(R.id.txtSelectedCount);
        layDynamicView = findViewById(R.id.layDynamicView);

        from = getIntent().getStringExtra("from");

        LinearLayoutManager linearfollowing = new LinearLayoutManager(this);
        rv_following.setLayoutManager(linearfollowing);
        followingAdapter = new FollowingListAdapter(this, followingList, from,this);
        rv_following.setAdapter(followingAdapter);

        LinearLayoutManager linearfollowing1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvDynamicView.setLayoutManager(linearfollowing1);
        selectedMemberAdapter = new SelectedMemberAdapter(this, selectedList,this);
        rvDynamicView.setAdapter(selectedMemberAdapter);

        loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        connectFollowingList(pageFollowing);

        imgBack.setOnClickListener(this);
        txtSelectedCount.setOnClickListener(this);

        rv_following.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && !recyclerView.canScrollVertically(1) && followingDataModal != null) {
                    if (Integer.parseInt(followingDataModal.getData().getPagination().getCurrent_page()) < Integer.parseInt(followingDataModal.getData().getPagination().getTotal_pages())) {
                        loadMoreFollowing();
                    }
                }
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                followingAdapter.filter(value);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void connectFollowingList(int page) {
        if (NetworkCheck.isConnected(this)) {
            FollowingApiService followingApiService = new FollowingApiService(this);
            followingApiService.Connect(loginResDataModal.getData().getToken(), String.valueOf(page), this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void connectFollowingList(String url) {
        if (NetworkCheck.isConnected(this)) {
            SharingChannelNameApiService sharingChannelNameApiService = new SharingChannelNameApiService(this);
            sharingChannelNameApiService.Connect(loginResDataModal.getData().getToken(), url, this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void createGroupChannel(List<String> userIds, boolean distinct) {
        GroupChannel.createChannelWithUserIds(userIds, distinct, new GroupChannel.GroupChannelCreateHandler() {
            @Override
            public void onResult(GroupChannel groupChannel, SendBirdException e) {
                if (e != null) {
                    // Error!
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra(EXTRA_NEW_CHANNEL_URL, groupChannel.getUrl());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase(ApiConstants.me_following)) {
            followingDataModal = (FollowingDataModal) superCastClass;
            followingList.addAll(followingDataModal.getData().getFollowing());
            followingAdapter.notifyDataSetChanged();
        }else if (Tag.equalsIgnoreCase(ApiConstants.sharing_channel)){
            callingCount++;
            SharingChannelModel sharingChannelModel = (SharingChannelModel) superCastClass;
            Toast.makeText(FollowingListActivity.this, sharingChannelModel.getMessage(), Toast.LENGTH_LONG).show();
            if (callingCount==selectedList.size()){
                FollowingListActivity.this.finish();
            }else {
                String url = "members/"+selectedList.get(callingCount).getUsername()+"/invite?channel="+loginResDataModal.getData().getUser().getId()+"_"+loginResDataModal.getData().getUser().getName().replace(" ","")+"&username=1&purpose=call";
                connectFollowingList(url);
            }
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onException(String message) {

    }

    private void loadMoreFollowing() {
        pageFollowing++;
    }

    @Override
    public void onClickListener(int position, int count) {
        txtSelectedCount.setText(getResources().getString(R.string.ok)+"("+count+")");
        if (this.count>count){
            for (int i=0; i<selectedList.size(); i++){
                if (selectedList.get(i).getId().equalsIgnoreCase(followingDataModal.getData().getFollowing().get(position).getId())){
                    selectedList.remove(i);
                }
            }
        }else if (this.count==count) {
            selectedList.clear();
            selectedList.add(followingDataModal.getData().getFollowing().get(position));
        } else {
            selectedList.add(followingDataModal.getData().getFollowing().get(position));
        }
        selectedMemberAdapter.notifyDataSetChanged();
        this.count = count;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.imgBack){
            finish();
        }else if (id==R.id.txtSelectedCount){
            if(from.equalsIgnoreCase("VideoCallActivity")) {
                if (selectedList.size() > 0) {
                    String url = "members/" + selectedList.get(0).getUsername() + "/invite?channel=" + loginResDataModal.getData().getUser().getId() + "_" + loginResDataModal.getData().getUser().getName().replace(" ","") + "&username=1&purpose=call";

                    connectFollowingList(url);

                } else {
                    Toast.makeText(FollowingListActivity.this, getResources().getString(R.string.selectanyfollowingmember), Toast.LENGTH_LONG).show();
                }
            }else if (from.equalsIgnoreCase("ChatDashboardActivity")){
                List<String> list = new ArrayList<>();
                list.add(selectedList.get(0).getId());
                createGroupChannel(list, true);
            }
        }
    }
}
