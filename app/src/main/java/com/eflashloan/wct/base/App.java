package com.eflashloan.wct.base;

import android.app.Application;
import android.content.Intent;
import android.os.StrictMode;

import com.eflashloan.wct.BuildConfig;
import com.eflashloan.wct.R;
import com.eflashloan.wct.util.DebugUtils;
import com.eflashloan.wct.util.ResourcesUtils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import cn.jpush.android.api.JPushInterface;

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
        initBugly();
        initJPush();
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

    private void initJPush() {
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
    }

    private void initBugly() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setAppVersion(BuildConfig.VERSION_NAME)
                .setAppPackageName(BuildConfig.APPLICATION_ID);
        CrashReport.setIsDevelopmentDevice(this, BuildConfig.DEBUG);
        CrashReport.initCrashReport(this, ResourcesUtils.getString(this, R.string.bugly_key), true, strategy);
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
                intent.putExtra(CrashReportService.EXTRA_UNCAUGHT_EXCEPTION, e);
                app.startActivity(intent);
            }
        }
    }
}
