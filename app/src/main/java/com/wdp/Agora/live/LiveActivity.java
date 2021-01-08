package com.wdp.Agora.live;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.wdp.Agora.RTM.ChatManager;
import com.wdp.Agora.RTM.MessageAdapter;
import com.wdp.Agora.RTM.MessageBean;
import com.wdp.Agora.RTM.MessageEmojiAdapter;
import com.wdp.Agora.RTM.MessageUtil;
import com.wdp.Agora.stats.LocalStatsData;
import com.wdp.Agora.stats.RemoteStatsData;
import com.wdp.Agora.stats.StatsData;
import com.wdp.Agora.ui.VideoGridContainer;
import com.wdp.ApiServices.InviteApiService;
import com.wdp.ApiServices.LiveBroadCastApiService;
import com.wdp.Interface.ApiResponseListner;
import com.wdp.Interface.SuperCastClass;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.Utility.NetworkCheck;
import com.wdp.turnstr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.video.VideoEncoderConfiguration;
import io.agora.rtm.ErrorInfo;
import io.agora.rtm.ResultCallback;
import io.agora.rtm.RtmChannel;
import io.agora.rtm.RtmChannelAttribute;
import io.agora.rtm.RtmChannelListener;
import io.agora.rtm.RtmChannelMember;
import io.agora.rtm.RtmClient;
import io.agora.rtm.RtmClientListener;
import io.agora.rtm.RtmMessage;
import io.agora.rtm.RtmStatusCode;


public class LiveActivity extends RtcBaseActivity implements ApiResponseListner,MessageEmojiAdapter.OnIteamclick {
    private static final String TAG = LiveActivity.class.getSimpleName();
    private VideoGridContainer mVideoGridContainer;
    private ImageView mMuteAudioBtn;
    private ImageView mMuteVideoBtn;
    private VideoEncoderConfiguration.VideoDimensions mVideoDimension;
    //Real Time Messaging........
    private EditText mMsgEditText;
    private RecyclerView mRecyclerView,message_list_emoji;
    private List<MessageBean> mMessageBeanList = new ArrayList<>();
    private MessageAdapter mMessageAdapter;
    private MessageEmojiAdapter messageEmojiAdapter;
    private boolean mIsPeerToPeerMode = true;
    private String mUserId = "";
    private String mPeerId = "";
    private String mChannelName = "";
    private int mChannelMemberCount = 0;
    private ChatManager mChatManager;
    private RtmClient mRtmClient;
    private RtmClientListener mClientListener;
    private RtmChannel mRtmChannel;
    int role;
    ArrayList<String> emojiList = new ArrayList<>();
    RelativeLayout rl_bottam;
    ImageView img_send;
    boolean isBroadcaster;
    ImageView live_btn_mute_video;
    TextView txt_message,txt_reguest;
    LoginResDataModal loginModel;
    AlertDialog alertDialog;
    String otheruserid;
    String otherusername;
    String otheruserimage;
    RelativeLayout rl_inviteuser;
    String message;
    private TextView txt_eyes;
    private CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_room);
        initUI();
        initData();
        initRealTimeMessaging();

    }

    private void initUI() {
        txt_eyes = findViewById(R.id.txt_eyes);
        TextView roomName = findViewById(R.id.live_room_name);
        roomName.setText(config().getChannelName());
        roomName.setSelected(true);
        initUserIcon();
        loginModel = commonSharedPreference.getSubscriberLoginSharedPref(this);

        role = getIntent().getIntExtra(com.wdp.Agora.live.Constants.KEY_CLIENT_ROLE, Constants.CLIENT_ROLE_AUDIENCE);
        isBroadcaster =  (role == Constants.CLIENT_ROLE_BROADCASTER);
        mMuteVideoBtn = findViewById(R.id.live_btn_mute_video);
        live_btn_mute_video = findViewById(R.id.live_btn_mute_video);
        rl_inviteuser = findViewById(R.id.rl_inviteuser);
        mMuteVideoBtn.setActivated(isBroadcaster);

        mMuteAudioBtn = findViewById(R.id.live_btn_mute_audio);
        mMuteAudioBtn.setActivated(isBroadcaster);

        ImageView beautyBtn = findViewById(R.id.live_btn_beautification);
        beautyBtn.setActivated(true);
        rtcEngine().setBeautyEffectOptions(beautyBtn.isActivated(), com.wdp.Agora.live.Constants.DEFAULT_BEAUTY_OPTIONS);
        message_list_emoji = findViewById(R.id.message_list_emoji);
        mVideoGridContainer = findViewById(R.id.live_video_grid_layout);
        mVideoGridContainer.setStatsManager(statsManager());

        txt_message = findViewById(R.id.txt_message);
        txt_reguest = findViewById(R.id.txt_reguest);
        mMsgEditText = findViewById(R.id.edt_message);
        rtcEngine().setClientRole(role);
        if (isBroadcaster) startBroadcast();

        rl_bottam = findViewById(R.id.rl_bottam);
        img_send = findViewById(R.id.img_send);

        if (isBroadcaster){
            rl_bottam.setVisibility(View.VISIBLE);
            emojiList.add("Thank You!");
            rl_inviteuser.setVisibility(View.GONE);
        }
        else {
            rl_inviteuser.setVisibility(View.VISIBLE);
            txt_message.setText(getResources().getString(R.string.send_request_to_join_live));
            txt_reguest.setText(getResources().getString(R.string.request));
        }


        emojiList.add("Hello!");
        emojiList.add("👋");
        emojiList.add("👍");
        emojiList.add("😍😍😍");
        emojiList.add("😂");
        emojiList.add("😜");
        emojiList.add("😎");

        txt_reguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_reguest.getText().toString().equalsIgnoreCase("Accept")){
                    message = getResources().getString(R.string.do_you_want_to_invite )+" " + otherusername + " " + getResources().getString(R.string.into_your_live);
                    String url = "members"+"/"+ otherusername+"/"+ "invite?channel=" + loginModel.getData().getUser().getUsername() + "&username=1";
                    Log.d("url","---->>>>" + url);
                    showInviteDialog(message,url);
                }
                else {
                    String profileUrl = "null";
                    if (loginModel.getData().getUser().getAvatar() != null){
                        if (loginModel.getData().getUser().getAvatar() != null){
                            profileUrl = loginModel.getData().getUser().getAvatar().getThumb();
                        }
                    }
                    String finalmessage = "requested to join your live." +">>>>" +  profileUrl + ">>>>" +loginModel.getData().getUser().getId();
                    sendChannelMessage(finalmessage);
                    txt_reguest.setText(getResources().getString(R.string.wating_for_invite));
                }

            }
        });





        img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = mMsgEditText.getText().toString();
                String profileUrl = "null";
                if (loginModel.getData().getUser().getAvatar() != null){
                    profileUrl = loginModel.getData().getUser().getAvatar().getThumb();
                }
                String finalmessage = msg +">>>>" + profileUrl  + ">>>>" +loginModel.getData().getUser().getId();
                if (!msg.equals("")) {
                    MessageBean messageBean = new MessageBean(mUserId, msg, profileUrl,true);
                    mMessageBeanList.add(messageBean);
                    mMessageAdapter.notifyItemRangeChanged(mMessageBeanList.size(), 1);
                    if (mMessageBeanList.size() >1){
                        mRecyclerView.scrollToPosition(mMessageBeanList.size() - 1);
                        mMessageAdapter.notifyDataSetChanged();
                    }
                    Log.d("mMessageBeanList","---->" + mMessageBeanList.size());
                    sendChannelMessage(finalmessage);
                }
                mMsgEditText.setText("");
            }
        });

        commonSharedPreference = new CommonSharedPreference();



    }

    public BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message.contains(getResources().getString(R.string.join_broadcast))){
                if (!isBroadcaster){
                    showJoinDialog("Join");
                }
            }
        }
    };

    private void connectInvite(String url) {
        if (NetworkCheck.isConnected(this)){
            InviteApiService inviteApiService = new InviteApiService(this);
            inviteApiService.Connect(url,loginModel.getData().getToken(),this);
        }
        else {
            Toast.makeText(this,getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void connectRemoveBroadcast(){
        if (NetworkCheck.isConnected(this)){
            commonSharedPreference = new CommonSharedPreference();
            LiveBroadCastApiService liveBroadCastApiService = new LiveBroadCastApiService(this);
            liveBroadCastApiService.Connect1(loginModel.getData().getUser().getId(),this);
        }
        else {
            Toast.makeText(this,getResources().getString(R.string.please_check_internet_connection), Toast.LENGTH_LONG).show();
        }
     }


    private void initUserIcon() {
        Bitmap origin = BitmapFactory.decodeResource(getResources(), R.drawable.user);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), origin);
        drawable.setCircular(true);
        ImageView iconView = findViewById(R.id.live_name_board_icon);
        iconView.setImageDrawable(drawable);
    }

    private void initData() {
        mVideoDimension = com.wdp.Agora.live.Constants.VIDEO_DIMENSIONS[
                config().getVideoDimenIndex()];
    }

    @Override
    protected void onGlobalLayoutCompleted() {
        RelativeLayout topLayout = findViewById(R.id.live_room_top_layout);
        RelativeLayout.LayoutParams params =
                (RelativeLayout.LayoutParams) topLayout.getLayoutParams();
        params.height = mStatusBarHeight + topLayout.getMeasuredHeight();
        topLayout.setLayoutParams(params);
        topLayout.setPadding(0, mStatusBarHeight, 0, 0);
    }

    private void startBroadcast() {
        rtcEngine().setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
        SurfaceView surface = prepareRtcVideo(0, true);
        mVideoGridContainer.addUserVideoSurface(0, surface, true);
        mMuteAudioBtn.setActivated(true);
    }

    private void stopBroadcast() {
        rtcEngine().setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
        removeRtcVideo(0, true);
        mVideoGridContainer.removeUserVideo(0, true);
        mMuteAudioBtn.setActivated(false);
    }

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        // Do nothing at the moment
    }

    @Override
    public void onUserJoined(int uid, int elapsed) {
        // Do nothing at the moment
    }

    @Override
    public void onUserOffline(final int uid, int reason) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removeRemoteUser(uid);
                finish();
            }
        });
    }

    @Override
    public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                renderRemoteUser(uid);
            }
        });
    }

    private void renderRemoteUser(int uid) {
        SurfaceView surface = prepareRtcVideo(uid, false);
        mVideoGridContainer.addUserVideoSurface(uid, surface, false);
    }

    private void removeRemoteUser(int uid) {
        removeRtcVideo(uid, false);
        mVideoGridContainer.removeUserVideo(uid, false);
    }

    @Override
    public void onLocalVideoStats(IRtcEngineEventHandler.LocalVideoStats stats) {
        if (!statsManager().isEnabled()) return;

        LocalStatsData data = (LocalStatsData) statsManager().getStatsData(0);
        if (data == null) return;

        data.setWidth(mVideoDimension.width);
        data.setHeight(mVideoDimension.height);
        data.setFramerate(stats.sentFrameRate);
    }

    @Override
    public void onRtcStats(IRtcEngineEventHandler.RtcStats stats) {
        if (!statsManager().isEnabled()) return;

        LocalStatsData data = (LocalStatsData) statsManager().getStatsData(0);
        if (data == null) return;
        data.setLastMileDelay(stats.lastmileDelay);
        data.setVideoSendBitrate(stats.txVideoKBitRate);
        data.setVideoRecvBitrate(stats.rxVideoKBitRate);
        data.setAudioSendBitrate(stats.txAudioKBitRate);
        data.setAudioRecvBitrate(stats.rxAudioKBitRate);
        data.setCpuApp(stats.cpuAppUsage);
        data.setCpuTotal(stats.cpuAppUsage);
        data.setSendLoss(stats.txPacketLossRate);
        data.setRecvLoss(stats.rxPacketLossRate);
    }

    @Override
    public void onNetworkQuality(int uid, int txQuality, int rxQuality) {
        if (!statsManager().isEnabled()) return;
        StatsData data = statsManager().getStatsData(uid);
        if (data == null) return;

        data.setSendQuality(statsManager().qualityToString(txQuality));
        data.setRecvQuality(statsManager().qualityToString(rxQuality));
    }

    @Override
    public void onRemoteVideoStats(IRtcEngineEventHandler.RemoteVideoStats stats) {
        if (!statsManager().isEnabled()) return;

        RemoteStatsData data = (RemoteStatsData) statsManager().getStatsData(stats.uid);
        if (data == null) return;

        data.setWidth(stats.width);
        data.setHeight(stats.height);
        data.setFramerate(stats.rendererOutputFrameRate);
        data.setVideoDelay(stats.delay);
    }

    @Override
    public void onRemoteAudioStats(IRtcEngineEventHandler.RemoteAudioStats stats) {
        if (!statsManager().isEnabled()) return;

        RemoteStatsData data = (RemoteStatsData) statsManager().getStatsData(stats.uid);
        if (data == null) return;

        data.setAudioNetDelay(stats.networkTransportDelay);
        data.setAudioNetJitter(stats.jitterBufferDelay);
        data.setAudioLoss(stats.audioLossRate);
        data.setAudioQuality(statsManager().qualityToString(stats.quality));
    }

    @Override
    public void finish() {
        super.finish();
        statsManager().clearAllData();
    }

    public void onLeaveClicked(View view) {
        finish();
    }

    public void onSwitchCameraClicked(View view) {
        rtcEngine().switchCamera();
    }

    public void onBeautyClicked(View view) {
        view.setActivated(!view.isActivated());
        rtcEngine().setBeautyEffectOptions(view.isActivated(), com.wdp.Agora.live.Constants.DEFAULT_BEAUTY_OPTIONS);
    }

    public void onMoreClicked(View view) {
        // Do nothing at the moment
    }

    public void onPushStreamClicked(View view) {
        // Do nothing at the moment
    }

    public void onMuteAudioClicked(View view) {
        if (!mMuteVideoBtn.isActivated()) return;

        rtcEngine().muteLocalAudioStream(view.isActivated());
        view.setActivated(!view.isActivated());
    }

    public void onMuteVideoClicked(View view) {
        if (view.isActivated()) {
            stopBroadcast();
        } else {
            startBroadcast();
        }
        view.setActivated(!view.isActivated());
    }


    public void initRealTimeMessaging(){
        mChatManager = AgoraApplication.the().getChatManager();
        mRtmClient = mChatManager.getRtmClient();
        mClientListener = new MyRtmClientListener();
        mChatManager.registerListener(mClientListener);
        Intent intent = getIntent();
        mIsPeerToPeerMode = intent.getBooleanExtra(MessageUtil.INTENT_EXTRA_IS_PEER_MODE, true);
        CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
        final String targetName;
        mUserId = String.valueOf(loginModel.getData().getUser().getUsername());
        if (role ==1){
            targetName = loginModel.getData().getUser().getUsername();
        }
        else {
            targetName = commonSharedPreference.getTitleName(this);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(LiveActivity.this);
        mMessageAdapter = new MessageAdapter(LiveActivity.this, mMessageBeanList);
        mRecyclerView = findViewById(R.id.message_list);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMessageAdapter);


        LinearLayoutManager layoutManagereMOJI = new LinearLayoutManager(LiveActivity.this, LinearLayoutManager.HORIZONTAL, false);
        messageEmojiAdapter = new MessageEmojiAdapter(LiveActivity.this, emojiList,this);
        message_list_emoji.setLayoutManager(layoutManagereMOJI);
        message_list_emoji.setAdapter(messageEmojiAdapter);

        mRtmClient.login(null, mUserId, new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void responseInfo) {
                Log.i(TAG, "login success");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChannelName = targetName;
                        mChannelMemberCount = 0;
                        createAndJoinChannel();



                    }
                });
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                Log.i(TAG, "login failed: " + errorInfo.getErrorCode());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        });





        message_list_emoji = findViewById(R.id.message_list_emoji);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver, new IntentFilter("GizmohJoin"));


    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }


    public void showInviteDialog(String message, String url) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_invite_live, null);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.show();
        TextView txt_message = alertDialog.findViewById(R.id.txt_message);
        TextView txt_invite = alertDialog.findViewById(R.id.txt_invite);
        TextView txt_cancel = alertDialog.findViewById(R.id.txt_cancel);
        txt_message.setText(message);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        txt_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                connectInvite(url);
            }
        });


    }

    public void showJoinDialog(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_invite, null);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.show();
        TextView txt_message = alertDialog.findViewById(R.id.txt_message);
        TextView txt_invite = alertDialog.findViewById(R.id.txt_invite);
        txt_invite.setText(message);
        TextView txt_cancel = alertDialog.findViewById(R.id.txt_cancel);
        txt_message.setText(message);
        String mess = message + " " + otherusername +" " +  "as live.";
        txt_message.setText(mess);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        txt_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                String profileUrl = "null";
                if (loginModel.getData().getUser().getAvatar() != null){
                    profileUrl = loginModel.getData().getUser().getAvatar().getThumb();
                }
                String finalmessage = "has joined your live." +">>>>" +  profileUrl  + ">>>>" +loginModel.getData().getUser().getId();
                sendChannelMessage(finalmessage);
                onMuteVideoClicked(live_btn_mute_video);
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChatManager.unregisterListener(mClientListener);
        leaveAndReleaseChannel();
        mRtmClient.logout( new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {

            }
        });
        connectRemoveBroadcast();
        Log.d("onBackPress","----1-->" + "onBackPressed");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("onBackPress","------>" + "onBackPressed");
        leaveAndReleaseChannel();
        mChatManager.unregisterListener(mClientListener);
        mRtmClient.logout( new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
            }
        });
        connectRemoveBroadcast();

    }


    public void onClickFinish(View v) {
        finish();
    }



    private void createAndJoinChannel() {
        mRtmChannel = mRtmClient.createChannel(mChannelName, new MyChannelListener());
        if (mRtmChannel == null) {
            showToast(getString(R.string.join_channel_failed));
            finish();
            return;
        }

        Log.e("channel", mRtmChannel + "");

        // step 2: join the channel
        mRtmChannel.join(new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void responseInfo) {
                Log.i(TAG, "join channel success");
                getChannelMemberList();
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                Log.e(TAG, "join channel failed");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(getString(R.string.join_channel_failed));
                        finish();
                    }
                });
            }
        });
    }
    private void getChannelMemberList() {
        mRtmChannel.getMembers(new ResultCallback<List<RtmChannelMember>>() {
            @Override
            public void onSuccess(final List<RtmChannelMember> responseInfo) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mChannelMemberCount = responseInfo.size();
                        refreshChannelTitle();
                    }
                });
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                Log.e(TAG, "failed to get channel members, err: " + errorInfo.getErrorCode());
            }
        });
    }

    private void sendChannelMessage(String content)
    {
        // step 1: create a message
        RtmMessage message = mRtmClient.createMessage();
        message.setText(content);

        Log.e("channel", mRtmChannel + "");

        mRtmChannel.sendMessage(message, new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                // refer to RtmStatusCode.ChannelMessageState for the message state
                final int errorCode = errorInfo.getErrorCode();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (errorCode) {
                            case RtmStatusCode.ChannelMessageError.CHANNEL_MESSAGE_ERR_TIMEOUT:
                            case RtmStatusCode.ChannelMessageError.CHANNEL_MESSAGE_ERR_FAILURE:
                                showToast(getString(R.string.send_msg_failed));
                                break;
                        }
                    }
                });
            }
        });
    }

    private void leaveAndReleaseChannel() {
        if (mRtmChannel != null) {
            mRtmChannel.leave(null);
            mRtmChannel.release();
            mRtmChannel = null;
        }
    }

    @Override
    public void onSuccess(String Tag, SuperCastClass superCastClass) {
        if (Tag.equalsIgnoreCase("livebroadcast")){

        }

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onException(String message) {

    }

    @Override
    public void onclickiteam(int pos) {
        String emoji  = emojiList.get(pos);
        if (isBroadcaster){
            String profileUrl = "null";
            if (loginModel.getData().getUser().getAvatar() != null){
                profileUrl = loginModel.getData().getUser().getAvatar().getThumb();
            }
            String finalmessage = emoji +">>>>" +  profileUrl  + ">>>>" +loginModel.getData().getUser().getId();
            if (!finalmessage.equals("")) {
                MessageBean messageBean = new MessageBean(mUserId, emoji, profileUrl,true);
                mMessageBeanList.add(messageBean);
                mMessageAdapter.notifyItemRangeChanged(mMessageBeanList.size(), 1);
                if (mMessageBeanList.size() >1){
                    mRecyclerView.scrollToPosition(mMessageBeanList.size() - 1);
                }

                sendChannelMessage(finalmessage);
            }
        }
        else {
            String profileUrl = "null";
            if (loginModel.getData().getUser().getAvatar() != null){
                profileUrl = loginModel.getData().getUser().getAvatar().getThumb();
            }
            String finalmessage = emoji +">>>>" +  profileUrl + ">>>>" +loginModel.getData().getUser().getId();
            if (!finalmessage.equals("")) {
                MessageBean messageBean = new MessageBean(mUserId, emoji, profileUrl,true);
                mMessageBeanList.add(messageBean);
                mMessageAdapter.notifyItemRangeChanged(mMessageBeanList.size(), 1);
                if (mMessageBeanList.size() >1){
                    mRecyclerView.scrollToPosition(mMessageBeanList.size() - 1);

                }
                sendChannelMessage(finalmessage);
            }

        }


    }

    class MyRtmClientListener implements RtmClientListener {
        @Override
        public void onConnectionStateChanged(final int state, int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (state) {
                        case RtmStatusCode.ConnectionState.CONNECTION_STATE_RECONNECTING:
                            showToast(getString(R.string.reconnecting));
                            break;
                        case RtmStatusCode.ConnectionState.CONNECTION_STATE_ABORTED:
                            showToast(getString(R.string.account_offline));
                            setResult(MessageUtil.ACTIVITY_RESULT_CONN_ABORTED);
                            finish();
                            break;
                    }
                }
            });
        }

        @Override
        public void onMessageReceived(final RtmMessage message, final String peerId) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
        }

        @Override
        public void onTokenExpired() {

        }

        @Override
        public void onPeersOnlineStatusChanged(Map<String, Integer> map) {

        }
    }

    class MyChannelListener implements RtmChannelListener {
        @Override
        public void onMemberCountUpdated(int i) {

        }

        @Override
        public void onAttributesUpdated(List<RtmChannelAttribute> list) {

        }

        @Override
        public void onMessageReceived(final RtmMessage message, final RtmChannelMember fromMember) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    otherusername = fromMember.getUserId();
                    String msg = message.getText();
                    String[]msgArray = msg.split(">>>>");
                    String checkMsg = msgArray[msgArray.length - 3];
                    otheruserimage = msgArray[msgArray.length - 2];
                    otheruserid = msgArray[msgArray.length - 1];
                    Log.d("msg","---->" + otheruserid);
                    if (checkMsg.equalsIgnoreCase("requested to join your live.")){
                        if (isBroadcaster){
                            rl_inviteuser.setVisibility(View.VISIBLE);
                            txt_message.setText(getResources().getString(R.string.requested_to_join_your_live));
                            txt_reguest.setText(getResources().getString(R.string.accept));
                        }
                    }
                    else if (checkMsg.equalsIgnoreCase("has joined your live.")){

                    }
                    else {
                        MessageBean messageBean = new MessageBean(otheruserid, checkMsg,otheruserimage, false);
                        messageBean.setBackground(getMessageColor(otheruserid));
                        mMessageBeanList.add(messageBean);
                        mMessageAdapter.notifyItemRangeChanged(mMessageBeanList.size(), 1);
                        mRecyclerView.scrollToPosition(mMessageBeanList.size() - 1);
                        mMessageAdapter.notifyDataSetChanged();
                    }


                }
            });
        }

        @Override
        public void onMemberJoined(RtmChannelMember member) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChannelMemberCount++;
                    refreshChannelTitle();
                }
            });
        }

        @Override
        public void onMemberLeft(RtmChannelMember member) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mChannelMemberCount--;
                    refreshChannelTitle();
                }
            });
        }
    }


    private int getMessageColor(String account) {
        for (int i = 0; i < mMessageBeanList.size(); i++) {
            if (account.equals(mMessageBeanList.get(i).getAccount())) {
                return mMessageBeanList.get(i).getBackground();
            }
        }
        return MessageUtil.COLOR_ARRAY[MessageUtil.RANDOM.nextInt(MessageUtil.COLOR_ARRAY.length)];
    }

    private void refreshChannelTitle() {
        String titleFormat = getString(R.string.channel_title);
        String title = String.format(titleFormat, mChannelName, mChannelMemberCount);
        txt_eyes.setText(String.valueOf(mChannelMemberCount));
    }

    private void showToast(final String text) {
        Toast.makeText(LiveActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
