package com.eflashloan.wct.module.home.fragment;

import android.support.annotation.NonNull;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseFragment;
import com.eflashloan.wct.mvp.contract.FinanceContract;
import com.eflashloan.wct.mvp.presenter.FinancePresenter;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:26
 */
public class FinanceFragment extends BaseFragment<FinanceContract.Presenter> implements FinanceContract.View {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_finance;
    }

    @NonNull
    @Override
    protected FinanceContract.Presenter createPresenter() {
        return new FinancePresenter(this);
    }
}
