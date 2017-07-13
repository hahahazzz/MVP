package com.eflashloan.wct.module.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.module.contract.BuyCarContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:23
 */
public class BuyCarPresenter extends BasePresenter<BuyCarContract.View> implements BuyCarContract.Presenter {
    public BuyCarPresenter(BuyCarContract.View view) {
        super(view);
    }
}
