package com.eflashloan.wct.base;

import android.app.Application;
import android.content.Intent;
import android.os.StrictMode;

import com.eflashloan.wct.util.DebugUtils;

import static com.eflashloan.wct.base.CrashReportService.EXTRA_UNCAUGHT_EXCEPTION;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:43
 */
public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Thread.setDefaultUncaughtExceptionHandler(GlobalUncaughtExceptionHandler.getHandler());
        StrictMode.enableDefaults();
    }

    public static App getApp() {
        return app;
    }

    static class GlobalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        private static volatile GlobalUncaughtExceptionHandler handler;

        private GlobalUncaughtExceptionHandler() {}

        public static GlobalUncaughtExceptionHandler getHandler() {
            if (handler == null) {
                synchronized (GlobalUncaughtExceptionHandler.class) {
                    if (handler == null) {
                        handler = new GlobalUncaughtExceptionHandler();
                    }
                }
            }
            return handler;
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            if (DebugUtils.debugMode()) {
                e.printStackTrace();
            } else {
                App app = getApp();
                Intent intent = new Intent(app, CrashReportService.class);
                intent.putExtra(EXTRA_UNCAUGHT_EXCEPTION, e);
                app.startActivity(intent);
            }
        }
    }
}
