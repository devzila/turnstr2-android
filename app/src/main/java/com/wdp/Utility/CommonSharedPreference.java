package com.wdp.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wdp.Modal.LoginResDataModal;


import java.lang.reflect.Type;


public class CommonSharedPreference {
    private static String PREF_KEY = "preference";
    private static String storebalance;
    private SharedPreferences.Editor editor;
    private static String PREF_FCMTOKEN_KEY = "FCMToken_PREF";
    private static String PREF_KEY_countryToken = "CountryAccessToken_PREF";
    private static String PREF_KEY_userfName = "FirstName_PREF";
    private static String PREF_KEY_userlName = "LastName_PREF";
    private static String PREF_KEY_userProfession = "Profession_PREF";
    private static String PREF_KEY_userProfileImage = "ProfileImage_PREF";
    private static String PREF_KEY_loginType= "PREF_KEY_loginType";
    private static String PREF_Notification_Sound_KEY = "NotificationSound_PREF";
    private static String PREF_Notification_Enable_KEY = "NotificationEnable_PREF";
    private static String PREF_Title_KEY = "Title_PREF";
    private static String PREF_Live_KEY = "Live_PREF";
    private final String PREF_isLogin_Key = "isLogin_PREF";


    public void setSubscriberLoginSharedPref(Context context, LoginResDataModal beans) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(beans);
        editor.putString("Login_PREF", json);
        editor.apply();
    }

    public LoginResDataModal getSubscriberLoginSharedPref(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sharedPref.getString("Login_PREF", null);
        Gson gson = new Gson();
        Type type = new TypeToken<LoginResDataModal>() {
        }.getType();

        LoginResDataModal beans = null;
        try {
            beans = gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
        return beans;
    }

    public boolean getisLoginSharedPref(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean(PREF_isLogin_Key, false);
    }

    public void setisLoginSharedPref(Context context, boolean isLogin) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(PREF_isLogin_Key, isLogin);
        editor.commit();
    }

    public void setLoginType(Context context,String login_type) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        editor.putString(PREF_KEY_loginType, login_type);
        editor.apply();
    }

    public String getLogintype(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(PREF_KEY_loginType, "");
    }


    public void setCountryAccessToken(Context context,String CountryAccessToken) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        editor.putString(PREF_KEY_countryToken, CountryAccessToken);
        editor.apply();
    }

    public String getCountryAccessToken(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(PREF_KEY_countryToken, "");
    }


    public void setUserNameCityCountrySharedPref(Context context, String fname, String lname,String profession ,String profileImage) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        editor.putString(PREF_KEY_userfName, fname);
        editor.putString(PREF_KEY_userlName, lname);
        editor.putString(PREF_KEY_userProfession, profession);
        editor.putString(PREF_KEY_userProfileImage, profileImage);
        editor.apply();
    }

    public String getUserFirstNameSharedPref(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(PREF_KEY_userfName, "");
    }

    public String getUserLastNameSharedPref(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(PREF_KEY_userlName, "");
    }

    public String getUserProfessionSharedPref(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(PREF_KEY_userProfession, "");
    }

    public String getUserImageSharedPref(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(PREF_KEY_userProfileImage, "");
    }

    public String getFCMToken(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String mToken=sharedPref.getString(PREF_FCMTOKEN_KEY,null);

        if (mToken==null){
            mToken= FirebaseInstanceId.getInstance().getToken();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(PREF_FCMTOKEN_KEY,mToken);
            editor.apply();
        }
        return mToken;
    }


    public void setFCMToken(Context context, String FCMToken) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        editor.putString(PREF_FCMTOKEN_KEY, FCMToken);
        editor.commit();
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    public void clearLanguage(Context context) {
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("Lang_PREF");
        editor.commit();
    }

    public void Logout_User(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
    }


    public String getTitleName(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String title=sharedPref.getString(PREF_Title_KEY,null);

        return title;
    }


    public void setTitleName(Context context, String title) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        editor.putString(PREF_Title_KEY, title);
        editor.commit();
    }

    public String getLiveType(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String live=sharedPref.getString(PREF_Live_KEY,null);
        return live;
    }


    public void setLiveType(Context context, String FCMToken) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        editor.putString(PREF_Live_KEY, FCMToken);
        editor.commit();
    }

}
