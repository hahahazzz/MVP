package com.dmh.mvp.module.presenter;

import com.dmh.mvp.base.BasePresenter;
import com.dmh.mvp.module.contract.FinanceContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:24
 */
public class FinancePresenter extends BasePresenter<FinanceContract.View> implements FinanceContract.Presenter {
    @Inject
    public FinancePresenter() {
    }
}
