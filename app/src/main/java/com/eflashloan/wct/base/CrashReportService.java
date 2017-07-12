package com.eflashloan.wct.base;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.io.Serializable;

public class CrashReportService extends Service {
    public final static String EXTRA_UNCAUGHT_EXCEPTION = "extraUncaughtException";
    private Handler handler;
    private long lastReportTimestamp = 0;   // 上一次上报时间的时间戳
    private final static int REPORT_INTERVAL = 3 * 1000;// 两次上报error的间隔,3s
    private final static int SERVICE_ALIVE_DURATION = 10 * 1000;//服务空闲存活时长,10s

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lastReportTimestamp = getCurrentTimestamp();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (shouldStopService()) {
                    stopSelf();
                } else {
                    sendEmptyMessageDelayed(0, 1000);
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra(EXTRA_UNCAUGHT_EXCEPTION)) {
            Serializable serializableExtra = intent.getSerializableExtra(EXTRA_UNCAUGHT_EXCEPTION);
            if (serializableExtra != null) {
                if (serializableExtra instanceof Throwable) {
                    if (needReport()) {
                        lastReportTimestamp = getCurrentTimestamp();
                        Throwable throwable = (Throwable) serializableExtra;
                        // TODO: 2017/7/6
                        //CrashReport.postCatchedException(throwable);
                    }
                }
            }
        }
        handler.sendEmptyMessage(0);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    private long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 当前时间与上次上报时间间隔大于{@link CrashReportService#REPORT_INTERVAL},则上报,否则忽略
     * @return
     */
    private boolean needReport() {
        return System.currentTimeMillis() - lastReportTimestamp > REPORT_INTERVAL;
    }

    /**
     * 当前时间与上次上报时间间隔大于{@link CrashReportService#SERVICE_ALIVE_DURATION},则停止掉服务
     * @return
     */
    private boolean shouldStopService() {
        return System.currentTimeMillis() - lastReportTimestamp >= SERVICE_ALIVE_DURATION;
    }
}
