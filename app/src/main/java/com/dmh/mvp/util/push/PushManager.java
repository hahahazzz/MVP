package com.dmh.mvp.util.push;

import android.content.Context;
import android.os.Handler;
import android.support.v4.util.ArraySet;

import com.dmh.mvp.base.App;
import com.dmh.mvp.util.LogUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 17:10
 */
public final class PushManager {
    private static final String KEY_ALIAS_SET = "keyAliasSet";
    private static final String KEY_TAG_SET = "keyTagSet";

    private static final int CODE_SUCCESS = 0;
    private static final int CODE_NEED_RETRY = 6002;
    private static final int CODE_SERVER_BUSY = 6014;

    private static final long RETRY_DELAY = 1500;

    private int setTagRetryCount = 0;
    private int setAliasRetryCount = 0;

    private static final int CODE_SET_ALIAS = 1;
    private static final int CODE_DELETE_ALIAS = 2;
    private static final int CODE_SET_TAG = 3;
    private static final int CODE_DELETE_TAG = 4;

    private static volatile PushManager manager;

    private final Handler aliasAndTagRegHandler = new Handler();
    private final Context appContext;

    private PushManager() {
        appContext = App.getApp().getApplicationContext();
    }

    public static PushManager getManager() {
        if (manager == null) {
            synchronized (PushManager.class) {
                if (manager == null) {
                    manager = new PushManager();
                }
            }
        }
        return manager;
    }

    public String getRegistrationId() {
        return JPushInterface.getRegistrationID(appContext);
    }

    public void setAlias(String alias) {
        if (alias.isEmpty()) {
            return;
        }
        JPushInterface.setAlias(appContext, CODE_SET_ALIAS, alias);
    }

    public void removeAlias() {
        JPushInterface.deleteAlias(appContext, CODE_DELETE_ALIAS);
    }

    public void stopPush() {
        JPushInterface.stopPush(appContext);
    }

    public void resumePush() {
        JPushInterface.resumePush(appContext);
    }

    public void setTag(String... tags) {
        if (tags == null || tags.length <= 0) {
            return;
        }
        Set<String> tagSet = new ArraySet<>();
        tagSet.addAll(tagSet);
        setTag(tagSet);
    }

    public void setTag(Set<String> tag) {
        Set<String> filterValidTags = JPushInterface.filterValidTags(tag);
        JPushInterface.setTags(appContext, CODE_SET_TAG, filterValidTags);
    }

    public void cleanTag() {
        JPushInterface.cleanTags(appContext, CODE_DELETE_TAG);
    }

    public void handleTagOperatorResult(final Context context, final JPushMessage message) {
        int errorCode = message.getErrorCode();
        switch (errorCode) {
            case CODE_SUCCESS:
                LogUtils.d("Tag operation success,tag = %s ", message.getTags().toArray().toString());
                break;
            case CODE_NEED_RETRY:
            case CODE_SERVER_BUSY:
                if (setTagRetryCount > 3) {
                    return;
                }
                LogUtils.w("Tag operation need retry");
                aliasAndTagRegHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Set<String> tags = message.getTags();
                        setTag(tags);
                        setTagRetryCount++;
                    }
                }, RETRY_DELAY);
                break;
            default:
                LogUtils.e("Tag operation error code= %d ", errorCode);
                break;
        }
    }

    public void handleAliasOperatorResult(final Context context, final JPushMessage message) {
        int errorCode = message.getErrorCode();
        switch (errorCode) {
            case CODE_SUCCESS:
                LogUtils.d("Alias operation success,alias = %s ", message.getAlias());
                break;
            case CODE_NEED_RETRY:
            case CODE_SERVER_BUSY:
                if (setAliasRetryCount > 3) {
                    return;
                }
                LogUtils.w("Alias operation need retry");
                aliasAndTagRegHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String alias = message.getAlias();
                        setAlias(alias);
                        setAliasRetryCount++;
                    }
                }, RETRY_DELAY);
                break;
            default:
                LogUtils.e("Alias operation error code = %d ", errorCode);
                break;
        }
    }
}
