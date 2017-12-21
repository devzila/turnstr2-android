package com.adroidtech.turnstr2.chat.activitys;


import android.app.Application;

import com.sendbird.android.SendBird;

public class BaseApplication extends Application {
//CC0CEA62-9A18-4ECA-9BEC-B5D93DEC75E4//CC0CEA62-9A18-4ECA-9BEC-B5D93DEC75E4
    private static final String APP_ID = "CC0CEA62-9A18-4ECA-9BEC-B5D93DEC75E4";//"9DA1B1F4-0BE6-4DA8-82C5-2E81DAB56F23"; // US-1 Demo
    public static final String VERSION = "3.0.38";

    @Override
    public void onCreate() {
        super.onCreate();
        SendBird.init(APP_ID, getApplicationContext());
    }
}
