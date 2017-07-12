package com.eflashloan.wct.module.home.fragment;

import android.support.annotation.NonNull;

import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseFragment;
import com.eflashloan.wct.mvp.contract.CommunityContract;
import com.eflashloan.wct.mvp.presenter.CommunityPresenter;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/12 12:27
 */
public class CommunityFragment extends BaseFragment<CommunityContract.Presenter> implements CommunityContract.View {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_community;
    }

    @NonNull
    @Override
    protected CommunityContract.Presenter createPresenter() {
        return new CommunityPresenter(this);
    }
}
