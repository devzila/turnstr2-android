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
    public static final String FACEBOOK_LOGIN = "/v1/facebook/login";
    public static final String CREATE_NEW_DEVICE = "/v1/user/devices";
    public static final String STORIES_LIKES = "/v1/stories/_STORY_ID_/likes";
    public static final String STORIES_UNLIKES = "/v1/stories/_STORY_ID_/unlikes";
    public static final String GET_STORIES_DETAIL = "/v1/stories/_STORY_ID_";
    public static final String GET_STORIE_COMMENTS = "/v1/stories/_STORY_ID_/comments";
    public static final String POST_STORIE_COMMENTS = "/v1/stories/_STORY_ID_/comments";
    public static final String GET_ALL_STORIES = "/v1/stories";
    public static final String GET_POPULARS_STORIES = "/v1/populars";
    public static final String GET_ALL_MEMBERS_STORIES = "/v1/members/_MEMBER_ID_/stories";
    public static final String GET_FAVOURITES_FIVE = "/v1/members/_MEMBER_ID_/favourites";
    public static final String EDIT_PROFILE = "/v1/user/profile";
    public static final String CREATE_STORIES = "/v1/user/stories";
    public static final String SEARCH_STORIES = "/v1/search";

    public static final String GET_VIDEOS = "/v1/videos";
    public static final String GET_VIDEO_STORIES_DETAIL = "/v1/videos/_STORY_ID_";
    public static final String GET_VIDEOS_MEMBERS = "/v1/members/_MEMBER_ID_/videos";
    public static String Push_Token = "";
    public static final String DEVICE_TYPE = "ANDROID";
    //Stagging
//    public static final String BASE_URL = "https://fathomless-retreat-45620.herokuapp.com";
    //live
    public static final String BASE_URL = "http://18.218.6.149";

    public static final String MEMBERS_URL = "/v1/members ";
    public static final String GET_MEMBERS_DATA = "/v1/members/_MEMBER_ID_";
    public static final String USER_STORIES = "/v1/user/stories";
    public static final String GET_USER_FAVE5 = "/v1/members/_MEMBER_ID_/favourites";

    public static final String LIVE_SESSION = "/v1/user/live_session ";
<<<<<<< HEAD
    public static final String GOLIVE_SESSION = "/v1/user/golive_session ";
    public static final String LIVE_NOTIFY = "/v1/user/live_notify";
=======
    public static final String MEMBERS_FOLLOW = "/v1/members/_MEMBER_ID_/follow";
    public static final String MEMBERS_UNFOLLOW = "/v1/members/_MEMBER_ID_/unfollow";
    public static final String MEMBERS_FAVOURITES = "/v1/members/_MEMBER_ID_/favourites";
>>>>>>> 333bcd1339db26ced6d63924ed8ead52146c3f63

//    public static final String MEMBERS_URL = "/v1/members ";


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

