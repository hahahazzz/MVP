package com.dmh.mvp.module.view.home.fragment;

import android.support.annotation.NonNull;

import com.dmh.mvp.R;
import com.dmh.mvp.base.BaseContract;
import com.dmh.mvp.base.BaseFragment;
import com.dmh.mvp.di.component.MainComponent;
import com.dmh.mvp.module.contract.BuyCarContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:25
 */
public class BuyCarFragment extends BaseFragment implements BuyCarContract.View {
    @Inject
    BuyCarContract.Presenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_buy_car;
    }

    @NonNull
    @Override
    protected BaseContract.Presenter injectPresenter(MainComponent component) {
        component.inject(this);
        return presenter;
    }
}
