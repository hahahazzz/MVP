package com.dmh.mvp.module.presenter;

import com.dmh.mvp.base.BasePresenter;
import com.dmh.mvp.module.contract.HomeContract;
import com.dmh.mvp.util.LogUtils;
import com.dmh.mvp.util.push.PushManager;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/10 13:08
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    @Inject
    public HomePresenter() {}

    @Override
    public void start() {
        PushManager.getManager().setAlias("wct_dev");
        LogUtils.d(PushManager.getManager().getRegistrationId());
    }
}
