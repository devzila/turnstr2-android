package com.adroidtech.turnstr2.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;

import java.lang.reflect.Type;
import java.util.Set;

public class SharedPreference {
    public static final String PREFS_NAME = "Bariatricpal_Prefs";
    private final Context mContext;
    SharedPreferences settings;
    Editor editor;

    /**
     * @param context
     */
    public SharedPreference(Context context) {
        this.mContext = context;
        settings = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    /**
     * @param text
     * @param prefs_key
     */
    public void putString(String prefs_key, String text) {
        editor.putString(prefs_key, text);
        editor.commit();
    }

    /**
     * @param object
     * @param prefs_key
     */
    public void putSerializableObject(String prefs_key, Object object) {
        Gson gson = new Gson();
        editor.putString(prefs_key, gson.toJson(object).toString());
        editor.commit();
    }

    /**
     * @param text
     * @param prefs_key
     */
    public void putBoolean(String prefs_key, Boolean text) {
        editor.putBoolean(prefs_key, text);
        editor.commit();
    }

    /**
     * @param text
     * @param prefs_key
     */
    public void putFloat(String prefs_key, Float text) {
        editor.putFloat(prefs_key, text);
        editor.commit();
    }

    /**
     * @param text
     * @param prefs_key
     */
    public void putInt(String prefs_key, int text) {
        editor.putInt(prefs_key, text);
        editor.commit();
    }

    /**
     * @param text
     * @param prefs_key
     */
    public void putLong(String prefs_key, Long text) {
        editor.putLong(prefs_key, text);
        editor.commit();
    }

    /**
     * @param text
     * @param prefs_key
     */
    public void putStringSet(String prefs_key, Set<String> text) {
        editor.putStringSet(prefs_key, text);
        editor.commit();
    }

    /**
     * @param prefs_key
     * @return
     */
    public String getString(String prefs_key) {
        return settings.getString(prefs_key, null);
    }

    /**
     * @param prefs_key
     * @return
     */
    public boolean getBoolean(String prefs_key) {
        return settings.getBoolean(prefs_key, false);
    }

    /**
     * @param prefs_key
     * @return
     */
    public float getFloat(String prefs_key) {
        return settings.getFloat(prefs_key, 0);
    }

    /**
     * @param prefs_key
     * @return
     */
    public int getInt(String prefs_key) {
        return settings.getInt(prefs_key, 0);
    }

    /**
     * @param prefs_key
     * @return
     */
    public long getLong(String prefs_key) {
        return settings.getLong(prefs_key, 0);
    }

    /**
     * @param prefs_key
     * @return
     */
    public Set<String> getStringSet(String prefs_key) {
        return settings.getStringSet(prefs_key, null);
    }

    /**
     * @param classOfT
     * @param prefs_key
     */
    public <T> T getSerializableObject(String prefs_key, Class<T> classOfT) {
        Gson gson = new Gson();
        String value = settings.getString(prefs_key, null);
        Object object = gson.fromJson(value, (Type) classOfT);
        return Primitives.wrap(classOfT).cast(object);
    }

    /**
     * @param prefs_key
     */
    public void removePreferenceValue(String prefs_key) {
        editor.remove(prefs_key);
        editor.commit();
    }

    /**
     * @param context
     */
    public void clearSharedPreference(Context context) {
        editor.clear();
        editor.commit();
    }
}