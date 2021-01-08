package com.wdp.Agora.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wdp.Agora.live.Constants;


public class PrefManager {
    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }
}
