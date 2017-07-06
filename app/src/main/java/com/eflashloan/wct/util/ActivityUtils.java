package com.eflashloan.wct.util;

import android.os.Process;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 9:17
 */
public final class ActivityUtils {
    private static volatile ActivityUtils activityUtils;
    private Stack<AppCompatActivity> activityStack;

    private ActivityUtils() {
        activityStack = new Stack<>();
    }

    public static ActivityUtils getActivityUtils() {
        if (activityUtils == null) {
            synchronized (ActivityUtils.class) {
                if (activityUtils == null) {
                    activityUtils = new ActivityUtils();
                }
            }
        }
        return activityUtils;
    }

    public boolean add(AppCompatActivity activity) {
        return activityStack.add(activity);
    }

    public boolean remove(AppCompatActivity activity) {
        return activityStack.remove(activity);
    }

    public AppCompatActivity removeAt(int index) {
        if (index < 0 || index > activityStack.size() - 1) {
            return null;
        }
        return activityStack.remove(index);
    }

    /**
     * finish记录的所有符合条件的Activity,并统计数量
     * @param cls 要关闭Activity的Class
     * @return 关闭的数量
     */
    public int finishActivity(Class<? extends AppCompatActivity> cls) {
        int finishCount = 0;
        if (cls != null && activityStack != null && activityStack.size() > 0) {
            final int stackSize = activityStack.size();
            ArrayList<AppCompatActivity> waitFinishActivityList = new ArrayList<>();
            for (int currentIndex = stackSize - 1; currentIndex >= 0; currentIndex--) {
                AppCompatActivity currentItem = activityStack.get(currentIndex);
                if (currentItem.getClass().equals(cls)) {
                    waitFinishActivityList.add(currentItem);
                }
            }
            finishCount = waitFinishActivityList.size();
            if (finishCount > 0) {
                for (int currentIndex = finishCount - 1; currentIndex >= 0; currentIndex--) {
                    AppCompatActivity activity = waitFinishActivityList.remove(currentIndex);
                    activityStack.remove(activity);
                    activity.finish();
                }
            }
        }
        return finishCount;
    }

    public int finishActivityUntilEquals(Class<? extends AppCompatActivity> cls) {
        int finishCount = 0;
        if (cls != null && activityStack != null) {
            final int stackSize = activityStack.size();
            for (int currentIndex = stackSize - 1; currentIndex >= 0; currentIndex--) {
                AppCompatActivity currentItem = activityStack.get(currentIndex);
                if (currentItem.getClass().equals(cls)) {
                    break;
                }
                boolean removed = activityStack.remove(currentItem);
                if (removed) {
                    finishCount++;
                }
            }
        }
        return finishCount;
    }

    public void exit() {
        while (activityStack.size() > 0) {
            AppCompatActivity removeActivity = activityStack.remove(0);
            removeActivity.finish();
        }
        ThreadPool.shutdownNoew();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}