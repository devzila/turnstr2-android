package com.adroidtech.turnstr2.chat.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.GeneralValues;
import com.adroidtech.turnstr2.Utils.PreferenceKeys;
import com.adroidtech.turnstr2.Utils.Serializer;
import com.adroidtech.turnstr2.Utils.SharedPreference;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.WebServices.CommonAsync;
import com.adroidtech.turnstr2.chat.adapters.AllFriendList_Adapter;
import com.adroidtech.turnstr2.chat.listeners.RecyclerItemClickListener;
import com.adroidtech.turnstr2.chat.models.Member;

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

public class AllFriendList extends AppCompatActivity implements AsyncCallback {


    private RecyclerView recyclerView;
    private AllFriendList_Adapter mAdapter;
    private SharedPreference sharedPreference;
    private String TAG = "AllFriendList";
    Member member = new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        sharedPreference = new SharedPreference(getApplicationContext());
        init();
        getVideoRequest();

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        if(null!=member)
                        {
                            try {
                                Intent intent=new Intent(getApplicationContext(), ChatWindow.class);
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("CHAT", Serializer.serialize(member.getMemberList().get(position)));
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

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
                                    if (dataName.equals("members")) {
                                        List<Member> memberList = new ArrayList<>();
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

            //
            mAdapter = new AllFriendList_Adapter(getApplicationContext(), member.getMemberList());
            recyclerView.setAdapter(mAdapter);


        }

    }

    private void getVideoRequest() {

        JSONObject mJson = new JSONObject();

        HashMap<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("auth_token", sharedPreference.getString(PreferenceKeys.APP_AUTH_TOKEN));
        new CommonAsync(this, "GET", this, GeneralValues.MEMBERS_URL, mJson, extraHeaders).execute();
        Log.e("Tag", "test................");


    }


}

