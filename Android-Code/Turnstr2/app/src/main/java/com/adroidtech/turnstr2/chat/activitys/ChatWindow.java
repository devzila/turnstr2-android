package com.adroidtech.turnstr2.chat.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.adroidtech.turnstr2.R;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.SendBirdException;

/**
 * Created by narinder on 19/12/17.
 */

public class ChatWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);


//        GroupChannel.createChannelWithUserIds(USER_IDS, true, new GroupChannel.GroupChannelCreateHandler() {
//            @Override
//            public void onResult(GroupChannel groupChannel, SendBirdException e) {
//                if (e != null) {
//                    // Error.
//                    return;
//                }
//            }
//        });



    }

}
