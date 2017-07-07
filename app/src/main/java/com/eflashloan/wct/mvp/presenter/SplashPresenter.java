package com.eflashloan.wct.mvp.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.SplashContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 13:10
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    public SplashPresenter(SplashContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        view.showLoadDialog();
    }
}
