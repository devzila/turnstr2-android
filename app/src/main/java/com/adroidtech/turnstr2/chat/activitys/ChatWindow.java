package com.adroidtech.turnstr2.chat.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.adroidtech.turnstr2.R;
import com.adroidtech.turnstr2.Utils.Serializer;
import com.adroidtech.turnstr2.chat.models.Member;

import java.io.IOException;

/**
 * Created by narinder on 19/12/17.
 */

public class ChatWindow extends AppCompatActivity {

    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);


        Bundle args = new Bundle();
        args = getIntent().getExtras();
        if (null != args) {
            try {

                member = (Member) Serializer.deserialize(args.getByteArray("CHAT"));

                Log.e("TAG", "....................." + member.getId() + " ............." + member.getFirst_name());


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }



    }

}
