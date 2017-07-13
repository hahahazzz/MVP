package com.eflashloan.wct.module.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.module.contract.HomeContract;
import com.eflashloan.wct.util.LogUtils;
import com.eflashloan.wct.util.push.PushManager;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/10 13:08
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        PushManager.getManager().setAlias("wct_dev");
        LogUtils.d(PushManager.getManager().getRegistrationId());
    }
}
