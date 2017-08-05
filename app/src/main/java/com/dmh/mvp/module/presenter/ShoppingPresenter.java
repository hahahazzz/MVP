package com.dmh.mvp.module.presenter;

import com.dmh.mvp.base.BasePresenter;
import com.dmh.mvp.module.contract.ShoppingGuideContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:16
 */
public class ShoppingPresenter extends BasePresenter<ShoppingGuideContract.View> implements ShoppingGuideContract
        .Presenter {
    @Inject
    public ShoppingPresenter() {}
}
