package com.wdp.Agora;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.SurfaceView;
import com.wdp.Agora.EngineConfig;
import com.wdp.Agora.EventHandler;
import com.wdp.Agora.live.BaseActivity;
import com.wdp.Modal.LoginResDataModal;
import com.wdp.Utility.CommonSharedPreference;
import com.wdp.turnstr.R;

import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

public abstract class RtcBaseActivity extends BaseActivity implements EventHandler {

    public static VideoEncoderConfiguration.VideoDimensions[] VIDEO_DIMENSIONS = new VideoEncoderConfiguration.VideoDimensions[]{
            VideoEncoderConfiguration.VD_320x240,
            VideoEncoderConfiguration.VD_480x360,
            VideoEncoderConfiguration.VD_640x360,
            VideoEncoderConfiguration.VD_640x480,
            new VideoEncoderConfiguration.VideoDimensions(960, 540),
            VideoEncoderConfiguration.VD_1280x720
    };
    public static int[] VIDEO_MIRROR_MODES = new int[]{
            io.agora.rtc.Constants.VIDEO_MIRROR_MODE_AUTO,
            io.agora.rtc.Constants.VIDEO_MIRROR_MODE_ENABLED,
            io.agora.rtc.Constants.VIDEO_MIRROR_MODE_DISABLED,
    };

    LoginResDataModal loginResDataModal;
    String channel_name="new";
    CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerRtcEventHandler(this);
        configVideo();
        joinChannel();
    }

    private void configVideo() {
        VideoEncoderConfiguration configuration = new VideoEncoderConfiguration(
                VIDEO_DIMENSIONS[config().getVideoDimenIndex()],
                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
        );
        configuration.mirrorMode = VIDEO_MIRROR_MODES[config().getMirrorEncodeIndex()];
        rtcEngine().setVideoEncoderConfiguration(configuration);
    }

    private void joinChannel() {
        // Initialize token, extra info here before joining channel
        // 1. Users can only see each other after they join the
        // same channel successfully using the same app id.
        // 2. One token is only valid for the channel name and uid that
        // you use to generate this token.
        String token = getString(R.string.agora_access_token);
        if (TextUtils.isEmpty(token) || TextUtils.equals(token, "")) {
            token = null; // default, no token
        }
        rtcEngine().joinChannel(token, config().getChannelName(), "", 0);
    }

    protected SurfaceView prepareRtcVideo(int uid, boolean local) {
        // Render local/remote video on a SurfaceView

        SurfaceView surface = RtcEngine.CreateRendererView(getApplicationContext());
        if (local) {
            rtcEngine().setupLocalVideo(
                    new VideoCanvas(
                            surface,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            0,
                            VIDEO_MIRROR_MODES[config().getMirrorLocalIndex()]
                    )
            );
        } else {
            rtcEngine().setupRemoteVideo(
                    new VideoCanvas(
                            surface,
                            VideoCanvas.RENDER_MODE_HIDDEN,
                            uid,
                            VIDEO_MIRROR_MODES[config().getMirrorRemoteIndex()]
                    )
            );
        }
        return surface;
    }

    protected void removeRtcVideo(int uid, boolean local) {
        if (local) {
            rtcEngine().setupLocalVideo(null);
        } else {
            rtcEngine().setupRemoteVideo(new VideoCanvas(null, VideoCanvas.RENDER_MODE_HIDDEN, uid));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeRtcEventHandler(this);
        rtcEngine().leaveChannel();
    }
}
