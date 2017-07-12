package com.eflashloan.wct.util;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 17:10
 */
public final class MessagePushUtils {
    private static final String KEY_SET_ALIAS_SUCCESS = "setAliasSuccess";
    private final Context context;
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler aliasHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    JPushInterface.setAliasAndTags(context, (String) msg.obj, null, mAliasCallback);
                    break;
                default:
                    break;
            }
        }
    };

    public MessagePushUtils(Context context) {
        this.context = context.getApplicationContext();
    }

    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias(String alias) {
        if (TextUtils.isEmpty(alias)) {
            JPushInterface.deleteAlias(context, 1);
            return;
        }
        // 调用 Handler 来异步设置别名
        aliasHandler.sendMessage(aliasHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                case 0:
                    SharedPrefUtils.save(KEY_SET_ALIAS_SUCCESS, true, false);
                    break;
                case 6002:
                    aliasHandler.sendMessageDelayed(aliasHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
            }
        }
    };
}
