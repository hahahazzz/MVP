package com.dmh.mvp.module.view.home.fragment;

import android.support.annotation.NonNull;

import com.dmh.mvp.R;
import com.dmh.mvp.base.BaseContract;
import com.dmh.mvp.base.BaseFragment;
import com.dmh.mvp.di.component.DaggerMainComponent;
import com.dmh.mvp.module.contract.CommunityContract;

import javax.inject.Inject;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:27
 */
public class CommunityFragment extends BaseFragment implements CommunityContract.View {
    @Inject
    CommunityContract.Presenter presenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_community;
    }

    @NonNull
    @Override
    protected BaseContract.Presenter injectPresenter() {
        DaggerMainComponent.builder().build().inject(this);
        return presenter;
    }
}
