package com.dmh.mvp.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dmh.mvp.base.App;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 17:21
 */
public final class SharedPrefUtils {
    private final static SharedPreferences SHARED_PREFERENCES = PreferenceManager.getDefaultSharedPreferences(App
            .getApp());

    public static String get(String key, String defValue) {
        return SHARED_PREFERENCES.getString(key, defValue);
    }

    public static boolean get(String key, boolean defValue) {
        return SHARED_PREFERENCES.getBoolean(key, defValue);
    }

    public static int get(String key, int defValue) {
        return SHARED_PREFERENCES.getInt(key, defValue);
    }

    public static void save(String key, String value, boolean apply) {
        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit().putString(key, value);
        save(editor, apply);
    }

    public static void save(String key, boolean value, boolean apply) {
        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit().putBoolean(key, value);
        save(editor, apply);
    }

    public static void save(String key, int value, boolean apply) {
        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit().putInt(key, value);
        save(editor, apply);
    }

    public static void remove(String key) {
        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit().remove(key);
        save(editor, false);
    }

    private static void save(SharedPreferences.Editor editor, boolean apply) {
        if (apply) {
            editor.apply();
        } else {
            editor.commit();
        }
    }
}
