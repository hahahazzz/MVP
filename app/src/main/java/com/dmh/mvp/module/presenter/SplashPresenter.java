package com.dmh.mvp.module.presenter;

import com.dmh.mvp.base.BasePresenter;
import com.dmh.mvp.module.contract.SplashContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:10
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    private final static long SPLASH_DURATION = 3000L;

    @Inject
    public SplashPresenter() {}

    @Override
    public void start() {
    }
}
