package com.eflashloan.wct.module.view.home.fragment;

import android.support.annotation.NonNull;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseFragment;
import com.eflashloan.wct.module.contract.BuyCarContract;
import com.eflashloan.wct.module.presenter.BuyCarPresenter;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:25
 */
public class BuyCarFragment extends BaseFragment<BuyCarContract.Presenter> implements BuyCarContract.View {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_buy_car;
    }

    @NonNull
    @Override
    protected BuyCarContract.Presenter createPresenter() {
        return new BuyCarPresenter(this);
    }
}
