package com.dmh.mvp.module.view.home.fragment;

import android.support.annotation.NonNull;

import com.dmh.mvp.R;
import com.dmh.mvp.base.BaseContract;
import com.dmh.mvp.base.BaseFragment;
import com.dmh.mvp.di.component.DaggerMainComponent;
import com.dmh.mvp.module.contract.ShoppingGuideContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 11:39
 */
public class ShoppingGuideFragment extends BaseFragment implements ShoppingGuideContract.View {
    @Inject
    ShoppingGuideContract.Presenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shopping_guide;
    }

    @NonNull
    @Override
    protected BaseContract.Presenter injectPresenter() {
        DaggerMainComponent.builder().build().inject(this);
        return presenter;
    }
}
