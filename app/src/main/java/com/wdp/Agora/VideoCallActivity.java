package com.wdp.Agora;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wdp.ActivityScreen.FollowingListActivity;
import com.wdp.Agora.EngineConfig;

import com.wdp.Agora.stats.LocalStatsData;
import com.wdp.Agora.stats.RemoteStatsData;

import com.wdp.Agora.stats.StatsData;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommonSharedPreference;



import com.wdp.turnstr.R;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class VideoCallActivity extends RtcBaseActivity implements View.OnClickListener {

    public static VideoEncoderConfiguration.VideoDimensions[] VIDEO_DIMENSIONS = new VideoEncoderConfiguration.VideoDimensions[]{
            VideoEncoderConfiguration.VD_320x240,
            VideoEncoderConfiguration.VD_480x360,
            VideoEncoderConfiguration.VD_640x360,
            VideoEncoderConfiguration.VD_640x480,
            new VideoEncoderConfiguration.VideoDimensions(960, 540),
            VideoEncoderConfiguration.VD_1280x720
    };
    boolean videoOn = true;
    Button btnVideoCall;
    private static final String TAG = VideoCallActivity.class.getSimpleName();
    private ImageView imgBack, mMuteAudioBtn;
    private VideoGridContainer mVideoGridContainer;
    LoginResDataModal loginResDataModal;
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    private ImageView imgFollowingList;
    public static final String KEY_CLIENT_ROLE = "key_client_role";
    private VideoEncoderConfiguration.VideoDimensions mVideoDimension;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_chat);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        imgBack = findViewById(R.id.imgBack);
        imgFollowingList = findViewById(R.id.imgFollowingList);
        btnVideoCall = findViewById(R.id.btnVideoCall);

        mMuteAudioBtn = findViewById(R.id.imgMic);
        mMuteAudioBtn.setActivated(true);
        mVideoGridContainer = findViewById(R.id.live_video_grid_layout);
        mVideoGridContainer.setStatsManager( statsManager());

        imgBack.setOnClickListener(this);
        imgFollowingList.setOnClickListener(this);

        rtcEngine().setClientRole(1);
        startBroadcast();
        mVideoDimension = VIDEO_DIMENSIONS[
                new EngineConfig().getVideoDimenIndex()];



    }
    public void onLocalAudioMuteClicked(View view){
        if (!videoOn) return;
        rtcEngine().muteLocalAudioStream(view.isActivated());
        view.setActivated(!view.isActivated());
    }
    private void startBroadcast() {
        rtcEngine().setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
        SurfaceView surface = prepareRtcVideo(0, true);
        mVideoGridContainer.addUserVideoSurface(0, surface, true);
        mMuteAudioBtn.setActivated(true);
        rtcEngine().muteLocalAudioStream(false);
    }

    private void stopBroadcast() {
        rtcEngine().setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
        removeRtcVideo(0, true);
        mVideoGridContainer.removeUserVideo(0, true);
        mMuteAudioBtn.setActivated(true);
        rtcEngine().muteLocalAudioStream(true);
    }

    public void onSwitchCameraClicked(View view) {
        rtcEngine().switchCamera();
    }

    public void onCallClicked(View view){
        if (videoOn){
            videoOn =true;
            stopBroadcast();
            btnVideoCall.setText(getResources().getString(R.string.startcall));
            finish();
        }else {
            videoOn =true;
            startBroadcast();
            btnVideoCall.setText(getResources().getString(R.string.endcall));
        }
    }

    private void removeRemoteUser(int uid) {
        removeRtcVideo(uid, false);
        mVideoGridContainer.removeUserVideo(uid, false);
        count-=1;
        if (count==0){
            stopBroadcast();
            finish();
        }
    }

    private void renderRemoteUser(int uid) {
        SurfaceView surface = prepareRtcVideo(uid, false);
        mVideoGridContainer.addUserVideoSurface(uid, surface, false);
        count+=1;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.imgBack){
            finish();
        }else if (id==R.id.imgFollowingList){
            Intent intent = new Intent(this, FollowingListActivity.class);
            intent.putExtra("from", "VideoCallActivity");
            startActivity(intent);
        }
    }

    @Override
    public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                renderRemoteUser(uid);
            }
        });
    }

    @Override
    public void onLeaveChannel(IRtcEngineEventHandler.RtcStats stats) {

    }

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {

    }

    @Override
    public void onUserOffline(int uid, int reason) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removeRemoteUser(uid);
            }
        });
    }

    @Override
    public void onUserJoined(int uid, int elapsed) {

    }

    @Override
    public void onLastmileQuality(int quality) {

    }

    @Override
    public void onLastmileProbeResult(IRtcEngineEventHandler.LastmileProbeResult result) {

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
}
