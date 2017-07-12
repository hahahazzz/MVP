package com.eflashloan.wct.mvp.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.ShoppingGuideContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:16
 */
public class ShoppingPresenter extends BasePresenter<ShoppingGuideContract.View> implements ShoppingGuideContract
        .Presenter {
    public ShoppingPresenter(ShoppingGuideContract.View view) {
        super(view);
    }
}
