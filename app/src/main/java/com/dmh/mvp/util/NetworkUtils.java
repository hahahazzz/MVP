package com.dmh.mvp.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.dmh.mvp.base.App;

/**
 * Created by QiuGang on 2017/9/24 22:32
 * Email : 1607868475@qq.com
 */
public final class NetworkUtils {
    private static ConnectivityManager cm = null;

    public static final boolean networkAvailable() {
        if (cm == null) {
            cm = (ConnectivityManager) App.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return cm != null && cm.getActiveNetworkInfo().isAvailable();
    }
}
