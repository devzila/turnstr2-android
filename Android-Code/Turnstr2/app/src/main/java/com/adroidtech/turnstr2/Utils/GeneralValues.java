package com.adroidtech.turnstr2.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.adroidtech.turnstr2.WebServices.AsyncCallback;

public class GeneralValues {
    //GCM Keys
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String LOGIN_URL = "/v1/sessions";
    public static final String SIGNUP_URL = "/v1/signup";
    public static final java.lang.String FACEBOOK_LOGIN = "/v1/facebook/login";

    public static String Push_Token = "";
    public static final String DEVICE_TYPE = "ANDROID";
    //Stagging
    public static final String BASE_URL = "https://fathomless-retreat-45620.herokuapp.com";

    public static final String MEMBERS_URL = "/v1/members ";

    public static int getScreenWidth(Context context) {
        try {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            return metrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

