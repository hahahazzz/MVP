package com.dmh.mvp.util;

import com.dmh.mvp.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:22
 */
public final class LogUtils {
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private LogUtils() {
        throw new RuntimeException();
    }

    static {
        if (DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }

    public static void d(String msg, Object... args) {
        if (DEBUG) {
            Logger.d(msg, args);
        }
    }

    public static void d(Number number) {
        if (DEBUG) {
            Logger.d(String.valueOf(number));
        }
    }

    public static void w(String msg, Object... args) {
        if (DEBUG) {
            Logger.w(msg, args);
        }
    }

    public static void e(String msg, Object... args) {
        if (DEBUG) {
            Logger.d(msg, args);
        }
    }

    public static void out(String msg, Object... args) {
        if (DEBUG) {
            System.out.println(String.format(msg, args));
        }
    }

    public static void json(String json) {
        if (DEBUG) {
            Logger.json(json);
        }
    }
}
