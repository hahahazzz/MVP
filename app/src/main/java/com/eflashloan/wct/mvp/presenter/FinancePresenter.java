package com.eflashloan.wct.mvp.presenter;

import com.eflashloan.wct.base.BasePresenter;
import com.eflashloan.wct.mvp.contract.FinanceContract;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:24
 */
public class FinancePresenter extends BasePresenter<FinanceContract.View> implements FinanceContract.Presenter {
    public FinancePresenter(FinanceContract.View view) {
        super(view);
    }
}
