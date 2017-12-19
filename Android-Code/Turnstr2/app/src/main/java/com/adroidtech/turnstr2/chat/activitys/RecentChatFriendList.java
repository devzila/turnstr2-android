package com.adroidtech.turnstr2.chat.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.WebServices.AsyncCallback;
import com.adroidtech.turnstr2.chat.adapters.FriendList_Adapter;

/**
 * Created by narinder on 18/12/17.
 */

public class RecentChatFriendList extends AppCompatActivity implements AsyncCallback {


    private RecyclerView recyclerView;
    private FriendList_Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentchat_friendl_ist);


        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new FriendList_Adapter(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void getAsyncResult(String jsonObject, String txt) {

    }



}

