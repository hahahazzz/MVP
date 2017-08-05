package com.dmh.mvp.module.presenter;

import com.dmh.mvp.base.BasePresenter;
import com.dmh.mvp.module.contract.BuyCarContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:23
 */
public class BuyCarPresenter extends BasePresenter<BuyCarContract.View> implements BuyCarContract.Presenter {
    @Inject
    public BuyCarPresenter() {

    }
}
