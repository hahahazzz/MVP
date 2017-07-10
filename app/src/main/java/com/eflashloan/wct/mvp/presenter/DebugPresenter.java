package com.eflashloan.wct.mvp.presenter;

import android.text.TextUtils;

import com.eflashloan.wct.Constants;
import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.DebugContract;
import com.eflashloan.wct.mvp.model.DebugModel;
import com.eflashloan.wct.util.DebugUtils;
import com.eflashloan.wct.util.LogUtils;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/7 9:32
 */
public class DebugPresenter extends BasePresenter<DebugContract.View> implements DebugContract.Presenter {
    private DebugContract.Model model;

    public DebugPresenter(DebugContract.View view) {
        super(view);
        model = new DebugModel();
    }

    @Override
    public void start() {
        if (DebugUtils.releaseMode()) {
            view.close();
            return;
        }
        String localSavedUrl = model.readLocalSavedDebugUrl(Constants.LOCAL_SAVED_API_HOST);
        view.showLocalSaveUrl(localSavedUrl);
    }

    @Override
    public void handleSaveButtonClickEvent() {
        String url = view.getInputUrl();
        if (TextUtils.isEmpty(url)) {
            model.clearDebugUrl(Constants.LOCAL_SAVED_API_HOST);
            LogUtils.d("clear local saved api host");
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
        model.saveDebugUrl(Constants.LOCAL_SAVED_API_HOST, finalUrl);
        LogUtils.d("save %s to local", finalUrl);
        view.showSaveSuccess();
    }
}
