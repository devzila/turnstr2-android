package com.wdp.Agora.live;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;


public class LiveStartActivity extends BaseActivity {
    private static final String TAG = LiveStartActivity.class.getSimpleName();
    private CommonSharedPreference commonSharedPreference;
    Button button;
    String liveType;


    public void LiveStartActivity(String livetype) {
        liveType = livetype;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livestart);
        commonSharedPreference = new CommonSharedPreference();
        LoginResDataModal loginResDataModal = commonSharedPreference.getSubscriberLoginSharedPref(this);
        liveType = commonSharedPreference.getLiveType(this);
        Intent intent = new Intent(LiveStartActivity.this, LiveActivity.class);
        Log.d("liveType","----->" + liveType);
        if (liveType.equalsIgnoreCase("audience")) {
            CommonSharedPreference sharedPreferenceUserAuth = new CommonSharedPreference();
            String room  = sharedPreferenceUserAuth.getTitleName(this);
            config().setChannelName(room);
            Log.d("title","---->" + sharedPreferenceUserAuth.getTitleName(this));
            intent.putExtra(Constants.KEY_CLIENT_ROLE, io.agora.rtc.Constants.CLIENT_ROLE_AUDIENCE);
        } else {
            String room = loginResDataModal.getData().getUser().getUsername();
            Log.d("title","---1->" + room);
            Log.d("title","--0-->" + liveType);
            config().setChannelName(room);
            intent.putExtra(Constants.KEY_CLIENT_ROLE, io.agora.rtc.Constants.CLIENT_ROLE_BROADCASTER);
        }
        startActivity(intent);
        finish();

    }

    private void initUI() {


    }

    @Override
    protected void onGlobalLayoutCompleted() {

    }

}
