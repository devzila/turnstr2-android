package com.adroidtech.turnstr2.chat.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.chat.adapters.AllFriendList_Adapter;
import com.adroidtech.turnstr2.chat.groupchannel.GroupChannelActivity;
import com.adroidtech.turnstr2.chat.groupchannel.SelectDistinctFragment;
import com.adroidtech.turnstr2.chat.groupchannel.SelectUserFragment;
import com.adroidtech.turnstr2.chat.listeners.RecyclerItemClickListener;
import com.adroidtech.turnstr2.chat.models.Member;
import com.adroidtech.turnstr2.videoChat.MainVideoActivity;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.SendBirdException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by narinder on 18/12/17.
 */

public class AllFriendListVideo extends AppCompatActivity implements AsyncCallback , SelectUserFragment.UsersSelectedListener, SelectDistinctFragment.DistinctSelectedListener{

    public static final String EXTRA_NEW_CHANNEL_URL = "EXTRA_NEW_CHANNEL_URL";
    public static final String GROUP_CHANNEL_URL = "groupChannelUrl";
    public static final String INVITE_MEMBER = "inviteMember";

    private RecyclerView recyclerView;
    private AllFriendList_Adapter mAdapter;
    private SharedPreference sharedPreference;
    private String TAG = "AllFriendList";
    Member member = new Member();
    List<Member> memberList = new ArrayList<>();
    private boolean mIsDistinct;
    private List<String> mSelectedIds;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);



        sharedPreference = new SharedPreference(getApplicationContext());
        init();
        try {
            getMembersRequest(GeneralValues.FOLLOWERS_URL, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new AllFriendList_Adapter(getApplicationContext(), memberList);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        if(null!=member)
                        {

//                            mSelectedIds=new ArrayList<>();
//                            mSelectedIds.add(member.getMemberList().get(position).getId());
//                            mIsDistinct = PreferenceUtils.getGroupChannelDistinct(AllFriendListVideo.this);////CreateGroupChannelActivity
//                            createGroupChannel(mSelectedIds, mIsDistinct);

                            Intent intent = new Intent(getApplicationContext(), MainVideoActivity.class);
                            intent.putExtra(INVITE_MEMBER, memberList.get(position).getId());
                            startActivity(intent);
                            finish();

                        }

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(null!=member.getNext_page() && !member.getNext_page().equals("null"))
                {
                totalItemCount = mLayoutManager.getItemCount();
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    Log.e("TAG", "................... loading ...........");


                        isLoading = true;
                        Log.e("TAG", "........................page=..."+member.getNext_page());
                        //getMembersRequest(GeneralValues.FOLLOWERS_URL+"?page="+member.getNext_page());
                        try {
                            getMembersRequest(GeneralValues.FOLLOWERS_URL, false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });

    }

    @Override
    public void getAsyncResult(String json, String txt) {

        Log.e("TAG", "getAsyncResult............." + json);

        if (null != json) {

            JSONObject jsonobject = null;
            try {
                jsonobject = new JSONObject(json);

                if (null != jsonobject) {
                    String name;
                    Iterator<String> keys = jsonobject.keys();

                    while (keys.hasNext()) {

                        name = keys.next();
                        System.out.println("key namn:::::::" + name);
                        if (name.equals("status")) {


                        }
                        if (name.equals("data")) {

                            JSONObject dataJson = jsonobject.getJSONObject(name);
                            if (null != dataJson) {
                                String dataName;
                                Iterator<String> dataKey = dataJson.keys();
                                while (dataKey.hasNext()) {
                                    dataName = dataKey.next();
                                    if (dataName.equals("followers")) {

                                        JSONArray jsonTags = dataJson.getJSONArray(dataName);
                                        Log.e(TAG, "Contacts...." + jsonTags);

                                        for (int y = 0; y < jsonTags.length(); y++) {

                                            Member memberData = new Member();
                                            memberData.setAvatar_face1(jsonTags.getJSONObject(y).getString("avatar_face1"));
                                            memberData.setAvatar_face2(jsonTags.getJSONObject(y).getString("avatar_face2"));
                                            memberData.setAvatar_face3(jsonTags.getJSONObject(y).getString("avatar_face3"));
                                            memberData.setAvatar_face4(jsonTags.getJSONObject(y).getString("avatar_face4"));
                                            memberData.setAvatar_face5(jsonTags.getJSONObject(y).getString("avatar_face5"));
                                            memberData.setAvatar_face6(jsonTags.getJSONObject(y).getString("avatar_face6"));
                                            memberData.setFirst_name(jsonTags.getJSONObject(y).getString("first_name"));
                                            memberData.setId(jsonTags.getJSONObject(y).getString("id"));
                                            memberData.setIs_verified(jsonTags.getJSONObject(y).getBoolean("is_verified"));
                                            memberData.setLast_name(jsonTags.getJSONObject(y).getString("last_name"));
                                            memberData.setOnline(jsonTags.getJSONObject(y).getBoolean("online"));
                                            memberData.setUsername(jsonTags.getJSONObject(y).getString("username"));


                                            memberList.add(memberData);
                                        }
                                        member.setMemberList(memberList);
                                    }
                                    if (dataName.equals("current_page")) {

                                        member.setCurrent_page(dataJson.getString(dataName));
                                    }
                                    if (dataName.equals("first_page")) {
                                        member.setFirst_page(dataJson.getBoolean(dataName));
                                    }
                                    if (dataName.equals("last_page")) {
                                        member.setLast_page(dataJson.getBoolean(dataName));
                                    }
                                    if (dataName.equals("next_page")) {


                                        member.setNext_page(dataJson.getString(dataName));
                                        Log.e("TAG", ".member.getNext_page...."+member.getNext_page());
                                    }
                                    if (dataName.equals("prev_page")) {
                                        member.setPrev_page(dataJson.getString(dataName));
                                    }
                                    if (dataName.equals("total_pages")) {
                                        member.setTotal_pages(dataJson.getInt(dataName));
                                    }
                                }

                            }

                        }
                    }
//                    return user;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            isLoading = false;
            //


            mAdapter.notifyItemRangeChanged(0, memberList.size());

        }

    }

    private void getMembersRequest(String memberUrl, boolean isShowLoading) throws JSONException {

        JSONObject mJson = new JSONObject();
        if(null!=member  && null!=member.getNext_page())
        {
            mJson.put("page", member.getNext_page());
        }

        //Log.e("Tag", "test................");
        HashMap<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, isShowLoading,"GET", this, memberUrl, mJson, extraHeaders).execute();
        //Log.e("Tag", "test................11");

    }

    private void createGroupChannel(final List<String> userIds, boolean distinct) {
        try {
            Log.e("TAG", "userID............"+userIds);
            GroupChannel.createChannelWithUserIds(userIds, distinct, new GroupChannel.GroupChannelCreateHandler() {
                @Override
                public void onResult(GroupChannel groupChannel, SendBirdException e) {
                    if (e != null) {
                        // Error!
                        Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println("Error "+e.getMessage());
                        return;
                    }

                    Log.e("TAG", "EXTRA_NEW_CHANNEL_URL............"+groupChannel.getUrl());

//                    Intent intent = new Intent(getApplication(), GroupChannelActivity.class);
//                    intent.putExtra(GROUP_CHANNEL_URL, groupChannel.getUrl());
//                    setResult(RESULT_OK, intent);
//                    finish();


                    Intent intent=new Intent(getApplication(), GroupChannelActivity.class);
                    intent.putExtra(GROUP_CHANNEL_URL, groupChannel.getUrl());
                    startActivity(intent);


                    //GroupChannelActivity
                }
            });

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }



    @Override
    public void onDistinctSelected(boolean distinct) {
        mIsDistinct = distinct;
    }


    @Override
    public void onUserSelected(boolean selected, String userId) {
        if (selected) {
            mSelectedIds.add(userId);
        } else {
            mSelectedIds.remove(userId);
        }

        if (mSelectedIds.size() > 0) {
//            mCreateButton.setEnabled(true);

        } else {
   //         mCreateButton.setEnabled(false);

        }
    }
}

