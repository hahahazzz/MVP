package com.eflashloan.wct.mvp.presenter;

import android.text.TextUtils;

import com.eflashloan.wct.Contants;
import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.DebugContract;
import com.eflashloan.wct.util.DebugUtils;
import com.eflashloan.wct.util.LogUtils;
import com.eflashloan.wct.util.SharedPrefUtils;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/7 9:32
 */
public class DebugPresenter extends BasePresenter<DebugContract.View> implements DebugContract.Presenter {
    private DebugContract.Model model;

    public DebugPresenter(DebugContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        if (DebugUtils.releaseMode()) {
            view.close();
            return;
        }
    }

    @Override
    public void handleSaveButtonClickEvent() {
        String url = view.getInputUrl();
        if (TextUtils.isEmpty(url)) {
            SharedPrefUtils.remove(Contants.LOCAL_SAVED_API_HOST);
            view.showSaveSuccess();
            return;
        }
        String prefix = "https://";
        if (view.httpChecked()) {
            prefix = "http://";
        }
        if (url.startsWith("http")) {
            url = url.substring(url.indexOf("/") + 2);
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        String finalUrl = prefix + url;
        SharedPrefUtils.save(Contants.LOCAL_SAVED_API_HOST, finalUrl, false);
        LogUtils.d("save url to local=" + finalUrl);
        view.showSaveSuccess();
    }
}
