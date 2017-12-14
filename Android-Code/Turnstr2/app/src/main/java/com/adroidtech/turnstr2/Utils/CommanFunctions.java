package com.adroidtech.turnstr2.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sarbjot.singh on 1/17/2017.
 */

public class CommanFunctions {
    private static SharedPreference sharedPreference;
    public static String Push_Token = "";

    public static ArrayList<String> genrateHightArray() {
        ArrayList<String> heights = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 12; j++)
                heights.add(i + "Feet/" + j + "Inches");
        }
        return heights;
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }



    public static ProgressDialog showLoader(Context context) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        mProgressDialog.dismiss();
                    } catch (Exception e) {
                    }
                }
            }, 2 * 60 * 1000);
            return mProgressDialog;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void dismissLoader(ProgressDialog mProgressDialog) {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Activity mCurrentActivity = null;

    public static Activity getActivity() {
        return mCurrentActivity;
    }

    public static void setActivity(Activity CurrentActivity) {
        mCurrentActivity = CurrentActivity;
    }

}
