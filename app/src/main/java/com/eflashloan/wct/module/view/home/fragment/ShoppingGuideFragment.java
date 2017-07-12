package com.eflashloan.wct.module.view.home.fragment;

import android.support.annotation.NonNull;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseFragment;
import com.eflashloan.wct.module.contract.ShoppingGuideContract;
import com.eflashloan.wct.module.presenter.ShoppingPresenter;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 11:39
 */
public class ShoppingGuideFragment extends BaseFragment<ShoppingGuideContract.Presenter> implements
        ShoppingGuideContract.View {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shopping_guide;
    }

    @NonNull
    @Override
    protected ShoppingGuideContract.Presenter createPresenter() {
        return new ShoppingPresenter(this);
    }
}
