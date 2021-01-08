package com.wdp.Agora.live;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import com.danikula.videocache.HttpProxyCacheServer;
import com.sendbird.android.SendBird;
import com.wdp.Agora.AgoraEventHandler;
import com.wdp.Agora.EngineConfig;
import com.wdp.Agora.EventHandler;
import com.wdp.Agora.RTM.ChatManager;
import com.wdp.Agora.stats.StatsManager;
import com.wdp.Agora.utils.FileUtil;
import com.wdp.Agora.utils.PrefManager;
import com.wdp.Agora.utils.PushUtils;
import com.wdp.Firebase.MyFirebaseMessagingService;
import com.wdp.turnstr.R;

import io.agora.rtc.RtcEngine;


public class AgoraApplication extends Application {
    private RtcEngine mRtcEngine;
    private EngineConfig mGlobalConfig = new EngineConfig();
    private AgoraEventHandler mHandler = new AgoraEventHandler();
    private StatsManager mStatsManager = new StatsManager();
    private static final String APP_ID = "09BEC17E-F4F6-4C67-B34A-FEC990CCEF23"; // US-1 Demo
    public static final String VERSION = "3.0.40";
    private HttpProxyCacheServer proxy;
    private static AgoraApplication sInstance;
    public static AgoraApplication the() {
        return sInstance;
    }
    private ChatManager mChatManager;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            AgoraApplication.the();
            SendBird.init(APP_ID, getApplicationContext());
            sInstance = this;
            mChatManager = new ChatManager(this);
            mChatManager.init();
            mRtcEngine = RtcEngine.create(getApplicationContext(), getString(R.string.agora_app_id), mHandler);
            mRtcEngine.setChannelProfile(io.agora.rtc.Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
            mRtcEngine.enableVideo();
            mRtcEngine.setLogFile(FileUtil.initializeLogFile(this));
            //  sInstance = this;

        } catch (Exception e) {
            e.printStackTrace();
        }

        initConfig();
    }
    public static HttpProxyCacheServer getProxy(Context context) {
       AgoraApplication app = (AgoraApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024)
                .build();
    }
    private void initConfig() {
        SharedPreferences pref = PrefManager.getPreferences(getApplicationContext());
        mGlobalConfig.setVideoDimenIndex(pref.getInt(
                Constants.PREF_RESOLUTION_IDX, Constants.DEFAULT_PROFILE_IDX));

        boolean showStats = pref.getBoolean(Constants.PREF_ENABLE_STATS, false);
        mGlobalConfig.setIfShowVideoStats(showStats);
        mStatsManager.enableStats(showStats);

        mGlobalConfig.setMirrorLocalIndex(pref.getInt(Constants.PREF_MIRROR_LOCAL, 0));
        mGlobalConfig.setMirrorRemoteIndex(pref.getInt(Constants.PREF_MIRROR_REMOTE, 0));
        mGlobalConfig.setMirrorEncodeIndex(pref.getInt(Constants.PREF_MIRROR_ENCODE, 0));
    }

    public EngineConfig engineConfig() {
        return mGlobalConfig;
    }

    public RtcEngine rtcEngine() {
        return mRtcEngine;
    }

    public StatsManager statsManager() {
        return mStatsManager;
    }

    public void registerEventHandler(EventHandler handler) {
        mHandler.addHandler(handler);
    }

    public void removeEventHandler(EventHandler handler) {
        mHandler.removeHandler(handler);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        RtcEngine.destroy();
    }

    public ChatManager getChatManager() {
        return mChatManager;
    }
}
