package com.eflashloan.wct.util;

import android.os.Debug;

import com.eflashloan.wct.BuildConfig;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:49
 */
public final class DebugUtils {
    public static boolean debugConnection() {
        if (debugMode()) {
            return false;
        }
        return Debug.isDebuggerConnected();
    }

    public static boolean debugMode() {
        return BuildConfig.DEBUG;
    }

    public static boolean releaseMode() {
        return !debugMode();
    }
}
